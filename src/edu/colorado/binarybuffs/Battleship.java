package edu.colorado.binarybuffs;

import java.util.ArrayList;

/** Battleship subclass extends Ship super class and implements interface ArmoredShip and creates a Battleship ship*/
public class Battleship extends Ship implements ArmoredShip{
    private String ship_name = "Battleship";
    private static int ship_size = 4;
    private int hitCount = 0;

    //Constructor for Battleship
    public Battleship() {

    }

    //Getter function to get name of ship
    @Override
    public String getName() {
        return this.ship_name;
    }

    //Getter function to get size of ship
    @Override
    public int getShipSize() {
        return this.ship_size;
    }

    //Getter function to get hit count on ship
    @Override
    public int getHitCount() {
        return this.hitCount;
    }

    /** updateHitCount() increments how many times the ship has been hit */
    @Override
    public void updateHitCount() {
        hitCount++;
    }

     /**
     * getCoords() creates series of coordinates that make up the Battleship
     * Takes in user's input of x, y coordinates and direction which the head of ship should face
     * Based on direction entered, creates 4 coordinates that make up length of Battleship, and adds them to an array list
     * @param start_x integer that marks starting x coordinate of ship
     * @param start_y integer that marks starting y coordinate of ship
     * @param direction String that takes in direction which user wants to place ship
     * @return array list of coordinates for ship's coordinates
     */
    public ArrayList<Coordinate> getCoords(int start_x, int start_y, String direction) {
        ArrayList<Coordinate> ship_cells = new ArrayList<>();

        direction = direction.toLowerCase();

        if ((direction.equals("north")) || (direction.equals("n"))) {
            Coordinate coordinate1 = new Coordinate(start_x, start_y);
            Coordinate coordinate2 = new Coordinate(start_x, start_y + 1);
            Coordinate coordinate3 = new Coordinate(start_x, start_y + 2);
            Coordinate coordinate4 = new Coordinate(start_x, start_y + 3);
            ship_cells.add(coordinate1);
            ship_cells.add(coordinate2);
            ship_cells.add(coordinate3);
            ship_cells.add(coordinate4);
            return ship_cells;
        } else if ((direction.equals("south")) || (direction.equals("s"))) {
            Coordinate coordinate1 = new Coordinate(start_x, start_y);
            Coordinate coordinate2 = new Coordinate(start_x, start_y - 1);
            Coordinate coordinate3 = new Coordinate(start_x, start_y - 2);
            Coordinate coordinate4 = new Coordinate(start_x, start_y - 3);
            ship_cells.add(coordinate1);
            ship_cells.add(coordinate2);
            ship_cells.add(coordinate3);
            ship_cells.add(coordinate4);
            return ship_cells;
        } else if ((direction.equals("east") || (direction.equals("e")))) {
            Coordinate coordinate1 = new Coordinate(start_x, start_y);
            Coordinate coordinate2 = new Coordinate(start_x - 1, start_y);
            Coordinate coordinate3 = new Coordinate(start_x - 2, start_y);
            Coordinate coordinate4 = new Coordinate(start_x - 3, start_y);
            ship_cells.add(coordinate1);
            ship_cells.add(coordinate2);
            ship_cells.add(coordinate3);
            ship_cells.add(coordinate4);
            return ship_cells;
        } else if ((direction.equals("west")) || (direction.equals("w"))) {
            Coordinate coordinate1 = new Coordinate(start_x, start_y);
            Coordinate coordinate2 = new Coordinate(start_x + 1, start_y);
            Coordinate coordinate3 = new Coordinate(start_x + 2, start_y);
            Coordinate coordinate4 = new Coordinate(start_x + 3, start_y);
            ship_cells.add(coordinate1);
            ship_cells.add(coordinate2);
            ship_cells.add(coordinate3);
            ship_cells.add(coordinate4);
            return ship_cells;
        }
        return null;
    }

     /**
     * getCaptsCoords() sets a single coordinate as the designated captain's quarters
     * Depending on which direction ship is placed, captain's quarters are placed 2 cells away from starting coordinate
     * @param start_x integer that marks starting x coordinate of ship
     * @param start_y integer that marks starting y coordinate of ship
     * @param direction String that takes in direction which user wants to place ship
     * @return coordinate of ship's capatain's quarters
     */
    public Coordinate getCaptsCoords(int start_x, int start_y, String direction){
        direction = direction.toLowerCase();

        if ((direction.equals("north")) || (direction.equals("n"))) {
            Coordinate coordinate1 = new Coordinate(start_x, start_y+2);
            return coordinate1;
        } else if ((direction.equals("south")) || (direction.equals("s"))) {
            Coordinate coordinate1 = new Coordinate(start_x, start_y-2);
            return coordinate1;
        } else if ((direction.equals("east")) || (direction.equals("e"))) {
            Coordinate coordinate1 = new Coordinate(start_x-2, start_y);
            return coordinate1;
        } else if ((direction.equals("west")) || (direction.equals("w"))) {
            Coordinate coordinate1 = new Coordinate(start_x+2, start_y);
            return coordinate1;
        }
        return null;
    }
}
