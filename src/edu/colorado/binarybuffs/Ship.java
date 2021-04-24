package edu.colorado.binarybuffs;

import java.util.ArrayList;

/** Ship is a superclass which can create different ship subclasses, which can be placed on certain player maps. Each ship only keeps track
        of their sizes, captains quarters, and name.
 * Minesweeper
 * Destroyer
 * Battleship
 * Submarine
 * Spaceshuttle
 */
public abstract class Ship {
    private String ship_name;
    private int ship_size;

    public Ship() {

    }


    public String getName() {
        return this.ship_name;
    }

    public int getShipSize(){
        return ship_size;
    }

    /** The below functions are implemented in each ship subclass, to keep track of what coordinates make up their length and their captain's quarters*/
    public abstract ArrayList<Coordinate> getCoords(int start_x, int start_y, String direction);

    public abstract Coordinate getCaptsCoords(int start_x, int start_y, String direction);

    public String toString(){
        return getName();
    }
}
