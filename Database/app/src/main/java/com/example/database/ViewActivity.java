package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {
    SQLiteDatabase db;
    EditText name;
    TextView phone;
    Button view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        db = openOrCreateDatabase("db", MODE_PRIVATE, null);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        view = findViewById(R.id.view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c = db.rawQuery("select phone from user where name = ?", new String[]{name.getText().toString()});
                if (c.moveToNext()){
                    String p = c.getString(0);
                    phone.setText(p);
                }
                else{
                    phone.setText("Not found");
                }
            }
        });

    }
}