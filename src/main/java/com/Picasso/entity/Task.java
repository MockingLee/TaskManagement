package com.Picasso.entity;


import java.util.Date;

public class Task {
    private int tid;//任务id
    private String title;//任务标题
    private String content;//任务内容
    private Date init_time;//初始时间
    private Date update_time;//最近更新时间
    private int process;//当前进度 1~100

    public Task(int task_id, String title, String content, Date init_time, Date update_time, int process) {
        this.tid = task_id;
        this.title = title;
        this.content = content;
        this.init_time = init_time;
        this.update_time = update_time;
        this.process = process;
    }

    public int getTask_id() {
        return tid;
    }

    public void setTask_id(int task_id) {
        this.tid = task_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getInit_time() {
        return init_time;
    }

    public void setInit_time(Date init_time) {
        this.init_time = init_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }
}
