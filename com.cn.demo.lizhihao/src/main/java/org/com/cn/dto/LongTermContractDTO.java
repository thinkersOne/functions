package org.com.cn.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 长期合同模板入参
 */
public class LongTermContractDTO {
    private String purchaserCompanyName;
    private String sellerCompanyName;
    private String idCardName;
    private String vehicleLicense;
    private String vehicleTypeName;
    private String startYear;
    private String startMonth;
    private String startDay;
    private String endYear;
    private String endMonth;
    private String endDay;
    private List<LineAmountDTO> lineAmountDTOList;
    private BigDecimal deposits;
    private String sellerBankName;
    private String sellerBankAccount;
    private String purchaserPhone;
    private String signTime;


    public String getPurchaserCompanyName() {
        return purchaserCompanyName;
    }

    public void setPurchaserCompanyName(String purchaserCompanyName) {
        this.purchaserCompanyName = purchaserCompanyName;
    }

    public String getSellerCompanyName() {
        return sellerCompanyName;
    }

    public void setSellerCompanyName(String sellerCompanyName) {
        this.sellerCompanyName = sellerCompanyName;
    }

    public String getIdCardName() {
        return idCardName;
    }

    public void setIdCardName(String idCardName) {
        this.idCardName = idCardName;
    }

    public String getVehicleLicense() {
        return vehicleLicense;
    }

    public void setVehicleLicense(String vehicleLicense) {
        this.vehicleLicense = vehicleLicense;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public List<LineAmountDTO> getLineAmountDTOList() {
        return lineAmountDTOList;
    }

    public void setLineAmountDTOList(List<LineAmountDTO> lineAmountDTOList) {
        this.lineAmountDTOList = lineAmountDTOList;
    }

    public BigDecimal getDeposits() {
        return deposits;
    }

    public void setDeposits(BigDecimal deposits) {
        this.deposits = deposits;
    }

    public String getSellerBankName() {
        return sellerBankName;
    }

    public void setSellerBankName(String sellerBankName) {
        this.sellerBankName = sellerBankName;
    }

    public String getSellerBankAccount() {
        return sellerBankAccount;
    }

    public void setSellerBankAccount(String sellerBankAccount) {
        this.sellerBankAccount = sellerBankAccount;
    }

    public String getPurchaserPhone() {
        return purchaserPhone;
    }

    public void setPurchaserPhone(String purchaserPhone) {
        this.purchaserPhone = purchaserPhone;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }
}
