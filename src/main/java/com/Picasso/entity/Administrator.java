package com.Picasso.entity;

public class Administrator {
    private String user_id;
    private String passwd;

    public Administrator(String user_id, String passwd) {
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