package com.zcwl.ps.dao.mysql.impl;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zcwl.ps.dao.OperatorDao;
import com.zcwl.ps.dto.OperatorDto;
import com.zcwl.tool.MD5Util;

/**
 * 
 * @author Hugo
 * 
 */
@Service("operatorDao")
public class OperatorDaoImpl implements OperatorDao {

	protected static final Logger LOG = LoggerFactory
			.getLogger(OperatorDaoImpl.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void init(@Qualifier("dataSource") DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final String SQL_add = "INSERT PS_OPERATOR(name,account,password,roleId,createDate,email,phone)VALUES(?,?,?,?,?,?,?)";
	private static final String SQL_selectId ="SELECT id FROM PS_OPERATOR WHERE account=?";
	@Override
	public OperatorDto add(OperatorDto operator) throws Exception {
		this.jdbcTemplate.update(
				SQL_add,
				new Object[] { operator.getName(), operator.getAccount(),
						operator.getPassword(), operator.getRoleId(),
						operator.getCreateDate(), operator.getEmail(),
						operator.getPhone() });
		int id = this.jdbcTemplate.queryForInt(SQL_selectId,new Object[]{operator.getAccount()});
		operator.setId(id);
		return operator;
	}

	@Override
	public void delete(int id) throws Exception {
		// TODO Auto-generated method stub

	}

	private static final String SQL_getAllOperators = "SELECT * FROM PS_OPERATOR";

	@SuppressWarnings("unchecked")
	@Override
	public List<OperatorDto> getAllOperators() throws Exception {
		return this.jdbcTemplate.query(SQL_getAllOperators,
				new BeanPropertyRowMapper(OperatorDto.class));
	}

	private static final String SQL_isLogin = "SELECT * FROM PS_OPERATOR WHERE account=? and password=?";

	@Override
	public OperatorDto login(String account, String password) throws Exception {
		String md5Password = MD5Util.getMD5String(password);
		try {
			return (OperatorDto) this.jdbcTemplate.queryForObject(SQL_isLogin,
					new String[] { account, md5Password },
					new BeanPropertyRowMapper(OperatorDto.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	@Override
	public OperatorDto update(OperatorDto operator) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	private static final String SQL_updateLoginDate = "UPDATE PS_OPERATOR SET lastLoginDate=? WHERE id=?";

	@Override
	public void updateLoginDate(int operatorId, Date date) throws Exception {
		this.jdbcTemplate.update(SQL_updateLoginDate, new Object[] { date,
				operatorId });
	}

}
