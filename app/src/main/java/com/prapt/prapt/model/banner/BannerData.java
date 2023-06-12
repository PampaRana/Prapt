package com.prapt.prapt.model.banner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.prapt.prapt.model.cart.CartDataDetails;

import java.util.ArrayList;
import java.util.List;

public class BannerData {
    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("result")
    @Expose
    private List<BannerDataDetails> bannerDataDetails = new ArrayList<>();

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

    public List<BannerDataDetails> getBannerDataDetails() {
        return bannerDataDetails;
    }

    public void setBannerDataDetails(List<BannerDataDetails> bannerDataDetails) {
        this.bannerDataDetails = bannerDataDetails;
    }
}
