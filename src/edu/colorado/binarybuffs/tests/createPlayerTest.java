package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class createPlayerTest {
    /**
     * createPlayerTest() tests that a two players can be created
     */
    @Test
    public void createPlayerTest() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");
        assertEquals(player1, player1);
        assertEquals(player2, player2);
    }
}
