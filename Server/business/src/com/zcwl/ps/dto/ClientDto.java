package com.zcwl.ps.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author hugo
 * 
 */
public class ClientDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7283957861510593077L;

	/**
	 * 数据库的唯一标示
	 */
	private int id;
	
	private String appKey;
	private String deviceId;
	private String userAgent;
	private String lastLatLon;
	private Date lastAccessTime;
	private Date createDate;

	/**
	 * 0:正常，1：黑名单
	 */
	private int status;
	
	private boolean isOnline;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getLastLatLon() {
		return lastLatLon;
	}

	public void setLastLatLon(String lastLatLon) {
		this.lastLatLon = lastLatLon;
	}

	public Date getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(Date lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

}
