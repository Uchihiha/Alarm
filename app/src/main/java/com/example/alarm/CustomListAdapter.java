package com.example.alarm;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DigitalClock;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] time;
    private final String[] ampm;

    public CustomListAdapter(Activity context, String[] time, String[] ampm) {
        super(context, R.layout.clock_fragment, time);

        this.context = context;
        this.time = time;
        this.ampm = ampm;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.clock_fragment, null, true);

        TextView timeText = rowView.findViewById(R.id.clock);
        TextView ampmText = rowView.findViewById(R.id.ampmText);

        timeText.setText(time[position]);
        ampmText.setText(ampm[position]);
        return rowView;
    };

}
