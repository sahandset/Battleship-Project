package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinesweeperTest {

    @Test
    public void MinesweeperGetCoords() {
        Minesweeper sweeper = new Minesweeper();
        ArrayList<Coordinate> results = sweeper.getCoordOffsets(1, 1, "north");
        assertEquals(1, results.get(1).x);
        assertEquals(2, results.get(1).y);
    }

    @Test
    public void MinesweeperGetCoords2() {
        Minesweeper sweeper = new Minesweeper();
        ArrayList<Coordinate> results = sweeper.getCoordOffsets(1, 1, "north");
        assertEquals(1, results.get(1).x);
        assertEquals(2, results.get(1).y);
        assertEquals(1, results.get(0).x);
        assertEquals(1, results.get(0).y);
    }

    @Test
    public void MinesweeperGetCaptsCoords() {
        Minesweeper sweeper = new Minesweeper();
        Coordinate cord1 = sweeper.getCaptsOffset(1, 1, "north");
        assertEquals(1, cord1.x);
        assertEquals(1, cord1.y);
    }

}