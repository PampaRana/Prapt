package com.prapt.prapt.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProductData {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<ProductDataDetails> productDetailsDataList = new ArrayList<>();

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

    public List<ProductDataDetails> getProductDetailsDataList() {
        return productDetailsDataList;
    }

    public void setProductDetailsDataList(List<ProductDataDetails> productDetailsDataList) {
        this.productDetailsDataList = productDetailsDataList;
    }
}
