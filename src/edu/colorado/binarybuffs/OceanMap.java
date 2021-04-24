package edu.colorado.binarybuffs;

import java.util.Random;

public class OceanMap extends Map {
    private String name = "OceanMap";

    /**
     * OceanMap() Constructor that automatically places a Narwhal and Jaws Animal Object on the OceanMap
     */
    public OceanMap() {
        placeNarwhal();
        placeJaws();
    }

    /**
     * Returns the name of the UnderwaterMap
     * @return name of UnderwaterMap
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the Coordinate of the Narwhal
     * @return Returns the Coordinate Object of the Narwhal
     */
    public Coordinate getNarwhalCoord() {
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).getName() == "Narwhal") {
                return animal_coordinates.get(animals.get(i));
            }
        }
        return null;
    }

    /**
     * Returns the Coordinate of the Jaws
     * @return Returns the Coordinate Object of the Jaws
     */
    public Coordinate getJawsCoord() {
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).getName() == "Jaws") {
                return animal_coordinates.get(animals.get(i));
            }
        }
        return null;
    }

    /**
     * Validates that certain types of ships are able to be deployed on certain maps (ex. Only Submersible Ships can
     * be deployed on the UnderwaterMap)
     * @param ship Ship Object whose deployment on a certain Map is to be validated
     * @return true if the ship is an instance of an appropriate type for the map, else false
     */
    public boolean validateDeployment(Ship ship) {
        return true;
    }

    /**
     * Places a Narwhal Object on the Map at a random location
     * Puts the Animal and it's associated location to the animal_coordinates Hashtable
     * Adds the Animal to the Map's list of Animal Objects
     */
    public void placeNarwhal() {
        Random rand = new Random(); //instance of random class
        int max_x = 10;
        int max_y = 10;
        int random_x = rand.nextInt(max_x);
        int random_y = rand.nextInt(max_y);

        Animal narwhal = new Narwhal();
        Coordinate narwhal_coord = new Coordinate(random_x, random_y);
        animal_coordinates.put(narwhal, narwhal_coord);
        animals.add(narwhal);
    }

    /**
     * Places a Jaws Object on the Map at a random location
     * Puts the Animal and it's associated location to the animal_coordinates Hashtable
     * Adds the Animal to the Map's list of Animal Objects
     */
    public void placeJaws() {
        Random rand = new Random(); //instance of random class
        int max_x = 10;
        int max_y = 10;
        int random_x = rand.nextInt(max_x);
        int random_y = rand.nextInt(max_y);

        Animal jaws = new Jaws();
        Coordinate jaws_coord = new Coordinate(random_x, random_y);
        animal_coordinates.put(jaws, jaws_coord);
        animals.add(jaws);
    }

    /**
     * Checks if an Animal overlaps with existing Ships on the Map
     * @param current_player The player who will be affected if an Animal overlaps with their Ship
     * @return true if an Animal exists at an overlapping coordinate with a Ship, false otherwise
     */
    public boolean checkForAnimal(Player current_player) {
        /* Iterates through every ship coordinate and compares it with the coordinates of animals on the map */
        boolean animal_exists = false;
        for (int i = 0; i < animal_coordinates.size(); i++) {
            for (int j = 0; j < ship_coordinates.size(); j++) {
                Animal a = animals.get(i);
                Ship shipy = existing_ships.get(j);
                for (int k = 0; k < ship_coordinates.get(shipy).size(); k++) {
                    /* If any coordinates overlap */
                    if ((animal_coordinates.get(a).x == ship_coordinates.get(shipy).get(k).x) && (animal_coordinates.get(a).y == ship_coordinates.get(shipy).get(k).y)) {
                        a.useAnimal(current_player, this);
                        animal_exists = true;
                    }
                }
            }
        }
        return animal_exists;
    }
}
