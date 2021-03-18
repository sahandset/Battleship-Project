package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.Coordinate;
import edu.colorado.binarybuffs.Grid;
import edu.colorado.binarybuffs.Player;
import edu.colorado.binarybuffs.Ship;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SonarPulseTest {

    @Test
    public void sonarPulse() {
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
        player2.attack(1,2, player2Grid, player1Grid, player1); //This should attack captains' quarters of minesweeper. Ship should sink.

        Hashtable<String, String> revealedCoords = new Hashtable<String, String>();

        revealedCoords = player2.useSonarPulse(4,4, player1Grid, player1);

        assertEquals("Grey", revealedCoords.get("(4, 4)"));
    }

    @Test
    public void sonarPulseAvailability() {
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
        player2.attack(1,2, player2Grid, player1Grid, player1); //This should attack captains' quarters of minesweeper. Ship should sink.

        Hashtable<String, String> revealedCoords = new Hashtable<String, String>();

        revealedCoords = player2.useSonarPulse(4,4, player1Grid, player1);

        revealedCoords = player2.useSonarPulse(5,7, player1Grid, player1);

        revealedCoords = player2.useSonarPulse(6,6, player1Grid, player1);

        player2.attack(3,7, player2Grid, player1Grid, player1);

        player2.attack(3,7, player2Grid, player1Grid, player1);

        assertEquals(0, player2.my_sonar_pulse.possible_uses);

    }
}
