package edu.colorado.binarybuffs.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.colorado.binarybuffs.Grid;
import org.junit.jupiter.api.Test;

public class BattleshipTests {

    @Test
    public void checkGrid() {
        Grid myGrid = new Grid(); //testing the creation of grid
    }

    @Test
    public void getGridSize() {
        Grid myGrid = new Grid();
        assertEquals(10, myGrid.getSize(), "grid must be 10x10"); //checking for size of grid
    }
}
