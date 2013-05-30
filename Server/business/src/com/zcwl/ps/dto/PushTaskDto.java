package com.zcwl.ps.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 推送任务
 * 
 * @author hugo
 * 
 * 
 */
public class PushTaskDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3710410918876469973L;

	public final static int TASK_SINGLE = 0;
	public final static int TASK_ALL_ONLINE = 1;
	public final static int TASK_ALL = 2;
	public final static int TASK_AREA = 3;
	public final static int TASK_UA = 4;

	public final static int CHANNEL_API = 0;
	public final static int CHANNEL_WEB = 1;
	public final static int CHANNEL_SOCKET = 2;

	/**
	 * 
	 */
	private int id;
	/**
	 * 0:单个用户 1：全部在线用户，2：全部用户，3：某地区用户，4：某UA用户 9：其他
	 */
	private int type;
	/**
	 * 操作员ID
	 */
	private int operatorId;
	/**
	 * appKEY
	 */
	private String appKey;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 定时任务时间
	 */
	private Date sendTime;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;

	/**
	 * 符合发送条数
	 */
	private int count;

	/**
	 * 完成条数
	 */
	private int finishCount;

	/**
	 * 0:api 1:web 2:socket
	 */
	private int channel;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getFinishCount() {
		return finishCount;
	}

	public void setFinishCount(int finishCount) {
		this.finishCount = finishCount;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

}
