package edu.colorado.binarybuffs.tests;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import edu.colorado.binarybuffs.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class newPlayerTest {

    @Test

    public void moveFleetTest() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        assertEquals(true, player1.deployShip(sweeper, 1, 1, "south", 0));
        assertEquals(true, player1.deployShip(sub, 1, 4, "south", 1));
        assertEquals(true, player1.deployShip(dest, 5, 5, "north", 0));
        assertEquals(true, player1.deployShip(bat, 3, 3, "east", 0));

        player2.useWeapon(0, 5, 6, player1, 0);
        player2.useWeapon(0, 5, 6, player1, 0);

        player2.useWeapon(1, 1, 2, player1, 0);
    }

}
