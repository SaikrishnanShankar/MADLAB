package com.example.multithreading;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button count;
    TextView counter;
    ProgressBar pb;
    TextView Banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Banner = findViewById(R.id.Banner);
        count = findViewById(R.id.count);
        counter = findViewById(R.id.counter);
        pb = findViewById(R.id.progressBar);

        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = Integer.parseInt(counter.getText().toString());
                i+=1;
                counter.setText(String.valueOf(i));
            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 100; i++){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Random rand = new Random();
                            Banner.animate().translationXBy(5);
                            Banner.setTextColor(Color.argb(255, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
                            pb.setProgress(pb.getProgress() + 1);
                            pb.getProgressDrawable().setColorFilter(Color.argb(255, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)), PorterDuff.Mode.SRC_IN);
                        }
                    });
                }
            }
        }).start();



    }
}