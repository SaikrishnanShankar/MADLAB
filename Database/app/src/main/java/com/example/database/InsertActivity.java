package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertActivity extends AppCompatActivity {
    EditText name, phone;
    SQLiteDatabase db;
    Button insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        db = openOrCreateDatabase("db", MODE_PRIVATE, null);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        insert = findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.execSQL("insert into user(name, phone) values(?, ?)",
                        new String[]{name.getText().toString(), phone.getText().toString()});
            }
        });


    }
}