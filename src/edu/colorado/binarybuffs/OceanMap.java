package edu.colorado.binarybuffs;

import java.util.Random;

public class OceanMap extends Map {
    private String name = "OceanMap";

    public OceanMap() {
        narwhal = new Narwhal();
        jaws = new Jaws();
    }

    public String getName() {
        return this.name;
    }

    public boolean validateDeployment(newShip ship) {
        return true;
    }

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
