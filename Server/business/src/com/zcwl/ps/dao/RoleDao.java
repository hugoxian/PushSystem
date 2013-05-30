package com.zcwl.ps.dao;

import java.util.List;

import com.zcwl.ps.dto.RoleDto;

/**
 * Role相关数据库操作接口
 * 
 * @author Hugo
 * 
 */
public interface RoleDao {

	/**
	 * 增加一个角色
	 * 
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public RoleDto add(RoleDto role) throws Exception;

	/**
	 * 查询角色列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<RoleDto> getRoles() throws Exception;

	/**
	 * 
	 * 删除一个角色
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void delete(int id) throws Exception;

	/**
	 * 更新角色
	 * 
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public RoleDto update(RoleDto role) throws Exception;

}
