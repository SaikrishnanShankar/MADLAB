package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    SQLiteDatabase db;
    EditText name;
    EditText phone;
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        db = openOrCreateDatabase("db", MODE_PRIVATE, null);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        update = findViewById(R.id.update);

       update.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               db.execSQL("update user set phone = ? where name = ?",
                       new String[]{phone.getText().toString(), name.getText().toString()});
               Toast.makeText(getApplicationContext(), "Updated table", Toast.LENGTH_LONG);
           }
       });
    }
}