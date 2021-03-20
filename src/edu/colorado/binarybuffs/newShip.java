package edu.colorado.binarybuffs;

import java.util.ArrayList;

public abstract class newShip {
    private String ship_name;
    private int ship_length;
    private int health_value;

    public newShip() {

    }

    public abstract ArrayList<Coordinate> getCoords(int start_x, int start_y, String direction);

    public abstract Coordinate getCaptsCoords(int start_x, int start_y, String direction);
}
