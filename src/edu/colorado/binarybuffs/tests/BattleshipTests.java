package edu.colorado.binarybuffs.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.colorado.binarybuffs.Coordinate;
import edu.colorado.binarybuffs.Grid;
import edu.colorado.binarybuffs.Player;
import edu.colorado.binarybuffs.Ship;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.*;


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


    @Test
    public void attackTest(){
        Player player1 = new Player("Tanvi");
        Grid player1Grid = new Grid();
        Player player2 = new Player("Sahand");
        Grid player2Grid = new Grid();

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
            player1Grid.placeShip(test_fleet.get(i), coordinates.get(2 * i).x, coordinates.get(2 * i).y, coordinates.get(2 * i + 1).x, coordinates.get(2 * i + 1).y);
        }

        player2.attack(1,2, player2Grid, player1Grid, player1);
    }

    @Test
    public void sinkTest(){
        Player player1 = new Player("Tanvi");
        Grid player1Grid = new Grid();
        Player player2 = new Player("Sahand");
        Grid player2Grid = new Grid();

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
            player1Grid.placeShip(test_fleet.get(i), coordinates.get(2 * i).x, coordinates.get(2 * i).y, coordinates.get(2 * i + 1).x, coordinates.get(2 * i + 1).y);
        }

        player2.attack(1,2, player2Grid, player1Grid, player1);
        player2.attack(1,3, player2Grid, player1Grid, player1);
    }

    @Test
    public void surrenderTest(){
        Player player1 = new Player("Tanvi");
        Grid player1Grid = new Grid();
        Player player2 = new Player("Sahand");
        Grid player2Grid = new Grid();

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
            player1Grid.placeShip(test_fleet.get(i), coordinates.get(2 * i).x, coordinates.get(2 * i).y, coordinates.get(2 * i + 1).x, coordinates.get(2 * i + 1).y);
        }

        player2.attack(1,2, player2Grid, player1Grid, player1);
        player2.attack(1,3, player2Grid, player1Grid, player1);

        player2.attack(2,4, player2Grid, player1Grid, player1);
        player2.attack(2,5, player2Grid, player1Grid, player1);
        player2.attack(2,6, player2Grid, player1Grid, player1);

        player2.attack(3,5, player2Grid, player1Grid, player1);
        player2.attack(3,6, player2Grid, player1Grid, player1);
        player2.attack(3,7, player2Grid, player1Grid, player1);
        player2.attack(3,8, player2Grid, player1Grid, player1);
    }

    @Test
    public void captainsQuartersMinesweeperTest(){
        Player player1 = new Player("Tanvi");
        Grid player1Grid = new Grid();
        Player player2 = new Player("Sahand");
        Grid player2Grid = new Grid();

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
            player1Grid.placeShip(test_fleet.get(i), coordinates.get(2 * i).x, coordinates.get(2 * i).y, coordinates.get(2 * i + 1).x, coordinates.get(2 * i + 1).y);
        }

        player2.attack(1,2, player2Grid, player1Grid, player1); //This should attack first cell of minesweeper. Ship should sink.
    }

    @Test
    public void captainsQuartersAllShipsWithoutArmorTest(){
        Player player1 = new Player("Tanvi");
        Grid player1Grid = new Grid();
        Player player2 = new Player("Sahand");
        Grid player2Grid = new Grid();

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
            player1Grid.placeShip(test_fleet.get(i), coordinates.get(2 * i).x, coordinates.get(2 * i).y, coordinates.get(2 * i + 1).x, coordinates.get(2 * i + 1).y);
        }

        player2.attack(1,2, player2Grid, player1Grid, player1); //This should attack captains' quarters cell of minesweeper. Ship should sink.
        player2.attack(2,5, player2Grid, player1Grid, player1); //This should attack captains' quarters of destroyer. Ship should not sink.
        player2.attack(3,7, player2Grid, player1Grid, player1); //This should attack captains' quarters of battleship. Ship should not sink.
        //Player2 should win!
    }

    @Test
    public void captainsQuartersAllShipsWithArmorTest(){
        Player player1 = new Player("Tanvi");
        Grid player1Grid = new Grid();
        Player player2 = new Player("Sahand");
        Grid player2Grid = new Grid();

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
            player1Grid.placeShip(test_fleet.get(i), coordinates.get(2 * i).x, coordinates.get(2 * i).y, coordinates.get(2 * i + 1).x, coordinates.get(2 * i + 1).y);
        }

        player2.attack(2,5, player2Grid, player1Grid, player1); //This should attack captain's quarters of destroyer. Should count as a miss, Ship should not sink.
        player2.attack(2,5, player2Grid, player1Grid, player1); //This should attack captain's quarters of destroyer again. Ship should sink.

        player2.attack(3,7, player2Grid, player1Grid, player1); // This should attack captain's quarters of battleship. Should count as a miss, Ship should not sink.
        player2.attack(3,7, player2Grid, player1Grid, player1); // This should attack captain's quarters of battleship. Ship should sink.

    }

    @Test
    public void finalCaptainsQuarterTest(){
        Player player1 = new Player("Tanvi");
        Grid player1Grid = new Grid();
        Player player2 = new Player("Sahand");
        Grid player2Grid = new Grid();

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
            player1Grid.placeShip(test_fleet.get(i), coordinates.get(2 * i).x, coordinates.get(2 * i).y, coordinates.get(2 * i + 1).x, coordinates.get(2 * i + 1).y);
        }
        player2.attack(1,2, player2Grid, player1Grid, player1); //This should attack captains' quarters of minesweeper. Ship should sink.

        player2.attack(2,5, player2Grid, player1Grid, player1); //This should attack captain's quarters of destroyer. Should count as a miss, Ship should not sink.
        player2.attack(2,5, player2Grid, player1Grid, player1); //This should attack captain's quarters of destroyer again. Ship should sink.

        player2.attack(3,7, player2Grid, player1Grid, player1); // This should attack captain's quarters of battleship. Should count as a miss, Ship should not sink.
        player2.attack(3,7, player2Grid, player1Grid, player1); // This should attack captain's quarters of battleship. Ship should sink.

    }

    @Test
    public void sonarPulse() {
        Player player1 = new Player("Tanvi");
        Grid player1Grid = new Grid();
        Player player2 = new Player("Sahand");
        Grid player2Grid = new Grid();

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
            player1Grid.placeShip(test_fleet.get(i), coordinates.get(2 * i).x, coordinates.get(2 * i).y, coordinates.get(2 * i + 1).x, coordinates.get(2 * i + 1).y);
        }

        Hashtable<Coordinate, String> revealedCoords = new Hashtable<Coordinate, String>();


        revealedCoords = player2.sonarPulse(4,4, player1Grid, player1);

    }
}
