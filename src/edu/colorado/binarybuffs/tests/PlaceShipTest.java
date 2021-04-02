package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaceShipTest {
    @Test
    public void placeShipTest() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        assertEquals(true, player1.deployShip(sweeper, 1, 1, "south", 0));
        assertEquals(true, player1.deployShip(dest, 5, 5, "north", 0));
        assertEquals(true, player1.deployShip(bat, 3, 3, "east", 0));
    }

    @Test
    public void placeSurfaceShipUnderwater() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Battleship bat = new Battleship();

        assertEquals(false, player1.deployShip(bat, 1, 1, "south", 1));
    }

    @Test
    public void placeShipOverlappingTest() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        assertEquals(true, player1.deployShip(sweeper, 1, 1, "south", 0));
        assertEquals(false, player1.deployShip(dest, 1, 1, "north", 0));
    }
    @Test
    public void placeShipOutOfBoundsTest() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        assertEquals(false, player1.deployShip(sweeper, -1, -1, "south", 0));
        assertEquals(false, player1.deployShip(dest, 11, 11, "north", 0));
        assertEquals(false, player1.deployShip(bat, -1, 15, "east", 0));
    }

    @Test
    public void submarineSurfacePlacementTest() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        assertEquals(true, player1.deployShip(sweeper, 1, 1, "south", 0));
        assertEquals(false, player1.deployShip(sub, 1, 4, "south", 0));
    }

    @Test
    public void submarineUnderwaterPlacementTest() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        assertEquals(true, player1.deployShip(sweeper, 1, 1, "south", 0));
        assertEquals(true, player1.deployShip(sub, 1, 4, "south", 1));
    }

    @Test
    public void submarineUnderwaterOverlapTest() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Submarine sub1 = new Submarine();
        Submarine sub2 = new Submarine();


        assertEquals(true, player1.deployShip(sub1, 1, 4, "south", 1));
        assertEquals(false, player1.deployShip(sub2, 1, 4, "south", 1));
    }

    //START OF SPACE SHUTTLE TEST
    @Test
    public void placeSpaceshuttleTest() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Spaceshuttle shut = new Spaceshuttle();

        assertEquals(true, player1.deployShip(shut, 0, 0, "north", 2));
    }

    @Test
    public void placeShuttleOOBTest() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Spaceshuttle shut = new Spaceshuttle();

        boolean deployed = player1.deployShip(shut, 1, 1, "north", 2);

        assertEquals(false, deployed);
    }

    @Test
    public void placeShipsUnderShuttleTest() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Spaceshuttle shut = new Spaceshuttle();
        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        boolean deployed1 = player1.deployShip(sweeper, 1, 0, "east", 0);
        boolean deployed2 = player1.deployShip(dest, 0, 1, "west", 0);
        boolean deployed3 = player1.deployShip(bat, 0, 7, "west", 0);
        boolean deployed4 = player1.deployShip(sub, 0, 1, "west", 1);
        boolean deployed5 = player1.deployShip(shut, 0, 0, "west", 2);

        assertEquals(true, deployed1);
        assertEquals(true, deployed2);
        assertEquals(true, deployed3);
        assertEquals(true, deployed4);
        assertEquals(true, deployed5);
    }

}
