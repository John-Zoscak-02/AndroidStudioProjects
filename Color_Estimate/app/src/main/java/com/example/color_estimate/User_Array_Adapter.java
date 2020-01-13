package com.example.color_estimate;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class User_Array_Adapter extends ArrayAdapter<Result> {

    public User_Array_Adapter(@NonNull Context context, List<Result> days) {
        super(context, 0, days);
    }

    public View getView(int position, View view, ViewGroup parent ) {
        if ( view == null ) {
            view = LayoutInflater.from( getContext() ).inflate(R.layout.custom_array_adapter, parent, false );
        }
        Result result = getItem(position);
        TextView estimate = view.findViewById(R.id.textView5);
        estimate.setText( "Distance from color: " + result.getDistance() );
        ImageView imageView = view.findViewById( R.id.imageView2 );
        imageView.setBackgroundColor( result.getColor().toArgb() );
        return view;
    }
}
