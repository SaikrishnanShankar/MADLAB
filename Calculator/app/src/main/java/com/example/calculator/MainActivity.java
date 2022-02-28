package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.fathzer.soft.javaluator.DoubleEvaluator;

public class MainActivity extends AppCompatActivity {
    TextView field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        field = findViewById(R.id.field);
    }


    public void handleClick(View view) {
        Button btn = (Button) view;
        String text = btn.getText().toString();
        if(text.equals("ans")){
            DoubleEvaluator de = new DoubleEvaluator();
            try {
                double result = de.evaluate(field.getText().toString());
                field.setText(String.valueOf(result));
            } catch (Exception e) {
                e.printStackTrace();
                field.setText("Math Error");
            }
        }
        else if(text.equals("del")){
            int len = field.getText().toString().length();
            if(len > 0){
                field.setText(field.getText().toString().substring(0, len - 1));
            }
        }
        else if(text.equals("cls")){
            field.setText("");
        }
        else{
            String current = field.getText().toString();
            field.setText(current + text);
        }
    }
}
