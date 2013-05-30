package com.zcwl.xmpp.bo;

import java.util.Date;
import java.util.List;

import com.xpush.android.xptp.dto.Message;

public class MessageTask {
	private String appKey;
	private Message message;
	private List<Message> messages;
	private Date sendTime;

	private boolean isSingle;

	public MessageTask(String appKey, Message message, Date sendTime) {
		isSingle = true;
		this.appKey = appKey;
		this.message = message;
		this.sendTime = sendTime;
	}

	public MessageTask(String appKey, List<Message> messages, Date sendTime) {
		this.appKey = appKey;
		this.messages = messages;
		this.sendTime = sendTime;
	}

	public String getAppKey() {
		return appKey;
	}


	public Message getMessage() {
		return message;
	}


	public List<Message> getMessages() {
		return messages;
	}


	public Date getSendTime() {
		return sendTime;
	}


	public boolean isSingle() {
		return isSingle;
	}

}
