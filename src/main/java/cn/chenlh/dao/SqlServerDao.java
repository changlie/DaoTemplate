package cn.chenlh.dao;

import java.util.List;

import cn.chenlh.bean.Field;

public class SqlServerDao extends DaoSupport {

	
	public List<Field> getTableInfo(String tableName) {
		String sql = "SELECT syscolumns.name,systypes.name as type,syscolumns.isnullable,"+
				"syscolumns.length "+
				"FROM syscolumns, systypes "+
				"WHERE syscolumns.xusertype = systypes.xusertype "+
				"AND syscolumns.id = object_id(?)";
		List<Field> fields = getEntityList(sql, Field.class, tableName);
		return fields;
	}
	
	public List<String> getFields(String tableName) {
		String sql = "SELECT syscolumns.name,systypes.name as type,syscolumns.isnullable,"+
				"syscolumns.length "+
				"FROM syscolumns, systypes "+
				"WHERE syscolumns.xusertype = systypes.xusertype "+
				"AND syscolumns.id = object_id(?)";
		List<String> fields = getColumnList(sql, String.class, tableName);
		return fields;
	}

}
