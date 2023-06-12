package com.prapt.prapt.pogo;

public class Languagesetget {
    private String languageId;
    private String languageName;
    private String languageNameEng;
    private int languageImage;
    public Languagesetget(String languageId,String languageName,
                        String languageNameEng,int languageImage){
        this.languageId=languageId;
        this.languageName=languageName;
        this.languageNameEng=languageNameEng;
        this.languageImage=languageImage;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getLanguageNameEng() {
        return languageNameEng;
    }

    public void setLanguageNameEng(String languageNameEng) {
        this.languageNameEng = languageNameEng;
    }

    public int getLanguageImage() {
        return languageImage;
    }

    public void setLanguageImage(int languageImage) {
        this.languageImage = languageImage;
    }
}
