package edu.colorado.binarybuffs;

import java.util.ArrayList;

public class Submarine extends newShip implements SubmersibleShip {
    private String ship_name = "Submarine";
    private static int ship_size = 5;

    public Submarine() {

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
            Coordinate coordinate1 = new Coordinate(start_x, start_y);
            Coordinate coordinate2 = new Coordinate(start_x, start_y + 1);
            Coordinate coordinate3 = new Coordinate(start_x, start_y + 2);
            Coordinate coordinate4 = new Coordinate(start_x + 1, start_y + 2);
            Coordinate coordinate5 = new Coordinate(start_x, start_y + 3);
            ship_cells.add(coordinate1);
            ship_cells.add(coordinate2);
            ship_cells.add(coordinate3);
            ship_cells.add(coordinate4);
            ship_cells.add(coordinate5);
            return ship_cells;
        } else if ((direction.equals("south")) || (direction.equals("s"))) {
            Coordinate coordinate1 = new Coordinate(start_x, start_y);
            Coordinate coordinate2 = new Coordinate(start_x, start_y - 1);
            Coordinate coordinate3 = new Coordinate(start_x, start_y - 2);
            Coordinate coordinate4 = new Coordinate(start_x - 1, start_y - 2);
            Coordinate coordinate5 = new Coordinate(start_x, start_y - 3);
            ship_cells.add(coordinate1);
            ship_cells.add(coordinate2);
            ship_cells.add(coordinate3);
            ship_cells.add(coordinate4);
            ship_cells.add(coordinate5);
            return ship_cells;
        } else if ((direction.equals("east")) || (direction.equals("e"))) {
            Coordinate coordinate1 = new Coordinate(start_x, start_y);
            Coordinate coordinate2 = new Coordinate(start_x - 1, start_y);
            Coordinate coordinate3 = new Coordinate(start_x - 2, start_y);
            Coordinate coordinate4 = new Coordinate(start_x - 2, start_y + 1);
            Coordinate coordinate5 = new Coordinate(start_x - 3, start_y);
            ship_cells.add(coordinate1);
            ship_cells.add(coordinate2);
            ship_cells.add(coordinate3);
            ship_cells.add(coordinate4);
            ship_cells.add(coordinate5);
            return ship_cells;
        } else if ((direction.equals("west")) || (direction.equals("w"))) {
            Coordinate coordinate1 = new Coordinate(start_x, start_y);
            Coordinate coordinate2 = new Coordinate(start_x + 1, start_y);
            Coordinate coordinate3 = new Coordinate(start_x + 2, start_y);
            Coordinate coordinate4 = new Coordinate(start_x + 2, start_y - 1);
            Coordinate coordinate5 = new Coordinate(start_x + 3, start_y);
            ship_cells.add(coordinate1);
            ship_cells.add(coordinate2);
            ship_cells.add(coordinate3);
            ship_cells.add(coordinate4);
            ship_cells.add(coordinate5);
            return ship_cells;
        }
        return null;
    }

    public Coordinate getCaptsCoords(int start_x, int start_y, String direction){
        direction = direction.toLowerCase();

        if ((direction.equals("north")) || (direction.equals("n"))) {
            Coordinate coordinate1 = new Coordinate(start_x, start_y+3);
            return coordinate1;
        } else if ((direction.equals("south")) || (direction.equals("s"))) {
            Coordinate coordinate1 = new Coordinate(start_x, start_y-3);
            return coordinate1;
        } else if ((direction.equals("east")) || (direction.equals("e"))) {
            Coordinate coordinate1 = new Coordinate(start_x-3, start_y);
            return coordinate1;
        } else if ((direction.equals("west")) || (direction.equals("w"))) {
            Coordinate coordinate1 = new Coordinate(start_x+3, start_y);
            return coordinate1;
        }
        return null;
    }
}
