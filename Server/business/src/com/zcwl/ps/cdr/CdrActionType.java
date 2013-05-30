package com.zcwl.ps.cdr;

/**
 * 11、新增角色；12、编辑角色；13、删除角色 21、新增操作员；22、编辑操作员；23、删除操作员；31、新增节点；32、编辑节点；33、删除节点；
 * 
 * 41、修改个人资料；42、修改个人密码；
 * 
 * 81、群发推送；82、单个推送
 * 
 * 小于10的为登入登出等CDR，具体参考com.zcwl.cdr.Cdrmeta.java
 * 
 * @author Hugo
 * 
 */
public interface CdrActionType {

	public int ADD_ROLE = 11;
	public int EDIT_ROLE = 12;
	public int DEL_ROLE = 13;

	public int ADD_OPERATOR = 21;
	public int EDIT_OPERATOR = 22;
	public int DEL_OPERATOR = 23;

	public int ADD_NODE = 31;
	public int EDIT_NODE = 32;
	public int DEL_NODE = 33;

	public int EDIT_PROFILE = 41;
	public int CHANGE_PASSWORD = 42;

	public int GROUNP_SEND = 81;
	public int SINGLE_SEND = 82;

}
