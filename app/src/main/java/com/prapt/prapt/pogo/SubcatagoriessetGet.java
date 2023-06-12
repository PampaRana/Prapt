package com.prapt.prapt.pogo;

public class SubcatagoriessetGet {
    private String subId;
    private String subName;
    private int subImage;
    public SubcatagoriessetGet(String subId,String subName,int subImage){
        this.subId=subId;
        this.subName=subName;
        this.subImage=subImage;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public int getSubImage() {
        return subImage;
    }

    public void setSubImage(int subImage) {
        this.subImage = subImage;
    }
}
