package com.example.login;

import android.app.Activity;

public class InfoDto extends Activity {
    private String user_id;
    private String bus;
    private String date;
    private String seat;

    String getUserId(){return user_id;}
    String getBus(){return bus;}
    String getDate(){return date;}
    String getSeat(){return seat;}
    void setUserId(String user_id){ this.user_id = user_id; }
    void setBus(String bus){ this.bus = bus; }
    void setDate(String date){ this.date = date; }
    void setSeat(String seat){ this.seat = seat; }
}

