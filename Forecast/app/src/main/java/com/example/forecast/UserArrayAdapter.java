package com.example.forecast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class UserArrayAdapter extends ArrayAdapter<Day_Weather> {

    public UserArrayAdapter(@NonNull Context context, List<Day_Weather> days) {
        super(context, 0, days);
    }
    public View getView(int position, View view, ViewGroup parent ) {

        if ( view == null ) {
            view = LayoutInflater.from( getContext() ).inflate(R.layout.customlayout, parent, false );
        }

        Day_Weather day_weather = getItem( position );

        TextView farenheit = view.findViewById(R.id.farenheit);
        farenheit.setText( "Temperature: " + day_weather.getFarenheit() + " degrees" );

        TextView high = view.findViewById( R.id.high );
        high.setText( "High: " + day_weather.getHigh() + " degrees" );

        TextView low = view.findViewById( R.id.low );
        low.setText( "Low: " + day_weather.getLow() + " degrees" );

        ImageView imageView1 = view.findViewById( R.id.imageView2 );
        imageView1.setImageResource( Forecast.images[ day_weather.getImg() ] );

        return view;
    }

}
