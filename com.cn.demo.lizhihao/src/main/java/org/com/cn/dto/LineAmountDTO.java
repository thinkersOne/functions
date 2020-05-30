package org.com.cn.dto;

import java.math.BigDecimal;

public class LineAmountDTO {
    private String lineName;
    private String vehicleTypeName;
    private Integer portCount;
    private BigDecimal settleDistance;
    private Integer runtimeMinute;
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
