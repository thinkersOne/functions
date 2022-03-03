package org.com.cn.pdf;

import com.alibaba.fastjson.JSON;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import java.io.UnsupportedEncodingException;

/**
 * @author lqx
 * @create 2019-05-23 10:23
 */

public class PageEvent extends PdfPageEventHelper {
    /**
     * 总页数
     */
    private Integer pageTotal = 0;


    // 当当前页面开始加载时，触发(页眉)
    @Override
    public void onStartPage(PdfWriter writer, Document document) {
        // TODO Auto-generated method stub
        super.onStartPage(writer, document);
    }

    // 当当前页面初始化完成，切入document之前触发(页脚)
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        Rectangle rect = new Rectangle(0, 38, 50, 50);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_BOTTOM,
                new Phrase(String.format("- %d -", writer.getPageNumber())), (rect.getLeft() + rect.getRight()) / 2,
                rect.getBottom() - 18, 0);
        int pageNumber = document.getPageNumber();
        this.pageTotal = pageNumber;
    }

}
