package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.Coordinate;
import edu.colorado.binarybuffs.Minesweeper;
import edu.colorado.binarybuffs.Spaceshuttle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpaceshuttleTest {

    @Test
    public void SpaceshuttleGetCoords() {
        Spaceshuttle shut = new Spaceshuttle();
        ArrayList<Coordinate> results = shut.getCoords(0, 0, "north");
        assertEquals(0, results.get(0).x);
        assertEquals(0, results.get(0).y);
    }

    @Test
    public void SpaceshuttleGetCoords2() {
        Spaceshuttle shut = new Spaceshuttle();
        ArrayList<Coordinate> results = shut.getCoords(9, 9, "south");
        assertEquals(9, results.get(0).x);
        assertEquals(9, results.get(0).y);
        assertEquals(9, results.get(3).x);
        assertEquals(6, results.get(3).y);
    }

    @Test
    public void SpaceshuttleGetCaptsCoords() {
        Spaceshuttle shut = new Spaceshuttle();
        Coordinate cord1 = shut.getCaptsCoords(0, 0, "north");
        assertEquals(0, cord1.x);
        assertEquals(5, cord1.y);
    }
}
