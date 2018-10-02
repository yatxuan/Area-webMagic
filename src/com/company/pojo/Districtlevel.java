package com.company.pojo;

/**
 * 县级
 * @author Administrator
 */
public class Districtlevel {
    private int id;
    private String districtLevelName;
    private int toCityId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistrictLevelName() {
        return districtLevelName;
    }

    public void setDistrictLevelName(String districtLevelName) {
        this.districtLevelName = districtLevelName;
    }

    public int getToCityId() {
        return toCityId;
    }

    public void setToCityId(int toCityId) {
        this.toCityId = toCityId;
    }
}
