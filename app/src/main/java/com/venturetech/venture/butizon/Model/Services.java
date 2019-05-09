package com.venturetech.venture.butizon.Model;

public class Services  {
    String Id,Shopid,Servicename,Rate;
    public  Services(String id,String shopid,String name,String rate){
        this.Id = id;
        this.Shopid = shopid;
        this.Servicename = name;
        this.Rate =rate;
    }

    public String getId() {
        return Id;
    }

    public String getRate() {
        return Rate;
    }

    public String getServicename() {
        return Servicename;
    }

    public String getShopid() {
        return Shopid;
    }

}
