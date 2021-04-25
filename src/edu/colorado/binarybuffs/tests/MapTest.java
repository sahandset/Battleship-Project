package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.*;
import org.junit.jupiter.api.Test;

public class MapTest {
    /**
     *
     */
    @Test
    public void shipPlacementTest() {
        Minesweeper sweeper = new Minesweeper();
        OceanMap newMap = new OceanMap();
        boolean result = newMap.placeShip(sweeper, 1,1, "s");
    }
}
