package com.example.notificationdrawerdemo.model;

/**
 * Created by Brishav on 12/18/2017.
 */

public class User {
    private int userId;
    private String username;
    private String userpassword;
    private String userImageUrl;

    public User(){
        this.userId=userId;
        this.username=username;
        this.userpassword=userpassword;
        this.userImageUrl=userImageUrl;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }
}
