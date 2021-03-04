package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.Coordinate;
import edu.colorado.binarybuffs.Grid;
import edu.colorado.binarybuffs.Player;
import edu.colorado.binarybuffs.Ship;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GridTest {

    @Test
    public void checkGrid() {
        Grid myGrid = new Grid(); //testing the creation of grid
    }

    @Test
    public void checkOffensiveGrid() {
        Grid myGrid = new Grid();
        for (int i = 0; i < myGrid.getSize(); i++) {
            for (int j = 0; j < myGrid.getSize(); j++) {
                assertEquals(1, myGrid.checkOffensiveGridStatus(i,j));
            }
        }
    }

    @Test
    public void getGridSize() {
        Grid myGrid = new Grid();
        assertEquals(10, myGrid.getSize(), "grid must be 10x10"); //checking for size of grid
    }

    @Test
    public void testCheckCellStatus() {
        Grid myGrid = new Grid();
        for (int i = 0; i < myGrid.getSize(); i++) {
            for (int j = 0; j < myGrid.getSize(); j++) {
                assertEquals(0, myGrid.checkCellStatus(i, j));
            }
        }
    }

    @Test
    public void testShipPlacement() {
        Player player1 = new Player("Tanvi");
        Grid grid1 = new Grid();
        ArrayList<Ship> test_fleet;
        test_fleet = player1.createFleet();
        for (int i = 0; i < test_fleet.size(); i++) {
            grid1.placeShip(test_fleet.get(i), 1, 1, 1, 1);
        }
        assertEquals(0, grid1.player_grid[1][1]);
    }

    @Test
    public void testShipPlacement2() {
        Player player1 = new Player("Tanvi");
        Grid grid1 = new Grid();
        ArrayList<Ship> test_fleet;
        test_fleet = player1.createFleet();
        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
        Coordinate coord1 = new Coordinate(1, 2);
        Coordinate coord2 = new Coordinate(1, 4);
        Coordinate coord3 = new Coordinate(2, 4);
        Coordinate coord4 = new Coordinate(2, 7);
        Coordinate coord5 = new Coordinate(3, 5);
        Coordinate coord6 = new Coordinate(3, 9);

        coordinates.add(coord1);
        coordinates.add(coord2);
        coordinates.add(coord3);
        coordinates.add(coord4);
        coordinates.add(coord5);
        coordinates.add(coord6);


        for (int i = 0; i < test_fleet.size(); i++) {
            grid1.placeShip(test_fleet.get(i), coordinates.get(2 * i).x, coordinates.get(2 * i).y, coordinates.get(2 * i + 1).x, coordinates.get(2 * i + 1).y);
        }
        assertEquals(1, grid1.player_grid[2][5]);
    }
}
