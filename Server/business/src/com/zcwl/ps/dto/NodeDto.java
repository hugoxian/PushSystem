package com.zcwl.ps.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 节点实体
 * 
 * @author Hugo
 * 
 */
public class NodeDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6666804977814056756L;
	/**
	 * 节点ID
	 */
	private int id;
	/**
	 * 父节点ID
	 */
	private int parentId;
	/**
	 * 节点名称
	 */
	private String name;
	/**
	 * 父节点名称
	 */
	private String parentName;
	/**
	 * 节点序号
	 */
	private int sequence;
	/**
	 * 0:非终极子节点，1：终极节点
	 */
	private int end;
	/**
	 * 0：有效，1：无效
	 */
	private int status;

	/**
	 * 是否能修改，默认是可以修改，初始化的一些节点默认是不能修改
	 */
	private boolean isEdit = true;

	/**
	 * 点击执行uri
	 */
	private String uri;
	/**
	 * 子节点
	 */
	private List<NodeDto> subNodes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getStatus() {
		return status;
	}

	public boolean isEdit() {
		return isEdit;
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<NodeDto> getSubNodes() {
		return subNodes;
	}

	public void setSubNodes(List<NodeDto> subNodes) {
		this.subNodes = subNodes;
	}
}
