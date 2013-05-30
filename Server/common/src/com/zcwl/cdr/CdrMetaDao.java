package com.zcwl.cdr;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Hugo
 * 
 */
@Service("cdrMetaDao")
public class CdrMetaDao {

	private static final String SQL_batchInsertRecord = "insert into CDR_META"
			+ "(VERSION,USER_ID,UA,CLIENT_IP,CLIENT_TYPE,ACTION_TYPE,REMARK,EVENT_TIME,PROCESS_TIME,DURATION,REASON)"
			+ "values(?,?,?,?,?,?,?,?,?,?,?)";

	private JdbcTemplate jdbcTemplate;

	private static SimpleDateFormat format4datetime = new SimpleDateFormat(
			"yyMMddHHmmss");

	@Autowired
	public void init(@Qualifier("dataSource") DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void batchInsert(final List<CdrMeta> list) throws Exception {
		this.jdbcTemplate.batchUpdate(SQL_batchInsertRecord,
				new BatchPreparedStatementSetter() {
					Date now = new Date();

					public int getBatchSize() {
						return list.size();
					}

					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						CdrMeta cdr = list.get(i);

						ps.setString(1, cdr.version);
						ps.setString(2, cdr.userId);
						ps.setString(3, cdr.ua);
						ps.setString(4, cdr.clientIP);
						ps.setInt(5, cdr.clientType);
						ps.setInt(6, cdr.actionType);
						ps.setString(7, cdr.remark);
						if (cdr.eventTS != null) {
							ps
									.setString(8, format4datetime
											.format(cdr.eventTS));
						} else {
							ps.setString(8, format4datetime.format(now));
						}
						ps.setInt(9, cdr.processTime);
						ps.setInt(10, cdr.duration);
						ps.setInt(11, cdr.reason);
					}
				});

	}

}
