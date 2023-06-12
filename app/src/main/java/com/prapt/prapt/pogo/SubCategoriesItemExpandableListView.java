package com.prapt.prapt.pogo;

public class SubCategoriesItemExpandableListView {
    private String titleName;
    private String subName;
    private int titleImage;
    private int subImage;
    public SubCategoriesItemExpandableListView(String titleName, String subName, int titleImage, int subImage){
        this.titleName=titleName;
        this.subName=subName;
        this.titleImage=titleImage;
        this.subImage=subImage;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public int getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(int titleImage) {
        this.titleImage = titleImage;
    }

    public int getSubImage() {
        return subImage;
    }

    public void setSubImage(int subImage) {
        this.subImage = subImage;
    }
}
