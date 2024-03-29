package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaceShipTest {
    /**
     * placeShipTest() tests that ships are able to be validated and deployed on the OceanMap
     */
    @Test
    public void placeShipTest() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        assertEquals(true, player1.deployShip(sweeper, 1, 1, "south", 0));
        assertEquals(true, player1.deployShip(dest, 5, 5, "north", 0));
        assertEquals(true, player1.deployShip(bat, 3, 3, "east", 0));
    }

    /**
     * placeSurfaceShipUnderwater() tests that a ship that is not submersible (not able to be placed underwater) cannot
     * be placed on the UnderwaterMap.
     */
    @Test
    public void placeSurfaceShipUnderwater() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Battleship bat = new Battleship();

        assertEquals(false, player1.deployShip(bat, 1, 1, "south", 1));
    }

    /**
     * placeShipOverlappingTest() tests that a ship cannot be placedsuch that its generated coordinates would consequently
     * overlap with another ship that has been already been placed
     */
    @Test
    public void placeShipOverlappingTest() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        assertEquals(true, player1.deployShip(sweeper, 1, 1, "south", 0));
        assertEquals(false, player1.deployShip(dest, 1, 1, "north", 0));
    }
    @Test
    public void placeShipOutOfBoundsTest() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

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
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        assertEquals(true, player1.deployShip(sweeper, 1, 1, "south", 0));
        assertEquals(false, player1.deployShip(sub, 1, 4, "south", 0));
    }

    @Test
    public void submarineUnderwaterPlacementTest() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        assertEquals(true, player1.deployShip(sweeper, 1, 1, "south", 0));
        assertEquals(true, player1.deployShip(sub, 1, 4, "south", 1));
    }

    @Test
    public void submarineUnderwaterOverlapTest() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Submarine sub1 = new Submarine();
        Submarine sub2 = new Submarine();


        assertEquals(true, player1.deployShip(sub1, 1, 4, "south", 1));
        assertEquals(false, player1.deployShip(sub2, 1, 4, "south", 1));
    }

    //START OF SPACE SHUTTLE TEST
    @Test
    public void placeSpaceshuttleTest() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Spaceshuttle shut = new Spaceshuttle();

        assertEquals(true, player1.deployShip(shut, 0, 0, "north", 2));
    }

    @Test
    public void placeShuttleOOBTest() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Spaceshuttle shut = new Spaceshuttle();

        boolean deployed = player1.deployShip(shut, 1, 1, "north", 2);

        assertEquals(false, deployed);
    }

    @Test
    public void placeShipsUnderShuttleTest() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

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
