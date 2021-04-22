package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FleetTest {
    @Test
    public void CreateRandOceanFleet() {
        Minesweeper mine = new Minesweeper();
        Battleship battle = new Battleship();
        Destroyer dest = new Destroyer();
        Submarine sub = new Submarine();
        Spaceshuttle shuttle = new Spaceshuttle();
        ArrayList<newShip> myShips = new ArrayList<>();
        myShips.add(mine);
        myShips.add(battle);
        myShips.add(dest);
        myShips.add(sub);
        myShips.add(shuttle);
        Fleet myFleet = new Fleet(myShips);
        ArrayList<newShip> ships = myFleet.getShips();
        assertEquals(5, ships.size());
        assertEquals("Minesweeper", ships.get(0).getName());
    }

//    @Test
//    public void CreateRandUnderwaterFleet() {
//        Fleet myFleet = new Fleet("UnderwaterMap");
//        ArrayList<newShip> ships = myFleet.getShips();
//        assertEquals(1, ships.size());
//        assertEquals("Submarine", ships.get(0).getName());
//
//    }
//
//    @Test
//    public void CreateRandSpaceFleet() {
//        Fleet myFleet = new Fleet("SpaceMap");
//        ArrayList<newShip> ships = myFleet.getShips();
//        assertEquals(1, ships.size());
//        assertEquals("Spaceshuttle", ships.get(0).getName());
//    }

    @Test
    public void PlaceRandOceanFleet() {
        Minesweeper mine = new Minesweeper();
        Battleship battle = new Battleship();
        Destroyer dest = new Destroyer();
        Submarine sub = new Submarine();
        Spaceshuttle shuttle = new Spaceshuttle();
        ArrayList<newShip> myShips = new ArrayList<>();
        myShips.add(mine);
        myShips.add(battle);
        myShips.add(dest);
        myShips.add(sub);
        myShips.add(shuttle);
        Fleet myFleet = new Fleet(myShips);

        newPlayer player1 = new newPlayer("Tanvi");
        myFleet.placeFleet(player1);
        ArrayList<newShip> shipsOnOceanMap = player1.getPlayerMaps().get(0).getExistingShips();
        ArrayList<newShip> shipsOnUnderwaterMap = player1.getPlayerMaps().get(1).getExistingShips();
        ArrayList<newShip> shipsOnSpaceMap = player1.getPlayerMaps().get(2).getExistingShips();
        assertEquals(3, shipsOnOceanMap.size());
        assertEquals(1, shipsOnSpaceMap.size());
        assertEquals(1, shipsOnUnderwaterMap.size());
    }
}
