package com.hyperloop.uploadapp.login.entities;

/**
 * Created by DaniRosas on 17/7/17.
 */

public class User {
    String email;
    boolean online;
    boolean verified;
    public final static boolean ONLINE = true;
    public final static boolean OFFLINE = false;

    public User(){ }

    public User(String email, boolean online){
        this.email = email;
        this.online = online;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

}
