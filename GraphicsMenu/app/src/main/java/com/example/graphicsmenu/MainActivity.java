package com.example.graphicsmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    RadioButton red, blue, green;
    ImageView iv;
    Button rotate, zoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        red = findViewById(R.id.red);
        blue = findViewById(R.id.blue);
        green = findViewById(R.id.green);
        iv = findViewById(R.id.iv);
        rotate = findViewById(R.id.rotate);
        zoom = findViewById(R.id.zoom);

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bm = Bitmap.createBitmap(300, 300 , Bitmap.Config.ARGB_8888);
                iv.setBackgroundDrawable(new BitmapDrawable(bm));
                Canvas c = new Canvas(bm);
                Paint paint = new Paint();
                paint.setColor(Color.RED);

                c.drawCircle(100 ,100 , 75, paint);


            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bm = Bitmap.createBitmap(300, 300 , Bitmap.Config.ARGB_8888);
                iv.setBackgroundDrawable(new BitmapDrawable(bm));
                Canvas c = new Canvas(bm);
                Paint paint = new Paint();
                paint.setColor(Color.BLUE);

                c.drawCircle(100 ,100 , 75, paint);


            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bm = Bitmap.createBitmap(300, 300 , Bitmap.Config.ARGB_8888);
                iv.setBackgroundDrawable(new BitmapDrawable(bm));
                Canvas c = new Canvas(bm);
                Paint paint = new Paint();
                paint.setColor(Color.GREEN);

                c.drawCircle(100 ,100 , 75, paint);


            }
        });

        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate));
            }
        });

        zoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoom));
            }
        });



    }
}