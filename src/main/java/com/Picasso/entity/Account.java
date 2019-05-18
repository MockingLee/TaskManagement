package com.Picasso.entity;

//用户登录信息验证
public class Account {
  private int uid;// 用户id
  private String username;// 用户登录id
  private String password;// 用户密码
  private int weight;// 用户权限等级

  public Account(int uid, String username, String password, int weight) {
    this.uid = uid;
    this.username = username;
    this.password = password;
    this.weight = weight;
  }

  public int getUid() {
    return uid;
  }

  public void setUid(int uid) {
    this.uid = uid;
  }

  public String getusername() {
    return username;
  }

  public void setusername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }
}
