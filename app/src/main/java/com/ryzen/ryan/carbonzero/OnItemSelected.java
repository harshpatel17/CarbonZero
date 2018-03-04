package com.ryzen.ryan.carbonzero;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by mchan on 3/3/2018.
 */

public class OnItemSelected implements AdapterView.OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


}
