package com.prapt.prapt.pogo;

public class Toppacks {
    private String itemId;
    private int itemImage;
    public Toppacks(String itemId, int itemImage){
        this.itemId = itemId;
        this.itemImage = itemImage;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getItemImage() {
        return itemImage;
    }

    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }
}
