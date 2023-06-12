package com.prapt.prapt.pogo;

public class ItemView {
    private String itemId;
    private String itemName;
    private int itemImage;
    public ItemView(String itemId,String itemName,int itemImage){
        this.itemId=itemId;
        this.itemName=itemName;
        this.itemImage=itemImage;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemImage() {
        return itemImage;
    }

    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }
}
