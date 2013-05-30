package com.xpush.android.xptp.dto;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.xpush.android.api.XPushServerManager;
import com.xpush.android.xptp.Constants;

/**
 * 
 * @author hugo
 * 
 */
public class Message extends Packet {
	private String msgType;
	private String msgTitle;
	private String msgContent;
	private String deviceId;

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public String toString() {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("xPush");
		root.addAttribute("version", XPushServerManager.VERSION);

		Element idEle = root.addElement("id");
		idEle.addText(this.getId());

		Element typeEle = root.addElement("type");
		typeEle.addText(Constants.TYPE_MESSAGE);

		Element deviceIdEle = root.addElement("deviceId");
		deviceIdEle.addText(this.getDeviceId());

		Element messageEle = root.addElement("message");

		Element msgTypeEle = messageEle.addElement("msgType");
		msgTypeEle.addText(this.getMsgType());

		Element msgTitleEle = messageEle.addElement("msgTitle");
		msgTitleEle.addText(this.getMsgTitle());

		Element msgContentEle = messageEle.addElement("msgContent");
		msgContentEle.addText(this.getMsgContent());

		return document.asXML().replaceFirst("\n", "");
	}

}
