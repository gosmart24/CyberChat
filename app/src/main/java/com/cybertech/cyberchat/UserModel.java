package com.cybertech.cyberchat;

/**
 * Created by Samuel Adakole King on 8/20/2019.
 */

public class UserModel {
    String name;
    String username;
    String phone;
    String key;
    String token;
    String img;

    public UserModel() {
    }

    public UserModel(String name, String username, String phone, String key, String token, String img) {
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.key = key;
        this.token = token;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
