package org.com.cn.controller;

import freemarker.template.TemplateException;
import org.com.cn.dto.LineAmountDTO;
import org.com.cn.model.Response;
import org.com.cn.utils.DateTools;
import org.com.cn.utils.PdfUtils;
import org.com.cn.utils.TemplateFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = "previewHtml")
    public String previewHtml() throws IOException, TemplateException {
        Response resultResponse = new Response();
        Map<String, Object> map = generatorPDF();
        String s = TemplateFactory.generateHtmlFromFtl("long_term_pdf.ftl", map);
        return s;
    }
    /**
     * pdf下载
     *9
     */
    @RequestMapping(value = "downloadContract", method = RequestMethod.GET)
    public Response downloadContractPDF(HttpServletResponse response){
        Response resultResponse = new Response();
        Map<String, Object> map = generatorPDF();
        String pdfName = "contract"+ DateTools.getTime14()+".pdf";
        PdfUtils.download("long_term_pdf.ftl",map,response,pdfName);
        resultResponse.setCode(Response.OK);
        resultResponse.setMessage("下载成功！");
        return resultResponse;
    }

    //组装map数据并生成HTM返回
    public Map<String,Object> generatorPDF(){
        //开始组装数据并返回html
        Map<String,Object> map = new HashMap<>();
        map.put("purchaserCompanyName","上海城驹供应链管理有限公司");
        map.put("sellerCompanyName","小李");
        map.put("idCardName","420684122222222");
        map.put("vehicleLicense","8888888");
        map.put("vehicleTypeName","雅马哈");
        map.put("startYear","2012");
        map.put("startMonth","2");
        map.put("startDay","2");
        map.put("endYear","2020");
        map.put("endMonth","5");
        map.put("endDay","23");
//        表格内容
        List<LineAmountDTO> lineAmountDTOList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            LineAmountDTO lineAmountDTO = new LineAmountDTO();
            lineAmountDTO.setLineName("测试线路"+(i + 1));
            lineAmountDTO.setVehicleTypeName("测试车型"+(i+1));
            lineAmountDTO.setAmount(new BigDecimal(2000));
            lineAmountDTO.setPortCount(i + 2);
            lineAmountDTO.setRuntimeMinute(i + 300);
            lineAmountDTO.setSettleDistance(new BigDecimal(230.25+i));
            lineAmountDTOList.add(lineAmountDTO);
        }
        map.put("lineAmountDTOList",lineAmountDTOList);
        map.put("deposits",3000);
        map.put("sellerBankName","中国银行");
        map.put("sellerBankAccount","222222222222222");
        map.put("purchaserPhone","13585865081");
        map.put("sellerCompanyName","17621483873");
        map.put("signTime","20200529");
        return map;
    }

}
