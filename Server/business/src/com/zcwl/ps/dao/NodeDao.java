package com.zcwl.ps.dao;

import java.util.List;

import com.zcwl.ps.dto.NodeDto;

/**
 * Node相关的数据库操作
 * 
 * @author Hugo
 * 
 */
public interface NodeDao {

	/**
	 * 
	 * 取得所有节点信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<NodeDto> getAllNodes() throws Exception;

	/**
	 * 根据parentId取得子节点列表，如果parentId为0则返回第一级节点列表
	 * 
	 * 注：仅取状态为有效的节点
	 * 
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public List<NodeDto> getNodesByParentId(int parentId) throws Exception;

	/**
	 * 根据ID取得节点信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public NodeDto getNodeById(int id) throws Exception;

	/**
	 * 根据ID删除节点信息，注意：需把该节点的子节点列表也删除掉
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void delete(int id) throws Exception;

	/**
	 * 添加一个节点信息
	 * 
	 * @param node
	 * @return
	 * @throws Exception
	 */
	public NodeDto add(NodeDto node) throws Exception;

	/**
	 * 更新节点信息
	 * 
	 * @param node
	 * @return
	 * @throws Exception
	 */
	public NodeDto update(NodeDto node) throws Exception;

}
