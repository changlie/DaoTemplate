package cn.chenlh.common;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


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
			System.out.println("config1:"+ConfigInfo.class.getResource("generator-config.properties"));
			System.out.println("config2:"+ConfigInfo.class.getResource("typeMap.properties"));
			InputStream is = ConfigInfo.class.getResourceAsStream("generator-config.properties");
			InputStream is1 = ConfigInfo.class.getResourceAsStream("typeMap.properties");
			Properties properties = new Properties();
			properties.load(is);
			System.out.println(properties.keySet());
			DbType = properties.getProperty("DbType");
			tableNames = properties.getProperty("tableNames");
			EntityPackage = properties.getProperty("EntityPackage");
			templateDir = properties.getProperty("templateDir");
			beanTemplate= properties.getProperty("bean-template");
			
			properties.clear();
			properties.load(is1);
			System.out.println(properties.keySet());
			
			Enumeration<Object> keys = properties.keys();
			while (keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				if(key.contains(DbType)){
					typeMap.put(key.substring(key.indexOf(".")+1), properties.getProperty(key));
				}
			}
		
		} catch (Exception e) {
			System.out.println("init jdbc fail!!!");
			e.printStackTrace();
		}
	}
	
}
