package com.prapt.prapt.model.allOffer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllOfferInfoDetails {

    @SerializedName("id")
    @Expose
    private String info_offer_id;

    @SerializedName("details")
    @Expose
    private String details;

    @SerializedName("add_date")
    @Expose
    private String add_date;

    @SerializedName("add_time")
    @Expose
    private String add_time;

    public String getInfo_offer_id() {
        return info_offer_id;
    }

    public void setInfo_offer_id(String info_offer_id) {
        this.info_offer_id = info_offer_id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAdd_date() {
        return add_date;
    }

    public void setAdd_date(String add_date) {
        this.add_date = add_date;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }
}
