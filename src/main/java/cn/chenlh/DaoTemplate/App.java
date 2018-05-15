package cn.chenlh.DaoTemplate;

import cn.chenlh.index.GenerateBean;
import cn.chenlh.index.GenerateSql;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
//		System.out.println("Hello World!");
//		FreeMarkerUtil instance = FreeMarkerUtil.instance();
//		Map<String, Object> m = new HashMap<String, Object>();
//		m.put("name", "clh");
//		String geneFileStr = instance.geneFileStr("test.ftl", m);
//		System.out.println(geneFileStr);
		new GenerateBean().start();
		
		new GenerateSql().getSql("sys_user", "u");
	}
}
