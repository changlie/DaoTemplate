package cn.chenlh.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.chenlh.dao.SqlServerDao;
import cn.chenlh.template.FreeMarkerUtil;

public class GenerateSql {

	public void getSql(String...args) {
		String tableName = args[0];
		String simpleName = null;
		if(args.length==2) {
			simpleName = args[1];
		}
		
		SqlServerDao dao = new SqlServerDao();
		List<String> fields = dao.getFields(tableName);
		
		Map<String, Object> propMap = new HashMap<String, Object>();
		propMap.put("tableName", tableName);
		propMap.put("simpleName", simpleName);
		propMap.put("fields", fields);
		
		FreeMarkerUtil instance = FreeMarkerUtil.instance();
		String ret = instance.geneFileStr("sql.ftl", propMap);
		System.out.println(ret);
	}
}
