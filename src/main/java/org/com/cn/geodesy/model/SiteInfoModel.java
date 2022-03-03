package org.com.cn.geodesy.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 揽收点、配送点
 * 每个站点信息
 * 地址 经纬度
 * 货物总体积
 * 始发地 货物体积 为0
 */
public class SiteInfoModel {
    /**
     * 经度
     */
    private Double mLatitude;
    /**
     * 纬度
     */
    private Double mLongitude;
    /**
     * 货物总体积
     */
    private BigDecimal volume;
    /**
     * 商品数量
     */
    private Integer productCount;

    public SiteInfoModel() {
    }

    public SiteInfoModel(Double mLatitude, Double mLongitude) {
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
    }

    public SiteInfoModel(Double mLatitude, Double mLongitude, BigDecimal volume) {
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
        this.volume = volume;
    }

    public SiteInfoModel(Double mLatitude, Double mLongitude, BigDecimal volume, Integer productCount) {
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
        this.volume = volume;
        this.productCount = productCount;
    }

    public SiteInfoModel(Double mLatitude, Double mLongitude, Integer productCount) {
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
        this.productCount = productCount;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Double getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(Double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public Double getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(Double mLongitude) {
        this.mLongitude = mLongitude;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SiteInfoModel)) return false;
        SiteInfoModel that = (SiteInfoModel) o;
        return mLatitude.equals(that.mLatitude) &&
                mLongitude.equals(that.mLongitude) &&
                volume.equals(that.volume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mLatitude, mLongitude, volume);
    }

    @Override
    public String toString() {
        return "SiteInfoModel{" +
                "mLatitude=" + mLatitude +
                ", mLongitude=" + mLongitude +
                ", volume=" + volume +
                '}';
    }
}
