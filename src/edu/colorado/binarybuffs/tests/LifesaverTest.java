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
        boolean mineSaved = player1.useBoost(0, 0, 0);
        boolean mineHit2 = player2.useWeapon(0, 1,2, player1, 0);

        assertEquals(true, mineHit);
        assertEquals(true, mineSaved);
        assertEquals(true, mineHit2);


    }

    @Test
    public void UseLifesaverTwice() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        player1.deployShip(sweeper, 1,2,"south", 0);
        player1.deployShip(dest, 3,5,"south", 0);

        player2.useWeapon(0, 1,2, player1, 0);
        player1.useBoost(0, 0, 0);
        player2.useWeapon(0, 1,2, player1, 0);
        player1.useBoost(0, 0, 0);
        player2.useWeapon(0, 1,2, player1, 0);
        player1.useBoost(0, 0, 0);


    }

    @Test
    public void UseLifesaverNotSunkShip() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        player1.deployShip(sweeper, 1,2,"south", 0);
        player1.deployShip(dest, 3,5,"south", 0);

        boolean destHit = player2.useWeapon(0, 3,5, player1, 0);
        boolean destSaved = player1.useBoost(0, 0, 0);

        assertEquals(true, destHit);

        assertEquals(false, destSaved);


    }

    @Test
    public void UseLifesaverSubmarine() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        player1.deployShip(sweeper, 1,2,"south", 0);
        player1.deployShip(dest, 3,5,"south", 0);
        player1.deployShip(sub, 4,3,"west", 1);

        boolean mineHit = player2.useWeapon(0, 1,2, player1, 0);
        player2.useWeapon(0, 4,3, player1, 1);
        player2.useWeapon(0, 7,3, player1, 1);

        boolean subSaved = player1.useBoost(0, 0, 1);


        assertEquals(true, mineHit);
        assertEquals(true, subSaved);


    }

}
