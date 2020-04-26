package com.example.tempertatureconversion;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void changeUnit(View v){     // change the temperature unit
        Button b = (Button) v;
        b.setText((b.getText().toString().equals("Celsius"))?"Fahrenheit":"Celsius");
    }
    public void toHistory(View v){  // go to the history page
        startActivity(new Intent(this, History.class));
    }
    @SuppressLint("SetTextI18n")
    public void convert(View v) {
        EditText input = findViewById(R.id.tempInput);
        TextView display = findViewById(R.id.tempDisplay);
        boolean unit = (((Button)(findViewById(R.id.unitButton))).getText().equals("Celsius"));   //true->input c ; false->input f
        String inputString = input.getText().toString();
        if (inputString.length() == 0) {
            Toast.makeText(this, "Please check input and try again :)", Toast.LENGTH_LONG).show();
            Log.d("fail", "User have not input anything.");
        }else{
            if(inputString.contains(".")){
                double value = Double.parseDouble(inputString);
                if(unit) display.setText("" + value + "\u00B0C\n=\n" + CtoF(value) + "\u00B0F");
                else display.setText("" + value + "\u00B0F\n=\n" + FtoC(value) + "\u00B0C");
            }else {
                int value = Integer.parseInt(inputString);
                if(unit) display.setText("" + value + "\u00B0C\n=\n" + CtoF(value) + "\u00B0F");
                else display.setText("" + value + "\u00B0F\n=\n" + FtoC(value) + "\u00B0C");
            }
            Log.d("success", "converted to f/c");
        }
    }
    //--------------- helper methods -------------------
    public int CtoF(int c){ return (int)((9.0/5.0)*c+32); }
    public double CtoF(double c){ return (9.0/5.0)*c+32; }
    public int FtoC(int f){ return (int)((5.0/9.0)*(f-32)); }
    public double FtoC(double f){ return (5.0/9.0)*(f-32); }
}
