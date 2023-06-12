package com.prapt.prapt.model.brand;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.prapt.prapt.model.category.CategoryDataDetails;

import java.util.ArrayList;
import java.util.List;

public class BrandData {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<BrandDataDetails> brandDetailsDataList = new ArrayList<>();

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BrandDataDetails> getBrandDetailsDataList() {
        return brandDetailsDataList;
    }

    public void setBrandDetailsDataList(List<BrandDataDetails> brandDetailsDataList) {
        this.brandDetailsDataList = brandDetailsDataList;
    }
}
