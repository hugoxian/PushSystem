package com.zcwl.ps.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 推送记录
 * 
 * @author hugo
 * 
 */
public class PushRecordDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3072982542914499486L;
	
	/**
	 * 记录Id
	 */
	private int id;
	/**
	 * 任务ID
	 */
	private int taskId;
	/**
	 * 设备ID
	 */
	private String deviceId;
	/**
	 * 消息ID
	 */
	private String messageId;
	/**
	 * 0:未发送，1：发送中 9：发送成功
	 */
	private int pushStatus;
	/**
	 * 推送时间
	 */
	private Date pushTime;
	/**
	 * 完成时间
	 */
	private Date finishTime;
	
	/**
	 * 消息标题
	 */
	private String title;
	/**
	 * 消息内容
	 */
	private String content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public int getPushStatus() {
		return pushStatus;
	}

	public void setPushStatus(int pushStatus) {
		this.pushStatus = pushStatus;
	}

	public Date getPushTime() {
		return pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
