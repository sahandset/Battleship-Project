package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinesweeperTest {

    /**
     * MinesweeperGetCoords() tests that a Minesweeper generates the right x and y coordinates.
     * Ships DO NOT know their own coordinates, this generator function helps other classes validate ship placements
     */
    @Test
    public void MinesweeperGetCoords() {
        Minesweeper sweeper = new Minesweeper();
        ArrayList<Coordinate> results = sweeper.getCoords(1, 1, "north");
        assertEquals(1, results.get(1).x);
        assertEquals(2, results.get(1).y);
    }

    /**
     * MinesweeperGetCoords() tests that a Minesweeper generates the right x and y coordinates for multiple
     * coordinate pairs. Ships DO NOT know their own coordinates, this generator function helps other classes validate ship placements
     */
    @Test
    public void MinesweeperGetCoords2() {
        Minesweeper sweeper = new Minesweeper();
        ArrayList<Coordinate> results = sweeper.getCoords(1, 1, "north");
        assertEquals(1, results.get(1).x);
        assertEquals(2, results.get(1).y);
        assertEquals(1, results.get(0).x);
        assertEquals(1, results.get(0).y);
    }

    /**
     * MinesweeperGetCaptsCoords() tests that a Minesweeper generates the right x and y coordinates for the Captain's
     * Quarters. Ships DO NOT know their own coordinates, this generator function helps other classes interact with the captain's quarters
     */
    @Test
    public void MinesweeperGetCaptsCoords() {
        Minesweeper sweeper = new Minesweeper();
        Coordinate cord1 = sweeper.getCaptsCoords(1, 1, "north");
        assertEquals(1, cord1.x);
        assertEquals(1, cord1.y);
    }

}