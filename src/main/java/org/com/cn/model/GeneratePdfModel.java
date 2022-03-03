package org.com.cn.model;

import com.itextpdf.awt.geom.Rectangle2D;

import java.io.InputStream;
import java.util.List;

public class GeneratePdfModel {
    /**
     * pdf 文件输入流
     */
    private InputStream inputStream;
    /**
     * 关键字 坐标集合
     */
    private List<Rectangle2D.Float> floatList;
    /**
     * 总页数
     */
    private Integer pageTotal;


    public GeneratePdfModel(InputStream inputStream, List<Rectangle2D.Float> floatList, Integer pageTotal) {
        this.inputStream = inputStream;
        this.floatList = floatList;
        this.pageTotal = pageTotal;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public List<Rectangle2D.Float> getFloatList() {
        return floatList;
    }

    public void setFloatList(List<Rectangle2D.Float> floatList) {
        this.floatList = floatList;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }
}
