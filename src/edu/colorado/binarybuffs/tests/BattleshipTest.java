package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BattleshipTest {

    /**
     * BattleshipGetCoords() checks if battleship coordinates are properly created with a given starting coordinate
     */
    @Test
    public void BattleshipGetCoords() {
        Battleship bs = new Battleship();
        ArrayList<Coordinate> results = bs.getCoords(5, 5, "east");
        assertEquals(2, results.get(3).x);
        assertEquals(5, results.get(3).y);
        assertEquals(3, results.get(2).x);
        assertEquals(5, results.get(2).y);
        assertEquals(4, results.get(1).x);
        assertEquals(5, results.get(1).y);
        assertEquals(5, results.get(0).x);
        assertEquals(5, results.get(0).y);
    }

    /**
     * BattleshipGetCaptsCoords() checks if battleship captain's coordinates are properly created with a given starting coordinate
     */
    @Test
    public void BattleshipGetCaptsCoords() {
        Battleship bs = new Battleship();
        Coordinate cord1 = bs.getCaptsCoords(5, 5, "south");
        assertEquals(5, cord1.x);
        assertEquals(3, cord1.y);
    }

}