package com.xpush.android.xptp.dto;

/**
 * 
 * @author hugo
 * 
 */
public class Result extends Packet {

	private String msgCode;
	private String msgDesc;

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public String getMsgDesc() {
		return msgDesc;
	}

	public void setMsgDesc(String msgDesc) {
		this.msgDesc = msgDesc;
	}

}
