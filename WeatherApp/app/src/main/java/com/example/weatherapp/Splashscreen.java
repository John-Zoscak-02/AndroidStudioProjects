package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;

public class Splashscreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.splashscreen );

        runTimer();
    }

    public void runTimer() {
        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long l) { }
            @Override
            public void onFinish() {
                Intent intent2 = new Intent( getApplicationContext(), MainActivity.class );
                startActivity( intent2 );
            }
        }.start();
    }
}
