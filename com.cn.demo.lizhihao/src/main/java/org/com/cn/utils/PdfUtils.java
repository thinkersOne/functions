/*
 * 版权所有 © 成都太阳高科技有限责任公司
 * http://www.suncd.com
 */
package org.com.cn.utils;

import com.lowagie.text.pdf.BaseFont;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.Map;

/**
 * 功能：pdf处理工具类
 *
 * @author qust
 * @version 1.0 2018/2/23 17:21
 */
public class PdfUtils {
    private PdfUtils() {
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(PdfUtils.class);

    /**
     * 按模板和参数生成html字符串,再转换为flying-saucer识别的Document
     *
     * @param templateName freemarker模板名称
     * @param variables    freemarker模板参数
     * @return Document
     */
    public static Document generateDoc(String templateName, Map<String, Object> variables)  {
        Template tp;
        try {
            tp = TemplateFactory.getTemplateByName(templateName);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
        StringWriter stringWriter = new StringWriter();
        try(BufferedWriter writer = new BufferedWriter(stringWriter)) {
            try {
                tp.process(variables, writer);
                writer.flush();
            } catch (TemplateException e) {
                LOGGER.error("模板不存在或者路径错误", e);
            } catch (IOException e) {
                LOGGER.error("IO异常", e);
            }
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            return builder.parse(new ByteArrayInputStream(stringWriter.toString().getBytes()));
        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public static Document generateDoc(String templateName, Object object)  {
        Template tp;
        try {
            tp = TemplateFactory.getTemplateByName(templateName);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
        StringWriter stringWriter = new StringWriter();
        try(BufferedWriter writer = new BufferedWriter(stringWriter)) {
            try {
                tp.process(object, writer);
                writer.flush();
            } catch (TemplateException e) {
                LOGGER.error("模板不存在或者路径错误", e);
            } catch (IOException e) {
                LOGGER.error("IO异常", e);
            }
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            return builder.parse(new ByteArrayInputStream(stringWriter.toString().getBytes()));
        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 核心: 根据freemarker模板生成pdf文档
     *
     * @param templateName freemarker模板名称
     * @param out          输出流
     * @throws Exception 模板无法找到、模板语法错误、IO异常
     */
    private static void generateAll( String templateName, OutputStream out,Map<String, Object> map) throws Exception {
        ITextRenderer renderer = new ITextRenderer();
        Document doc = generateDoc(templateName,map);
        renderer.setDocument(doc, null);
        //设置字符集(宋体),此处必须与模板中的<body style="font-family: SimSun">一致,区分大小写,不能写成汉字"宋体"
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont("font/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        //展现和输出pdf
        renderer.layout();
        renderer.createPDF(out, false);
//        Document docAppend = generateDoc(templateName,map);
//        renderer.setDocument(docAppend, null);
//        renderer.writeNextDocument(); //写下一个pdf页面
        renderer.finishPDF(); //完成pdf写入
    }

    private static void generateAll( String templateName, OutputStream out,Object object) throws Exception {
        ITextRenderer renderer = new ITextRenderer();
        Document doc = generateDoc(templateName,object);
        renderer.setDocument(doc, null);
        //设置字符集(宋体),此处必须与模板中的<body style="font-family: SimSun">一致,区分大小写,不能写成汉字"宋体"
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont("font/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        //展现和输出pdf
        renderer.layout();
        renderer.createPDF(out, false);
//        Document docAppend = generateDoc(templateName,map);
//        renderer.setDocument(docAppend, null);
//        renderer.writeNextDocument(); //写下一个pdf页面
        renderer.finishPDF(); //完成pdf写入
    }

    /**
     * pdf下载
     *
     * @param templateName freemarker模板名称(带后缀.ftl)
     * @param response     HttpServletResponse
     * @param fileName     下载文件名称(带文件扩展名后缀)
     */
    public static void download(String templateName, Map<String, Object> map, HttpServletResponse response, String fileName) {
        // 设置编码、文件ContentType类型、文件头、下载文件名
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        try {
            response.setHeader("Content-Disposition", "attachment;fileName=" +
                    new String(fileName.getBytes("gb2312"), "ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
        }
        ServletOutputStream out = null;
        try{
            out = response.getOutputStream();
            generateAll(templateName, out, map);
            out.flush();

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public static void download(String templateName, Object object, HttpServletResponse response, String fileName) {
        // 设置编码、文件ContentType类型、文件头、下载文件名
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        try {
            response.setHeader("Content-Disposition", "attachment;fileName=" +
                    new String(fileName.getBytes("gb2312"), "ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
        }
        ServletOutputStream out = null;
        try{
            out = response.getOutputStream();
            generateAll(templateName, out, object);
            out.flush();

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * pdf预览
     *
     * @param templateName freemarker模板名称(带后缀.ftl)
     * @param response     HttpServletResponse
     */
    public static void preview(String templateName,Map<String, Object> map, HttpServletResponse response) {
        try (ServletOutputStream out = response.getOutputStream()) {
            generateAll(templateName, out, map);
            out.flush();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
