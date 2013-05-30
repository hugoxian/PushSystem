package com.xpush.android.xptp.dto;

/**
 * 
 * @author hugo
 * 
 */
public class Register extends Packet {
	private String appKey;
	private String deviceId;

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

}
