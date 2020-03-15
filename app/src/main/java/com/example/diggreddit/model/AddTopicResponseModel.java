package com.example.diggreddit.model;

public class AddTopicResponseModel {
    private boolean isSuccess;
    private String msg;

    public AddTopicResponseModel(boolean isSuccess,String msg) {
        this.isSuccess = isSuccess;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
