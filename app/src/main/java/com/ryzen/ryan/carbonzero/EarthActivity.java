package com.ryzen.ryan.carbonzero;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import github.hellocsl.cursorwheel.CursorWheelLayout;

public class EarthActivity extends AppCompatActivity implements SensorEventListener, CursorWheelLayout.OnMenuSelectedListener {


    CursorWheelLayout earthWheel;
    List<EarthWheelData> list;

    // Step Counter Sensor
    private SensorManager mSensorManager;
    private Sensor mSensor;
    TextView pedometerText;
    boolean running = false;

    // Firebase
    DatabaseReference pedometerRef;
    DatabaseReference dailyRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth);

        // Hides action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Hides status bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // Sets up pedometer sensor
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        pedometerText = findViewById(R.id.pedometerText);

        // Wheel
        earthWheel = findViewById(R.id.wheel_image);
        loadData();
        earthWheel.setOnMenuSelectedListener(this);

        // Firebase
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        pedometerRef = database.getReference("pedometer");
        dailyRef = database.getReference("daily");
    }

    // Wheel data loaded
    private void loadData(){
        list = new ArrayList<>();
        list.add(new EarthWheelData(R.drawable.fb_ic, "Facebook"));
        list.add(new EarthWheelData(R.drawable.inst_ic, "Instagram"));
        list.add(new EarthWheelData(R.drawable.twitter_ic, "Twitter"));

        EarthWheelAdapter earthWheelAdapter = new EarthWheelAdapter(getBaseContext(), list);
        earthWheel.setAdapter(earthWheelAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(mSensor != null){
            mSensorManager.registerListener(this, mSensor, mSensorManager.SENSOR_DELAY_UI);
        }else{
            Toast.makeText(this, "Sensor not found", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
    }

    // Pedometer
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
            pedometerText.setText(String.valueOf(sensorEvent.values[0]));
            pedometerRef.setValue(String.valueOf(sensorEvent.values[0]));
            String key = dailyRef.push().getKey();
            dailyRef.setValue(new DailyData(0,150, (int)sensorEvent.values[0]));
            dailyRef.child("Victor").setValue("setting custom key when pushing new data to firebase database");

    }
    // Pedometer
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    //Wheel
    @Override
    public void onItemSelected(CursorWheelLayout parent, View view, int pos) {
        if (parent.getId() == R.id.wheel_image){
            Toast.makeText(getBaseContext(), "Selected: "+ list.get(pos).imageDescription, Toast.LENGTH_SHORT).show();
        }
    }
}
