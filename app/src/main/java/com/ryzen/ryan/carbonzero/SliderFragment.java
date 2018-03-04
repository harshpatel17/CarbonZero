package com.ryzen.ryan.carbonzero;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by mchan on 3/3/2018.
 */
public class SliderFragment extends Fragment {
    int meatCP;
    int veggieCP;
    int totalCP;

    SeekBar seekBar;
    TextView progressText;
    TextView newCPText;
    TextView currentCPText;

    int currentCP;
    int newCP;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_slider, container, false);

        totalCP = (int)getActivity().getIntent().getDoubleExtra("GET_ALL",66);
        meatCP = (int)getActivity().getIntent().getDoubleExtra("GET_SOME_MEAT",66);
        veggieCP = (int)getActivity().getIntent().getDoubleExtra("GET_SOME_VEG",66);

        seekBar = v.findViewById(R.id.VeggieMeatBar);
        seekBar.setMax(100);

        progressText = v.findViewById(R.id.progressText);
        newCPText = v.findViewById(R.id.newCP);
        currentCPText = v.findViewById(R.id.currentCP);
        currentCP = getArguments().getInt("totalCarbon");
        currentCPText.setText("Your current carbon points are " + currentCP);

        newCP = currentCP;

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

                int changeFactor = 0;

                progressText.setText("Your vegetable to meat ratio is: " + progressChanged + "/" + progressOther);
                Toast.makeText(getContext(),"Bar Progress:" + progressChanged,Toast.LENGTH_SHORT).show();
                if(progressChanged ==50){
                    changeFactor = progressChanged/20;
                }else if(progressChanged >50){
                    changeFactor = (progressChanged/20)*(-1);
                }else{
                    changeFactor = progressChanged/20;
                }
                // veggieCP = (int)(progressChanged*0.01)*veggieCP;
                //meatCP = (int)((progressOther*0.01)*meatCP);


//                int YY = newNew + (int)((progressChanged)*veggieCP) + (int)(progressOther*0.01)*meatCP;
                int YY = currentCP + changeFactor;
                newCP = YY;

                newCPText.setText("New Total Carbon Points: " + (YY));
                //  newCPText.setText("New Total Carbon Points: " + (newNew + (int)((progressChanged*0.01)*veggieCP) + (int)(progressOther*0.01)*meatCP)/-1);
            }
        });
        return v;

    }

}


