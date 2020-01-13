package com.example.weather_app;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.example.weather_app.Day_Weather;

import java.io.File;
import java.util.List;


public class UserArrayAdapter extends ArrayAdapter<Day_Weather> {

    public UserArrayAdapter(@NonNull Context context, List<Day_Weather> days) {
        super(context, 0, days);
    }
    public View getView(int position, View view, ViewGroup parent ) {
        if ( view == null ) {
            view = LayoutInflater.from( getContext() ).inflate(R.layout.activity_main, parent, false );
        }
        Day_Weather day_weather = getItem(position);
        TextView farenheit = view.findViewById(R.id.farenheit);
        farenheit.setText( "Temperature: " + day_weather.getFarenheit() );
        TextView high = view.findViewById( R.id.high );
        high.setText( "High: " + day_weather.getHigh() );
        TextView low = view.findViewById( R.id.low );
        low.setText( "Low: " + day_weather.getLow() );
        ImageView imageView1 = view.findViewById( R.id.imageView2 );
        imageView1.setVisibility( View.GONE );
        ImageView imageView2 = view.findViewById( R.id.imageView3 );
        imageView2.setVisibility( View.GONE );
        ImageView imageView3 = view.findViewById( R.id.imageView4 );
        imageView3.setVisibility( View.GONE );
        int choice = (int)(Math.random()*3);
        switch ( choice ) {
            case 0:  imageView1.setVisibility( View.VISIBLE );  break;
            case 1: imageView2.setVisibility( View.VISIBLE );  break;
            default: imageView3.setVisibility( View.VISIBLE );
        }
        return view;
    }
}