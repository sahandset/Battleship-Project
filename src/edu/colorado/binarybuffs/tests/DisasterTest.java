package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DisasterTest {

    @Test
    public void ghostZoneTest() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Minesweeper sweeper = new Minesweeper();

        GhostZone testGhostZone = new GhostZone();
        ArrayList<Coordinate> ghost_coords = testGhostZone.getGhostZoneCoords();

        player1.deployShip(sweeper, ghost_coords.get(0).x, ghost_coords.get(0).y, "north", 0);

        player2.useWeapon(0, ghost_coords.get(0).x,ghost_coords.get(0).y, player1, 0, 2);

        testGhostZone.applyDisaster(player2);

        assertEquals(true, testGhostZone.getGhosted());
    }

    @Test
    public void hurricaneTest() {
        Player player1 = new Player("Tanvi");

        Minesweeper sweeper = new Minesweeper();

        Hurricane testHurricane = new Hurricane();

        ArrayList<Coordinate> hurricane_coords = testHurricane.getHurricaneCoordinates();
        player1.deployShip(sweeper, hurricane_coords.get(2).x, hurricane_coords.get(2).y, "west", 0);

        System.out.println(player1.getPlayerMaps().get(0).defensiveGrid);
//
        testHurricane.applyDisaster(player1);
        //System.out.println(player1.getPlayerMaps().get(0).defensiveGrid);
        ArrayList<Coordinate> new_sweeper_coords = player1.getPlayerMaps().get(0).getShipCoordinatesHash().get(sweeper);
        assertEquals(hurricane_coords.get(0).x, new_sweeper_coords.get(0).x);
        assertEquals(hurricane_coords.get(0).y, new_sweeper_coords.get(0).y);

    }

    @Test
    public void asteroidTest() {
        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        Spaceshuttle shut = new Spaceshuttle();

        AsteroidField testAsteroidField = new AsteroidField();

        ArrayList<Coordinate> asteroid_coords = testAsteroidField.getCoords();

        player1.deployShip(shut, asteroid_coords.get(0).x, asteroid_coords.get(0).y, "north", 2);
        Coordinate hit = new Coordinate(asteroid_coords.get(0).x, asteroid_coords.get(0).y);
        testAsteroidField.applyDisaster(player1);
        assertEquals(2, player1.getPlayerMaps().get(2).defensiveGrid.checkCellStatus(hit.x, hit.y));
    }

}
