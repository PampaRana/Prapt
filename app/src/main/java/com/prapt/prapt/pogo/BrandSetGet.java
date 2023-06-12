package com.prapt.prapt.pogo;

public class BrandSetGet {
    private String brandId;
    private int brandImage;
    public BrandSetGet(String brandId,int brandImage){
        this.brandId=brandId;
        this.brandImage=brandImage;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public int getBrandImage() {
        return brandImage;
    }

    public void setBrandImage(int brandImage) {
        this.brandImage = brandImage;
    }
}
