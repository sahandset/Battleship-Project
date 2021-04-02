package edu.colorado.binarybuffs;

public class SpaceMap extends Map{
    private String name = "SpaceMap";

    public SpaceMap() {

    }

    public String getName() {
        return this.name;
    }

    public boolean validateDeployment(newShip ship) {
        if (ship instanceof OrbitableShip) {
            return true;
        } else {
            return false;
        }
    }
}
