package com.prapt.prapt.model.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.prapt.prapt.model.product.ProductDataDetails;

import java.util.ArrayList;
import java.util.List;

public class CartData {
    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<CartDataDetails> cartDetailsDataList = new ArrayList<>();

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

    public List<CartDataDetails> getCartDetailsDataList() {
        return cartDetailsDataList;
    }

    public void setCartDetailsDataList(List<CartDataDetails> cartDetailsDataList) {
        this.cartDetailsDataList = cartDetailsDataList;
    }
}
