package cn.chenlh.common;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigInfo {
	// jdbc 配置
	public static String driver = "";
	public static String url = "";
	public static String username = "";
	public static String password = "";

	// template 配置
	public static String DbType;
	public static String tableNames;
	public static String EntityPackage;
	public static String beanTemplate;
	public static String templateDir;
	public static Map<String, String> typeMap = new HashMap<String, String>();
	public static boolean isMavenProject = true;

	static {
		try {
			ClassLoader classLoader = ConfigInfo.class.getClassLoader();

			LogUtil.i("config1:" + classLoader.getResource("generator-config.properties"));
			LogUtil.i("config2:" + classLoader.getResource("typeMap.properties"));

			Properties properties = new Properties();

			loadGeneratorConfig(properties, classLoader);

			properties.clear();
			loadTypeMap(properties, classLoader);

		} catch (Exception e) {
			LogUtil.i("加载  properties 失败！！");
			e.printStackTrace();
		}
	}

	private static void loadGeneratorConfig(Properties properties, ClassLoader classLoader) {
		try {

			InputStream is = classLoader.getResourceAsStream("generator-config.properties");
			properties.load(is);
			LogUtil.i("generator-config.propertiesKEYS", properties.keySet());

			driver = properties.getProperty("jdbc.driverClassName");
			url = properties.getProperty("jdbc.url");
			username = properties.getProperty("jdbc.username");
			password = properties.getProperty("jdbc.password");

			DbType = properties.getProperty("DbType");
			tableNames = properties.getProperty("tableNames");
			EntityPackage = properties.getProperty("EntityPackage");
			templateDir = properties.getProperty("templateDir");
			beanTemplate = properties.getProperty("bean-template");
		} catch (Exception e) {
			LogUtil.i("加载 generator-config.properties 失败！！");
			e.printStackTrace();
		}
	}

	private static void loadTypeMap(Properties properties, ClassLoader classLoader) {
		try {
			InputStream is1 = classLoader.getResourceAsStream("typeMap.properties");
			properties.load(is1);
			LogUtil.i("typeMap.propertiesKEYS", properties.keySet());

			Enumeration<Object> keys = properties.keys();
			while (keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				if (key.contains(DbType)) {
					typeMap.put(key.substring(key.indexOf(".") + 1), properties.getProperty(key));
				}
			}
		} catch (Exception e) {
			LogUtil.i("加载 typeMap.properties 失败！！");
			e.printStackTrace();
		}
	}

}
