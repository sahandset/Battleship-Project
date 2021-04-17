package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {
    @Test
    public void NarwhalTest() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        player1.player_maps.get(0).placeNarwhal();
        player1.deployShip(sweeper, 1,2,"south", 0);
        player1.player_maps.get(0).checkForAnimal(player1);


    }

    public void JawsTest() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        player1.player_maps.get(0).placeJaws();
        player1.deployShip(sweeper, 1,2,"south", 0);
//        jaws.biteShip(player1, map_choice);
    }

}
