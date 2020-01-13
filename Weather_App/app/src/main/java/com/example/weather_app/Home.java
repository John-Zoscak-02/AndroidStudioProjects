package com.example.weather_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class Home extends AppCompatActivity {

    private ListView listView;
    private List<Day_Weather> forecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_home );

        createListView();
    }

    public void createForecast() {
        for ( int i = 0; i < 10; i++) {
            forecast.add( new Day_Weather() );
        }
    }
    public void createListView() {
        listView = findViewById( R.id.listView );
        createForecast();
        UserArrayAdapter userArrayAdapter = new UserArrayAdapter( this, forecast );
        listView.setAdapter( userArrayAdapter );
    }
}
