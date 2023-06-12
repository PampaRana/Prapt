package com.prapt.prapt.model.topOffer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.prapt.prapt.model.banner.BannerDataDetails;

import java.util.ArrayList;
import java.util.List;

public class TopOfferData {
    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("result")
    @Expose
    private List<TopOfferDataDetails> topOfferDataDetails = new ArrayList<>();

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

    public List<TopOfferDataDetails> getTopOfferDataDetails() {
        return topOfferDataDetails;
    }

    public void setTopOfferDataDetails(List<TopOfferDataDetails> topOfferDataDetails) {
        this.topOfferDataDetails = topOfferDataDetails;
    }
}
