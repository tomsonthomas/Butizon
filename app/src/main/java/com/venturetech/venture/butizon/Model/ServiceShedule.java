package com.venturetech.venture.butizon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceShedule {
    @SerializedName("error")
    @Expose
    private Boolean error;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }
}
