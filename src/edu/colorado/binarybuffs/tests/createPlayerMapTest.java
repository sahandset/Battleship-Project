package edu.colorado.binarybuffs.tests;
import edu.colorado.binarybuffs.*;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class createPlayerMapTest {
    @Test
    public void createPlayerMapTest() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        OceanMap om1 = new OceanMap();
        UnderwaterMap uw1 = new UnderwaterMap();

        assertEquals(uw1.getName(), player1.getPlayerMaps().get(0).getName());
        assertEquals(om1.getName(), player1.getPlayerMaps().get(1).getName());
    }
}
