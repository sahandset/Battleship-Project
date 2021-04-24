package edu.colorado.binarybuffs;
import java.util.ArrayList;

/** Minesweeper subclass extends Ship super class, and creates a Minesweeper ship */
public class Minesweeper extends Ship {
    private String ship_name = "Minesweeper";
    private static int ship_size = 2;

    //Constructor for Minesweeper class
    public Minesweeper() {

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

     /**
     * getCoords() creates series of coordinates that make up the Minesweeper
     * Takes in user's input of x, y coordinates and direction which the head of ship should face
     * Based on direction entered, creates 2 coordinates that make up length of Minesweeper, and adds them to an array list
     * @param start_x integer that marks starting x coordinate of ship
     * @param start_y integer that marks starting y coordinate of ship
     * @param direction String that takes in direction which user wants to place ship
     * @return array list of coordinates for ship's coordinates
     */
    public ArrayList<Coordinate> getCoords(int start_x, int start_y, String direction) {
        ArrayList<Coordinate> ship_cells = new ArrayList<Coordinate>();

        direction = direction.toLowerCase();

        if ((direction.equals("north")) || (direction.equals("n"))) {
            Coordinate coordinate1 = new Coordinate(start_x, start_y);
            Coordinate coordinate2 = new Coordinate(start_x, start_y + 1);
            ship_cells.add(coordinate1);
            ship_cells.add(coordinate2);
            return ship_cells;
        } else if ((direction.equals("south")) || (direction.equals("s"))) {
            Coordinate coordinate1 = new Coordinate(start_x, start_y);
            Coordinate coordinate2 = new Coordinate(start_x, start_y - 1);
            ship_cells.add(coordinate1);
            ship_cells.add(coordinate2);
            return ship_cells;
        } else if ((direction.equals("east")) || (direction.equals("e"))) {
            Coordinate coordinate1 = new Coordinate(start_x, start_y);
            Coordinate coordinate2 = new Coordinate(start_x - 1, start_y);
            ship_cells.add(coordinate1);
            ship_cells.add(coordinate2);
            return ship_cells;
        } else if ((direction.equals("west")) || (direction.equals("w"))) {
            Coordinate coordinate1 = new Coordinate(start_x, start_y);
            Coordinate coordinate2 = new Coordinate(start_x + 1, start_y);
            ship_cells.add(coordinate1);
            ship_cells.add(coordinate2);
            return ship_cells;
        }
        return null;
    }

     /**
     * getCaptsCoords() sets a single coordinate as the designated captain's quarters
     * Sets the first coordinate of the Minesweeper as the captain's quarters
     * @param start_x integer that marks starting x coordinate of ship
     * @param start_y integer that marks starting y coordinate of ship
     * @param direction String that takes in direction which user wants to place ship
     * @return coordinate of ship's capatain's quarters
     */
    public Coordinate getCaptsCoords(int start_x, int start_y, String direction){
        Coordinate coordinate1 = new Coordinate(start_x,start_y);
        return coordinate1;
    }
}


