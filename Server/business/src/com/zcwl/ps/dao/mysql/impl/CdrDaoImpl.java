package com.zcwl.ps.dao.mysql.impl;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zcwl.ps.dao.CdrDao;
import com.zcwl.ps.dto.CdrDto;

/**
 * CDR数据库操作mysql实现
 * 
 * @author Hugo
 * 
 */
@Service("cdrDao")
public class CdrDaoImpl implements CdrDao {

	protected static final Logger LOG = LoggerFactory
			.getLogger(CdrDaoImpl.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void init(@Qualifier("dataSource") DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final String SQL_getLoginRecords = "SELECT * FROM CDR_META WHERE USER_ID=? AND ACTION_TYPE=1 ORDER BY EVENT_TIME DESC LIMIT ?,?";

	@SuppressWarnings("unchecked")
	@Override
	public List<CdrDto> getLoginRecords(int userId) throws Exception {
		return this.jdbcTemplate.query(SQL_getLoginRecords,
				new Object[] { userId,1,15 },
				new BeanPropertyRowMapper(CdrDto.class));
	}

}
