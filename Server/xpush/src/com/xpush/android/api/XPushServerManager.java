package com.xpush.android.api;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.UUID;

import org.apache.mina.core.session.IoSession;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xpush.android.xptp.Constants;
import com.xpush.android.xptp.XPTPFilter;
import com.xpush.android.xptp.XPTPManager;
import com.xpush.android.xptp.dto.Heartbeat;
import com.xpush.android.xptp.dto.Login;
import com.xpush.android.xptp.dto.Message;
import com.xpush.android.xptp.dto.Packet;
import com.xpush.android.xptp.dto.Register;
import com.xpush.android.xptp.dto.Result;
import com.zcwl.xmpp.server.NewIoSessionManager;

/**
 * 
 * @author hugo
 * 
 */
public class XPushServerManager {

	private static final Logger LOG = LoggerFactory
			.getLogger(XPushServerManager.class);

	public final static String CHARSET_NAME = "UTF-8";
	public final static String VERSION = "1.0";

	/**
	 * 
	 * @param xml
	 * @throws Exception
	 */
	public static void handle(IoSession session, String xml) throws Exception {
		Document doc = null;
		try {
			doc = str2Document(xml);
		} catch (Exception e) {
			LOG.error("That xml format is illegal!", e);
			return;
		}

		Packet requestPacket = initRequestPacket(doc);
		if (requestPacket == null) {
			LOG.warn("That was an null packet!");
			return;
		}

		if (!(requestPacket instanceof Login)&&!(requestPacket instanceof Register)) {
			if (session.getAttribute(Constants.APP_KEY) == null
					|| session.getAttribute(Constants.PHONE_DEVICEID) == null) {
				LOG.warn("That was an illegal packet!");
				return;
			}
		}

		if (requestPacket instanceof Login) {
			Login temp = (Login) requestPacket;
			session.setAttribute(Constants.APP_KEY, temp.getAppkey());
			session.setAttribute(Constants.PHONE_DEVICEID, temp.getDeviceId());
			NewIoSessionManager.getInstance().addIoSession(session);
		}

		if (requestPacket.getParser() == null) {
			LOG.debug("That was an response packet,id[{}]",
					new String[] { requestPacket.getId() });
		}

		Packet responsePacket = getResponsePacket(requestPacket);
		List<XPTPFilter> filters = XPTPManager.getInstance().getFilters();
		for (XPTPFilter filter : filters) {
			if (filter.filter(requestPacket, responsePacket)) {
				break;
			}
		}

		if (responsePacket != null) {
			session.write(getResponseXml(responsePacket));
		}
	}

	/**
	 * 
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public static Document str2Document(String xml) throws Exception {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new ByteArrayInputStream(xml
				.getBytes(CHARSET_NAME)));
		return doc;
	}

	/**
	 * 将xml转为packet
	 * 
	 * @param doc
	 * @return
	 */
	public static Packet initRequestPacket(Document doc) {
		Packet packet = null;

		Element rootElement = doc.getRootElement();
		String type = rootElement.elementText("type");
		Element messageEle = rootElement.element("message");

		if (Constants.TYPE_REGISTER.equals(type)) {
			Register temp = new Register();
			temp.setAppKey(messageEle.elementText("appkey"));
			temp.setDeviceId(messageEle.elementText("deviceId"));
			temp.setParser(XPTPManager.getInstance().getParsers()
					.get(Constants.TYPE_REGISTER));
			packet = temp;
		}
		
		if (Constants.TYPE_LOGIN.equals(type)) {
			Login temp = new Login();
			temp.setAppkey(messageEle.elementText("appkey"));
			temp.setDeviceId(messageEle.elementText("deviceId"));
			temp.setLonlat(messageEle.elementText("lonlat"));
			temp.setUserAgent(messageEle.elementText("userAgent"));
			temp.setParser(XPTPManager.getInstance().getParsers()
					.get(Constants.TYPE_LOGIN));
			packet = temp;
		}

		if (Constants.TYPE_HEARTBEAT.equals(type)) {
			Heartbeat temp = new Heartbeat();
			temp.setAppkey(messageEle.elementText("appkey"));
			temp.setDeviceId(messageEle.elementText("deviceId"));
			temp.setParser(XPTPManager.getInstance().getParsers()
					.get(Constants.TYPE_HEARTBEAT));
			packet = temp;
		}

		if (Constants.TYPE_MESSAGE.equals(type)) {
			Message temp = new Message();
			temp.setMsgType(messageEle.elementText("msgType"));
			temp.setMsgTitle(messageEle.elementText("msgTitle"));
			temp.setMsgContent(messageEle.elementText("msgContent"));
			temp.setParser(XPTPManager.getInstance().getParsers()
					.get(Constants.TYPE_MESSAGE));
			packet = temp;
		}

		if (Constants.TYPE_RESULT.equals(type)) {
			Result temp = new Result();
			temp.setMsgCode(messageEle.elementText("msgCode"));
			temp.setMsgDesc(messageEle.elementText("msgDesc"));
			temp.setParser(XPTPManager.getInstance().getParsers()
					.get(Constants.TYPE_RESULT));
			packet = temp;
		}

		if (packet != null) {
			packet.setVersion(rootElement.attributeValue("version"));
			packet.setId(rootElement.elementText("id"));
			packet.setType(type);
		}

		return packet;
	}

	public static Packet getResponsePacket(Packet requestPacket) {

		if (requestPacket instanceof Result) {
			return null;
		}

		Packet packet = new Result();
		packet.setId(requestPacket.getId());
		packet.setType(Constants.TYPE_RESULT);
		return packet;
	}

	public static String getResponseXml(Packet responsePacket) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("xPush");
		root.addAttribute("version", responsePacket.getVersion());

		Element idEle = root.addElement("id");
		idEle.addText(responsePacket.getId());

		Element typeEle = root.addElement("type");
		typeEle.addText(responsePacket.getType());

		Element messageEle = root.addElement("message");

		if (responsePacket instanceof Result) {
			Result temp = (Result) responsePacket;
			Element msgCodeEle = messageEle.addElement("msgCode");
			msgCodeEle.addText(temp.getMsgCode());

			Element msgDescEle = messageEle.addElement("msgDesc");
			msgDescEle.addText(temp.getMsgDesc());
		}

		return document.asXML().replaceFirst("\n", "");
	}

	/**
	 * 生成UUID
	 * 
	 * @return
	 */
	public static String generateUuid() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "");
		return uuid;
	}

	/**
	 * 
	 * @param message
	 * @return
	 */
	public static String getMessageXml(Message message) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("xPush");
		root.addAttribute("version", VERSION);

		Element idEle = root.addElement("id");
		idEle.addText(message.getId());

		Element typeEle = root.addElement("type");
		typeEle.addText(Constants.TYPE_MESSAGE);

		Element messageEle = root.addElement("message");

		Element msgTypeEle = messageEle.addElement("msgType");
		msgTypeEle.addText(message.getMsgType());

		Element msgTitleEle = messageEle.addElement("msgTitle");
		msgTitleEle.addText(message.getMsgTitle());

		Element msgContentEle = messageEle.addElement("msgContent");
		msgContentEle.addText(message.getMsgContent());

		return document.asXML().replaceFirst("\n", "");
	}

}
