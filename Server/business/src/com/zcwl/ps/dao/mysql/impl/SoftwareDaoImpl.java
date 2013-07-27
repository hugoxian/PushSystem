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

import com.zcwl.ps.dao.SoftwareDao;
import com.zcwl.ps.dto.CdrDto;
import com.zcwl.ps.dto.SoftwareDto;

/**
 * 
 * @author Hugo
 * 
 */
@Service("softwareDao")
public class SoftwareDaoImpl implements SoftwareDao {

	protected static final Logger LOG = LoggerFactory
			.getLogger(SoftwareDaoImpl.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void init(@Qualifier("dataSource") DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final String SQL_add = "INSERT PS_SOFTWARE(appKey,name,nameEn,packageName,iconUrl,createDate,operatorId)VALUES(?,?,?,?,?,?,?)";
	private static final String SQL_selectId = "SELECT id FROM PS_SOFTWARE WHERE appKey=?";

	@Override
	public SoftwareDto add(SoftwareDto software) throws Exception {
		this.jdbcTemplate.update(SQL_add,
				new Object[] { software.getAppKey(), software.getName(),
						software.getNameEn(), software.getPackageName(),
						software.getIconUrl(), software.getCreateDate(),
						software.getOperatorId() });
		int id = this.jdbcTemplate.queryForInt(SQL_selectId,
				new Object[] { software.getAppKey() });
		software.setId(id);
		return software;
	}

	@Override
	public void del(int softwareId) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void del(String appKey) throws Exception {
		// TODO Auto-generated method stub

	}

	private static final String SQL_update = "UPDATE PS_SOFTWARE SET status=? , name=? , packageName=? , welcomeMsg=? WHERE id=?";
	@Override
	public void update(SoftwareDto software) throws Exception {
		this.jdbcTemplate.update(SQL_update,
				new Object[] { software.getStatus(), software.getName(),
						software.getPackageName(),software.getWelcomeMsg(), software.getId() });
	}

	private static final String SQL_getAllSoftwares = "SELECT * FROM PS_SOFTWARE";

	@SuppressWarnings("unchecked")
	@Override
	public List<SoftwareDto> getAllSoftwares() throws Exception {
		return this.jdbcTemplate.query(SQL_getAllSoftwares,
				new BeanPropertyRowMapper(SoftwareDto.class));
	}

	private static final String SQL_getSoftwaresByOperatorId = "SELECT * FROM PS_SOFTWARE WHERE operatorId=?";

	@SuppressWarnings("unchecked")
	@Override
	public List<SoftwareDto> getSoftwaresByOperatorId(int operatorId)
			throws Exception {
		return this.jdbcTemplate.query(SQL_getSoftwaresByOperatorId,
				new Object[] { operatorId }, new BeanPropertyRowMapper(
						SoftwareDto.class));
	}

	private static final String SQL_getOpertorIdByAppKey = "SELECT operatorId FROM PS_SOFTWARE WHERE appKey=?";

	@Override
	public int getOpertorIdByAppKey(String appKey) throws Exception {
		return this.jdbcTemplate.queryForInt(SQL_getOpertorIdByAppKey,
				new Object[] { appKey });
	}

	private static final String SQL_getSoftwareByAppKey = "SELECT * FROM PS_SOFTWARE WHERE appKey=?";
	
	@Override
	public SoftwareDto getSoftwareByAppKey(String appKey) throws Exception {
		return (SoftwareDto)this.jdbcTemplate.queryForObject(SQL_getSoftwareByAppKey, new Object[]{appKey},  new BeanPropertyRowMapper(
						SoftwareDto.class));
	}

}
