package cn.chenlh.index;

import java.util.List;

import cn.chenlh.dao.SqlServerDao;

public class GenerateSql {

	public void getSql(String... args) {
		String tableName = args[0];
		String simpleName = null;
		if (args.length == 2) {
			simpleName = args[1];
		}

		SqlServerDao dao = new SqlServerDao();
		List<String> fields = dao.getFields(tableName);

		// Map<String, Object> propMap = new HashMap<String, Object>();
		// propMap.put("tableName", tableName);
		// propMap.put("simpleName", simpleName);
		// propMap.put("fields", fields);
		// FreeMarkerUtil instance = FreeMarkerUtil.instance();
		// String ret = instance.geneFileStr("sql", propMap);

		String ret = doGetSql(tableName, simpleName, fields);
		System.out.println("result:");
		System.out.println(ret);
	}

	private String doGetSql(String tableName, String simpleName, List<String> fields) {
		StringBuilder ret = new StringBuilder();

		ret.append("select \n");
		for (int i = 0; i < fields.size(); i++) {
			String fieldName = fields.get(i);
			
			if(i!=0) {
				ret.append(", ");
			}
			if(simpleName!=null) {
				ret.append(simpleName).append(".");
			}
			ret.append(fieldName);
		}
		
		ret.append("\nfrom " + tableName);
		if (simpleName != null) {
			ret.append(" " + simpleName);
		}

		return ret.toString();
	}

}
