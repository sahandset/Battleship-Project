package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BombTest {

    @Test
    public void BombAttack() {
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
}
