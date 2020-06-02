package org.com.cn.dto;

import java.math.BigDecimal;

public class LineAmountDTO {
    /**
     * 线路名
     */
    private String lineName;
    /**
     * 车型名
     */
    private String vehicleTypeName;
    /**
     * 运输点数
     */
    private Integer portCount;
    /**
     * 结算里程
     */
    private BigDecimal settleDistance;
    /**
     * 时效数
     */
    private Integer runtimeMinute;
    /**
     * 结算价格
     */
    private BigDecimal amount;

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public Integer getPortCount() {
        return portCount;
    }

    public void setPortCount(Integer portCount) {
        this.portCount = portCount;
    }

    public BigDecimal getSettleDistance() {
        return settleDistance;
    }

    public void setSettleDistance(BigDecimal settleDistance) {
        this.settleDistance = settleDistance;
    }

    public Integer getRuntimeMinute() {
        return runtimeMinute;
    }

    public void setRuntimeMinute(Integer runtimeMinute) {
        this.runtimeMinute = runtimeMinute;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
