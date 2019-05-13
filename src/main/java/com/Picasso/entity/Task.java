package com.Picasso.entity;

/***
 * 
 * @author 18140
 *	Task 实体  
 */

import java.util.Date;

public class Task {
    private int task_id;
    private String title;
    private String content;
    private Date init_time;
    private Date update_time;
    private int process;

    public Task(int task_id, String title, String content, Date init_time, Date update_time, int process) {
        this.task_id = task_id;
        this.title = title;
        this.content = content;
        this.init_time = init_time;
        this.update_time = update_time;
        this.process = process;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
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
