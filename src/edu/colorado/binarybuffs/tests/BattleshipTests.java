package edu.colorado.binarybuffs.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.colorado.binarybuffs.Grid;
import edu.colorado.binarybuffs.Player;
import edu.colorado.binarybuffs.Ship;
import org.junit.jupiter.api.Test;


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
    public void testShipPlacement() {
        Player player1 = new Player("Tanvi");
        Grid grid1 = new Grid();
        Ship test_ship = player1.createShip(2, 2, 2, 2, 4);
        assertEquals(4, test_ship.getShipLength(test_ship));
        //grid1.placeShip();
    }
}
