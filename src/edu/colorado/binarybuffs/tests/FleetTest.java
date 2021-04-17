package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FleetTest {
    @Test
    public void CreateRandOceanFleet() {
        Fleet myFleet = new Fleet("OceanMap");
        ArrayList<newShip> ships = myFleet.getShips();
        assertEquals(3, ships.size());
    }

    @Test
    public void CreateRandUnderwaterFleet() {
        Fleet myFleet = new Fleet("UnderwaterMap");
        ArrayList<newShip> ships = myFleet.getShips();
        assertEquals(1, ships.size());
        assertEquals("Submarine", ships.get(0).getName());

    }

    @Test
    public void CreateRandSpaceFleet() {
        Fleet myFleet = new Fleet("SpaceMap");
        ArrayList<newShip> ships = myFleet.getShips();
        assertEquals(1, ships.size());
        assertEquals("Spaceshuttle", ships.get(0).getName());
    }

    @Test
    public void PlaceRandOceanFleet() {
        Fleet myFleet = new Fleet("OceanMap");
        newPlayer player1 = new newPlayer("Tanvi");
        myFleet.placeFleet(player1);
        ArrayList<newShip> ships = player1.getPlayerMaps().get(0).getExistingShips();
        System.out.print(ships);
        assertEquals(3, ships.size());

    }
}
