package com.zcwl.ps.dao.mysql.impl;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zcwl.bo.Page;
import com.zcwl.ps.dao.PushDao;
import com.zcwl.ps.dto.PushRecordDto;
import com.zcwl.ps.dto.PushTaskDto;
import com.zcwl.tool.DateUtil;

/**
 * 
 * @author hugo
 * 
 */
@Service("pushDao")
public class PushDaoImpl implements PushDao {

	protected static final Logger LOG = LoggerFactory
			.getLogger(PushDaoImpl.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void init(@Qualifier("dataSource") DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final String SQL_addTask = "insert into PS_PUSH_TASK(type,operatorId,appKey,createDate,sendTime,title,content,channel,count)values(?,?,?,?,?,?,?,?,?)";
	private static final String SQL_LAST_INSERT_ID = "SELECT LAST_INSERT_ID()";

	@Override
	public PushTaskDto add(PushTaskDto task) throws Exception {
		this.jdbcTemplate.update(
				SQL_addTask,
				new Object[] { task.getType(), task.getOperatorId(),
						task.getAppKey(), task.getCreateDate(),
						task.getSendTime(), task.getTitle(), task.getContent(),
						task.getChannel(), task.getCount() });
		int taskId = this.jdbcTemplate.queryForInt(SQL_LAST_INSERT_ID);
		task.setId(taskId);
		return task;
	}

	// ?包括返回值
	private static final String SQL_addWithIdBack = "{call insertTask(?,?,?,?,?,?,?,?,?,?)}";

	@Override
	public PushTaskDto addWithIdBack(final PushTaskDto task) throws Exception {

		int taskId = (Integer) this.jdbcTemplate.execute(SQL_addWithIdBack,
				new CallableStatementCallback() {
					@Override
					public Object doInCallableStatement(CallableStatement cs)
							throws SQLException, DataAccessException {
						String createDateStr = DateUtil.sdf.format(task
								.getCreateDate());
						String sendTimeStr = DateUtil.sdf.format(task
								.getSendTime());
						cs.setInt(1, task.getType());
						cs.setInt(2, task.getOperatorId());
						cs.setString(3, task.getAppKey());
						cs.setString(4, createDateStr);
						cs.setString(5, sendTimeStr);
						cs.setString(6, task.getTitle());
						cs.setString(7, task.getContent());
						cs.setInt(8, task.getChannel());
						cs.setInt(9, task.getCount());
						cs.execute();
						int result = cs.getInt("taskId");
						return result;
					}

				});
		task.setId(taskId);
		return task;
	}

	private static final String SQL_updateTaskCount = "update PS_PUSH_TASK set count=? where id=?";

	@Override
	public void updateTaskCount(PushTaskDto task) throws Exception {
		this.jdbcTemplate.update(SQL_updateTaskCount,
				new Object[] { task.getCount(), task.getId() });
	}

	private static final String SQL_addRecord = "insert into PS_PUSH_RECORD(taskId,deviceId,messageId,pushStatus,pushTime,finishTime)values(?,?,?,?,?,?)";

	@Override
	public PushRecordDto add(PushRecordDto record) throws Exception {
		this.jdbcTemplate.update(
				SQL_addRecord,
				new Object[] { record.getTaskId(), record.getDeviceId(),
						record.getMessageId(), record.getPushStatus(),
						record.getPushTime(), record.getFinishTime() });
		return record;
	}

	private static final String SQL_batchInsertRecord = "insert into PS_PUSH_RECORD(taskId,deviceId,messageId,pushTime)values(?,?,?,?)";

	@Override
	public void batchInsert(final List<PushRecordDto> records) throws Exception {
		this.jdbcTemplate.batchUpdate(SQL_batchInsertRecord,
				new BatchPreparedStatementSetter() {
					public int getBatchSize() {
						return records.size();
					}

					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						PushRecordDto record = records.get(i);
						String dateStr = DateUtil.sdf.format(record
								.getPushTime());
						ps.setInt(1, record.getTaskId());
						ps.setString(2, record.getDeviceId());
						ps.setString(3, record.getMessageId());
						ps.setString(4, dateStr);
					}
				});
	}

	private static final String SQL_updateRecord = "update PS_PUSH_RECORD set pushStatus=?,finishTime=? where messageId=?";

	@Override
	public void updateRecord(String messageId, int pushStatus, Date finishTime)
			throws Exception {
		this.jdbcTemplate.update(SQL_updateRecord, new Object[] { pushStatus,
				finishTime, messageId });
	}

	@Override
	public void batchUpdate(List<PushRecordDto> records) throws Exception {

	}

	private static final String SQL_getTasks = "select * from PS_PUSH_TASK where appKey=?  order by id desc LIMIT ?,?";

	@SuppressWarnings("unchecked")
	@Override
	public Page<PushTaskDto> getTasks(Page<PushTaskDto> page, String appKey)
			throws Exception {
		int start = (page.getPageNo() - 1) * page.getPageSize();
		int end = page.getPageSize();
		List<PushTaskDto> tasks = this.jdbcTemplate.query(SQL_getTasks,
				new Object[] { appKey, start, end }, new BeanPropertyRowMapper(
						PushTaskDto.class));
		page.setResult(tasks);
		page.setTotalCount(getTotalCount(appKey));
		return page;
	}

	private static final String SQL_getTotalCount = "select count(id) from PS_PUSH_TASK where appKey=? ";

	private int getTotalCount(String appKey) {
		return jdbcTemplate.queryForInt(SQL_getTotalCount,
				new Object[] { appKey });
	}

	@Override
	public Page<PushRecordDto> getRecords(Page<PushRecordDto> page, int taskId)
			throws Exception {
		return null;
	}

	private static final String SQL_updateFinishCount = "UPDATE PS_PUSH_TASK T SET finishCount=(SELECT COUNT(id) FROM PS_PUSH_RECORD R WHERE pushStatus=9 AND taskId=T.id)";

	@Override
	public void updateFinishCount() throws Exception {
		this.jdbcTemplate.update(SQL_updateFinishCount);
	}

	private static final String SQL_getUnSendingRecords = "SELECT R.id,R.messageId,R.deviceId,T.title,T.content FROM PS_PUSH_RECORD R LEFT OUTER JOIN PS_PUSH_TASK T ON R.taskId=T.id WHERE R.pushStatus!=9 AND deviceId=? AND T.appKey=?";

	@SuppressWarnings("unchecked")
	@Override
	public List<PushRecordDto> getUnSendingRecords(String appKey,
			String deviceId) throws Exception {
		List<PushRecordDto> records = this.jdbcTemplate.query(
				SQL_getUnSendingRecords, new Object[] { deviceId, appKey },
				new BeanPropertyRowMapper(PushRecordDto.class));
		return records;
	}

}
