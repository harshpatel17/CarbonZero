package com.ryzen.ryan.carbonzero;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.ryzen.ryan.carbonzero.EarthWheelData;
import github.hellocsl.cursorwheel.CursorWheelLayout;


/**
 * Created by Ryan on 3/3/2018.
 */

public class EarthWheelAdapter extends CursorWheelLayout.CycleWheelAdapter {

    private Context mContext;
    private List<EarthWheelData> earthItems;
    private LayoutInflater inflater;
    private int gravity;




    public EarthWheelAdapter(Context mContext, List<EarthWheelData> earthItems) {
        this.mContext = mContext;
        this.earthItems = earthItems;
        gravity = Gravity.CENTER;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return earthItems.size();
    }

    @Override
    public View getView(View parent, int position) {
        EarthWheelData data = getItem(position);
        View root = inflater.inflate(R.layout.earth_wheel_layout, null, false);
        ImageView imageView= root.findViewById(R.id.wheel_image_item_iv);
        imageView.setImageResource(data.imageResource);
        return root;
    }

    @Override
    public EarthWheelData getItem(int position) {
        return earthItems.get(position);
    }
}
