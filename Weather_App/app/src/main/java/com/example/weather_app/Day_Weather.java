package com.example.weather_app;

import android.widget.ImageView;

public class Day_Weather {

    private double farenheit;
    private double high;
    private double low;

    public Day_Weather ( ) {
        high = (int)(Math.random() * 110) - 10;
        low = (int)(Math.random() * (high - 10) - 10);
        farenheit = (int)(Math.random() * (high - low)) + low;
    }

    public double getFarenheit() { return farenheit; }

    public double getHigh() { return high; }

    public double getLow() { return low; }

    public void setFarenheit( double farenheit) { this.farenheit = farenheit; }

    public void setHigh( double high ) { this.high = high; }

    public void setLow( double low ) { this.low = low; }


}
