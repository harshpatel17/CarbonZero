package com.ryzen.ryan.carbonzero;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Scanner;

public class TotalCarbon extends AppCompatActivity {


    private double gasPrice ;
    public double carbonProduced;

    int q1;
    int q2;
    int q3;
    int q4;
    int q5;
    int q6;
    int q7;

    Button toEarthBtn;
    TextView totalCP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_carbon);

        // Hides action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Hides status bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        toEarthBtn = findViewById(R.id.earthBtn);
        totalCP = findViewById(R.id.totalCP);

        Intent intent = getIntent();
        final String answers = intent.getStringExtra("qSeven");

        Scanner scanner = new Scanner(answers);

        q1 = Integer.parseInt(scanner.next());
        q2 = Integer.parseInt(scanner.next());
        q3 = Integer.parseInt(scanner.next());
        q4 = Integer.parseInt(scanner.next());
        q5 = Integer.parseInt(scanner.next());
        q6 = Integer.parseInt(scanner.next());
        q7 = Integer.parseInt(scanner.next());

        int total = getTotalCarbonPoints();
        totalCP.setText(total+"");

        toEarthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TotalCarbon.this, EarthActivity.class);
                startActivity(intent);
            }
        });
    }

    public double calculateFuel(int q1) {

        double averageFuel = 50;

        switch (q1) {
            case 0:
                averageFuel = 50;
                break;
            case 1:
                averageFuel = 150;
                break;
            case 2:
                averageFuel = 250;
                break;
            case 3:
                averageFuel = 350;
                break;
            case 4:
                averageFuel = 450;
        }

        calculateGasPrice(q5);

        double numGallons = averageFuel / gasPrice;

        double fuelCarbon = numGallons * 0.00889 * 100;

        return fuelCarbon;


    }


    public double calculateElectricity(int q2) {

        double averageElec = 50;

        switch (q2) {

            case 0:
                averageElec = 50;
                break;
            case 1:
                averageElec = 150;
                break;
            case 2:
                averageElec = 250;
                break;
            case 3:
                averageElec = 350;
                break;
            case 4:
                averageElec = 450;


        }

        double numKWh = averageElec / 11.42;

        double elecCarbon = numKWh * 0.000744 * 1000;
        return elecCarbon;
    }

    public double calculateNG(int q3) {

        double averageNG = 50;

        switch (q3) {

            case 0:
                averageNG = 50;
                break;
            case 1:
                averageNG = 150;
                break;
            case 2:
                averageNG = 250;
                break;
            case 3:
                averageNG = 350;
                break;
            case 4:
                averageNG = 450;


        }

        double numMcf = averageNG / 21.76;

        double ngCarbon = numMcf * 0.0550;

        return ngCarbon;
    }


    public double calculateRecycle(int q4) {

        double recycledCarbon = 50;

        switch (q4) {

            case 0:
                recycledCarbon+= 0;
            case 1:
                recycledCarbon += 5;

        }

        return recycledCarbon;
    }
    public double calculateGasPrice(int q5){
        gasPrice = 2.55;
        return gasPrice;
    }

    public double calculateMeat(int q6) {

        double averageMeat = 50;


        switch (q6) {

            case 0:
                averageMeat = 0;
                break;
            case 1:
                averageMeat = 10;
                break;
            case 2:
                averageMeat = 20;
                break;
            case 3:
                averageMeat = 30;
                break;
            case 4:
                averageMeat = 40;


        }
        double meatCarbon = (averageMeat * 19.22) / 12;
        return meatCarbon;

    }

    public double calculateVeggies(int q7) {


        double averageVeggie = 50;

        switch (q7) {

            case 0:
                averageVeggie = 0;
                break;
            case 1:
                averageVeggie = 10;
                break;
            case 2:
                averageVeggie = 20;
                break;
            case 3:
                averageVeggie = 30;
                break;
            case 4:
                averageVeggie = 40;
        }
        double veggieCarbon = (averageVeggie * 3.1) / 12;
        return veggieCarbon;

    }

    public int getTotalCarbonPoints() {

        //call getTotalCarbonPoints when submitting results
        double total = calculateFuel(q1)+ calculateElectricity(q2)+ calculateNG(q3)+ calculateRecycle(q4)+calculateMeat(q6)+calculateVeggies(q7);



        int carbonPoints = (int) total;
        return carbonPoints;
    }

}
