package com.example.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText et;
    Button load_url, load_file;
    WebView wv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.url);
        load_file = findViewById(R.id.load_file);
        load_url = findViewById(R.id.load_url);
        wv = findViewById(R.id.web_view);

        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);


        load_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wv.loadUrl("file:///android_asset/sample.html");
            }
        });

        load_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wv.loadUrl(et.getText().toString());
            }
        });


    }
}