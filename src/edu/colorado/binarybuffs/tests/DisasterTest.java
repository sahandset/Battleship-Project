package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DisasterTest {

    @Test
    public void ghostZoneTest() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        player1.deployShip(sweeper, 1, 1, "south", 0);
        player1.deployShip(sub, 8, 8, "south", 1);
        player1.deployShip(dest, 5, 5, "north", 0);
        player1.deployShip(bat, 3, 3, "east", 0);

        player2.useWeapon(0, 5,6, player1, 0, 1);
        player2.useWeapon(0, 5,6, player1, 0, 1);

        GhostZone testGhostZone = new GhostZone();
        testGhostZone.applyDisaster(player2);
    }

    @Test
    public void hurricaneTest() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

//        player1.deployShip(sweeper, 1, 1, "south", 0);
//        player1.deployShip(sub, 8, 8, "south", 1);
//        player1.deployShip(dest, 5, 5, "north", 0);
//        player1.deployShip(bat, 3, 3, "east", 0);

        Hurricane testHurricane = new Hurricane();

        System.out.println(testHurricane.getHurricaneCoordinates());
        ArrayList<Coordinate> hurricane_coords = testHurricane.getHurricaneCoordinates();
        player1.deployShip(sweeper, hurricane_coords.get(0).x, hurricane_coords.get(0).y, "west", 0);

        System.out.println(player1.getPlayerMaps().get(0).defensiveGrid);

        testHurricane.applyDisaster(player1);
        System.out.println(player1.getPlayerMaps().get(0).defensiveGrid);

        //assertEquals(1, player2.getPlayerMaps().get(0).offensiveGrid.checkCellStatus(1,2));
        //assertEquals(1, player2.getPlayerMaps().get(0).offensiveGrid.checkCellStatus(1,1));
    }

    @Test
    public void asteroidTest() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Spaceshuttle shut = new Spaceshuttle();

        player1.deployShip(shut, 0, 0, "north", 2);

        AsteroidField testAsteroidField = new AsteroidField();
        testAsteroidField.applyDisaster(player1);

//        assertEquals(1, player2.getPlayerMaps().get(2).offensiveGrid.checkCellStatus(0,0));
//        assertEquals(1, player2.getPlayerMaps().get(2).offensiveGrid.checkCellStatus(0,3));
//        assertEquals(0, player2.getPlayerMaps().get(2).offensiveGrid.checkCellStatus(5,5));
    }

}
