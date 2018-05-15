package cn.chenlh.template;


import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.Map;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

/**
 * 
 * <b>
 *    FreeMarkerUtil
 * </b>
 * @author kangxu
 *
 */
public class FreeMarkerUtil {
    
    private static FreeMarkerUtil instance;
    private Configuration config;
    public static String templateDir = null;
    
    
    private FreeMarkerUtil() {
    	try {
	    	config = new Configuration(new Version("2.3.23"));
	    	config.setDirectoryForTemplateLoading(new File(getTemplateDir()));
    	} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public static String getTemplateDir() {
    	if(templateDir==null) {
    		URL url = FreeMarkerUtil.class.getResource("");
    		templateDir = url.getPath();
    	}
    	System.out.println("template dir: "+templateDir);
		return templateDir;
	}
    
    /**
     * instance FreeMarkerUtil
     * @return
     */
    public static FreeMarkerUtil instance() {
        if (instance == null) {
            instance = new FreeMarkerUtil();
        }
        return instance;
    }
    
    
    /**
     * 通过模板文件生成字符串
     * @param request
     * @param templateFileName
     * @param propMap
     * @return
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
