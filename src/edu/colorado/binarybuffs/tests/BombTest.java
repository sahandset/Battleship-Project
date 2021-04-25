package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BombTest {

    /**
     * OutOfBoundsTest() checks whether weapon is being deployed in the right bounds
     * Call useWeapon function on invalid coordinates to check error messages
     */
    @Test
    public void OutOfBoundsTest() {
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

    /**
     * WeaponHit() checks whether weapon is being deployed and hitting a ship based on coordinates
     * Call useWeapon function on valid coordinates to check whether ship was properly hit
     */
    @Test
    public void WeaponHit() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();

        player1.deployShip(sweeper, 1,2,"south", 0);

        assertEquals(true, player2.useWeapon(0, 1,1, player1, 0, 2));
    }

    /**
     * WeaponMiss() checks whether weapon is being deployed and missing a ship based on coordinates
     * Call useWeapon function on coordinates without ship present to check whether ship was missed
     */
    @Test
    public void WeaponMiss() {
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

    /**
     * Sunk() checks whether weapon is being deployed and sinks a ship on its captain's quarters
     * Call useWeapon function on ship's captain quarters to check whether ship gets sunk
     */
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

    /**
     * Surrender() checks whether weapon is being deployed and sinks all opponent player's ships
     * Call useWeapon function on all ships' captain quarters to check whether player surrender status becomes true and player loses
     */
    @Test
    public void Surrender() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
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

    /**
     * BombHittingWrongMap() checks whether weapon is being deployed on the correct map (only on ocean map)
     * Call useWeapon function on valid and invalid maps to check proper error messages
     */
    @Test
    public void BombHittingWrongMap() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();


        assertEquals(false, player2.useWeapon(0, 1,1, player1, 1, 2));
        assertEquals(false, player2.useWeapon(0, 1,1, player1, 2, 2));
    }

}
