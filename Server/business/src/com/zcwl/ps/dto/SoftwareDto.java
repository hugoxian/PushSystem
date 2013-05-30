package com.zcwl.ps.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 * 软件实体
 * 
 * @author hugo
 * 
 */
public class SoftwareDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1392169370831630473L;
	private int id;
	private int operatorId;
	// 软件唯一标示
	private String appKey;
	private String name;
	private String nameEn;
	// 包名称，Android APP需要
	private String packageName;
	// 图标URL
	private String iconUrl;
	// 状态 0：正常 1：无效
	private int status;
	// 创建日期
	private Date createDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
