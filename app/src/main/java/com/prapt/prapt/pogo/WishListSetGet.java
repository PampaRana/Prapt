package com.prapt.prapt.pogo;

public class WishListSetGet {
    private String wishId;
    private String wishName;
    private String wishGram;
    private String wishPrice;
    private String wishOfferPrice;
    private int wishImage;
    public WishListSetGet(String wishId,String wishName,String wishGram,String wishPrice,String wishOfferPrice,int wishImage){
        this.wishId=wishId;
        this.wishName=wishName;
        this.wishGram=wishGram;
        this.wishPrice=wishPrice;
        this.wishOfferPrice=wishOfferPrice;
        this.wishImage=wishImage;
    }

    public String getWishId() {
        return wishId;
    }

    public void setWishId(String wishId) {
        this.wishId = wishId;
    }

    public String getWishName() {
        return wishName;
    }

    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

    public String getWishGram() {
        return wishGram;
    }

    public void setWishGram(String wishGram) {
        this.wishGram = wishGram;
    }

    public String getWishPrice() {
        return wishPrice;
    }

    public void setWishPrice(String wishPrice) {
        this.wishPrice = wishPrice;
    }

    public String getWishOfferPrice() {
        return wishOfferPrice;
    }

    public void setWishOfferPrice(String wishOfferPrice) {
        this.wishOfferPrice = wishOfferPrice;
    }

    public int getWishImage() {
        return wishImage;
    }

    public void setWishImage(int wishImage) {
        this.wishImage = wishImage;
    }
}
