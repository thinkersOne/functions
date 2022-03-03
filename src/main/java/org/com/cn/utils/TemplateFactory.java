package org.com.cn.utils;

import com.lowagie.text.pdf.BaseFont;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称: 票易通ZEUS
 * 模块名称:
 * 说明:
 * JDK 版本: JDK1.8
 * 作者：yangtao
 * 创建日期：2017/3/18.
 */
public class TemplateFactory {
    // 日志记录对象
    private static Logger log = LoggerFactory.getLogger(TemplateFactory.class);
    // 模板文件路径
    private static String templatePath = "/template";
    // 模板文件内容编码
    private static final String ENCODING = "utf-8";
    // 模板生成配置
    private static Configuration conf = new Configuration();
    // 邮件模板缓存池
    private static Map<String, Template> tempMap = new HashMap<String, Template>();

    static {
        // 设置模板文件路径
        conf.setClassForTemplateLoading(TemplateFactory.class, templatePath);
    }

    /**
     * 通过模板文件名称获取指定模板
     *
     * @param name 模板文件名称
     * @return Template
     * @throws IOException
     * @Description:
     */
    public static Template getTemplateByName(String name) throws IOException {
        if (tempMap.containsKey(name)) {
            log.debug("the template is already exist in the map,template name :"
                    + name);
            // 缓存中有该模板直接返回
            return tempMap.get(name);
        }
        // 缓存中没有该模板时，生成新模板并放入缓存中
        Template temp = conf.getTemplate(name);
        tempMap.put(name, temp);
        log.debug("the template is not found  in the map,template name :"
                + name);
        return temp;
    }

    /**
     * 根据指定模板将内容输出到控制台
     *
     * @param name 模板文件名称
     * @param map  与模板内容转换对象
     * @return void
     * @Description:
     */
    public static void outputToConsole(String name, Map<String, String> map) {
        try {
            // 通过Template可以将模板文件输出到相应的流
            Template temp = getTemplateByName(name);
            temp.setEncoding(ENCODING);
            temp.process(map, new PrintWriter(System.out));
        } catch (TemplateException e) {
            log.error(e.toString(), e);
        } catch (IOException e) {
            log.error(e.toString(), e);
        }
    }

    /**
     * 根据指定模板将内容直接输出到文件
     *
     * @param name    模板文件名称
     * @param map     与模板内容转换对象
     * @param outFile 输出的文件绝对路径
     * @return void
     * @Description:
     */
    public static void outputToFile(String name, Map<String, String> map,
                                    String outFile) {
        FileWriter out = null;
        try {
            out = new FileWriter(new File(outFile));
            Template temp = getTemplateByName(name);
            temp.setEncoding(ENCODING);
            temp.process(map, out);
        } catch (IOException e) {
            log.error(e.toString(), e);
        } catch (TemplateException e) {
            log.error(e.toString(), e);
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                log.error(e.toString(), e);
            }
        }
    }

    /**
     * @param name 模板文件的名称
     * @param map  与模板内容转换对象
     * @return String
     * @throws IOException
     * @throws TemplateException
     * @Description:
     */
    public static String generateHtmlFromFtl(String name,
                                             Map<String, Object> map) throws IOException, TemplateException {
        Writer out = new StringWriter(2048);
        Template temp = getTemplateByName(name);
        temp.setEncoding(ENCODING);
        temp.process(map, out);
        return out.toString();
    }


    public static String generateHtmlFromFtl(String name,Object object) throws IOException, TemplateException {
        Writer out = new StringWriter(2048);
        Template temp = getTemplateByName(name);
        temp.setEncoding(ENCODING);
        temp.process(object, out);
        return out.toString();
    }

    /**
     * HTML代码转PDF文档
     *
     * @param htmlString  待转换的HTML代码
     * @param storagePath 保存为PDF文件的路径
     */
    public static void parsePdf(String htmlString, String storagePath) {
        FileOutputStream os = null;
        try {
            File file = new File(storagePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            os = new FileOutputStream(file);
            ITextRenderer renderer = new ITextRenderer();
            //解决中文支持问题
            ITextFontResolver resolver = renderer.getFontResolver();
            renderer.getFontResolver().addFont("font/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            renderer.setDocumentFromString(htmlString);
            // 解决图片的相对路径问题,图片路径必须以file开头
            // renderer.getSharedContext().setBaseURL("file:/");
            renderer.layout();
            renderer.createPDF(os, false);
            renderer.finishPDF();
        } catch (IOException e) {
            log.error(e.getMessage());
        } catch (com.lowagie.text.DocumentException e) {
            log.error(e.getMessage());
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
    }

    public static void parsePdf(String htmlString, HttpServletResponse response) {
//        FileOutputStream os = null;
        ServletOutputStream os = null;
        try {
//            File file = new File(storagePath);
//            if(!file.exists()) {
//                file.createNewFile();
//            }
//            os = new FileOutputStream(file);
            os = response.getOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            //解决中文支持问题
            ITextFontResolver resolver = renderer.getFontResolver();
            renderer.getFontResolver().addFont("font/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            renderer.setDocumentFromString(htmlString);
            // 解决图片的相对路径问题,图片路径必须以file开头
            // renderer.getSharedContext().setBaseURL("file:/");
            renderer.layout();
            renderer.createPDF(os, false);
        } catch (IOException e) {
            log.error(e.getMessage());
        } catch (com.lowagie.text.DocumentException e) {
            log.error(e.getMessage());
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
    }

    //下载方法
    public static void downLoad(HttpServletResponse resp, String allPath, String suffix) {
        resp.setCharacterEncoding("utf-8");
        //这里是一个生成名字的方法，我是用了一个我们自己的公共类，按照日期生成名字
        String name = System.currentTimeMillis() + ""; //根据后缀判断resp的题头文件
        if ("html".equals(suffix)) {
            resp.setContentType("text/html");
        } else if ("doc".equals(suffix)) {
            resp.setContentType("application/msword");
        } else if ("pdf".equals(suffix)) {
            resp.setContentType("application/PDF");
        }
        resp.addHeader("Content-Disposition", "attachment;filename=" + name + suffix);
        //开始下载
        File file = new File(allPath);
        try {
            InputStream fin = new FileInputStream(file);
            ServletOutputStream out = resp.getOutputStream();
            byte[] buffer = new byte[512];
            // 缓冲区
            int bytesToRead = -1;
            // 通过循环将读入的文件的内容输出到浏览器中
            while ((bytesToRead = fin.read(buffer)) != -1) {
                out.write(buffer, 0, bytesToRead);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

//    public static void main(String[] args) throws IOException, TemplateException {
//        Map<String,String> aa=new HashMap<>();
//        aa.put("code","大家好，XXXX");
//        System.out.println(generateHtmlFromFtl("test.ftl",aa));
//    }
}
