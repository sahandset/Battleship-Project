package edu.colorado.binarybuffs;

import java.util.ArrayList;

public class Spaceshuttle extends newShip implements OrbitableShip{
    private String ship_name = "Spaceshuttle";
    private static int ship_size = 10;

    public Spaceshuttle() {

    }

    @Override
    public String getName() {
        return this.ship_name;
    }

    @Override
    public int getShipSize() {
        return this.ship_size;
    }

    public ArrayList<Coordinate> getCoords(int start_x, int start_y, String direction) {
        ArrayList<Coordinate> ship_cells = new ArrayList<Coordinate>();

        if ((direction.toLowerCase() == "north") || (direction.toLowerCase() == "n")) {
            for (int i = 0; i < 10; i++) {
                Coordinate new_coordinate = new Coordinate(start_x, start_y + i);
                ship_cells.add(new_coordinate);
            }
            return ship_cells;
        } else if ((direction.toLowerCase() == "south") || (direction.toLowerCase() == "s")) {
            for (int i = 0; i < 10; i++) {
                Coordinate new_coordinate = new Coordinate(start_x, start_y - i);
                ship_cells.add(new_coordinate);
            }
            return ship_cells;
        } else if ((direction.toLowerCase() == "east") || (direction.toLowerCase() == "e")) {
            for (int i = 0; i < 10; i++) {
                Coordinate new_coordinate = new Coordinate(start_x - i, start_y);
                ship_cells.add(new_coordinate);
            }
            return ship_cells;
        } else if ((direction.toLowerCase() == "west") || (direction.toLowerCase() == "w")) {
            for (int i = 0; i < 10; i++) {
                Coordinate new_coordinate = new Coordinate(start_x + i, start_y);
                ship_cells.add(new_coordinate);
            }
            return ship_cells;
        }
        return null;
    }

    public Coordinate getCaptsCoords(int start_x, int start_y, String direction){
        if ((direction.toLowerCase() == "north") || (direction.toLowerCase() == "n")) {
            Coordinate coordinate1 = new Coordinate(start_x, start_y+5);
            return coordinate1;
        } else if ((direction.toLowerCase() == "south") || (direction.toLowerCase() == "s")) {
            Coordinate coordinate1 = new Coordinate(start_x, start_y-5);
            return coordinate1;
        } else if ((direction.toLowerCase() == "east") || (direction.toLowerCase() == "e")) {
            Coordinate coordinate1 = new Coordinate(start_x-5, start_y);
            return coordinate1;
        } else if ((direction.toLowerCase() == "west") || (direction.toLowerCase() == "w")) {
            Coordinate coordinate1 = new Coordinate(start_x+5, start_y);
            return coordinate1;
        }
        return null;
    }
}
