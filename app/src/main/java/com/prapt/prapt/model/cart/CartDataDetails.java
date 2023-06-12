package com.prapt.prapt.model.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartDataDetails {

    @SerializedName("product_id")
    @Expose
    private String product_id;

    @SerializedName("product_name")
    @Expose
    private String product_name;

    @SerializedName("dfile1")
    @Expose
    private String dfile1;

    @SerializedName("cat_id")
    @Expose
    private String cat_id;

    @SerializedName("subcat_id")
    @Expose
    private String subcat_id;

    @SerializedName("actual_price")
    @Expose
    private String actual_price;

    @SerializedName("after_discount_price")
    @Expose
    private String after_discount_price;

    @SerializedName("quantity")
    @Expose
    private String quantity;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDfile1() {
        return dfile1;
    }

    public void setDfile1(String dfile1) {
        this.dfile1 = dfile1;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getSubcat_id() {
        return subcat_id;
    }

    public void setSubcat_id(String subcat_id) {
        this.subcat_id = subcat_id;
    }

    public String getActual_price() {
        return actual_price;
    }

    public void setActual_price(String actual_price) {
        this.actual_price = actual_price;
    }

    public String getAfter_discount_price() {
        return after_discount_price;
    }

    public void setAfter_discount_price(String after_discount_price) {
        this.after_discount_price = after_discount_price;
    }
    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
