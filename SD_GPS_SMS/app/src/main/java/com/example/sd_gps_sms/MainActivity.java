package com.example.sd_gps_sms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.SmsManager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    Button send, read;
    EditText msg, phone;
    TextView contents;
    String latitude = "", longitude= " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send = findViewById(R.id.send);
        read = findViewById(R.id.read);
        msg = findViewById(R.id.msg);
        phone = findViewById(R.id.phone);
        contents = findViewById(R.id.contents);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.SEND_SMS}, 0);


        SmsManager smsManager = SmsManager.getDefault();
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);


        LocationListener ll = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                latitude = String.valueOf(location.getLatitude());
                longitude = String.valueOf(location.getLongitude());
                smsManager.sendTextMessage(phone.getText().toString(),
                        null,
                        msg.getText().toString() + "\nLat: " + latitude + " Lon:" + longitude,
                        null,
                        null);
                Toast.makeText(MainActivity.this, "Message sent", Toast.LENGTH_LONG).show();
                File f = new File(Environment.getExternalStorageDirectory(), "sample.txt");
                FileOutputStream fs = null;
                try {
                    fs = new FileOutputStream(f);
                    fs.write(new String(msg.getText().toString() + "\nLat: " + latitude + " Lon:" + longitude).getBytes(StandardCharsets.UTF_8));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, "Message Written to SD", Toast.LENGTH_LONG).show();
            }
            public void onStatusChanged(String s, int i, Bundle b){
                ;
            }
        };


        send.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                lm.requestLocationUpdates("gps", 50000, 0, ll);
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File f = new File(Environment.getExternalStorageDirectory(), "sample.txt");
                FileReader fr = null;
                String buff = "", c = "";
                try {
                    fr = new FileReader(f);
                    BufferedReader br = new BufferedReader(fr);
                    while ((buff=br.readLine()) != null) c += buff + "\n";

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                contents.setText(c);
            }
        });



    }
}