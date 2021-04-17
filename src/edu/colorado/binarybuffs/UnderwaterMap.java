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

    public void placeNarwhal() {
        System.out.println("Cannot place a narwhal on Underwater Map");
    }

    public void placeJaws() {
        System.out.println("Cannot place a jaws on Underwater Map");
    }

    public boolean checkForAnimal(newPlayer curr_player) {
        for (int i = 0; i < animal_coordinates.size(); i++) {
            for (int j = 0; j < ship_coordinates.size(); j++) {
                Animal a = animals.get(i);
                newShip shipy = existing_ships.get(j);
                for (int k = 0; k < ship_coordinates.get(shipy).size(); k++) {
                    if ((animal_coordinates.get(a).x == ship_coordinates.get(shipy).get(k).x) && (animal_coordinates.get(a).y == ship_coordinates.get(shipy).get(k).y)) {
                        a.useAnimal(curr_player, this);
                        System.out.println("There's a magic narwhal under one of your ships!");
                        return true;
                    }
                }

            }
        }
        return false;
    }

}
