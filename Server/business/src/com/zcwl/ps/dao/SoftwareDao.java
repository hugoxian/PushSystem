package com.zcwl.ps.dao;

import java.util.List;

import com.zcwl.ps.dto.SoftwareDto;

/**
 * Software相关数据库操作接口
 * 
 * @author Hugo
 * 
 */
public interface SoftwareDao {

	/**
	 * 新增一个app
	 * 
	 * @param software
	 * @return
	 * @throws Exception
	 */
	public SoftwareDto add(SoftwareDto software) throws Exception;

	/**
	 * 删除一个APP
	 * 
	 * @param softwareId
	 * @throws Exception
	 */
	public void del(int softwareId) throws Exception;

	/**
	 * 删除一个APP
	 * 
	 * @param appKey
	 * @throws Exception
	 */
	public void del(String appKey) throws Exception;

	/**
	 * 更新APP
	 * 
	 * @param software
	 * @throws Exception
	 */
	public void update(SoftwareDto software) throws Exception;

	/**
	 * 取得所有APP
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SoftwareDto> getAllSoftwares() throws Exception;

	/**
	 * 取得操作员的APP
	 * 
	 * @param operatorId
	 * @return
	 * @throws Exception
	 */
	public List<SoftwareDto> getSoftwaresByOperatorId(int operatorId)
			throws Exception;

	/**
	 * 去的操作员的ID
	 * 
	 * @param appKey
	 * @return
	 * @throws Exception
	 */
	public int getOpertorIdByAppKey(String appKey) throws Exception;

}
