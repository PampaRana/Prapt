package com.prapt.prapt.model.allOffer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.prapt.prapt.model.topOffer.TopOfferDataDetails;

import java.util.ArrayList;
import java.util.List;

public class AllOfferData {
    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("details_cnt")
    @Expose
    private int details_cnt;

    @SerializedName("result")
    @Expose
    private List<AllOfferDataDetails> allOfferDataDetails = new ArrayList<>();

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

    public List<AllOfferDataDetails> getAllOfferDataDetails() {
        return allOfferDataDetails;
    }

    public void setAllOfferDataDetails(List<AllOfferDataDetails> allOfferDataDetails) {
        this.allOfferDataDetails = allOfferDataDetails;
    }

    public int getDetails_cnt() {
        return details_cnt;
    }

    public void setDetails_cnt(int details_cnt) {
        this.details_cnt = details_cnt;
    }
}
