package org.com.cn.geodesy.model;

import java.util.List;
import java.util.Objects;

/**
 * 智能排线 得到 的线路信息
 *
 */
public class LineInfoModel {
    /**
     * 点到点 的距离，从始发地开始算
     */
    private List<Double> pointsDiff;
    /**
     * 线路包含的站点
     * 已经排好线
     * 按照：始发地、目的地1、目的地2、、、、
     */
    private List<SiteInfoModel> siteInfoModels;
    /**
     * 线路 总的商品数量
     */
    private Long productTotalCount;
    /**
     * 线路 总的订单数
     */
    private Integer orderTotalCount;

    public LineInfoModel() {
    }

    public LineInfoModel(List<Double> pointsDiff, List<SiteInfoModel> siteInfoModels) {
        this.pointsDiff = pointsDiff;
        this.siteInfoModels = siteInfoModels;
    }

    public LineInfoModel(List<Double> pointsDiff, List<SiteInfoModel> siteInfoModels, Long productTotalCount, Integer orderTotalCount) {
        this.pointsDiff = pointsDiff;
        this.siteInfoModels = siteInfoModels;
        this.productTotalCount = productTotalCount;
        this.orderTotalCount = orderTotalCount;
    }

    public Long getProductTotalCount() {
        return productTotalCount;
    }

    public void setProductTotalCount(Long productTotalCount) {
        this.productTotalCount = productTotalCount;
    }

    public Integer getOrderTotalCount() {
        return orderTotalCount;
    }

    public void setOrderTotalCount(Integer orderTotalCount) {
        this.orderTotalCount = orderTotalCount;
    }

    public List<Double> getPointsDiff() {
        return pointsDiff;
    }

    public void setPointsDiff(List<Double> pointsDiff) {
        this.pointsDiff = pointsDiff;
    }

    public List<SiteInfoModel> getSiteInfoModels() {
        return siteInfoModels;
    }

    public void setSiteInfoModels(List<SiteInfoModel> siteInfoModels) {
        this.siteInfoModels = siteInfoModels;
    }

    @Override
    public String toString() {
        return "LineInfoModel{" +
                "pointsDiff=" + pointsDiff +
                ", siteInfoModels=" + siteInfoModels +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LineInfoModel)) return false;
        LineInfoModel that = (LineInfoModel) o;
        return pointsDiff.equals(that.pointsDiff) &&
                siteInfoModels.equals(that.siteInfoModels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointsDiff, siteInfoModels);
    }
}
