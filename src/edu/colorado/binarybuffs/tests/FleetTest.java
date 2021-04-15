<<<<<<< HEAD
//package edu.colorado.binarybuffs.tests;
//
//import org.junit.jupiter.api.Test;
//
//public class FleetTest {
//    @Test
//    public void CreateRandomFleet() {
//        Fleet myFleet = new Fleet();
//        print(myFleet.getShips());
//    }
//}
=======
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
        assertEquals(3, ships.size());
        assertEquals("Spaceshuttle", ships.get(0).getName());
    }

//    @Test
//    public void PlaceRandOceanFleet() {
//        Fleet myFleet = new Fleet("OceanMap");
//        myFleet.placeFleet();
//
//    }


}
>>>>>>> 4f8a1e37a69a94ec370068c2202288138e1fe4b2
