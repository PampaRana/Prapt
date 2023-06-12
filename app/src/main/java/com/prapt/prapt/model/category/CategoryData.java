package com.prapt.prapt.model.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CategoryData {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<CategoryDataDetails> categoryDetailsDataList = new ArrayList<>();

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

    public List<CategoryDataDetails> getCategoryDetailsDataList() {
        return categoryDetailsDataList;
    }

    public void setCategoryDetailsDataList(List<CategoryDataDetails> categoryDetailsDataList) {
        this.categoryDetailsDataList = categoryDetailsDataList;
    }
}
