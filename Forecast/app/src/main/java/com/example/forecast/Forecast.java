package com.example.forecast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Forecast extends AppCompatActivity {

    private ListView listView;
    private List<Day_Weather> forecast;
    public static final int[] images = { R.drawable.cloud, R.drawable.rain, R.drawable.sun};
    private Button b;
    private UserArrayAdapter userArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_forecast );

        createButton();
        createListView();

    }

    public void createForecast() {
        forecast = new ArrayList<>();
        for ( int i = 0; i < 10; i++) {
            double high = (int)(Math.random() * 110) - 10;
            double low = (int)(Math.random() * (high - 10) - 10);
            double farenheit = (int)(Math.random() * (high - low)) + low;
            forecast.add( new Day_Weather( ) );
        }
    }

    public void addToForecast() {
        for ( int i = 0; i < 10; i++) {
            double high = (int)(Math.random() * 110) - 10;
            double low = (int)(Math.random() * (high - 10) - 10);
            double farenheit = (int)(Math.random() * (high - low)) + low;
            forecast.add( new Day_Weather( ) );
        }
    }


    public void createListView() {
        listView = findViewById( R.id.listView );
        createForecast();
        userArrayAdapter = new UserArrayAdapter( this, forecast );
        listView.setAdapter( userArrayAdapter );
        //listView.addFooterView( b );
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) { }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if (listView.getLastVisiblePosition() == listView.getAdapter().getCount() -1 &&
                        listView.getChildAt(listView.getChildCount() - 1).getBottom() <= listView.getHeight())
                {
                    int y = forecast.size() - 1;
                    addToForecast();
                    listView = null;
                    listView = findViewById( R.id.listView );
                    userArrayAdapter = new UserArrayAdapter( getApplicationContext(), forecast);
                    listView.setAdapter( userArrayAdapter );
                    listView.setSelection( y );
                }

            }
        });
    }

    public void createButton () {
        b = findViewById( R.id.button2 );
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setSelection( 0 );
            }
        });
    }
}
