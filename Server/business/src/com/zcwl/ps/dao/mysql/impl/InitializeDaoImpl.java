package com.zcwl.ps.dao.mysql.impl;

import java.util.ArrayList;
import java.util.List;

import com.zcwl.ps.dao.InitializeDao;

/**
 * 
 * @author Hugo
 *
 */
public class InitializeDaoImpl implements InitializeDao {

	@Override
	public void initDatas() throws Exception {
	}
	
	@Override
	public void initTables() throws Exception {
		
	}
	
	private List<String> getInitTableSqls(){
		List<String> sqls = new ArrayList<String>();
		String table_node_drop = "DROP TABLE IF EXISTS 'PS_NODE'";
		String table_node_create = "CREATE TABLE 'PS_NODE'(";
		sqls.add(table_node_drop);
		sqls.add(table_node_create);
		return sqls;
	}

}
