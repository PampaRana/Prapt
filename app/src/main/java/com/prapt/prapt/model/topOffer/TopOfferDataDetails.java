package com.prapt.prapt.model.topOffer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopOfferDataDetails {
    @SerializedName("p_title")
    @Expose
    private String p_title;

    @SerializedName("dfile1")
    @Expose
    private String dfile1;

    public String getP_title() {
        return p_title;
    }

    public void setP_title(String p_title) {
        this.p_title = p_title;
    }

    public String getDfile1() {
        return dfile1;
    }

    public void setDfile1(String dfile1) {
        this.dfile1 = dfile1;
    }
}
