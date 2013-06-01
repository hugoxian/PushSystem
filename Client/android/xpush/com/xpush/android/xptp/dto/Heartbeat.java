package com.xpush.android.xptp.dto;

/**
 * 心跳发送信息实体
 * 
 * @author hugo
 * 
 */
public class Heartbeat extends Packet {
	
	/**
	 * 
	 */
	private String appkey;
	private String deviceId;

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

}
