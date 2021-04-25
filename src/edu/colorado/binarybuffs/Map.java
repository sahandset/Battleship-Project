package edu.colorado.binarybuffs;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Map is an abstract superclass that can define different domains to which certain Ship, Animal, and Disaster objects
 * are placed and interacted with each other
 *
 * Each Map has a Defensive and Offensive Grid attribute that keeps track of cell statuses pertaining to a player's
 * own ships (defensive grid of each map) and their attacks on the opponent's Map objects (offensive grid of each map)
 *
 * Places a narwhal and jaws everytime a map is initiated
 */
public abstract class Map {

    private String name;

    public Grid offensiveGrid;
    public Grid defensiveGrid;

    Hashtable<Ship, Coordinate> captains_quarters = new Hashtable<>();

    Hashtable<Ship, ArrayList<Coordinate>> ship_coordinates = new Hashtable<>();

    Hashtable<Animal, Coordinate> animal_coordinates = new Hashtable<>();

    Hashtable<Ship, String> ship_directions = new Hashtable<>();

    Hashtable<Ship, Integer> ship_health = new Hashtable<>();

    ArrayList<Ship> existing_ships = new ArrayList<>();

    ArrayList<Ship> sunk_ships = new ArrayList<>();

    ArrayList<Animal> animals = new ArrayList<>();

    private int ships_alive = 0;

    /**
     * Constructor that creates a Map Object and it's associated Offensive and Defensive Grids
     */
    public Map(){
        offensiveGrid = new Grid();
        defensiveGrid = new Grid();
    }

    /**
     * Returns the name of the Map (ex. OceanMap, UnderwaterMap, SpaceMap)
     * @return name of map
     */
    public abstract String getName();

    /**
     * Returns the number of ships alive on that Map
     * @return the number of ships alive for that Map object
     */
    public int getShipsAlive(){
        return this.ships_alive;
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
     * Returns the ships that exist (not yet sunk) on that Map
     * @return ArrayList containing Ship Objects (that have not been deemed as sunk)
     */
    public ArrayList<Ship> getExistingShips(){
        return existing_ships;
    }

    /**
     * Returns a Hashtable containing Ship objects mapped to their corresponding ArrayList of Coordinate Objects
     * @return Hashtable with each element being a <Ship, ArrayList<Coordinate>> association
     */
    public Hashtable<Ship, ArrayList<Coordinate>> getShipCoordinatesHash(){
        return ship_coordinates;
    }

    /**
     * Places a Ship Object with a starting X and Y coordinate and an associated direction to which the Ship
     * will face while validating the placement of the ship (not overlapping with any other ship, not on the wrong Map,
     * not out of bounds).
     * @param ship
     * @param start_x
     * @param start_y
     * @param direction
     * @return true if the ship was successfully placed, false otherwise
     */
    public boolean placeShip(Ship ship, int start_x, int start_y, String direction) {
        ArrayList<Coordinate> coords = ship.getCoords(start_x, start_y, direction);
        Coordinate captsQuart = ship.getCaptsCoords(start_x, start_y, direction);

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
            return false;
        }

    }

    /**
     * Validates that certain types of ships are able to be deployed on certain maps (ex. Only Submersible Ships can
     * be deployed on the UnderwaterMap)
     * @param ship Ship Object whose deployment on a certain Map is to be validated
     * @return true if the ship is an instance of an appropriate type for the map, else false
     */
    public abstract boolean validateDeployment(Ship ship);

    /**
     * Validates the Coordinates of a ship by checking if they are out of bounds or if any coordinate is occupied
     * @param coords ArrayList of Coordinates to be validated
     * @return false if any coordinate is out of bounds or would result in a Ship occupying the same coordinate
     * as another Ship, else true (it is validated)
     */
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

    /**
     * Adds a Ship Object to an ArrayList of Ship Objects that are considered to be sunk on that Map.
     * Decrements the number of Ships alive on that Map
     * @param ship the ship Object that is to be added to the sunk_ships ArrayList
     */
    public void sinkShip(Ship ship) {
        sunk_ships.add(ship);
        this.ships_alive--;
    }

    /**
     * Revives a Ship Object by adjusting the health of the Ship Object with its size (what it would be at full health)
     * Removes the Ship Object from the ArrayList of Sunk Ships
     * Increments the number of ships alive on that Map
     * @param ship The ship to be revived
     */
    public void reviveShip(Ship ship) {
        ship_health.replace(ship, ship.getShipSize());
        sunk_ships.remove(ship);
//        System.out.println(sunk_ships);
        this.ships_alive++;
    }

    /**
     * Checks that a Ship Object is sunk
     * @param ship
     * @return true if Ship Object is in the sunk_ships ArrayList, else false
     */
    public boolean checkIfSunk(Ship ship){
        if (sunk_ships.contains(ship)){
            return true;
        }
	    else {
            return false;
        }
    }

    /**
     * Prints a formatted Defensive Grid of the Map
     */
    public void printDefensiveGrid() {
        System.out.println(this.getName() + " -- DEFENSIVE GRID -- ");
        System.out.println("0: Cell is empty, 1: Cell is occupied");
//        System.out.println(defensiveGrid);

        String result = "";
        String axis_label = "";
        for (int axis = 0; axis < 10; axis++) {
            if (axis == 0) {
                System.out.print("X   " + axis + "  ");
            }
            else {
                System.out.print("    " + axis + "  ");
            }
        }
        System.out.println("\nY ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        for (int row = 0; row < this.defensiveGrid.grid.length; row++) {
//            System.out.print(row + "║");
            for (int col = 0; col < this.defensiveGrid.grid[row].length; col++) {
                if (col == 0) {
                    axis_label = row + "┃ ";
                }
                else {
                    axis_label = "";
                }

                if (defensiveGrid.grid[col][row] == 1 || defensiveGrid.grid[col][row] == 2) {
                    //get ship that is at the coordinate
                    Ship ship_at_coord = new Minesweeper();

                    for (int i = 0; i < this.existing_ships.size(); i++) {
                        Ship shipy = this.existing_ships.get(i);
                        ArrayList<Coordinate> coordsList = this.ship_coordinates.get(shipy);
                        for (int j = 0; j < coordsList.size(); j++) {
                            if (coordsList.get(j).x == col && coordsList.get(j).y == row) {
                                ship_at_coord = shipy;
                            }
                        }
                    }

                    Character ship_char = ship_at_coord.getName().charAt(0);

                    if (defensiveGrid.grid[col][row] == 1) {
                        result += axis_label + " " + ship_char + "   │ ";
                    }
                    else if (defensiveGrid.grid[col][row] == 2 ) {
                        result += axis_label + ship_char + "(X)" + " │ ";
                    }
                }
                else {
                    result += axis_label + " " + defensiveGrid.grid[col][row] + "   │ ";
                }

            }
            result += "\n ┃---------------------------------------------------------------------│\n";
        }
        System.out.println(result);
    }

    /**
     * Prints a formatted Offensive Grid of the Map
     */
    public void printOffensiveGrid() {
        System.out.println(this.getName() + " -- OFFENSIVE GRID -- ");
        System.out.println("0: Cell is not hit, 1: Cell has been hit but found empty, 2: Cell has been hit and found occupied");
//        System.out.println(offensiveGrid);

        String result = "";
        String axis_label = "";
        int status = 0;
        for (int axis = 0; axis < 10; axis++) {

            if (axis == 0) {
                System.out.print("X   " + axis + "  ");
            }
            else {
                System.out.print("    " + axis + "  ");
            }
        }
        System.out.println("\nY ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        for (int row = 0; row < this.offensiveGrid.grid.length; row++) {
            for (int col = 0; col < this.offensiveGrid.grid[row].length; col++) {
                if (col == 0) {
                    axis_label = row + " ┃ ";
                }
                else {
                    axis_label = "";
                }
                status = this.offensiveGrid.grid[col][row];
                result += axis_label + status + "   │  ";

            }
            result += "\n  ┃--------------------------------------------------------------------│\n";
        }
        System.out.println(result);
    }

    /**
     * Places a Narwhal on the Map
     */
    public abstract void placeNarwhal();

    /**
     * Places a Jaws on the Map
     */
    public abstract void placeJaws();

    /**
     * Checks if an Animal overlaps with existing Ships on the Map
     * @param current_player The player who will be affected if an Animal overlaps with their Ship
     * @return true if an Animal exists at an overlapping coordinate with a Ship, false otherwise
     */
    public abstract boolean checkForAnimal(Player current_player);


}
