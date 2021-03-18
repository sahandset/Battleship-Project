package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DestroyerTest {

    @Test
    public void DestroyerGetCoords() {
        Destroyer dest = new Destroyer();
        ArrayList<Coordinate> results = dest.getCoords(3, 3, "south");
        assertEquals(3, results.get(2).x);
        assertEquals(1, results.get(2).y);
        assertEquals(3, results.get(1).x);
        assertEquals(2, results.get(1).y);
        assertEquals(3, results.get(0).x);
        assertEquals(3, results.get(0).y);
    }

    @Test
    public void DestroyerGetCaptsCoords() {
        Destroyer dest = new Destroyer();
        Coordinate cord1 = dest.getCaptsCoords(3, 3, "east");
        assertEquals(2, cord1.x);
        assertEquals(3, cord1.y);
    }

}