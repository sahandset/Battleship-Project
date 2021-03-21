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
}
