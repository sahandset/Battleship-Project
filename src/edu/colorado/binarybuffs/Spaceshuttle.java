package edu.colorado.binarybuffs;

import java.util.ArrayList;

public class Spaceshuttle extends Ship implements OrbitableShip {
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
