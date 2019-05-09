package com.venturetech.venture.butizon.Model.UserApp.Update;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserUpdate {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
