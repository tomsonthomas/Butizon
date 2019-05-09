package com.venturetech.venture.butizon.Model;

public class Model_shedule {
    String day,optime,cltime,id,shopid;


    public Model_shedule(String id,String day, String optime, String cltime) {
        this.id = id;
        this.day = day;
        this.optime = optime;
        this.cltime = cltime;
    }
    public Model_shedule(String day, String optime, String cltime) {
        this.day = day;
        this.optime = optime;
        this.cltime = cltime;
    }

    public Model_shedule(String id,String shopid, String day, String optime, String cltime) {
        this.day = day;
        this.optime = optime;
        this.cltime = cltime;
        this.id = id;
        this.shopid = shopid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getCltime() {
        return cltime;
    }

    public void setCltime(String cltime) {
        this.cltime = cltime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getOptime() {
        return optime;
    }

    public void setOptime(String optime) {
        this.optime = optime;
    }
}
