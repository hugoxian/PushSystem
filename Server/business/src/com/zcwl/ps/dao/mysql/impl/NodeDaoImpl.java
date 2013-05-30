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

import com.zcwl.ps.dao.NodeDao;
import com.zcwl.ps.dto.NodeDto;

/**
 * node数据库操作，mysql的实现
 * 
 * @author Hugo
 * 
 */
@Service("nodeDao")
public class NodeDaoImpl implements NodeDao {

	protected static final Logger LOG = LoggerFactory
			.getLogger(NodeDaoImpl.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void init(@Qualifier("dataSource") DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final String SQL_add = "insert into PS_NODE(parentId,name,sequence,status,uri)values(?,?,?,?,?)";

	@Override
	public NodeDto add(NodeDto node) throws Exception {
		this.jdbcTemplate.update(SQL_add, new Object[] { node.getParentId(),
				node.getName(), node.getSequence(), node.getSequence(),
				node.getStatus(), node.getUri() });
		return node;
	}

	@Override
	public NodeDto update(NodeDto node) throws Exception {
		return null;
	}

	@Override
	public void delete(int id) throws Exception {
		// TODO Auto-generated method stub

	}

	private static final String SQL_getAllNodes = "SELECT * FROM PS_NODE";

	@SuppressWarnings("unchecked")
	@Override
	public List<NodeDto> getAllNodes() throws Exception {
		return this.jdbcTemplate.query(SQL_getAllNodes,
				new BeanPropertyRowMapper(NodeDto.class));
	}

	@Override
	public NodeDto getNodeById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	private static final String SQL_getNodesByParentId = "SELECT * FROM PS_NODE WHERE parentId=? and status=0 ORDER BY sequence ASC";

	@SuppressWarnings("unchecked")
	@Override
	public List<NodeDto> getNodesByParentId(int parentId) throws Exception {
		return this.jdbcTemplate.query(SQL_getNodesByParentId,
				new Object[] { parentId }, new BeanPropertyRowMapper(
						NodeDto.class));
	}
}
