package com.ryzen.ryan.carbonzero;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by mchan on 3/3/2018.
 */

public class Questions extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private double gasPrice ;
    public double carbonProduced;
      TextView text;

    Spinner fuelSpinner;
    Spinner elecSpinner;
    Spinner gasSpinner;
    Spinner stateSpinner;
    Spinner recycleSpinner;
    Spinner meatSpinner ;
    Spinner veggieSpinner ;

    Intent totalCPIntent;
    Intent meatIntent;
    Intent veggiesIntent;

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        //  parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        createArray();
        fuelSpinner = (Spinner) findViewById(R.id.Q1);
        elecSpinner = (Spinner) findViewById(R.id.Q2);
        gasSpinner = (Spinner) findViewById(R.id.Q3);
        stateSpinner = (Spinner) findViewById(R.id.Q5);
        recycleSpinner = (Spinner) findViewById(R.id.Q6);
        meatSpinner = (Spinner) findViewById(R.id.Q7);
        veggieSpinner = (Spinner) findViewById(R.id.Q8);


        fuelSpinner.setOnItemSelectedListener(this);
        elecSpinner.setOnItemSelectedListener(this);
        gasSpinner.setOnItemSelectedListener(this);
        meatSpinner.setOnItemSelectedListener(this);
        veggieSpinner.setOnItemSelectedListener(this);
        recycleSpinner.setOnItemSelectedListener(this);


         meatIntent = new Intent(getBaseContext(), SliderFragment.class);
        meatIntent.putExtra("GET_SOME_MEAT", calculateMeat());
        startActivity(meatIntent);

         veggiesIntent = new Intent(getBaseContext(), SliderFragment.class);
        veggiesIntent.putExtra("GET_SOME_VEG", calculateVeggies());
        startActivity(veggiesIntent);


        totalCPIntent = new Intent(getBaseContext(), SliderFragment.class);
        totalCPIntent.putExtra("GET_ALL", getTotalCarbonPoints());
        startActivity(totalCPIntent);


        text = (TextView)findViewById(R.id.textView4);
       text.setText(String.valueOf(getTotalCarbonPoints()));


    }

    public void createArray() {

        Spinner spinner = (Spinner) findViewById(R.id.Q1);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.questionArray, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);




    }


    public double calculateFuel() {

        double averageFuel = 50;

        switch (fuelSpinner.getSelectedItemPosition()) {

            case 0:
                averageFuel = 50;
            case 1:
                averageFuel = 150;
            case 2:
                averageFuel = 250;
            case 3:
                averageFuel = 350;
            case 4:
                averageFuel = 450;


        }

        double numGallons = averageFuel / gasPrice;

        double fuelCarbon = numGallons * 0.00889 * 100;

        return fuelCarbon;


    }


    public double calculateElectricity() {

        double averageElec = 50;

        switch (elecSpinner.getSelectedItemPosition()) {

            case 0:
                averageElec = 50;
            case 1:
                averageElec = 150;
            case 2:
                averageElec = 250;
            case 3:
                averageElec = 350;
            case 4:
                averageElec = 450;


        }

        double numKWh = averageElec / 11.42;

        double elecCarbon = numKWh * 0.000744 * 1000;
        return elecCarbon;
    }

    public double calculateNG() {

        double averageNG = 50;

        switch (gasSpinner.getSelectedItemPosition()) {

            case 0:
                averageNG = 50;
            case 1:
                averageNG = 150;
            case 2:
                averageNG = 250;
            case 3:
                averageNG = 350;
            case 4:
                averageNG = 450;


        }

        double numMcf = averageNG / 21.76;

        double ngCarbon = numMcf * 0.0550;

        return ngCarbon;
    }


    public double calculateRecycle() {

        double recycledCarbon = 50;

        switch (recycleSpinner.getSelectedItemPosition()) {

            case 0:
                recycledCarbon+= 0;
            case 1:
                recycledCarbon += 5;

        }

        return recycledCarbon;
    }

    public double calculateMeat() {

        double averageMeat = 50;


        switch (meatSpinner.getSelectedItemPosition()) {

            case 0:
                averageMeat = 0;
            case 1:
                averageMeat = 10;
            case 2:
                averageMeat = 20;
            case 3:
                averageMeat = 30;
            case 4:
                averageMeat = 40;


        }
        double meatCarbon = (averageMeat * 19.22) / 12;
        return meatCarbon;

    }

    public double calculateVeggies() {


        double averageVeggie = 50;

        switch (veggieSpinner.getSelectedItemPosition()) {

            case 0:
                averageVeggie = 0;
            case 1:
                averageVeggie = 10;
            case 2:
                averageVeggie = 20;
            case 3:
                averageVeggie = 30;
            case 4:
                averageVeggie = 40;


        }


        double veggieCarbon = (averageVeggie * 3.1) / 12;
        return veggieCarbon;

    }

    public int getTotalCarbonPoints() {

        //call getTotalCarbonPoints when submitting results
        double total = calculateFuel()+ calculateElectricity()+ calculateNG()+ calculateRecycle()+calculateMeat()+calculateVeggies();



        int carbonPoints = (int) total;
        return carbonPoints;
    }


}
