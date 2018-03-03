package com.ryzen.ryan.carbonzero;

/**
 * Created by Ryan on 3/3/2018.
 */

public class DailyData {

    private int day;
    private int carbonPoints;
    private int steps;

    public DailyData(){

    }

    public DailyData(int day, int carbonPoints, int steps) {
        this.day = day;
        this.carbonPoints = carbonPoints;
        this.steps = steps;
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

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}
