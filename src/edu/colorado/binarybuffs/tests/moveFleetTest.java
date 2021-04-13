package edu.colorado.binarybuffs.tests;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import edu.colorado.binarybuffs.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class moveFleetTest {

    @Test
    //place ships and move them
    public void moveFleetTest() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        assertEquals(true, player1.deployShip(sweeper, 1, 1, "south", 0));
        assertEquals(true, player1.deployShip(sub, 1, 4, "south", 1));
        assertEquals(true, player1.deployShip(dest, 5, 5, "north", 0));
        assertEquals(true, player1.deployShip(bat, 3, 3, "east", 0));

        boolean moved = player1.playerMoveFleet("e");
        assertEquals(true, moved);
//        System.out.println(player1.player_maps.get(0).defensiveGrid);
//        System.out.println(player1.player_maps.get(1).defensiveGrid);

    }

    @Test
    //undo fleet move
    public void moveFleetBoundsTest() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        assertEquals(true, player1.deployShip(sweeper, 1, 1, "south", 0));
        assertEquals(true, player1.deployShip(sub, 1, 4, "south", 1));
        assertEquals(true, player1.deployShip(dest, 5, 5, "north", 0));
        assertEquals(true, player1.deployShip(bat, 3, 3, "east", 0));

        boolean moved = player1.playerMoveFleet("n");
//        System.out.println(player1.player_maps.get(0).defensiveGrid);
        assertEquals(false, moved);
//        System.out.println(player1.player_maps.get(0).defensiveGrid);
//        System.out.println(player1.player_maps.get(1).defensiveGrid);

    }
    @Test
    //undo fleet move
    public void undoFleetTest() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        assertEquals(true, player1.deployShip(sweeper, 1, 1, "south", 0));
        assertEquals(true, player1.deployShip(sub, 1, 4, "south", 1));
        assertEquals(true, player1.deployShip(dest, 5, 5, "north", 0));
        assertEquals(true, player1.deployShip(bat, 3, 3, "east", 0));

        boolean moved = player1.playerMoveFleet("e");
//        System.out.println(player1.player_maps.get(0).defensiveGrid);
        boolean undo_action = player1.undo();
        assertEquals(true, moved);
        assertEquals(true, undo_action);
//        System.out.println(player1.player_maps.get(0).defensiveGrid);
//        System.out.println(player1.player_maps.get(1).defensiveGrid);

    }

    @Test
    //redo fleet move
    public void redoFleetTest() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        assertEquals(true, player1.deployShip(sweeper, 1, 1, "south", 0));
        assertEquals(true, player1.deployShip(sub, 1, 4, "south", 1));
        assertEquals(true, player1.deployShip(dest, 5, 5, "north", 0));
        assertEquals(true, player1.deployShip(bat, 3, 3, "east", 0));

        boolean moved = player1.playerMoveFleet("e");
//        System.out.println(player1.player_maps.get(0).defensiveGrid);
        boolean undo_action = player1.undo();
//        System.out.println(player1.player_maps.get(0).defensiveGrid);
        boolean redo_action = player1.redo();
        assertEquals(true, moved);
        assertEquals(true, undo_action);
        assertEquals(true, redo_action);
//        System.out.println(player1.player_maps.get(0).defensiveGrid);
//        System.out.println(player1.player_maps.get(1).defensiveGrid);

    }

    @Test
    //checks to undo on empty stack
    public void undoCheck1() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        assertEquals(true, player1.deployShip(sweeper, 1, 1, "south", 0));
        assertEquals(true, player1.deployShip(sub, 1, 4, "south", 1));
        assertEquals(true, player1.deployShip(dest, 5, 5, "north", 0));
        assertEquals(true, player1.deployShip(bat, 3, 3, "east", 0));

//        System.out.println(player1.player_maps.get(0).defensiveGrid);
        boolean undo_action = player1.undo();
        assertEquals(false, undo_action);
//        System.out.println(player1.player_maps.get(0).defensiveGrid);
//        System.out.println(player1.player_maps.get(1).defensiveGrid);

    }

    @Test
    //move fleet twice, undo twice
    public void undoCheck2() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        assertEquals(true, player1.deployShip(sweeper, 1, 1, "south", 0));
        assertEquals(true, player1.deployShip(sub, 1, 4, "south", 1));
        assertEquals(true, player1.deployShip(dest, 5, 5, "north", 0));
        assertEquals(true, player1.deployShip(bat, 3, 3, "east", 0));

        boolean moved = player1.playerMoveFleet("e");
        boolean moved2 = player1.playerMoveFleet("s");
//        System.out.println(player1.player_maps.get(0).defensiveGrid);
        boolean undo_action1 = player1.undo();
        boolean undo_action2 = player1.undo();

        assertEquals(true, moved);
        assertEquals(true, moved2);
        assertEquals(true, undo_action1);
        assertEquals(true, undo_action2);
//        System.out.println(player1.player_maps.get(0).defensiveGrid);
//        System.out.println(player1.player_maps.get(1).defensiveGrid);

    }

    @Test
    //redo fleet move after new move is made
    public void redoNewMoveTest() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        assertEquals(true, player1.deployShip(sweeper, 1, 1, "south", 0));
        assertEquals(true, player1.deployShip(sub, 1, 4, "south", 1));
        assertEquals(true, player1.deployShip(dest, 5, 5, "north", 0));
        assertEquals(true, player1.deployShip(bat, 3, 3, "east", 0));

        boolean moved = player1.playerMoveFleet("e");
//        System.out.println(player1.player_maps.get(0).defensiveGrid);
        boolean undo_action = player1.undo();
//        System.out.println(player1.player_maps.get(0).defensiveGrid);
        boolean moved2 = player1.playerMoveFleet("s");
        boolean redo_action = player1.redo();
        assertEquals(true, moved);
        assertEquals(true, undo_action);
        assertEquals(true, moved2);
        assertEquals(false, redo_action);
//        System.out.println(player1.player_maps.get(0).defensiveGrid);
//        System.out.println(player1.player_maps.get(1).defensiveGrid);

    }

    //TEST FOR MOVING SPACE SHUTTLE
    @Test
    public void moveFleetWithSpaceShuttleTest() {
        newPlayer player1 = new newPlayer("Tanvi");
        newPlayer player2 = new newPlayer("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();
        Spaceshuttle shut = new Spaceshuttle();

        assertEquals(true, player1.deployShip(sweeper, 1, 1, "south", 0));
        assertEquals(true, player1.deployShip(sub, 1, 4, "south", 1));
        assertEquals(true, player1.deployShip(dest, 5, 5, "north", 0));
        assertEquals(true, player1.deployShip(bat, 3, 3, "east", 0));
        assertEquals(true, player1.deployShip(shut, 0, 0, "north", 2));


        boolean moved = player1.playerMoveFleet("e");
        assertEquals(true, moved);
//        System.out.println(player1.player_maps.get(0).defensiveGrid);
//        System.out.println(player1.player_maps.get(1).defensiveGrid);

    }

}
