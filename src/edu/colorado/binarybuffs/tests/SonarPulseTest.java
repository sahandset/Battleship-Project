package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SonarPulseTest {

    /**
     * sonarPulseTest() tests that a sonar pulse performs as expected when called
     */
    @Test
    public void sonarPulseTest(){
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        assertEquals(true, player1.deployShip(sweeper, 1, 1, "south", 0));
        assertEquals(true, player1.deployShip(sub, 1, 4, "south", 1));
        assertEquals(true, player1.deployShip(dest, 5, 5, "north", 0));
        assertEquals(true, player1.deployShip(bat, 3, 3, "east", 0));

        SonarPulse sp = new SonarPulse();
        player2.useWeapon(1, 1,2, player1, 1, 2);
    }

    /**
     * sonarPulseMaxUsesTest() tests that a Sonar pulse can only be used twice
     */
    @Test
    public void sonarPulseMaxUsesTest(){
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        assertEquals(true, player1.deployShip(sweeper, 1, 1, "south", 0));
        assertEquals(true, player1.deployShip(sub, 1, 4, "south", 1));
        assertEquals(true, player1.deployShip(dest, 5, 5, "north", 0));
        assertEquals(true, player1.deployShip(bat, 3, 3, "east", 0));

        SonarPulse sp = new SonarPulse();
        player2.useWeapon(0, 1,1, player1, 0, 2);
        player2.useWeapon(1, 1,2, player1, 0, 2);
        player2.useWeapon(1, 1,2, player1, 0, 2);

        player2.useWeapon(1, 1,2, player1, 1, 2);
    }
}
