package com.ryzen.ryan.carbonzero;

    import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ryzen.ryan.carbonzero.R;

/**
 * Created by Ryan on 2/24/2018.
 */

public class CarbonFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_carbon, container, false);




        return v;
    }

}
