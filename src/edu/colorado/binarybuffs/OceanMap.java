package edu.colorado.binarybuffs;

public class OceanMap extends Map {
    private String name = "OceanMap";

    public OceanMap() {

    }

    public String getName() {
        return this.name;
    }

    public boolean validateDeployment(newShip ship) {
        return true;
    }
}
