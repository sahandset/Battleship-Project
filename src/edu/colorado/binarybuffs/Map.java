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
//        narwhal = new Narwhal();
//        jaws = new Jaws();
    }

    public abstract String getName();

    public int getNumSunkShips() {
        return this.sunk_ships.size();
    }

    public int getShipsAlive(){
        return this.ships_alive;
    }

    public Coordinate getNarwhalCoord() {
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).getName() == "Narwhal") {
                return animal_coordinates.get(animals.get(i));
            }
        }
        return null;
    }

    public Coordinate getJawsCoord() {
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).getName() == "Jaws") {
                return animal_coordinates.get(animals.get(i));
            }
        }
        return null;
    }

    public ArrayList<newShip> getExistingShips(){
        return existing_ships;
    }

    public Hashtable<newShip, ArrayList<Coordinate>> getShipCoordinatesHash(){
        return ship_coordinates;
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
        System.out.println(this.getName() + " -- DEFENSIVE GRID -- ");
        System.out.println("0: Cell is empty, 1: Cell is occupied");
//        System.out.println(defensiveGrid);

        String result = "";
        String axis_label = "";
        for (int axis = 0; axis < 10; axis++) {
            System.out.print("   " + axis);
        }
        System.out.println("\n  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
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
                    newShip ship_at_coord = new Minesweeper();

                    for (int i = 0; i < this.existing_ships.size(); i++) {
                        newShip shipy = this.existing_ships.get(i);
                        ArrayList<Coordinate> coordsList = this.ship_coordinates.get(shipy);
                        for (int j = 0; j < coordsList.size(); j++) {
                            if (coordsList.get(j).x == row && coordsList.get(j).y == col) {
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

    public void printOffensiveGrid() {
        System.out.println(this.getName() + " -- OFFENSIVE GRID -- ");
        System.out.println("0: Cell is not hit, 1: Cell has been hit but found empty, 2: Cell has been hit and found occupied");
        System.out.println(offensiveGrid);
    }

    public abstract void placeNarwhal();

    public abstract void placeJaws();

    public abstract boolean checkForAnimal(newPlayer curr_player);


}
