package cn.chenlh.dao;

import java.util.List;
import java.util.Map;

import cn.chenlh.common.DaoSupport;

public class CommonDao extends DaoSupport{
	
	public CommonDao() {
		driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		url = "jdbc:sqlserver://localhost:1433;databaseName=standard";
		username = "sa";
		password = "root";
	}

	
	public void test(String sql) {
		System.out.println("sql: "+sql);
	}
	
	
	public List<Map<String, Object>> list(String sql, Object... args){
		return getMapList(sql, args);
	}
	
	public Map<String, Object> row(String sql, Object... args){
		return getMap(sql, args);
	}
	

}
