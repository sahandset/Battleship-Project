package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LifesaverTest {

    /**
     * UseLifesaverOnce() tests that a Livesaver boost revives a sunken ship and if that ship can be subsequently resunk
     */
    @Test
    public void UseLifesaverOnce() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        player1.deployShip(sweeper, 1,2,"south", 0);
        player1.deployShip(dest, 3,5,"south", 0);

        boolean mineHit = player2.useWeapon(0, 1,2, player1, 0, 2);
        boolean mineSaved = player1.useBoost(0, 0, 0);
        boolean mineHit2 = player2.useWeapon(0, 1,2, player1, 0 ,2);

        assertEquals(true, mineHit);
        assertEquals(true, mineSaved);
        assertEquals(true, mineHit2);


    }

    /**
     * UseLifesaverTwice() tests that a Lifesaver boost can only be used twice by a player
     */
    @Test
    public void UseLifesaverTwice() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        player1.deployShip(sweeper, 1,2,"south", 0);
        player1.deployShip(dest, 3,5,"south", 0);

        player2.useWeapon(0, 1,2, player1, 0, 2);
        boolean mine_saved = player1.useBoost(0, 0, 0);
        player2.useWeapon(0, 1,2, player1, 0, 2);
        boolean mine_saved_2 = player1.useBoost(0, 0, 0);
        player2.useWeapon(0, 1,2, player1, 0, 2);
        boolean mine_saved_3 = player1.useBoost(0, 0, 0);

        assertEquals(true, mine_saved);
        assertEquals(true, mine_saved_2);
        assertEquals(false, mine_saved_3);

    }

    /**
     * UseLifesaverNotSunkShip() tests that a Lifesaver boost cannot be used on a ship that has not yet been sunk
     */
    @Test
    public void UseLifesaverNotSunkShip() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        player1.deployShip(sweeper, 1,2,"south", 0);
        player1.deployShip(dest, 3,5,"south", 0);

        boolean destHit = player2.useWeapon(0, 3,5, player1, 0, 2);
        boolean destSaved = player1.useBoost(0, 0, 0);

        assertEquals(true, destHit);
        assertEquals(false, destSaved);

    }

    /**
     * UseLifesaverSubmarine() tests that a Livesaver boost can be used to revive a submarine
     */
    @Test
    public void UseLifesaverSubmarine() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        player1.deployShip(sweeper, 1,2,"south", 0);
        player1.deployShip(dest, 3,5,"south", 0);
        player1.deployShip(sub, 4,3,"west", 1);

        boolean mineHit = player2.useWeapon(0, 1,2, player1, 0, 2);
        player2.useWeapon(0, 4,3, player1, 1, 2);
        player2.useWeapon(0, 7,3, player1, 1, 2);

        boolean subSaved = player1.useBoost(0, 0, 1);

        assertEquals(true, mineHit);
        assertEquals(true, subSaved);

    }

}
