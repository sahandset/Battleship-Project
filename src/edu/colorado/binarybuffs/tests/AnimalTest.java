package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {

    /**
     * NarwhalTest() checks whether narwhal is being properly placed on map
     * Calls deployShip() function which should output whether there was a narwhal present under the ship
     */
    @Test
    public void NarwhalTest() {
        Player player1 = new Player("Tanvi");

        Minesweeper sweeper = new Minesweeper();

        player1.player_maps.get(0).placeNarwhal();
        Coordinate narwhal_coord = player1.getPlayerMaps().get(0).getNarwhalCoord();
        if (narwhal_coord != null) {
            assertEquals(true, player1.deployShip(sweeper, narwhal_coord.x, narwhal_coord.y, "east", 0));
        }

    }

    /**
     * NarwhalTest2() checks whether narwhal has granted us 1 more sonar pulse use
     * Calls deployShip() function which should output whether there was a narwhal present under the ship and granted us one use after sinking one ship
     */
    @Test
    public void NarwhalTest2() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();

        player1.player_maps.get(0).placeNarwhal();
        Coordinate narwhal_coord = player1.getPlayerMaps().get(0).getNarwhalCoord();
        if (narwhal_coord != null) {
            player2.deployShip(sweeper, 0, 0, "north", 0);

            player1.useWeapon(0, 0, 0, player2, 0, 0);

            assertEquals(true, player1.deployShip(sweeper, narwhal_coord.x, narwhal_coord.y, "east", 0));
        }
    }

    /**
     * JawsTest() checks whether the jaws shark was properly placed on the map
     * Calls deployShip() function which should output whether there was a shark present under the ship and bit off a cell.
     */
    @Test
    public void JawsTest() {
        Player player1 = new Player("Tanvi");

        Minesweeper sweeper = new Minesweeper();

        player1.player_maps.get(0).placeJaws();
        Coordinate jaws_coord = player1.getPlayerMaps().get(0).getJawsCoord();
        if (jaws_coord != null) {
            assertEquals(true, player1.deployShip(sweeper, jaws_coord.x, jaws_coord.y, "east", 0));
        }
    }

    /**
     * JawsTestCaptsCoords() checks whether the jaws shark was properly placed on the map and bit off captain's coordinates of ship
     * Calls deployShip() function which should output whether there was a shark present under the ship and bit off a cell with captain's quarters
     */
    @Test
    public void JawsTestCaptsCoords() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Destroyer dest = new Destroyer();

        player1.player_maps.get(0).placeJaws();
        Coordinate jaws_coord = player1.getPlayerMaps().get(0).getJawsCoord();
        if (jaws_coord != null) {
            player2.deployShip(sweeper, 0, 0, "north", 0);

            player1.useWeapon(0, 0, 0, player2, 0, 0);

            assertEquals(true, player1.deployShip(dest, jaws_coord.x+1, jaws_coord.y, "east", 0));
        }
    }

}
