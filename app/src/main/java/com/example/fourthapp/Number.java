package com.example.fourthapp;

import java.io.Serializable;

public class Number implements Serializable {
    private int id;
    private String pushupnumber;

    public Number(int id, String pushupnumber) {
        this.id = id;
        this.pushupnumber = pushupnumber;
    }

    public int getId() {
        return id;
    }

    public String getPushupnumber() {
        return pushupnumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPushupnumber(String pushupnumber) {
        this.pushupnumber = pushupnumber;
    }
}
