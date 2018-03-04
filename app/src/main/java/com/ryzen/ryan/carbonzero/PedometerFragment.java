package com.ryzen.ryan.carbonzero;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Ryan on 2/24/2018.
 */

public class PedometerFragment extends Fragment implements SensorEventListener{

    // Step Counter Sensor
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private Sensor accelerometerSensor;
    TextView pedometerText;
    boolean running = false;

    // Firebase
    DatabaseReference pedometerRef;
    DatabaseReference dailyRef;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pedometer, container, false);

        // Sets up pedometer sensor
        mSensorManager = (SensorManager) this.getActivity().getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        pedometerText = v.findViewById(R.id.pedometerText);


        // Firebase
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        pedometerRef = database.getReference("pedometer");
        dailyRef = database.getReference("daily");

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(mSensor != null){
            mSensorManager.registerListener(this, mSensor, mSensorManager.SENSOR_DELAY_UI);
        }else{
            Toast.makeText(getContext(), "Sensor not found", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onPause() {
        super.onPause();
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(mSensor != null){
            mSensorManager.registerListener(this, mSensor, mSensorManager.SENSOR_DELAY_UI);
        }else{
            Toast.makeText(getContext(), "Sensor not found", Toast.LENGTH_SHORT).show();
        }
    }

    // Pedometer
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
//        int accelerationFilter = (int) (sensorEvent.values[0] - (0.8)*9.81+(1-0.8)*sensorEvent.values[0]);
//        Toast.makeText(getContext(), accelerationFilter, Toast.LENGTH_SHORT).show();
//
//        if (accelerationFilter >= 2) {
//            Toast.makeText(getContext(), "Too fast!", Toast.LENGTH_SHORT).show();
//
//        }
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

}
