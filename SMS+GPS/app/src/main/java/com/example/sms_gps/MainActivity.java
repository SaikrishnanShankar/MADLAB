package com.example.sms_gps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public Button send;
    public EditText message_contents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send = (Button)findViewById(R.id.send);
        message_contents = (EditText) findViewById(R.id.message_contents);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.SEND_SMS}, 1);

        SmsManager smsManager  = SmsManager.getDefault();
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        LocationListener ll = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                String contents = message_contents.getText().toString() +
                        "\nLatitude: " + String.valueOf(location.getLatitude()) + "" +
                        "\nLongitude: " + String.valueOf(location.getLongitude());
                smsManager.sendTextMessage("5554", null, contents,null, null );
            }
            public void onStatusChanged(String s, int x, android.os.Bundle b){
                ;
            }


        };

        send.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                lm.requestLocationUpdates("gps", 5000, 0, ll);

            }
        });



    }
}