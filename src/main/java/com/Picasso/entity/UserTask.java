package com.Picasso.entity;

public class UserTask {
    private int uid;
    private int tid;

    public UserTask(int user_id, int task_id) {
        this.uid = user_id;
        this.tid = task_id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int user_id) {
        this.uid = user_id;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int task_id) {
        this.tid = task_id;
    }
}
