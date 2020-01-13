package com.example.weather_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main );

        runTimer();
    }

    public void runTimer() {
        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long l) { }
            @Override
            public void onFinish() {
                Intent intent = new Intent( getApplicationContext(), Home.class );
                startActivity( intent );
            }
        }.start();
    }
}

