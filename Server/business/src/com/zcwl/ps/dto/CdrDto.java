package com.zcwl.ps.dto;

import java.util.Date;

/**
 * 话单实体
 * 
 * @author Hugo
 * 
 */
public class CdrDto {
	private int id;
	private String version;
	private String userId;

	private String ua;
	/**
	 * 1、wap；2、3g；3、web；4、app；9、other
	 */
	private int clientType;
	/**
	 * APP客户端有这个属性
	 */
	private String clientVersion;

	/**
	 * 1、登入；2 、登出；3、在线人数
	 */
	private int actionType;
	/**
	 * 事件发生时间
	 */
	private Date eventTime;

	private String clientIp;
	/**
	 * 记录时间
	 */
	private Date createTime;

	/**
	 * 处理耗时，单位毫秒，系统做性能分析用
	 */
	private int processTime;

	/**
	 * 登入登出类，记录回话时长及退出原因，时长，单位秒
	 */
	private int duration;
	/**
	 * 退出原因：0：user；1：erver
	 */
	private int reason;

	/**
	 * 不同的cdr记录该字段意义不一样
	 */
	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public int getClientType() {
		return clientType;
	}

	public void setClientType(int clientType) {
		this.clientType = clientType;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public int getActionType() {
		return actionType;
	}

	public void setActionType(int actionType) {
		this.actionType = actionType;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getProcessTime() {
		return processTime;
	}

	public void setProcessTime(int processTime) {
		this.processTime = processTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getReason() {
		return reason;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
