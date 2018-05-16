package cn.chenlh.index;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import cn.chenlh.common.ConfigInfo;
import cn.chenlh.common.FreeMarkerUtil;
import cn.chenlh.common.LogUtil;

public class GenerateBean extends Generator {

	@Override
	protected void generate() {
		try {

			String[] tableNames = ConfigInfo.tableNames.split(",");
			for (String names : tableNames) {
				String[] nameArr = names.split(":");
				String tableName = nameArr[0];
				String className = getClassName(nameArr);
				
				// 获取 bean Template 填充 数据
				Map<String, Object> beanTemplateData = getBeanTemplateData(tableName, className);
				
				Writer out = getWriter(className);
				FreeMarkerUtil
					.instance()
					.generateFile(ConfigInfo.beanTemplate, beanTemplateData, out);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getClassName(String[] nameArr) {
		String className = nameArr.length > 1 ? nameArr[1] : nameArr[0];
		/***  class name, the first letter is upper case. ***/
		className = className.substring(0, 1).toUpperCase() + className.substring(1);
		return className;
	}

	private Writer getWriter(String className) throws IOException {
		File file = getFile(className);
		// show generated data
		Writer out = new FileWriter(file);
		return out;
	}

	private File getFile(String className) throws IOException {
		
		String beanPath = System.getProperty("user.dir");
		beanPath += ConfigInfo.isMavenProject ? "/src/main/java/" : "/src/"; 
		beanPath += ConfigInfo.EntityPackage.replace(".", "/")+ "/";
		
		File filePath = new File(beanPath);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		String filePathOfBean = beanPath + "/" + className + ".java";
		File file = new File(filePathOfBean);
		if (!file.exists()) {
			file.createNewFile();
		}
		LogUtil.i("outFile: "+file.getAbsolutePath());
		return file;
	}
	
	

}
