package edu.colorado.binarybuffs;

import java.util.ArrayList;

/** Spaceshuttle subclass extends Ship super class and implements interface Orbitable and creates a Spaceshuttle ship */
public class Spaceshuttle extends Ship implements OrbitableShip {
    private String ship_name = "Spaceshuttle";
    private static int ship_size = 10;

    //Constructor for Spaceshuttle class
    public Spaceshuttle() {

    }

    //Getter method to get name of the ship
    @Override
    public String getName() {
        return this.ship_name;
    }

    //Getter method to get size of ship
    @Override
    public int getShipSize() {
        return this.ship_size;
    }

     /**
     * getCoords() creates series of coordinates that make up the Spaceshuttle
     * Takes in user's input of x, y coordinates and direction which the head of ship should face
     * Based on direction entered, creates 10 coordinates that make up length of Spaceshuttle, and adds them to an array list
     * @param start_x integer that marks starting x coordinate of ship
     * @param start_y integer that marks starting y coordinate of ship
     * @param direction String that takes in direction which user wants to place ship
     * @return array list of coordinates for ship's coordinates
     */
    public ArrayList<Coordinate> getCoords(int start_x, int start_y, String direction) {
        ArrayList<Coordinate> ship_cells = new ArrayList<Coordinate>();

        direction = direction.toLowerCase();

        if ((direction.equals("north")) || (direction.equals("n"))) {
            for (int i = 0; i < 10; i++) {
                Coordinate new_coordinate = new Coordinate(start_x, start_y + i);
                ship_cells.add(new_coordinate);
            }
            return ship_cells;
        } else if ((direction.equals("south")) || (direction.equals("s"))) {
            for (int i = 0; i < 10; i++) {
                Coordinate new_coordinate = new Coordinate(start_x, start_y - i);
                ship_cells.add(new_coordinate);
            }
            return ship_cells;
        } else if ((direction.equals("east")) || (direction.equals("e"))) {
            for (int i = 0; i < 10; i++) {
                Coordinate new_coordinate = new Coordinate(start_x - i, start_y);
                ship_cells.add(new_coordinate);
            }
            return ship_cells;
        } else if ((direction.equals("west")) || (direction.equals("w"))) {
            for (int i = 0; i < 10; i++) {
                Coordinate new_coordinate = new Coordinate(start_x + i, start_y);
                ship_cells.add(new_coordinate);
            }
            return ship_cells;
        }
        return null;
    }

     /**
     * getCaptsCoords() sets a single coordinate as the designated captain's quarters
     * Depending on which direction ship is placed, captain's quarters are placed 5 cells away from starting coordinate
      * @param start_x integer that marks starting x coordinate of ship
      * @param start_y integer that marks starting y coordinate of ship
      * @param direction String that takes in direction which user wants to place ship
      * @return coordinate of ship's capatain's quarters
     */
    public Coordinate getCaptsCoords(int start_x, int start_y, String direction){
        direction = direction.toLowerCase();

        if ((direction.equals("north")) || (direction.equals("n"))) {
            Coordinate coordinate1 = new Coordinate(start_x, start_y+5);
            return coordinate1;
        } else if ((direction.equals("south")) || (direction.equals("s"))) {
            Coordinate coordinate1 = new Coordinate(start_x, start_y-5);
            return coordinate1;
        } else if ((direction.equals("east")) || (direction.equals("e"))) {
            Coordinate coordinate1 = new Coordinate(start_x-5, start_y);
            return coordinate1;
        } else if ((direction.equals("west")) || (direction.equals("w"))) {
            Coordinate coordinate1 = new Coordinate(start_x+5, start_y);
            return coordinate1;
        }
        return null;
    }
}
