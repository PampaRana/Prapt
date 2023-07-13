package com.prapt.prapt.model.allOffer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AllOfferDataDetails {
    @SerializedName("id")
    @Expose
    private String all_offer_id;

    @SerializedName("p_title")
    @Expose
    private String p_title;

    @SerializedName("dfile1")
    @Expose
    private String dfile1;

    @SerializedName("expires")
    @Expose
    private String expires;

    @SerializedName("details")
    @Expose
    private List<AllOfferInfoDetails> allOfferInfoDetails = new ArrayList<>();

    public String getAll_offer_id() {
        return all_offer_id;
    }

    public void setAll_offer_id(String all_offer_id) {
        this.all_offer_id = all_offer_id;
    }

    public String getP_title() {
        return p_title;
    }

    public void setP_title(String p_title) {
        this.p_title = p_title;
    }

    public String getDfile1() {
        return dfile1;
    }

    public void setDfile1(String dfile1) {
        this.dfile1 = dfile1;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public List<AllOfferInfoDetails> getAllOfferInfoDetails() {
        return allOfferInfoDetails;
    }

    public void setAllOfferInfoDetails(List<AllOfferInfoDetails> allOfferInfoDetails) {
        this.allOfferInfoDetails = allOfferInfoDetails;
    }
}
