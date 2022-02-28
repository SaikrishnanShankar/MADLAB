package com.example.alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {
    TimePicker timePicker;
    TextClock currentTime;
    Button cancel, set, snooze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AtomicInteger cancelFlag = new AtomicInteger();
        cancelFlag.getAndSet(1);
        timePicker = findViewById(R.id.timePicker);
        currentTime = findViewById(R.id.currentTime);
        cancel = findViewById(R.id.cancel);
        set = findViewById(R.id.set);
        snooze =findViewById(R.id.snooze);
        Ringtone ringtone = RingtoneManager.getRingtone(MainActivity.this, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel.setVisibility(View.VISIBLE);
                cancelFlag.getAndSet(0);
                set.setVisibility(View.INVISIBLE);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set.setVisibility(View.VISIBLE);
                cancelFlag.getAndSet(1);
                cancel.setVisibility(View.INVISIBLE);
                snooze.setVisibility(View.INVISIBLE);
                ringtone.stop();
            }
        });
        snooze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int min = timePicker.getMinute();
                timePicker.setMinute(min + 5);
                ringtone.stop();
                snooze.setVisibility(View.INVISIBLE);
            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                String curTimeString = getAlarmTime();
                if(currentTime.getText().toString().equals(curTimeString) && cancelFlag.get() == 0) {
                    ringtone.play();


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            snooze.setVisibility(View.VISIBLE);
                        }
                    });

                }
            }
        }, 0, 1000);


    }

    public String getAlarmTime(){
        int hr = timePicker.getHour();
        int m = timePicker.getMinute();
        String hour, min;
        if (hr <=12 ){
            hour = String.valueOf(hr);
            if (hr < 10){
                hour = "0" + hour;
            }
        }else{
            hour = String.valueOf(hr-12);
        }
        min = String.valueOf(m);
        if (m < 10){
            min = "0" + min;
        }
        if (hr>=12) {
            return hour + ":" + min + " PM";
        }
        return hour + ":" + min + " AM";
    }
}