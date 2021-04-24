package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FleetTest {
    @Test
    public void CreateRandFleet() {
        Minesweeper mine = new Minesweeper();
        Battleship battle = new Battleship();
        Destroyer dest = new Destroyer();
        Submarine sub = new Submarine();
        Spaceshuttle shuttle = new Spaceshuttle();
        ArrayList<Ship> myShips = new ArrayList<>();
        myShips.add(mine);
        myShips.add(battle);
        myShips.add(dest);
        myShips.add(sub);
        myShips.add(shuttle);
        Fleet myFleet = new Fleet(myShips);
        ArrayList<Ship> ships = myFleet.getShips();
        assertEquals(5, ships.size());
        assertEquals("Minesweeper", ships.get(0).getName());
    }

    @Test
    public void PlaceRandFleet() {
        Minesweeper mine = new Minesweeper();
        Battleship battle = new Battleship();
        Destroyer dest = new Destroyer();
        Submarine sub = new Submarine();
        Spaceshuttle shuttle = new Spaceshuttle();
        ArrayList<Ship> myShips = new ArrayList<>();
        myShips.add(mine);
        myShips.add(battle);
        myShips.add(dest);
        myShips.add(sub);
        myShips.add(shuttle);
        Fleet myFleet = new Fleet(myShips);

        Player player1 = new Player("Tanvi");
        myFleet.placeFleet(player1);
        ArrayList<Ship> shipsOnOceanMap = player1.getPlayerMaps().get(0).getExistingShips();
        ArrayList<Ship> shipsOnUnderwaterMap = player1.getPlayerMaps().get(1).getExistingShips();
        ArrayList<Ship> shipsOnSpaceMap = player1.getPlayerMaps().get(2).getExistingShips();
        assertEquals(3, shipsOnOceanMap.size());
        assertEquals(1, shipsOnSpaceMap.size());
        assertEquals(1, shipsOnUnderwaterMap.size());
    }

    @Test
    public void AftermathOfRandFleet() {
        Minesweeper mine = new Minesweeper();
        Battleship battle = new Battleship();
        Destroyer dest = new Destroyer();
        Submarine sub = new Submarine();
        Spaceshuttle shuttle = new Spaceshuttle();
        ArrayList<Ship> myShips = new ArrayList<>();
        myShips.add(mine);
        myShips.add(battle);
        myShips.add(dest);
        myShips.add(sub);
        myShips.add(shuttle);
        Fleet myFleet = new Fleet(myShips);

        Player player1 = new Player("Tanvi");
        Player player2 = new Player("Sahand");

        myFleet.placeFleet(player1);
        ArrayList<Ship> shipsOnOceanMap = player1.getPlayerMaps().get(0).getExistingShips();
        ArrayList<Ship> shipsOnUnderwaterMap = player1.getPlayerMaps().get(1).getExistingShips();
        ArrayList<Ship> shipsOnSpaceMap = player1.getPlayerMaps().get(2).getExistingShips();
        assertEquals(3, shipsOnOceanMap.size());
        assertEquals(1, shipsOnSpaceMap.size());
        assertEquals(1, shipsOnUnderwaterMap.size());

        Ship shipy = player1.getPlayerMaps().get(0).getExistingShips().get(0);
        ArrayList<Coordinate> coordinatesOfShipy = player1.getPlayerMaps().get(0).getShipCoordinatesHash().get(shipy);
        player2.useWeapon(0, coordinatesOfShipy.get(0).x, coordinatesOfShipy.get(0).y, player1, 0, 2);
    }
}
