package com.Picasso.entity;

import java.util.Date;

public class UserInfo {
    private int uid;//用户id
    private String name;//用户姓名
    private String school;//用户毕业学校
    private String profession;//专业
    private String phone;//联系电话
    private int sex;//性别
    private Date birth;//出生日期

    public UserInfo(int uid, String name, String school, String profession, String phone, int sex, Date birth) {
        this.uid = uid;
        this.name = name;
        this.school = school;
        this.profession = profession;
        this.phone = phone;
        this.sex = sex;
        this.birth = birth;
    }

    public int getuid() {
        return uid;
    }

    public void setuid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
