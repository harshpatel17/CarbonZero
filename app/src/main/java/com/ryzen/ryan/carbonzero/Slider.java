package com.ryzen.ryan.carbonzero;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by mchan on 3/3/2018.
 */

public class Slider extends AppCompatActivity{

    int meatCP;
    int veggieCP;
    int totalCP;

    SeekBar seekBar;
    TextView progressText;
    TextView newCPText;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        totalCP = (int)getIntent().getDoubleExtra("GET_ALL",66);
        meatCP = (int)getIntent().getDoubleExtra("GET_SOME_MEAT",66);
      veggieCP = (int)getIntent().getDoubleExtra("GET_SOME_VEG",66);

      seekBar = (SeekBar)findViewById(R.id.VeggieMeatBar);
        seekBar.setMax(100);

        progressText = (TextView)findViewById(R.id.progressText);
        newCPText = (TextView)findViewById(R.id.newCP);

      seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

        int progressChanged = 0;
        int progressOther = 0;
        int newNew = totalCP - meatCP - veggieCP;



          @Override
          public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
            progressChanged = progress;
            progressOther = 100 - progressChanged;
          }

          @Override
          public void onStartTrackingTouch(SeekBar seekBar) {

          }

          @Override
          public void onStopTrackingTouch(SeekBar seekBar) {


              progressText.setText("Your vegatable to meat ratio is: " + progressChanged + "/" + progressOther);
              Toast.makeText(Slider.this,"Bar Progress:" + progressChanged,Toast.LENGTH_SHORT).show();

             // veggieCP = (int)(progressChanged*0.01)*veggieCP;
              //meatCP = (int)((progressOther*0.01)*meatCP);


              int YY = newNew + (int)((progressChanged)*veggieCP) + (int)(progressOther*0.01)*meatCP;
              newCPText.setText("New Total Carbon Points: " + (YY));

            //  newCPText.setText("New Total Carbon Points: " + (newNew + (int)((progressChanged*0.01)*veggieCP) + (int)(progressOther*0.01)*meatCP)/-1);
          }
      });


    }

    }
