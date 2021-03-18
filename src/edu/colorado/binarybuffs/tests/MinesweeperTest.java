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

}