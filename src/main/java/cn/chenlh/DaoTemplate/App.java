package cn.chenlh.DaoTemplate;

import java.util.HashMap;
import java.util.Map;

import cn.chenlh.common.ConfigInfo;
import cn.chenlh.index.GenerateSql;
import cn.chenlh.template.FreeMarkerUtil;
import cn.chenlh.template.LogUtil;

/**
 * Hello world!
 *
 */
public class App {
	
	static {
		LogUtil.isDebug = true;
		ConfigInfo.beanTemplate = "abcdddd";
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
