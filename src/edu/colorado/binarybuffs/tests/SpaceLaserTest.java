package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpaceLaserTest {

    /**
     * spaceLaserTest() tests that a space laser can be used on any map, and repeatedly, after the first ship has been sunk
     */
    @Test
    public void spaceLaserTest() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        assertEquals(true, player1.deployShip(sweeper, 1, 1, "south", 0));
        assertEquals(true, player1.deployShip(sub, 5, 9, "south", 1));
        assertEquals(true, player1.deployShip(dest, 5, 5, "north", 0));

        //This uses a bomb, sinks a minesweeper, and then gets a space laser
        //use space laser twice to annihilate the rest of the ships
        player2.useWeapon(0, 1,1, player1, 0, 2);
        player2.useWeapon(0, 5, 6, player1, 0, 2);
        player2.useWeapon(0, 5, 6, player1, 0, 2);

    }

    /**
     * spaceLaserOnSpaceMapTest() tests the behavior of using a space laser on the space map
     */
    @Test
    public void spaceLaserOnSpaceMapTest() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();
        Spaceshuttle shuttle = new Spaceshuttle();

        assertEquals(true, player1.deployShip(sweeper, 1, 1, "south", 0));
        assertEquals(true, player1.deployShip(sub, 5, 9, "south", 1));
        assertEquals(true, player1.deployShip(dest, 5, 5, "north", 0));
        assertEquals(true, player1.deployShip(bat, 3, 3, "east", 0));
        assertEquals(true, player1.deployShip(shuttle, 0, 5, "west", 2));

        player2.useWeapon(0, 1,1, player1, 0, 3);
        player2.useWeapon(0, 5, 5, player1, 2, 3);

    }
}
