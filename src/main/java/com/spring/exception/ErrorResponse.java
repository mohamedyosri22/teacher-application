package com.spring.exception;


import java.time.LocalDateTime;


public class ErrorResponse {
    private String msg;
    private LocalDateTime timeStamp;

    public ErrorResponse(){

    }
    public ErrorResponse(String msg, LocalDateTime timeStamp) {
        this.msg = msg;
        this.timeStamp = timeStamp;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
