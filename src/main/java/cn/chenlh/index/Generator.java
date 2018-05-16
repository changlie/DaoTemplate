package cn.chenlh.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.chenlh.bean.Entity;
import cn.chenlh.bean.Field;
import cn.chenlh.common.ConfigInfo;
import cn.chenlh.dao.SqlServerDao;
import cn.chenlh.template.LogUtil;

public abstract class Generator {
	
	private void convertType(List<Field> fields){
		for(Field field : fields){
			String name = field.getName();
			String name2 = field.getNAME();
			String type = field.getType();
			type = ConfigInfo.typeMap.get(type);
			name2 = name.substring(0,1).toUpperCase() + name.substring(1);
			field.setNAME(name2);
			field.setType(type);
		}
	}
	
	protected Map<String, Object> getBeanTemplateData(String tableName, String className) {
		SqlServerDao sqlServerDao = new SqlServerDao();
		List<Field> fields = sqlServerDao.getTableInfo(tableName);
		/*** 把sql 数据类型转换成  java 格式的数据类型 */
		convertType(fields);
		
		boolean hasDate = false;
		Entity entity = new Entity();
		List<String> imports = new ArrayList<String>();
		entity.setName(className);
		entity.set_package(ConfigInfo.EntityPackage);
		entity.setFields(fields);

		for (Field f : fields) {
			if (!hasDate && "date".equalsIgnoreCase(f.getType())) {
				imports.add("java.util.Date");
				hasDate = true;
			}
		}
		entity.setImports(imports);

		Map<String, Object> root = new HashMap<String, Object>();
		root.put("bean", entity);

		root.put("tableName", tableName);
		return root;
	}
	
	protected abstract void generate();
	
	public void start() {
		long start = System.currentTimeMillis();
		generate();
		long end = System.currentTimeMillis();
		LogUtil.i("total spend: " + (end - start) + "ms, finish!");
	}
}
