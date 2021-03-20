package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.Coordinate;
import edu.colorado.binarybuffs.Grid;
import edu.colorado.binarybuffs.Player;
import edu.colorado.binarybuffs.Ship;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    @Test
    public void testPlayer() {
        Player player_1 = new Player("Sahand");
        Player player_2 = new Player("Tanvi");
        assertEquals("Sahand", player_1.getName(player_1));
//        assertEquals(false, player_1.getTurn(player_1));
    }

    @Test
    public void testShipFleetCreation() {
        Player player1 = new Player("Tanvi");
        Grid grid1 = new Grid();
        ArrayList<Ship> test_fleet;
        test_fleet = player1.createFleet();
        assertEquals(test_fleet.get(0).getShipName(test_fleet.get(0)), "Minesweeper");
        assertEquals(test_fleet.get(1).getShipName(test_fleet.get(1)), "Destroyer");
        assertEquals(test_fleet.get(2).getShipName(test_fleet.get(2)), "Battleship");
    }

    @Test
    public void attackTest(){
        Player player1 = new Player("Tanvi");
        Grid player1Grid = new Grid();
        Player player2 = new Player("Sahand");
        Grid player2Grid = new Grid();

        ArrayList<Ship> test_fleet;
        test_fleet = player1.createFleet();
        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
        Coordinate coord1 = new Coordinate(1, 2);
        Coordinate coord3 = new Coordinate(2, 4);
        Coordinate coord5 = new Coordinate(3, 5);

        coordinates.add(coord1);
        coordinates.add(coord3);
        coordinates.add(coord5);

        for (int i = 0; i < test_fleet.size(); i++) {
            player1Grid.placeShip(test_fleet.get(i), coordinates.get(i).x, coordinates.get(i).y, "south" );
        }

        player2.useWeapon(1,2, player2Grid, player1Grid, player1);
    }

    @Test
    public void surrenderTest(){
        Player player1 = new Player("Tanvi");
        Grid player1Grid = new Grid();
        Player player2 = new Player("Sahand");
        Grid player2Grid = new Grid();

        ArrayList<Ship> test_fleet;
        test_fleet = player1.createFleet();
        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
        Coordinate coord1 = new Coordinate(1, 2);
        Coordinate coord3 = new Coordinate(2, 4);
        Coordinate coord5 = new Coordinate(3, 5);

        coordinates.add(coord1);
        coordinates.add(coord3);
        coordinates.add(coord5);

        for (int i = 0; i < test_fleet.size(); i++) {
            player1Grid.placeShip(test_fleet.get(i), coordinates.get(i).x, coordinates.get(i).y, "south");
        }

        player2.useWeapon(1,2, player2Grid, player1Grid, player1);
        player2.useWeapon(1,3, player2Grid, player1Grid, player1);

        player2.useWeapon(2,4, player2Grid, player1Grid, player1);
        player2.useWeapon(2,5, player2Grid, player1Grid, player1);
        player2.useWeapon(2,6, player2Grid, player1Grid, player1);

        player2.useWeapon(3,5, player2Grid, player1Grid, player1);
        player2.useWeapon(3,6, player2Grid, player1Grid, player1);
        player2.useWeapon(3,7, player2Grid, player1Grid, player1);
        player2.useWeapon(3,8, player2Grid, player1Grid, player1);
    }


}
