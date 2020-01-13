package com.example.forecast;

public class Day_Weather {

    private double farenheit;
    private double high;
    private double low;
    private int img;

    public Day_Weather ( ) {
        high = (int)(Math.random() * 120);
        low = (int)(Math.random() * (high - 10) - 10);
        farenheit = (int)(Math.random() * (high - low)) + low;
        img = (int)(Math.random()*3);
    }

    public double getFarenheit() { return farenheit; }

    public double getHigh() { return high; }

    public double getLow() { return low; }

    public int getImg() { return img; }

    public void setFarenheit(double farenheit) { this.farenheit = farenheit; }

    public void setHigh( double high ) { this.high = high; }

    public void setLow( double low ) { this.low = low; }

}
