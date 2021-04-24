package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BombTest {

    @Test
    public void OOB() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        player1.deployShip(sweeper, 1,2,"south", 0);

        assertEquals(false, player2.useWeapon(0, -1,-1, player1, 0, 2));
        assertEquals(false, player2.useWeapon(0, 11,11, player1, 0, 2));
        assertEquals(false, player2.useWeapon(0, -1,11, player1, 0, 2));


    }

    @Test
    public void Hit() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        player1.deployShip(sweeper, 1,2,"south", 0);

        assertEquals(true, player2.useWeapon(0, 1,1, player1, 0, 2));
    }

    @Test
    public void Miss() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        player1.deployShip(sweeper, 1, 1, "south", 0);
        player1.deployShip(sub, 1, 4, "south", 1);
        player1.deployShip(dest, 5, 5, "north", 0);
        player1.deployShip(bat, 3, 3, "east", 0);

        assertEquals(true, player2.useWeapon(0, 8,8, player1, 0, 2));
    }

    @Test
    public void Sunk() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        player1.deployShip(sweeper, 1, 1, "south", 0);
        player1.deployShip(sub, 1, 4, "south", 1);
        player1.deployShip(dest, 5, 5, "north", 0);
        player1.deployShip(bat, 3, 3, "east", 0);

        assertEquals(true, player2.useWeapon(0, 1,1, player1, 0, 2));
    }

    @Test
    public void Surrender() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        player1.deployShip(sweeper, 1, 1, "south", 0);
        player1.deployShip(dest, 5, 5, "north", 0);
        player1.deployShip(bat, 3, 3, "east", 0);

        player2.useWeapon(0, 1,1, player1, 0, 2);

        player2.useWeapon(0, 5,6, player1, 0, 2);
        player2.useWeapon(0, 5,6, player1, 0, 2);

        player2.useWeapon(0, 1,3, player1, 0, 2);
        player2.useWeapon(0, 1,3, player1, 0, 2);

        assertEquals(true, player1.getSurrenderStatus());

    }

    @Test
    public void BombHittingWrongMap() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        //player1.deployShip(sweeper, 1,2,"south", 0);

        assertEquals(false, player2.useWeapon(0, 1,1, player1, 1, 2));
        assertEquals(false, player2.useWeapon(0, 1,1, player1, 2, 2));
    }

}
