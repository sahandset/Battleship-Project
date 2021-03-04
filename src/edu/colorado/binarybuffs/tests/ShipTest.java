package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.Coordinate;
import edu.colorado.binarybuffs.Grid;
import edu.colorado.binarybuffs.Player;
import edu.colorado.binarybuffs.Ship;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ShipTest {
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

}
