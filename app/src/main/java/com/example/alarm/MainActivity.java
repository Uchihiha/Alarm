package com.example.alarm;

import android.app.TimePickerDialog;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TimePicker;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String[] time = {
            "3:30",
            "4:00",
            "6:45",
            "7:00"
    };

    ListView list;

    String[] ampm = {
            "AM",
            "AM",
            "AM",
            "AM"
    };

    TimePickerDialog.OnTimeSetListener dialogListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minuteOfHour) {
            String AM_PM;

            if (hourOfDay == 0){
                hourOfDay += 12;
                AM_PM = "AM";
            }else if(hourOfDay < 12) {
                AM_PM = "AM";
            }else if (hourOfDay == 12){
                AM_PM = "PM";
            } else {
                hourOfDay -= 12;
                AM_PM = "PM";
            }

            String newTime = (String.format("%d:%02d", hourOfDay, minuteOfHour));

            time = Arrays.copyOf(time, time.length + 1);
            time[time.length - 1] = newTime;

            ampm = Arrays.copyOf(ampm, ampm.length + 1);
            ampm[ampm.length - 1] = AM_PM;
            CustomListAdapter newAdapter = new CustomListAdapter(MainActivity.this, time, ampm);
            list.findViewById(R.id.clockList);
            list.setAdapter(newAdapter);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int min = calendar.get(Calendar.MINUTE);

                new TimePickerDialog(MainActivity.this, dialogListener, hour, min, false).show();
            }
        });

        final CustomListAdapter adapter = new CustomListAdapter(this, time, ampm);
        list = findViewById(R.id.clockList);
        list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);

            new TimePickerDialog(MainActivity.this, dialogListener, hour, min, false).show();
        }

        return super.onOptionsItemSelected(item);
    }


}
