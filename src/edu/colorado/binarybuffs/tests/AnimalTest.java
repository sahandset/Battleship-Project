package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {
    @Test
    public void NarwhalTest() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        player1.player_maps.get(0).placeNarwhal();
        Coordinate narwhal_coord = player1.getPlayerMaps().get(0).getNarwhalCoord();
        if (narwhal_coord != null) {
            player1.deployShip(sweeper, narwhal_coord.x, narwhal_coord.y, "east", 0);
        }

    }

    @Test
    public void NarwhalTest2() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        player1.player_maps.get(0).placeNarwhal();
        Coordinate narwhal_coord = player1.getPlayerMaps().get(0).getNarwhalCoord();
        if (narwhal_coord != null) {
            player2.deployShip(sweeper, 0, 0, "north", 0);

            player1.useWeapon(0, 0, 0, player2, 0, 0);

            player1.deployShip(sweeper, narwhal_coord.x, narwhal_coord.y, "east", 0);

        }

    }

    @Test
    public void JawsTest() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        player1.player_maps.get(0).placeJaws();
        Coordinate jaws_coord = player1.getPlayerMaps().get(0).getJawsCoord();
        if (jaws_coord != null) {
            player1.deployShip(sweeper, jaws_coord.x, jaws_coord.y, "east", 0);
        }
    }

    @Test
    public void JawsTestCaptsCoords() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        player1.player_maps.get(0).placeJaws();
        Coordinate jaws_coord = player1.getPlayerMaps().get(0).getJawsCoord();
        if (jaws_coord != null) {
            player2.deployShip(sweeper, 0, 0, "north", 0);

            player1.useWeapon(0, 0, 0, player2, 0, 0);

            player1.deployShip(dest, jaws_coord.x+1, jaws_coord.y, "east", 0);
        }
    }

}
