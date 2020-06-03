/*
 * 版权所有 © 成都太阳高科技有限责任公司
 * http://www.suncd.com
 */
package org.com.cn.utils;

import com.alibaba.fastjson.JSON;
import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
import com.lowagie.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfReader;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.com.cn.model.GeneratePdfModel;
import org.com.cn.pdf.ITextRenderer3;
import org.com.cn.pdf.PageEvent;
import org.com.cn.pdf.RenderCustomListener;
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
import java.util.ArrayList;
import java.util.List;
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
//        ITextRenderer renderer = new ITextRenderer();
        ITextRenderer3 renderer = new ITextRenderer3();
        Document doc = generateDoc(templateName,map);
        renderer.setDocument(doc, null);
        //设置字符集(宋体),此处必须与模板中的<body style="font-family: SimSun">一致,区分大小写,不能写成汉字"宋体"
        ITextFontResolver fontResolver = renderer.getFontResolver();
//        BaseFont font = BaseFont.createFont("font/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        fontResolver.addFont("font/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        PageEvent pageEvent = new PageEvent();
        renderer.setPdfPageEvent(pageEvent);
        //展现和输出pdf
        renderer.layout();
        renderer.createPDF(out, false);
//        Document docAppend = generateDoc(templateName,map);
//        renderer.setDocument(docAppend, null);
//        renderer.writeNextDocument(); //写下一个pdf页面
        renderer.finishPDF(); //完成pdf写入
        PdfPageEvent pdfPageEvent = renderer.getPdfPageEvent();
        System.out.println(JSON.toJSONString(pdfPageEvent));
    }

    private static GeneratePdfModel generateAll(String templateName, Object object,List<String> primaryKeys) throws Exception {
        byte[] fileBytes = null;
        //文件输出流
        ByteArrayOutputStream byteArrayOutputStream = null;
        ITextRenderer renderer = new ITextRenderer();
        Document doc = generateDoc(templateName,object);
        renderer.setDocument(doc, null);
        //字节
        byteArrayOutputStream = new ByteArrayOutputStream();
        //设置字符集(宋体),此处必须与模板中的<body style="font-family: SimSun">一致,区分大小写,不能写成汉字"宋体"
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont("font/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        //展现和输出pdf
        renderer.layout();
        renderer.createPDF(byteArrayOutputStream);
        renderer.finishPDF(); //完成pdf写入
        fileBytes = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return getGeneratePdfModel(fileBytes,primaryKeys);
    }

    /**
     * 获取 文件流 及 坐标 、页数 等相关信息
     * @param fileBytes
     * @return
     * @throws IOException
     */
    public static GeneratePdfModel getGeneratePdfModel(byte[] fileBytes,List<String> primaryKeys) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(fileBytes);
        PdfReader pdfReader = new PdfReader(fileBytes);
        //新建一个PDF解析对象
        PdfReaderContentParser parser = new PdfReaderContentParser(pdfReader);
        List<Rectangle2D.Float> floatList = new ArrayList<>(2);
        //包含了PDF页面的信息，作为处理的对象
        for(int i = 1;i <= pdfReader.getNumberOfPages();i++){
            //新建一个ImageRenderListener对象，该对象实现了RenderListener接口，作为处理PDF的主要类
            RenderCustomListener listener = new RenderCustomListener();
            //解析PDF，并处理里面的文字
            parser.processContent(i, listener);
            List<Map<String,Rectangle2D.Float>> list_text = listener.rows_text_rect;
            for(int k = 0;k < list_text.size();k++){
                Map<String,Rectangle2D.Float> map = list_text.get(k);
                for(Map.Entry<String, Rectangle2D.Float>entry:map.entrySet()){
                    if(primaryKeys.contains(entry.getKey())){
                        floatList.add(entry.getValue());
                    }
                    System.out.println(entry.getKey()+"---"+entry.getValue());
                }
            }
        }
        GeneratePdfModel generatePdfModel = new GeneratePdfModel(inputStream,floatList,pdfReader.getNumberOfPages());
        return generatePdfModel;
    }

    /**
     * 根据关键字返回对应的坐标
     * @param keyWords
     * @param pdfReader
     * @return
     */
    private static List<List<float[]>> findKeywords(final List<String> keyWords, PdfReader pdfReader) {
        if (keyWords == null || keyWords.size() == 0) {
            return null;
        }
        int pageNum = pdfReader.getNumberOfPages();
        final List<List<float[]>> arrayLists = new ArrayList<List<float[]>>(keyWords.size());
        for (int k=0; k<keyWords.size(); k++) {
            List<float[]> positions = new ArrayList<float[]>();
            arrayLists.add(positions);
        }
        PdfReaderContentParser pdfReaderContentParser = new PdfReaderContentParser(pdfReader);
        try {
            for (int i = 1; i <= pageNum; i++) {
                final int finalI = i;
                pdfReaderContentParser.processContent(i, new RenderListener() {
                    private StringBuilder pdfsb = new StringBuilder();
                    private float yy = -1f;
                    @Override
                    public void renderText(TextRenderInfo textRenderInfo) {
                        String text = textRenderInfo.getText();
                        com.itextpdf.awt.geom.Rectangle2D.Float boundingRectange =
                                textRenderInfo.getBaseline().getBoundingRectange();
                        if(yy == -1f) {
                            yy = boundingRectange.y;
                        }
                        if(yy != boundingRectange.y) {
                            yy = boundingRectange.y;
                            pdfsb.setLength(0);
                        }
                        pdfsb.append(text);
                        if (pdfsb.length()>0) {
                            for (int j=0; j<keyWords.size(); j++) {
                                List<String> key_words = ListUtils.parseList
                                        (keyWords.get(j), ",");
                                //假如配置了多个关键字，找到一个就跑
                                for (final String key_word : key_words) {
                                    if (arrayLists.get(j) != null)
                                    {
                                        break;
                                    }
                                    if (pdfsb.length()>0 && pdfsb.toString
                                            ().contains(key_word)) {
                                        float[] resu = new float[3];
                                        resu[0] = boundingRectange.x +
                                                boundingRectange.width * (key_word.length()-1);
                                        resu[1] = boundingRectange.y;
                                        resu[2] = finalI;
                                        arrayLists.get(j).add(resu);
                                        pdfsb.setLength(0);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    @Override
                    public void renderImage(ImageRenderInfo arg0) {
                        //renderImage
                    }
                    @Override
                    public void endTextBlock() {
                        //endTextBlock
                    }
                    @Override
                    public void beginTextBlock() {
                        //beginTextBlock
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayLists;
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

//    public static void download(String templateName, Object object, HttpServletResponse response, String fileName) {
//        // 设置编码、文件ContentType类型、文件头、下载文件名
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("multipart/form-data");
//        try {
//            response.setHeader("Content-Disposition", "attachment;fileName=" +
//                    new String(fileName.getBytes("gb2312"), "ISO8859-1"));
//        } catch (UnsupportedEncodingException e) {
//            LOGGER.error(e.getMessage(), e);
//        }
//        ServletOutputStream out = null;
//        try{
//            out = response.getOutputStream();
//            generateAll(templateName, out, object);
//            out.flush();
//
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage(), e);
//        }
//    }

    public static GeneratePdfModel download(String templateName, Object object,List<String> primaryKeys) {
        try{
            return generateAll(templateName, object,primaryKeys);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
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
