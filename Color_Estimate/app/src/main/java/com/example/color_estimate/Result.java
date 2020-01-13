package com.example.color_estimate;

import android.graphics.Color;

public class Result {

    private Color color;
    private double distance;

    public Result( Color color, Double distance) {
        this.color = color;
        this.distance = distance;
    }

    public Color getColor() {
        return color;
    }

    public double getDistance() { return distance; }

}
