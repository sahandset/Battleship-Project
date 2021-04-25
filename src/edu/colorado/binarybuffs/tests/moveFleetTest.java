package edu.colorado.binarybuffs.tests;

import org.junit.jupiter.api.Test;

import edu.colorado.binarybuffs.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class moveFleetTest {

    /**
     * moveFleetTest() tests that a player's ships are able to be moved successfully
     */
    @Test
    //place ships and move them
    public void moveFleetTest() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

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

    }

    /**
     * moveFleetBoundsTest() tests that player cannot move their fleet in a certain direction if that collective
     * movement results in a ship exceeding the bounds of a map
     */
    @Test
    //undo fleet move
    public void moveFleetBoundsTest() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        assertEquals(true, player1.deployShip(sweeper, 1, 1, "south", 0));
        assertEquals(true, player1.deployShip(sub, 1, 4, "south", 1));
        assertEquals(true, player1.deployShip(dest, 5, 5, "north", 0));
        assertEquals(true, player1.deployShip(bat, 3, 3, "east", 0));

        boolean moved = player1.playerMoveFleet("n");

        assertEquals(false, moved);

    }

    /**
     * undoFleetTest() tests that a valid movement of a player's fleet is able to be undone
     */
    @Test
    //undo fleet move
    public void undoFleetTest() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        assertEquals(true, player1.deployShip(sweeper, 1, 1, "south", 0));
        assertEquals(true, player1.deployShip(sub, 1, 4, "south", 1));
        assertEquals(true, player1.deployShip(dest, 5, 5, "north", 0));
        assertEquals(true, player1.deployShip(bat, 3, 3, "east", 0));

        boolean moved = player1.playerMoveFleet("e");
        boolean undo_action = player1.undo();

        assertEquals(true, moved);
        assertEquals(true, undo_action);

    }

    /**
     * undoFleetTest() tests that a valid movement of a player's fleet is able to be re-done
     */
    @Test
    //redo fleet move
    public void redoFleetTest() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        assertEquals(true, player1.deployShip(sweeper, 1, 1, "south", 0));
        assertEquals(true, player1.deployShip(sub, 1, 4, "south", 1));
        assertEquals(true, player1.deployShip(dest, 5, 5, "north", 0));
        assertEquals(true, player1.deployShip(bat, 3, 3, "east", 0));

        boolean moved = player1.playerMoveFleet("e");
        boolean undo_action = player1.undo();
        boolean redo_action = player1.redo();

        assertEquals(true, moved);
        assertEquals(true, undo_action);
        assertEquals(true, redo_action);

    }

    /**
     * undoCheck1() tests that without a prior player movefleet command, the player is not able to undo their action
     */
    @Test
    //checks to undo on empty stack
    public void undoCheck1() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();
        Submarine sub = new Submarine();
        Destroyer dest = new Destroyer();
        Battleship bat = new Battleship();

        assertEquals(true, player1.deployShip(sweeper, 1, 1, "south", 0));
        assertEquals(true, player1.deployShip(sub, 1, 4, "south", 1));
        assertEquals(true, player1.deployShip(dest, 5, 5, "north", 0));
        assertEquals(true, player1.deployShip(bat, 3, 3, "east", 0));

        boolean undo_action = player1.undo();

        assertEquals(false, undo_action);

    }

    /**
     * undoCheck2() tests that after two valid movefleet commands by a player, they can successfully undo twice
     */
    @Test
    //move fleet twice, undo twice
    public void undoCheck2() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

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
        boolean undo_action1 = player1.undo();
        boolean undo_action2 = player1.undo();

        assertEquals(true, moved);
        assertEquals(true, moved2);
        assertEquals(true, undo_action1);
        assertEquals(true, undo_action2);

    }

    /**
     * redoNewMoveTest() tests that a player can redo their movefleet command after a new move has been made
     */
    @Test
    //redo fleet move after new move is made
    public void redoNewMoveTest() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

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

    /**
     * moveFleetWithSpaceShuttleTest() tests that the space shuttle is successfully moved with the rest of fleet during
     * a move-fleet command
     */
    @Test
    public void moveFleetWithSpaceShuttleTest() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

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
    }
}
