package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubmarineTest {

    @Test
    public void SubmarineGetCoords2() {
        Submarine sub = new Submarine();
        ArrayList<Coordinate> results = sub.getCoords(7, 6, "west");
        assertEquals(10, results.get(4).x);
        assertEquals(6, results.get(4).y);
        assertEquals(9, results.get(3).x);
        assertEquals(5, results.get(3).y);
        assertEquals(9, results.get(2).x);
        assertEquals(6, results.get(2).y);
        assertEquals(8, results.get(1).x);
        assertEquals(6, results.get(1).y);
        assertEquals(7, results.get(0).x);
        assertEquals(6, results.get(0).y);
    }

    @Test
    public void SubmarineGetCaptsCoords() {
        Submarine sub = new Submarine();
        Coordinate cord1 = sub.getCaptsCoords(7, 6, "north");
        assertEquals(7, cord1.x);
        assertEquals(9, cord1.y);
    }

}