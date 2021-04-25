package edu.colorado.binarybuffs.tests;
import edu.colorado.binarybuffs.*;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class createPlayerMapTest {
    /**
     * createPlayerMapTest() tests that different maps can be created and that they are appropriately named and accessed
     */
    @Test
    public void createPlayerMapTest() {
        Player player1 = new Player("Tanvi");

        OceanMap om1 = new OceanMap();
        UnderwaterMap uw1 = new UnderwaterMap();

        assertEquals(om1.getName(), player1.getPlayerMaps().get(0).getName());
        assertEquals(uw1.getName(), player1.getPlayerMaps().get(1).getName());
    }
}
