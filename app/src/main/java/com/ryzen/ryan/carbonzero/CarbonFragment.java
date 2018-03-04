package com.ryzen.ryan.carbonzero;

    import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
    import android.widget.TextView;

    import com.ryzen.ryan.carbonzero.R;

/**
 * Created by Ryan on 2/24/2018.
 */

public class CarbonFragment extends Fragment {


    TextView currentCarbon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_carbon, container, false);

        currentCarbon = v.findViewById(R.id.currentCarbon);

        int currentTotal = getArguments().getInt("totalCarbon");
        currentCarbon.setText(currentTotal+"");

        return v;
    }

}
