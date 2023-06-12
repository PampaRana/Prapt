package com.prapt.prapt.model.subCategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCategoryDataDetails {

    @SerializedName("procate_id")
    @Expose
    private String procate_id;

    @SerializedName("link_name")
    @Expose
    private String link_name;

    @SerializedName("dfile1")
    @Expose
    private String dfile1;

    public String getProcate_id() {
        return procate_id;
    }

    public void setProcate_id(String procate_id) {
        this.procate_id = procate_id;
    }

    public String getLink_name() {
        return link_name;
    }

    public void setLink_name(String link_name) {
        this.link_name = link_name;
    }

    public String getDfile1() {
        return dfile1;
    }

    public void setDfile1(String dfile1) {
        this.dfile1 = dfile1;
    }
}
