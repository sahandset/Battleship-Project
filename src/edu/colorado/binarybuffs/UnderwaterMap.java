package edu.colorado.binarybuffs;

public class UnderwaterMap extends Map {

    private String name = "UnderwaterMap";

    public UnderwaterMap(){

    }
    public String getName() {
        return this.name;
    }

    public boolean validateDeployment(newShip ship) {
        if (ship instanceof SubmersibleShip) {
            return true;
        } else {
            return false;
        }
    }

}
