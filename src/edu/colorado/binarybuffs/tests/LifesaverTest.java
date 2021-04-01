package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LifesaverTest {

    @Test
    public void UseLifesaverOnce() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        player1.deployShip(sweeper, 1,2,"south", 0);
        player1.deployShip(dest, 3,5,"south", 0);

        boolean mineHit = player2.useWeapon(0, 1,2, player1, 0);
//        boolean firstUse = player2.useWeapon(1, 1, 1, player1, 0);
        boolean mineSaved = player1.useBoost(0, 0, 0);
        boolean mineHit2 = player2.useWeapon(0, 1,2, player1, 0);
//        boolean secondUse = player2.useWeapon(1, 1, 1, player1, 0);

        assertEquals(true, mineHit);
//        assertEquals(true, firstUse);
        assertEquals(true, mineSaved);
        assertEquals(true, mineHit2);

//        assertEquals(true, secondUse);

    }

}
