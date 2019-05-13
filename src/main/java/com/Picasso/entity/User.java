package com.Picasso.entity;

public class User {
    private String user_id;
    private String passwd;

    public User(String user_id, String passwd) {
        this.user_id = user_id;
        this.passwd = passwd;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}

