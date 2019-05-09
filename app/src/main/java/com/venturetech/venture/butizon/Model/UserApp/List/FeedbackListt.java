package com.venturetech.venture.butizon.Model.UserApp.List;

import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

public class FeedbackListt {

    String userid,shopid,message,club_name,user_name,servicename;

    public FeedbackListt(String userid, String shopid, String message, String club_name) {
        this.userid = userid;
        this.shopid = shopid;
        this.message = message;
        this.club_name = club_name;
        this.user_name = DBTransactionFunctions.getUsername(userid);
    }

    public FeedbackListt(String userid, String shopid, String message, String serviceid, String club_name) {
        this.userid = userid;
        this.shopid = shopid;
        this.message = message;
        this.club_name = club_name;
        this.user_name = DBTransactionFunctions.getUsername(userid);
        this.servicename = DBTransactionFunctions.getServiceName(serviceid);
    }

    public String getUserid() {
        return userid;
    }

    public String getShopid() {
        return shopid;
    }

    public String getMessage() {
        return message;
    }

    public String getClub_name() {
        return club_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getServicename() {
        return servicename;
    }
}
