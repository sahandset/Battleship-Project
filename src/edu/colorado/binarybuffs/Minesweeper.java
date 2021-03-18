package edu.colorado.binarybuffs;
import java.util.ArrayList;

public class Minesweeper extends newShip {
    private String ship_name = "Minesweeper";
    private static int ship_length = 2;

    public Minesweeper() {

    }

    public ArrayList<Coordinate> getCoordOffsets(int start_x, int start_y, String direction) {
        ArrayList<Coordinate> ship_cells = new ArrayList<Coordinate>();

        if ((direction.toLowerCase() == "north") || (direction.toLowerCase() == "n")) {
            Coordinate coordinate1 = new Coordinate(start_x, start_y);
            Coordinate coordinate2 = new Coordinate(start_x, start_y + 1);
            ship_cells.add(coordinate1);
            ship_cells.add(coordinate2);
            return ship_cells;
        } else if ((direction.toLowerCase() == "south") || (direction.toLowerCase() == "s")) {
            Coordinate coordinate1 = new Coordinate(start_x, start_y);
            Coordinate coordinate2 = new Coordinate(start_x, start_y - 1);
            ship_cells.add(coordinate1);
            ship_cells.add(coordinate2);
            return ship_cells;
        } else if ((direction.toLowerCase() == "east") || (direction.toLowerCase() == "e")) {
            Coordinate coordinate1 = new Coordinate(start_x, start_y);
            Coordinate coordinate2 = new Coordinate(start_x - 1, start_y);
            ship_cells.add(coordinate1);
            ship_cells.add(coordinate2);
            return ship_cells;
        } else if ((direction.toLowerCase() == "west") || (direction.toLowerCase() == "w")) {
            Coordinate coordinate1 = new Coordinate(start_x, start_y);
            Coordinate coordinate2 = new Coordinate(start_x + 1, start_y);
            ship_cells.add(coordinate1);
            ship_cells.add(coordinate2);
            return ship_cells;
        }
        return null;
    }

    public Coordinate getCaptsOffset(int start_x, int start_y, String direction){
        Coordinate coordinate1 = new Coordinate(start_x,start_y);
        return coordinate1;
    }
}


