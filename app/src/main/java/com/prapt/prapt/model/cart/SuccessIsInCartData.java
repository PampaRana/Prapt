package com.prapt.prapt.model.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SuccessIsInCartData {
    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("result")
    @Expose
    private String result;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
