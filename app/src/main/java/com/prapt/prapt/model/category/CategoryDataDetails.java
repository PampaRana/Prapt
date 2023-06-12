package com.prapt.prapt.model.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryDataDetails {

    @SerializedName("propage_id")
    @Expose
    private String propage_id;

    @SerializedName("propage_name")
    @Expose
    private String propage_name;

    @SerializedName("banner_image")
    @Expose
    private String banner_image;

    public String getPropage_id() {
        return propage_id;
    }

    public void setPropage_id(String propage_id) {
        this.propage_id = propage_id;
    }

    public String getPropage_name() {
        return propage_name;
    }

    public void setPropage_name(String propage_name) {
        this.propage_name = propage_name;
    }

    public String getBanner_image() {
        return banner_image;
    }

    public void setBanner_image(String banner_image) {
        this.banner_image = banner_image;
    }
}
