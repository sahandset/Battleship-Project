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

    //Constructor for Ship class
    public Ship() {

    }

    //Getter method to get name of the ship
    public String getName() {
        return this.ship_name;
    }

    //Getter method to get the size of the ship
    public int getShipSize(){
        return ship_size;
    }

    /**
     * getCoords() is an abstract function that creates series of coordinates that make up the ship
     * @param start_x integer that marks starting x coordinate of ship
     * @param start_y integer that marks starting y coordinate of ship
     * @param direction String that takes in direction which user wants to place ship
     * @return array list of coordinates for ship's coordinates
     */    public abstract ArrayList<Coordinate> getCoords(int start_x, int start_y, String direction);

    /**
     * getCaptsCoords() is an abstract function which sets a single coordinate as the designated captain's quarters
     * @param start_x integer that marks starting x coordinate of ship
     * @param start_y integer that marks starting y coordinate of ship
     * @param direction String that takes in direction which user wants to place ship
     * @return coordinate of ship's capatain's quarters
     */
    public abstract Coordinate getCaptsCoords(int start_x, int start_y, String direction);

    /**
     * toString() converts the name of the ship to a String value
     * @return String name of the ship
     */
    public String toString(){
        return getName();
    }
}
