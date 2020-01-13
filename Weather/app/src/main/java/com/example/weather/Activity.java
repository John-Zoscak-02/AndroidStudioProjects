package com.example.weather;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Activity extends AppCompatActivity {

    private ListView listView;
    private List<Day_Weather> forecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main );

        createForecast();

        createListView();
    }

    public void createForecast() {
        for ( int i = 0; i < 10; i++) {
            forecast.add( new Day_Weather() );
        }
    }
    public void createListView() {
        listView = findViewById( R.id.listView );
        UserArrayAdapter userArrayAdapter = new UserArrayAdapter( this, forecast );
        listView.setAdapter( userArrayAdapter );
    }
}
