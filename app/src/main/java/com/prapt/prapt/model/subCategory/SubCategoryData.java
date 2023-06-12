package com.prapt.prapt.model.subCategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryData {
    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<SubCategoryDataDetails> subCategoryDetailsDataList = new ArrayList<>();

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

    public List<SubCategoryDataDetails> getSubCategoryDetailsDataList() {
        return subCategoryDetailsDataList;
    }

    public void setSubCategoryDetailsDataList(List<SubCategoryDataDetails> subCategoryDetailsDataList) {
        this.subCategoryDetailsDataList = subCategoryDetailsDataList;
    }
}
