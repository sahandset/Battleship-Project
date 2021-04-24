package edu.colorado.binarybuffs;

public class UnderwaterMap extends Map {

    private String name = "UnderwaterMap";


    /**
     * UnderwaterMap() constructor for UnderwaterMap Object
     */
    public UnderwaterMap(){

    }

    /**
     * Returns the name of the UnderwaterMap
     * @return name of UnderwaterMap
     */
    public String getName() {
        return this.name;
    }

    /**
     * Validates that Ship is of type Submersible (only Submersible ships can be deployed on UnderwaterMap)
     * @param ship Ship Object whose deployment on a certain Map is to be validated
     * @return true if the ship is an instance of an appropriate type for the UnderwaterMap, else false
     */
    public boolean validateDeployment(Ship ship) {
        if (ship instanceof SubmersibleShip) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Places a Narwhal on the Map (can't be placed on UnderwaterMap)
     */
    public void placeNarwhal() {
        System.out.println("Cannot place a narwhal on UnderwaterMap");
    }

    /**
     * Places a Jaws on the Map (can't be placed on UnderwaterMap)
     */
    public void placeJaws() {
        System.out.println("Cannot place a jaws on UnderwaterMap");
    }

    /**
     * Checks if an Animal overlaps with existing Ships on the Map
     * @param current_player The player who will be affected if an Animal overlaps with their Ship
     * @return true if an Animal exists at an overlapping coordinate with a Ship, false otherwise
     */
    public boolean checkForAnimal(Player current_player) {
        for (int i = 0; i < animal_coordinates.size(); i++) {
            for (int j = 0; j < ship_coordinates.size(); j++) {
                Animal a = animals.get(i);
                Ship shipy = existing_ships.get(j);
                for (int k = 0; k < ship_coordinates.get(shipy).size(); k++) {
                    if ((animal_coordinates.get(a).x == ship_coordinates.get(shipy).get(k).x) && (animal_coordinates.get(a).y == ship_coordinates.get(shipy).get(k).y)) {
                        a.useAnimal(current_player, this);
                        System.out.println("There's a magic narwhal under one of your ships!");
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
