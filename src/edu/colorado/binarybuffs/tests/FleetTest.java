package edu.colorado.binarybuffs.tests;

import edu.colorado.binarybuffs.*;
import org.junit.jupiter.api.Test;

public class FleetTest {
    @Test
    public void CreateRandomFleet() {
        Fleet myFleet = new Fleet("OceanMap");
        System.out.println(myFleet.getShips());
    }
}
