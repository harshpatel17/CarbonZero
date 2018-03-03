package com.ryzen.ryan.carbonzero;

/**
 * Created by Ryan on 3/3/2018.
 */

public class DailyData {

    private int day;
    private int carbonPoints;


    public DailyData(){

    }

    public DailyData(int day, int carbonPoints) {
        this.day = day;
        this.carbonPoints = carbonPoints;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getCarbonPoints() {
        return carbonPoints;
    }

    public void setCarbonPoints(int carbonPoints) {
        this.carbonPoints = carbonPoints;
    }
}
