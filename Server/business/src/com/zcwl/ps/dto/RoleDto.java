package com.zcwl.ps.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * 角色实体
 * 
 * 
 * @author Hugo
 * 
 */
public class RoleDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8373807613694742146L;
	/**
	 * 角色id
	 */
	private int id;
	/**
	 * 角色名称
	 */
	private String name;
	/**
	 * 角色描述
	 */
	private String desc;

	/**
	 * 有权限的节点id字符串，用","分隔（只对一级节点做权限）
	 */
	private String permitIdsStr;

	/**
	 * 角色状态，0：有效；1：无效
	 */
	private int status;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 有权限的节点列表
	 */
	private List<NodeDto> permitNodes;

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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPermitIdsStr() {
		return permitIdsStr;
	}

	public void setPermitIdsStr(String permitIdsStr) {
		this.permitIdsStr = permitIdsStr;
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

	public List<NodeDto> getPermitNodes() {
		return permitNodes;
	}

	public void setPermitNodes(List<NodeDto> permitNodes) {
		this.permitNodes = permitNodes;
	}
}
