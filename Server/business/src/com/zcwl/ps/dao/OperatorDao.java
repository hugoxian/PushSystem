package com.zcwl.ps.dao;

import java.util.Date;
import java.util.List;

import com.zcwl.ps.dto.OperatorDto;

/**
 * Operator相关数据库操作接口
 * 
 * @author Hugo
 * 
 */
public interface OperatorDao {

	/**
	 * 取得所有操作者信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<OperatorDto> getAllOperators() throws Exception;

	/**
	 * 通过account和password判断用户是否可以登录
	 * 
	 * @param account
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public OperatorDto login(String account, String password) throws Exception;

	/**
	 * 增加一个操作者
	 * 
	 * @param operator
	 * @return
	 * @throws Exception
	 */
	public OperatorDto add(OperatorDto operator) throws Exception;

	/**
	 * 删除一个操作者
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void delete(int id) throws Exception;

	/**
	 * 更新一个操作者
	 * 
	 * @param operator
	 * @return
	 * @throws Exception
	 */
	public OperatorDto update(OperatorDto operator) throws Exception;

	/**
	 * 更新登录时间
	 * 
	 * @param date
	 * @throws Exception
	 */
	public void updateLoginDate(int operatorId,Date date) throws Exception;

}
