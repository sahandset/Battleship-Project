package edu.colorado.binarybuffs.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.colorado.binarybuffs.Grid;
import edu.colorado.binarybuffs.Player;
import edu.colorado.binarybuffs.Ship;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class BattleshipTests {

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
                assertEquals(1, myGrid.checkCellStatus(i, j));
            }
        }
    }

    @Test
    public void testPlayer() {
        Player player_1 = new Player("Sahand");
        Player player_2 = new Player("Tanvi");
        assertEquals("Sahand", player_1.getName(player_1));
        assertEquals(false, player_1.getTurn(player_1));
    }

    @Test
    public void testShipFleetCreation() {
        Player player1 = new Player("Tanvi");
        Grid grid1 = new Grid();
        ArrayList<Ship> test_fleet;
        test_fleet = player1.createFleet();
        assertEquals(test_fleet.get(0).getShipName(test_fleet.get(0)), "Minesweeper");
        assertEquals(test_fleet.get(1).getShipName(test_fleet.get(1)), "Destroyer");
        assertEquals(test_fleet.get(2).getShipName(test_fleet.get(2)), "Battleship");
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
        assertEquals(1, grid1.player_grid[1][1]);
    }



//    @Test
//    public void attackTest(){
//        Player player1 = new Player("Tanvi");
//        Grid grid1 = new Grid();
//        Player player2 = new Player("Sahand");
//        Grid grid2 = new Grid();
//
//        Ship player1_fleet = player1.createFleet(2, 2, 2, 2, 4);
//        //grid1.placeShip(player1_fleet)
//        Ship player2_ship = player2.createFleet(3, 3, 3, 3, 6);
//        //grid2.placeShip(player2_fleet)
//
//        //player1.attack(3,3, grid1, grid2);
//    }
}
