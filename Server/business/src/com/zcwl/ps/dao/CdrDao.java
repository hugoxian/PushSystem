package com.zcwl.ps.dao;

import java.util.List;

import com.zcwl.ps.dto.CdrDto;

/**
 * CDR话单数据库操作接口
 * @author Hugo
 *
 */
public interface CdrDao {

	public List<CdrDto> getLoginRecords(int userId) throws Exception;
	
}
