package cn.chenlh.common;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
/**
 * 
 * @author changlie
 *
 */
public class FreeMarkerUtil {
    
    private static FreeMarkerUtil instance;
    private Configuration config;
    
    
    private FreeMarkerUtil() {
    	try {
	    	config = new Configuration(new Version("2.3.23"));
	    	StringTemplateLoader templateLoader = getTemplateLoader();
	    	config.setTemplateLoader(templateLoader);
	    	
//	    	config.setDirectoryForTemplateLoading(new File(getTemplateDir()));
    	} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    private StringTemplateLoader getTemplateLoader() {
    	StringTemplateLoader templateLoader = new StringTemplateLoader();
    	
    	String customPath = ConfigInfo.templateDir;
    	scanAndLoadTemplate(templateLoader, customPath);
    	
    	return templateLoader;
	}

	private void scanAndLoadTemplate(StringTemplateLoader templateLoader, String customPath) {
		try {
    		URL url = getClass().getClassLoader().getResource(customPath);
    		JarURLConnection jarURLConnection  = (JarURLConnection )url.openConnection();
    		JarFile jarFile = jarURLConnection.getJarFile();
    		Enumeration<JarEntry> entries = jarFile.entries();
    		
    		while (entries.hasMoreElements()) {
				JarEntry jarEntry = (JarEntry) entries.nextElement();
				String name = jarEntry.getName();
				if(name.contains(customPath) && !name.equals(customPath+"/") && name.endsWith(".ftl")) {
					int start = name.lastIndexOf("/")+1;
					int endIndex = name.lastIndexOf('.');
					String templateKey = name.substring(start, endIndex);
					String templateKeyWithSuffix = name.substring(start);
					
					LogUtil.i("加载模板： "+templateKey+"=>"+name);
					LogUtil.i("模板resource: "+getClass().getResource("/"+name));
					
					String templateSource = getTemplateSource("/"+name);
			    	templateLoader.putTemplate(templateKey, templateSource);
			    	templateLoader.putTemplate(templateKeyWithSuffix, templateSource);
				}
			}
    		
		} catch (IOException e) {
			LogUtil.i("遍历  加载  template 文件失败！");
			e.printStackTrace();
		}
	}

	private String getTemplateSource(String templateFileName) {
    	InputStream inputStream = null;
    	BufferedReader reader = null; 
    	StringBuilder sb = new StringBuilder(); 
    	try {
    		inputStream = getClass().getResourceAsStream(templateFileName);
    		reader = new BufferedReader(new InputStreamReader(inputStream));
    		String line = null;
    		while ((line = reader.readLine()) != null) {
    			sb.append(line).append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.i(" templateFile "+templateFileName+" 获取失败！ ");
		}finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				LogUtil.i(" 获取 templateFile "+templateFileName+" 关闭流失败！ ");
			}
		}
    	return sb.toString();
	}
    
    public String test(String templateName, Map<String, Object> props) {
    	StringWriter out = new StringWriter();
    	Template template = null;
    	try {
			template = config.getTemplate(templateName);
		} catch (Exception e) {
			LogUtil.i("获取  模板："+templateName+"失败！");
			e.printStackTrace();
		}
    	try {
			template.process(props, out);
			out.flush();
			System.out.println(out.getBuffer().toString());
			return out.getBuffer().toString();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	return "nothing";
    }

    
    /**
     * instance FreeMarkerUtil
     * @return FreeMarkerUtil or null
     */
    public static FreeMarkerUtil instance() {
        if (instance == null) {
            instance = new FreeMarkerUtil();
        }
        return instance;
    }
    
    
    /**
     * 通过模板文件生成字符串
     * @param templateFileName 模板文件名
     * @param propMap 数据源
     * @return format string
     */
    public String geneFileStr(String templateFileName, Map<String, Object> propMap) {
        StringWriter out = new StringWriter();
        Template tmp;
        try {
            tmp = this.config.getTemplate(templateFileName,"UTF-8");
            tmp.process(propMap, out);
            tmp.setAutoFlush(true);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        return out.getBuffer().toString();
    }
    
    

	public void generateFile(String templateFileName, Map<String, Object> propMap, Writer out) {
		Template tmp;
        try {
            tmp = this.config.getTemplate(templateFileName,"UTF-8");
            tmp.process(propMap, out);
            tmp.setAutoFlush(true);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
	
}
