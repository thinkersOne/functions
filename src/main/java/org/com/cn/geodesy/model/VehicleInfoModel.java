package org.com.cn.geodesy.model;

import java.math.BigDecimal;

/**
 * 车辆属性信息
 */
public class VehicleInfoModel {
    /**
     * 时速
     */
    private BigDecimal speedHours;
    /**
     * 载重体积
     */
    private BigDecimal volume;

    public VehicleInfoModel(BigDecimal speedHours, BigDecimal volume) {
        this.speedHours = speedHours;
        this.volume = volume;
    }

    public VehicleInfoModel() {
    }

    public BigDecimal getSpeedHours() {
        return speedHours;
    }

    public void setSpeedHours(BigDecimal speedHours) {
        this.speedHours = speedHours;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }
}
