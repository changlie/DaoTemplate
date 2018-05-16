package cn.chenlh.DaoTemplate;

import java.util.HashMap;
import java.util.Map;

import cn.chenlh.common.ConfigInfo;
import cn.chenlh.common.FreeMarkerUtil;
import cn.chenlh.common.LogUtil;
import cn.chenlh.index.GenerateSql;

/**
 * Hello world!
 *
 */
public class App {
	
	static {
		
		LogUtil.isDebug = true;
		System.out.println("start 自定义配置!");
		ConfigInfo.beanTemplate = "abcdddd";
		System.out.println("end 自定义配置!");
	}
	
	public static void main(String[] args) {
//		System.out.println("Hello World!");
//		FreeMarkerUtil instance = FreeMarkerUtil.instance();
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("name", "clh-10086*");
		FreeMarkerUtil.instance().test("test", m);
//		String geneFileStr = instance.geneFileStr("test.ftl", m);
//		System.out.println(geneFileStr);
//		new GenerateBean().start();
		
		new GenerateSql().getSql("sys_user", "u");
		
		System.out.println(ConfigInfo.beanTemplate);
		System.out.println(ConfigInfo.tableNames);
		System.out.println(ConfigInfo.beanTemplate);
		
	}
}
