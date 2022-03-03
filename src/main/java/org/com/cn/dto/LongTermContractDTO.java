package org.com.cn.dto;

import java.math.BigDecimal;
import java.util.List;

public class LongTermContractDTO {
    /**
     * 甲方
     */
    private String purchaserCompanyName;
    /**
     * 乙方
     */
    private String sellerCompanyName;
    /**
     * 乙方身份证号
     */
    private String idCardName;
    /**
     * 项目名
     */
    private String projectName;
    /**
     * 油卡支付
     */
    private BigDecimal fuelCardRatio;
    /**
     * 开票方式   根据 是否选择了  是否开票税点 来判断
     */
    private Boolean enableTaxPoint;
    /**
     * 开票税点
     */
    private String taxPoint;
    /**
     * 运费账期  即  结算周期
     */
    private Integer settlementCycle;

    private String vehicleLicense;
    private String vehicleTypeName;
    /**
     * 合作期限  合同 起止 日期
     */
    private String startYear;
    private String startMonth;
    private String startDay;
    private String endYear;
    private String endMonth;
    private String endDay;
    /**
     * 附表一  线路信息
     */
    private List<LineAmountDTO> lineAmountDTOList;
    /**
     * 保证金
     */
    private BigDecimal deposits;
    /**
     * 乙方银行账号信息
     */
    private String sellerBankName;
    private String sellerBankAccount;
    /**
     * 乙方 纳税人识别号
     */
    private String sellerTaxNo;
    /**
     * 乙方地址
     */
    private String sellerAddress;
    /**
     * 乙方电话
     */
    private String sellerPhone;
    /**
     * 签署日期 ： 以当前时间 来
     */
    private String signTimeYear;
    private String signTimeMonth;
    private String signTimeDay;


    public String getTaxPoint() {
        return taxPoint;
    }

    public void setTaxPoint(String taxPoint) {
        this.taxPoint = taxPoint;
    }

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public BigDecimal getFuelCardRatio() {
        return fuelCardRatio;
    }

    public void setFuelCardRatio(BigDecimal fuelCardRatio) {
        this.fuelCardRatio = fuelCardRatio;
    }

    public Boolean getEnableTaxPoint() {
        return enableTaxPoint;
    }

    public void setEnableTaxPoint(Boolean enableTaxPoint) {
        this.enableTaxPoint = enableTaxPoint;
    }

    public Integer getSettlementCycle() {
        return settlementCycle;
    }

    public void setSettlementCycle(Integer settlementCycle) {
        this.settlementCycle = settlementCycle;
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

    public String getSellerTaxNo() {
        return sellerTaxNo;
    }

    public void setSellerTaxNo(String sellerTaxNo) {
        this.sellerTaxNo = sellerTaxNo;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public String getSignTimeYear() {
        return signTimeYear;
    }

    public void setSignTimeYear(String signTimeYear) {
        this.signTimeYear = signTimeYear;
    }

    public String getSignTimeMonth() {
        return signTimeMonth;
    }

    public void setSignTimeMonth(String signTimeMonth) {
        this.signTimeMonth = signTimeMonth;
    }

    public String getSignTimeDay() {
        return signTimeDay;
    }

    public void setSignTimeDay(String signTimeDay) {
        this.signTimeDay = signTimeDay;
    }
}
