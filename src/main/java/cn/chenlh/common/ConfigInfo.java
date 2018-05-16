package cn.chenlh.common;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import cn.chenlh.template.LogUtil;


public class ConfigInfo {
	public static String DbType ;
	public static String tableNames;
	public static String EntityPackage ;
	public static String beanTemplate;
	public static String templateDir ;
	public static Map<String, String> typeMap = new HashMap<String, String>();
	public static boolean isMavenProject = true;
	
	
	static{
		try {
			LogUtil.i("config1:"+ConfigInfo.class.getResource("generator-config.properties"));
			LogUtil.i("config2:"+ConfigInfo.class.getResource("typeMap.properties"));
			
			Properties properties = new Properties();
			InputStream is = ConfigInfo.class.getResourceAsStream("generator-config.properties");
			properties.load(is);
			LogUtil.i("generator-config.propertiesKEYS", properties.keySet());
			
			
			DbType = properties.getProperty("DbType");
			tableNames = properties.getProperty("tableNames");
			EntityPackage = properties.getProperty("EntityPackage");
			templateDir = properties.getProperty("templateDir");
			beanTemplate= properties.getProperty("bean-template");
			
			properties.clear();
			InputStream is1 = ConfigInfo.class.getResourceAsStream("typeMap.properties");
			properties.load(is1);
			LogUtil.i("typeMap.propertiesKEYS", properties.keySet());
			
			Enumeration<Object> keys = properties.keys();
			while (keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				if(key.contains(DbType)){
					typeMap.put(key.substring(key.indexOf(".")+1), properties.getProperty(key));
				}
			}
		
		} catch (Exception e) {
			LogUtil.i("加载 generator-config.properties 失败！！");
			e.printStackTrace();
		}
	}
	
}
