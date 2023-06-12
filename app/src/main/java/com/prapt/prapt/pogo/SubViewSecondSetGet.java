package com.prapt.prapt.pogo;

public class SubViewSecondSetGet {
    private String subId;
    private String subKg;
    public SubViewSecondSetGet(String subId,String subKg){
        this.subId=subId;
        this.subKg=subKg;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getSubKg() {
        return subKg;
    }

    public void setSubKg(String subKg) {
        this.subKg = subKg;
    }
}
