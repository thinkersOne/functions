package org.com.cn.fangxinqian.response;

public class GenerateSignResponse {
    private Integer code;
    private String baseStr;

    @Override
    public String toString() {
        return "GenerateSignResponse{" +
                "code=" + code +
                ", baseStr='" + baseStr + '\'' +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getBaseStr() {
        return baseStr;
    }

    public void setBaseStr(String baseStr) {
        this.baseStr = baseStr;
    }
}
