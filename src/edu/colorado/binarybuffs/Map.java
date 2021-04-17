package edu.colorado.binarybuffs;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public abstract class Map {

    private String name;

    public newGrid offensiveGrid;
    public newGrid defensiveGrid;
    public Animal narwhal;
    public Animal jaws;

    Hashtable<newShip, Coordinate> captains_quarters = new Hashtable<>();

    Hashtable<newShip, ArrayList<Coordinate>> ship_coordinates = new Hashtable<>();

    Hashtable<Animal, Coordinate> animal_coordinates = new Hashtable<>();

    Hashtable<newShip, String> ship_directions = new Hashtable<>();

    Hashtable<newShip, Integer> ship_health = new Hashtable<>();

    ArrayList<newShip> existing_ships = new ArrayList<>();

    ArrayList<newShip> sunk_ships = new ArrayList<>();

    ArrayList<Animal> animals = new ArrayList<>();

    private int ships_alive = 0;
    
    public Map(){
        offensiveGrid = new newGrid();
        defensiveGrid = new newGrid();
        narwhal = new Narwhal();
        jaws = new Jaws();
    }

    public abstract String getName();

    public int getNumSunkShips() {
        return this.sunk_ships.size();
    }

    public int getShipsAlive(){
        return this.ships_alive;
    }

    public boolean placeShip(newShip ship, int start_x, int start_y, String direction) {
        //get the cords
        ArrayList<Coordinate> coords = ship.getCoords(start_x, start_y, direction);
        //get the capts quart
        Coordinate captsQuart = ship.getCaptsCoords(start_x, start_y, direction);

        //validated it
        //boolean ship_is_legit ...
        //if(ship_is_legit){
        //set cell status == 1 for each in coords
        //add to hashtable of shipCoordinates
        //add capts quarts to captainsQuarters
        boolean ship_is_legit = this.validateShip(coords);

        if (ship_is_legit){
            for (int i = 0; i < coords.size(); i++) {
                defensiveGrid.setCellStatus(1, coords.get(i).x, coords.get(i).y);
            }
            ship_coordinates.put(ship, coords);
            captains_quarters.put(ship, captsQuart);
            ship_directions.put(ship, direction);
            ship_health.put(ship, ship.getShipSize());
            existing_ships.add(ship);
            ships_alive++;

            System.out.println("Successfully placed the " + ship.getName()  + "!");
            return true;
        } else {
            System.out.println("You can't place the " + ship.getName()+  " there! Try again.");
            return false;
        }

    }

    //validateShip(coords)
        // went through each coord and checked out of bounds
        // checked cell status
        // returned true or false

    public abstract boolean validateDeployment(newShip ship);

    public boolean validateShip(ArrayList<Coordinate> coords) {

        for (int i = 0; i < coords.size(); i++) {
            if (coords.get(i).x < 0 || coords.get(i).y < 0 || coords.get(i).x >= 10 || coords.get(i).y >= 10)
            {
                return false;
            }
            if ((defensiveGrid.checkCellStatus(coords.get(i).x, coords.get(i).y) == 1) || (defensiveGrid.checkCellStatus(coords.get(i).x, coords.get(i).y) == 2)) {
                return false;
            }
        }
        return true;
    }

    public void sinkShip(newShip ship) {
        sunk_ships.add(ship);
        this.ships_alive--;
    }

    public void reviveShip(newShip ship) {
        ship_health.replace(ship, ship.getShipSize());
        sunk_ships.remove(ship);
        this.ships_alive++;
    }

//    public boolean surrender() {
//        if (ships_alive == 0) {
//            return true;
//        }
//        return false;
//    }

    public boolean checkIfSunk(newShip ship){
        if (sunk_ships.contains(ship)){
            return true;
        }
	    else {
            return false;
        }
    }

    public void printDefensiveGrid() {
        System.out.println("0: Cell is empty, 1: Cell is occupied");
        System.out.println(defensiveGrid);
    }

    public void printOffensiveGrid() {
        System.out.println("0: Cell is not hit, 1: Cell has been hit but found empty, 2: Cell has been hit and found occupied");
        System.out.println(offensiveGrid);
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

    public void checkForAnimal(newPlayer curr_player) {
        for (int i = 0; i < animal_coordinates.size(); i++) {
            for (int j = 0; j < ship_coordinates.size(); j++) {
                for (int k = 0; k < ship_coordinates.get(j).size(); k++) {
                    if ((animal_coordinates.get(i).x == ship_coordinates.get(j).get(k).x) && (animal_coordinates.get(i).y == ship_coordinates.get(j).get(k).y)) {
                        animals.get(i).useAnimal(curr_player, this);
                    }
                }

            }
        }
    }


}
