package com.zcwl.ps.dao;

/**
 * 
 * 
 * @author Hugo
 * 
 */
public interface InitializeDao {

	/**
	 * 初始化数据库表结构
	 * 
	 * @throws Exception
	 */
	public void initTables() throws Exception;

	/**
	 * 初始化数据库表数据
	 * 
	 * @throws Exception
	 */
	public void initDatas() throws Exception;

}
