package org.com.cn.model;

import java.util.List;

/**
 * 签署人信息
 */
public class Signer {
    private String name;
    private String idcard;
    private String img;
    private String phone;
    private Number gapMargin;
    private List<Area> areas;

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Number getGapMargin() {
        return gapMargin;
    }

    public void setGapMargin(Number gapMargin) {
        this.gapMargin = gapMargin;
    }
}
