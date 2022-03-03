package org.com.cn.controller;

import com.liumapp.qtools.file.base64.Base64FileTool;
import freemarker.template.TemplateException;
import org.com.cn.dto.LineAmountDTO;
import org.com.cn.dto.LongTermContractDTO;
import org.com.cn.dto.ShortTermContractDTO;
import org.com.cn.model.GeneratePdfModel;
import org.com.cn.utils.DateTools;
import org.com.cn.utils.PdfUtils;
import org.com.cn.utils.TemplateFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * @copyright：上海云砺信息科技有限公司
 * @author: lizhihao
 * @projectname: hera-parent
 * @date: 2018/10/26 9:52
 */
@RestController
public class PdfController {

    /**
     * HTML预览
     */
    @RequestMapping(value = "previewHtmlLong")
    public String previewHtmlLong() throws IOException, TemplateException {
        LongTermContractDTO longTermContractDTO = generatorPDFObjLong();
        String s = TemplateFactory.generateHtmlFromFtl("long_term_pdf.ftl", longTermContractDTO);
        return s;
    }

    /**
     * HTML预览
     */
    @RequestMapping(value = "previewHtmlShort")
    public String previewHtmlShort() throws IOException, TemplateException {
        Map<String, Object> map = generatorPDF();
        String s = TemplateFactory.generateHtmlFromFtl("short_term_pdf.ftl", map);
        return s;
    }

    @RequestMapping(value = "previewHtmlObj")
    public String previewHtmlObj() throws IOException, TemplateException {
        ShortTermContractDTO shortTermContractDTO = generatorPDFObj();
        String s = TemplateFactory.generateHtmlFromFtl("short_term_pdf.ftl", shortTermContractDTO);
        return s;
    }

    /**
     * pdf下载
     * 9
     */
    @RequestMapping(value = "downloadContractLong", method = RequestMethod.GET)
    public String downloadContractLongPDF(HttpServletResponse response) {
        LongTermContractDTO longTermContractDTO = generatorPDFObjLong();
        String pdfName = "contract" + DateTools.getTime14() + ".pdf";
        PdfUtils.download("long_term_pdf.ftl", longTermContractDTO, response, pdfName);
        return "下载成功!";
    }

    /**
     * pdf下载
     * 9
     */
    @RequestMapping(value = "downloadContract", method = RequestMethod.GET)
    public String downloadContractPDF(HttpServletResponse response) {
        Map<String, Object> map = generatorPDF();
        String pdfName = "contract" + DateTools.getTime14() + ".pdf";
        PdfUtils.download("short_term_pdf.ftl", map, response, pdfName);
        return "下载成功!";
    }

    @RequestMapping(value = "downloadContractObj", method = RequestMethod.GET)
    public String downloadContractObj() throws IOException {
        ShortTermContractDTO shortTermContractDTO = generatorPDFObj();
        List<String> primaryKeys = new ArrayList<>(2);
        primaryKeys.add("甲方（盖章）：");
        primaryKeys.add("乙方（签字）：");
        GeneratePdfModel download = PdfUtils.download("short_term_pdf.ftl", shortTermContractDTO,primaryKeys);
        String baseStr = Base64FileTool.inputStreamToBase64(download.getInputStream());
        System.out.println(baseStr);
        return "下载成功!";
    }


    //组装map数据并生成HTM返回
    public Map<String, Object> generatorPDF() {
        //开始组装数据并返回html
        Map<String, Object> map = new HashMap<>();
        map.put("purchaserCompanyName", "上海城驹供应链管理有限公司");
        map.put("sellerCompanyName", "小李");
        map.put("idCardName", "420684122222222");
        map.put("vehicleLicense", "8888888");
        map.put("vehicleTypeName", "雅马哈");
        map.put("startYear", "2012");
        map.put("startMonth", "2");
        map.put("startDay", "2");
        map.put("endYear", "2020");
        map.put("endMonth", "5");
        map.put("endDay", "23");
//        表格内容
        List<LineAmountDTO> lineAmountDTOList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            LineAmountDTO lineAmountDTO = new LineAmountDTO();
            lineAmountDTO.setLineName("测试线路" + (i + 1));
            lineAmountDTO.setVehicleTypeName("测试车型" + (i + 1));
            lineAmountDTO.setAmount(new BigDecimal(2000));
            lineAmountDTO.setPortCount(i + 2);
            lineAmountDTO.setRuntimeMinute(i + 300);
            lineAmountDTO.setSettleDistance(new BigDecimal(230.25 + i));
            lineAmountDTOList.add(lineAmountDTO);
        }
        map.put("lineAmountDTOList", lineAmountDTOList);
        map.put("deposits", 3000);
        map.put("sellerBankName", "中国银行");
        map.put("sellerBankAccount", "222222222222222");
        map.put("purchaserPhone", "13585865081");
        map.put("sellerPhone", "17621483873");
        map.put("signTime", "20200529");
        return map;
    }

    public ShortTermContractDTO generatorPDFObj() {
        //开始组装数据并返回html
        ShortTermContractDTO shortTermContractDTO = new ShortTermContractDTO();
        shortTermContractDTO.setPurchaserCompanyName("上海城驹供应链管理有限公司");
        shortTermContractDTO.setSellerCompanyName("小李");
        shortTermContractDTO.setIdCardName("420684122222222");
        shortTermContractDTO.setVehicleLicense("8888888");
        shortTermContractDTO.setVehicleTypeName("雅马哈");
        shortTermContractDTO.setStartYear("2012");
        shortTermContractDTO.setStartMonth("2");
        shortTermContractDTO.setStartDay("2");
        shortTermContractDTO.setEndYear("2020");
        shortTermContractDTO.setEndMonth("5");
        shortTermContractDTO.setEndDay("23");
//        表格内容
        List<LineAmountDTO> lineAmountDTOList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            LineAmountDTO lineAmountDTO = new LineAmountDTO();
            lineAmountDTO.setLineName("测试线路" + (i + 1));
            lineAmountDTO.setVehicleTypeName("测试车型" + (i + 1));
            lineAmountDTO.setAmount(new BigDecimal(2000));
            lineAmountDTO.setPortCount(i + 2);
            lineAmountDTO.setRuntimeMinute(i + 300);
            lineAmountDTO.setSettleDistance(new BigDecimal(230.25 + i));
            lineAmountDTOList.add(lineAmountDTO);
        }
        shortTermContractDTO.setLineAmountDTOList(lineAmountDTOList);
        shortTermContractDTO.setDeposits(new BigDecimal(3000));
        shortTermContractDTO.setSellerBankName("中国银行");
        shortTermContractDTO.setSellerBankAccount("222222222222222");
        shortTermContractDTO.setPurchaserPhone("13585865081");
        shortTermContractDTO.setSignTime("20200529");
        return shortTermContractDTO;
    }

    public LongTermContractDTO generatorPDFObjLong() {
        //开始组装数据并返回html
        LongTermContractDTO longTermContractDTO = new LongTermContractDTO();
        longTermContractDTO.setPurchaserCompanyName("上海城驹供应链管理有限公司");
        longTermContractDTO.setSellerCompanyName("小李");
        longTermContractDTO.setIdCardName("420684122222222");
        longTermContractDTO.setProjectName("测试项目");
        longTermContractDTO.setFuelCardRatio(new BigDecimal(40));
        longTermContractDTO.setEnableTaxPoint(true);
        longTermContractDTO.setTaxPoint("是");
        longTermContractDTO.setSettlementCycle(30);
        longTermContractDTO.setVehicleLicense("8888888");
        longTermContractDTO.setVehicleTypeName("雅马哈");
        longTermContractDTO.setStartYear("2012");
        longTermContractDTO.setStartMonth("2");
        longTermContractDTO.setStartDay("2");
        longTermContractDTO.setEndYear("2020");
        longTermContractDTO.setEndMonth("5");
        longTermContractDTO.setEndDay("23");
//        表格内容
        List<LineAmountDTO> lineAmountDTOList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            LineAmountDTO lineAmountDTO = new LineAmountDTO();
            lineAmountDTO.setLineName("测试线路" + (i + 1));
            lineAmountDTO.setVehicleTypeName("测试车型" + (i + 1));
            lineAmountDTO.setAmount(new BigDecimal(2000));
            lineAmountDTO.setPortCount(i + 2);
            lineAmountDTO.setRuntimeMinute(i + 300);
            lineAmountDTO.setSettleDistance(new BigDecimal(230.25 + i));
            lineAmountDTOList.add(lineAmountDTO);
        }
        longTermContractDTO.setLineAmountDTOList(lineAmountDTOList);
        longTermContractDTO.setDeposits(new BigDecimal(3000));
        longTermContractDTO.setSellerBankName("中国银行");
        longTermContractDTO.setSellerBankAccount("222222222222222");
        longTermContractDTO.setSellerTaxNo("49449444444444444");
        longTermContractDTO.setSellerAddress("上海浦东");
        longTermContractDTO.setSellerPhone("13585865081");
        longTermContractDTO.setSignTimeYear("2012");
        longTermContractDTO.setSignTimeMonth("2");
        longTermContractDTO.setSignTimeDay("2");
        return longTermContractDTO;
    }


}
