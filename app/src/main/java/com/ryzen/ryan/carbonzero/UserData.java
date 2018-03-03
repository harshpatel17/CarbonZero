package com.ryzen.ryan.carbonzero;

/**
 * Created by Ryan on 3/3/2018.
 */

public class UserData {
    private String emailID;


    public UserData() {
    }

    public UserData(String emailID) {
        this.emailID = emailID;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }
}
