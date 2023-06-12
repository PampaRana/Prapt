package com.prapt.prapt.pogo;

public class MycartsetGet {
    private String cartId;
    private String cartName;
    private String cartGram;
    private String cartPrice;
    private String cartLessPrice;
    private String cartQty;
    private int cartImage;
    public MycartsetGet(String cartId,String cartName,
                        String cartGram,String cartPrice,
                        String cartLessPrice,String cartQty,int cartImage){
        this.cartId=cartId;
        this.cartName=cartName;
        this.cartGram=cartGram;
        this.cartPrice=cartPrice;
        this.cartLessPrice=cartLessPrice;
        this.cartQty=cartQty;
        this.cartImage=cartImage;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getCartName() {
        return cartName;
    }

    public void setCartName(String cartName) {
        this.cartName = cartName;
    }

    public String getCartGram() {
        return cartGram;
    }

    public void setCartGram(String cartGram) {
        this.cartGram = cartGram;
    }

    public String getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(String cartPrice) {
        this.cartPrice = cartPrice;
    }

    public String getCartLessPrice() {
        return cartLessPrice;
    }

    public void setCartLessPrice(String cartLessPrice) {
        this.cartLessPrice = cartLessPrice;
    }

    public String getCartQty() {
        return cartQty;
    }

    public void setCartQty(String cartQty) {
        this.cartQty = cartQty;
    }

    public int getCartImage() {
        return cartImage;
    }

    public void setCartImage(int cartImage) {
        this.cartImage = cartImage;
    }
}
