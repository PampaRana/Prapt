package com.prapt.prapt.pogo;

public class SubcatagoriesViewSetGet {
    private String pretext;
    private String pretextCross;
    private String pretextCrossText;
    private String itemName;
    private String itemName1;
    private String mrp;
    private String rate;
    private String mergin;
    private int image;
    public SubcatagoriesViewSetGet(String pretext,String pretextCross,
                                   String pretextCrossText,String itemName,String itemName1,String mrp,String rate,String mergin,int image){
        this.pretext=pretext;
        this.pretextCross=pretextCross;
        this.pretextCrossText=pretextCrossText;
        this.itemName=itemName;
        this.itemName1=itemName1;
        this.mrp=mrp;
        this.rate=rate;
        this.mergin=mergin;
        this.image=image;
    }

    public String getPretext() {
        return pretext;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName1() {
        return itemName1;
    }

    public void setItemName1(String itemName1) {
        this.itemName1 = itemName1;
    }

    public void setPretext(String pretext) {
        this.pretext = pretext;
    }

    public String getPretextCross() {
        return pretextCross;
    }

    public void setPretextCross(String pretextCross) {
        this.pretextCross = pretextCross;
    }

    public String getPretextCrossText() {
        return pretextCrossText;
    }

    public void setPretextCrossText(String pretextCrossText) {
        this.pretextCrossText = pretextCrossText;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getMergin() {
        return mergin;
    }

    public void setMergin(String mergin) {
        this.mergin = mergin;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
