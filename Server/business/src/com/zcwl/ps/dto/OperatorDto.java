package com.zcwl.ps.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * 操作者实体
 * 
 * @author Hugo
 * 
 */
public class OperatorDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1326835752233761736L;

	/**
	 * id
	 */
	private int id;

	/**
	 * 用户名称，用于显示
	 */
	private String name;

	/**
	 * 登录名称
	 */
	private String account;

	/**
	 * 登录密码，MD5加密
	 */
	private String password;

	/**
	 * 所属角色ID
	 */
	private int roleId;

	/**
	 * 操作者状态，0：正常有效；1：无效，不能登录
	 */
	private int status;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 上一次登录时间
	 */
	private Date lastLoginDate;

	/**
	 * 所属角色
	 * 
	 */
	private RoleDto role;

	/**
	 * 电子邮件
	 */
	private String email;
	/**
	 * 手机号码
	 */
	private String phone;

	/**
	 * 拥有的APP
	 */
	private List<SoftwareDto> softwares;

	/**
	 * 是否在线
	 */
	private boolean isOnline;

	/**
	 * 同时在几处地方登录
	 */
	private int loginAtTheSameTime;

	/**
	 * 增加一处登录
	 */
	public void addLoginPlace() {
		if (loginAtTheSameTime < 0) {
			loginAtTheSameTime = 0;
		}
		loginAtTheSameTime++;
	}

	/**
	 * 删减一处登录
	 */
	public void delLoginPlace() {
		loginAtTheSameTime--;
		if (loginAtTheSameTime < 0) {
			loginAtTheSameTime = 0;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
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

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public RoleDto getRole() {
		return role;
	}

	public void setRole(RoleDto role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getLoginAtTheSameTime() {
		return loginAtTheSameTime;
	}

	public List<SoftwareDto> getSoftwares() {
		return softwares;
	}

	public void setSoftwares(List<SoftwareDto> softwares) {
		this.softwares = softwares;
	}
}
