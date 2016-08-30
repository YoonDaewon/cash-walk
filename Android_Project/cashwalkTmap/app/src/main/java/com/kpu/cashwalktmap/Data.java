package com.kpu.cashwalktmap;

/**
 * Created by ydwin on 2016-08-30.
 */
public class Data {
    String id;
    String pw;
    double record;
    int cash;

    //getter
    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public double getRecord() {
        return record;
    }

    public int getCash() {
        return cash;
    }

    //setter
    public void setId(String id) {
        this.id = id;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setRecord(double record) {
        this.record = record;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }
}
