package com.prapt.prapt.pogo;

public class LoginModel {
    private String token;
    private String userId;
    private String name;
    private String code;
    public LoginModel(String token,String userId,String name,String code){
        this.token=token;
        this.userId=userId;
        this.name=name;
        this.code=code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
