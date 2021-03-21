//package edu.colorado.binarybuffs.tests;
//
//import edu.colorado.binarybuffs.Coordinate;
//import edu.colorado.binarybuffs.Grid;
//import edu.colorado.binarybuffs.Player;
//import edu.colorado.binarybuffs.Ship;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class GridTest {
//
//    @Test
//    public void checkGrid() {
//        Grid myGrid = new Grid(); //testing the creation of grid
//    }
//
//    @Test
//    public void checkOffensiveGrid() {
//        Grid myGrid = new Grid();
//        for (int i = 0; i < myGrid.getSize(); i++) {
//            for (int j = 0; j < myGrid.getSize(); j++) {
//                assertEquals(1, myGrid.checkOffensiveGridStatus(i,j));
//            }
//        }
//    }
//
//    @Test
//    public void getGridSize() {
//        Grid myGrid = new Grid();
//        assertEquals(10, myGrid.getSize(), "grid must be 10x10"); //checking for size of grid
//    }
//
//    @Test
//    public void testCheckCellStatus() {
//        Grid myGrid = new Grid();
//        for (int i = 0; i < myGrid.getSize(); i++) {
//            for (int j = 0; j < myGrid.getSize(); j++) {
//                assertEquals(0, myGrid.checkCellStatus(i, j));
//            }
//        }
//    }
//
//    @Test
//    public void testShipPlacement() {
//        Player player1 = new Player("Tanvi");
//        Grid grid1 = new Grid();
//        ArrayList<Ship> test_fleet;
//        test_fleet = player1.createFleet();
//        for (int i = 0; i < test_fleet.size(); i++) {
//            grid1.placeShip(test_fleet.get(i), 1, 1, "south");
//        }
//        assertEquals(1, grid1.player_grid[1][1]);
//    }
//
//    @Test
//    public void testShipPlacement2() {
//        Player player1 = new Player("Tanvi");
//        Grid grid1 = new Grid();
//        ArrayList<Ship> test_fleet;
//        test_fleet = player1.createFleet();
//        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
//        Coordinate coord1 = new Coordinate(1, 2);
//        Coordinate coord3 = new Coordinate(2, 4);
//        Coordinate coord5 = new Coordinate(3, 5);
//
//        coordinates.add(coord1);
//        coordinates.add(coord3);
//        coordinates.add(coord5);
//
//        for (int i = 0; i < test_fleet.size(); i++) {
//            grid1.placeShip(test_fleet.get(i), coordinates.get(i).x, coordinates.get(i).y, "south");
//        }
//        assertEquals(1, grid1.player_grid[2][5]);
//    }
//}
