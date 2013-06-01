package com.xpush.android.api;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.xpush.android.net.XPushConnection;
import com.xpush.android.net.XPushConnection.IncomeMessageListener;
import com.xpush.android.xptp.Constants;
import com.xpush.android.xptp.dto.Message;
import com.xpush.android.xptp.dto.Packet;
import com.xpush.android.xptp.dto.Result;

/**
 * 消息接受管理者
 * 
 * @author hugo
 * 
 */
public class XPushIncomeManager implements IncomeMessageListener {

	public final static String CHARSET_NAME = "UTF-8";

	public XPushConnection connection;

	@Override
	public void handleMessage(String msg, PrintWriter out) {
		try {
			Packet packet = xml2Packet(msg);
			if (Constants.TYPE_RESULT.equals(packet.getType())) {
				Result result = (Result) packet;
				// 根据注册msgId判断是否注册packet
				if (packet.getId().equals(XPushClientManager.registerMsgId)) {
					XPushClientManager.setDeviceId(result.getMsgDesc());
					connection.login();
				}
			}

			if (Constants.TYPE_MESSAGE.equals(packet.getType())) {
				Message temp = (Message) packet;
				XPushClientManager.handleMessage(temp);

				Result responsePacket = new Result();
				responsePacket.setId(temp.getId());
				responsePacket.setType(Constants.TYPE_RESULT);
				responsePacket.setMsgCode("0");
				responsePacket.setMsgDesc("Message Success!");
				if (out != null) {
					out.println(getResponseXml(responsePacket));
					out.flush();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setConnection(XPushConnection connection) {
		this.connection = connection;
	}

	/**
	 * 待优化，改为XmlSerializer
	 * 
	 * @param responsePacket
	 * @return
	 */
	public String getResponseXml(Result responsePacket) {
		String result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xPush version=\"1.0\"><id>"
				+ responsePacket.getId()
				+ "</id><type>RESULT</type><message><msgCode>"
				+ responsePacket.getMsgCode()
				+ "</msgCode><msgDesc>"
				+ responsePacket.getMsgDesc() + "</msgDesc></message></xPush>";
		return result;
	}

	/**
	 * 将xml转为消息
	 * 
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	private Packet xml2Packet(String xml) throws Exception {
		InputStream is = new ByteArrayInputStream(xml.getBytes(CHARSET_NAME));
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, CHARSET_NAME);
		Packet packet = null;
		int event = parser.getEventType();
		String id = null;
		String type = null;
		while (event != XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_DOCUMENT: {
				break;
			}
			case XmlPullParser.START_TAG: {
				if ("id".equals(parser.getName())) {
					id = parser.nextText();
				} else if ("type".equals(parser.getName())) {
					type = parser.nextText();
					if (Constants.TYPE_MESSAGE.equals(type)) {
						packet = new Message();
					}
					if (Constants.TYPE_RESULT.equals(type)) {
						packet = new Result();
					}

					if (packet == null) {
						break;
					}
					packet.setId(id);
					packet.setType(type);
				} else if ("msgCode".equals(parser.getName())) {
					((Result) packet).setMsgCode(parser.nextText());
				} else if ("msgDesc".equals(parser.getName())) {
					((Result) packet).setMsgDesc(parser.nextText());
				} else if ("msgType".equals(parser.getName())) {
					((Message) packet).setMsgType(parser.nextText());
				} else if ("msgTitle".equals(parser.getName())) {
					((Message) packet).setMsgTitle(parser.nextText());
				} else if ("msgContent".equals(parser.getName())) {
					((Message) packet).setMsgContent(parser.nextText());
				}
				break;
			}
			case XmlPullParser.END_TAG: {
				break;
			}
			}
			event = parser.next();
		}

		return packet;
	}

}
