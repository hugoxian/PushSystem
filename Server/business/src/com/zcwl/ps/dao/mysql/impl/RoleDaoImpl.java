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

import com.zcwl.ps.dao.RoleDao;
import com.zcwl.ps.dto.RoleDto;

/**
 * RoleDao接口mysql数据库实现
 * 
 * @author Hugo
 * 
 */
@Service("roleDao")
public class RoleDaoImpl implements RoleDao {

	protected static final Logger LOG = LoggerFactory
			.getLogger(RoleDaoImpl.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void init(@Qualifier("dataSource") DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public RoleDto add(RoleDto role) throws Exception {
		return null;
	}

	@Override
	public void delete(int id) throws Exception {
	}

	private static final String SQL_getRoles = "SELECT * FROM PS_ROLE";

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleDto> getRoles() throws Exception {
		return this.jdbcTemplate.query(SQL_getRoles, new BeanPropertyRowMapper(
				RoleDto.class));
	}

	@Override
	public RoleDto update(RoleDto role) throws Exception {
		return null;
	}

}
