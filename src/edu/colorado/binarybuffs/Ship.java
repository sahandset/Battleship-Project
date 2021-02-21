package edu.colorado.binarybuffs;

import java.util.ArrayList;

// This is the  baseclass for your ship.  Modify accordingly
// TODO: practice good OO design
public class Ship {
    private String ship_name;
    private int ship_length;
    private int start_x;
    private int start_y;
    private int end_x;
    private int end_y;
    private int health_value;
    private String status;
    private ArrayList<Coordinate> ship_cells;

    public Ship(String ship_name, int ship_length) {
        this.ship_name = ship_name;
        this.ship_length = ship_length;
        this.health_value = ship_length;
        this.status = "alive";
//        this.start_x = start_x;
//        this.start_y = start_y;
//        this.end_x = end_x;
//        this.end_y = end_y;
    }

    //Team BinaryBuffs, pair 1 was here
    // Team Binary Buffs, pair 2 was here

    // TODO: create appropriate getter and setter methods
    // TODO: Understand encapsulation
    // TODO: Understand what these todo comments mean

    public void show() {     // dunno why this is here maybe it is just an example method
        System.out.println("IF you can't see this then something is severely wrong!!");
    }

    public void setShipCoordinates(int start_x, int start_y, int end_x, int end_y) {
        this.start_x = start_x;
        this.start_y = start_y;
        this.end_x = end_x;
        this.end_y = end_y;

        ArrayList<Coordinate> ship_cells = new ArrayList<Coordinate>();

        if (start_x == end_x) {
            if (start_y < end_y) {
                for (int i = start_y; i < end_y; i++) {
                    Coordinate coordinate = new Coordinate(start_x, i);
                    ship_cells.add(coordinate);
                }
            } else if (end_y < start_y) {
                for (int i = end_y; i < start_y; i++) {
                    Coordinate coordinate = new Coordinate(start_x, i);
                    ship_cells.add(coordinate);
                }
            }
        } else if (start_y == end_y) {
            if (start_x < end_x) {
                for (int i = start_x; i < end_x; i++) {
                    Coordinate coordinate = new Coordinate(i, start_y);
                    ship_cells.add(coordinate);
                }
            } else if (end_x < start_x) {
                for (int i = end_x; i < start_x; i++) {
                    Coordinate coordinate = new Coordinate(i, start_y);
                    ship_cells.add(coordinate);
                }
            }
        }
    }

    public String getShipName(Ship ship1) {
        return this.ship_name;
    }

    public int getShipLength(Ship ship1) {
        return this.ship_length;
    }

    public ArrayList<Coordinate> getShipCoordinates(Ship ship) {
        return this.ship_cells;
    }
}

