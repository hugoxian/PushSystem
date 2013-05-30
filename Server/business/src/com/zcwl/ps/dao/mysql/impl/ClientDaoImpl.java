package com.zcwl.ps.dao.mysql.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zcwl.bo.Page;
import com.zcwl.ps.dao.ClientDao;
import com.zcwl.ps.dto.ClientDto;

/**
 * 
 * @author hugo
 * 
 */
@Service("clientDao")
public class ClientDaoImpl implements ClientDao {

	protected static final Logger LOG = LoggerFactory
			.getLogger(ClientDaoImpl.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void init(@Qualifier("dataSource") DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final String SQL_getClientByKeyAndId = "select * from PS_CLIENT where appKey=? and deviceId=?";

	@Override
	public ClientDto getClientByKeyAndId(String appKey, String deviceId)
			throws Exception {
		try {
			return (ClientDto) this.jdbcTemplate.queryForObject(
					SQL_getClientByKeyAndId, new Object[] { appKey, deviceId },
					new BeanPropertyRowMapper(ClientDto.class));
		} catch (Exception e) {
			return null;
		}
	}

	private static final String SQL_getClientsByKey = "select * from PS_CLIENT where appKey=? LIMIT ?,?";

	@SuppressWarnings("unchecked")
	@Override
	public Page<ClientDto> getClientsByKey(Page<ClientDto> page, String appKey)
			throws Exception {
		int start = (page.getPageNo() - 1) * page.getPageSize();
		int end = page.getPageSize();
		List<ClientDto> clients = this.jdbcTemplate.query(SQL_getClientsByKey,
				new Object[] { appKey, start, end }, new BeanPropertyRowMapper(
						ClientDto.class));
		page.setResult(clients);
		page.setTotalCount(getTotalCount(appKey));
		return page;
	}

	private static final String getAllClientDeviceIds = "select deviceId from PS_CLIENT where appKey=?";

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllClientDeviceIds(String appKey) throws Exception {
		List<String> deviceIds = this.jdbcTemplate.queryForList(
				getAllClientDeviceIds, new Object[] { appKey }, String.class);

		return deviceIds;
	}

	private static final String SQL_getTotalCount = "select count(id) from PS_CLIENT where appKey=? ";

	private int getTotalCount(String appKey) {
		return jdbcTemplate.queryForInt(SQL_getTotalCount,
				new Object[] { appKey });
	}

	private static final String SQL_insert = "insert into PS_CLIENT(appKey,deviceId,userAgent,lastLatLon,lastAccessTime,createDate,status)values(?,?,?,?,?,?,?)";

	@Override
	public ClientDto insert(ClientDto client) throws Exception {
		this.jdbcTemplate.update(
				SQL_insert,
				new Object[] { client.getAppKey(), client.getDeviceId(),
						client.getUserAgent(), client.getLastLatLon(),
						client.getLastAccessTime(), client.getCreateDate(),
						client.getStatus() });
		return client;
	}

	private static final String SQL_update = "update PS_CLIENT set lastAccessTime=? where appKey=? and deviceId=?";

	@Override
	public void update(ClientDto client) throws Exception {
		this.jdbcTemplate.update(SQL_update,
				new Object[] { client.getLastAccessTime(), client.getAppKey(),
						client.getDeviceId() });
	}

	@Override
	public void batchInsert(final List<ClientDto> clients) throws Exception {
		this.jdbcTemplate.batchUpdate(SQL_insert,
				new BatchPreparedStatementSetter() {
					public int getBatchSize() {
						return clients.size();
					}

					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ClientDto client = clients.get(i);
						java.sql.Date sqlDate = new java.sql.Date(client
								.getLastAccessTime().getTime());
						ps.setString(1, client.getAppKey());
						ps.setString(2, client.getDeviceId());
						ps.setString(3, client.getUserAgent());
						ps.setString(4, client.getLastLatLon());
						ps.setDate(5, sqlDate);
						ps.setDate(6, sqlDate);
						ps.setInt(7, client.getStatus());
					}
				});
	}

	@Override
	public void batchUpdate(final List<ClientDto> clients) throws Exception {
		this.jdbcTemplate.batchUpdate(SQL_update,
				new BatchPreparedStatementSetter() {

					public int getBatchSize() {
						return clients.size();
					}

					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ClientDto client = clients.get(i);
						java.sql.Date sqlDate = new java.sql.Date(client
								.getLastAccessTime().getTime());
						ps.setDate(1, sqlDate);
						ps.setString(2, client.getAppKey());
						ps.setString(3, client.getDeviceId());
					}
				});
	}

	@Override
	public int getMaxClientIndex() throws Exception {
		String sql = "select max(deviceId) from PS_CLIENT where deviceId!='null' and length(deviceId)<15";
		return this.jdbcTemplate.queryForInt(sql);
	}

}
