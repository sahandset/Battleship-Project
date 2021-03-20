package edu.colorado.binarybuffs;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;

public abstract class Map {

    private String name;

    newGrid offensiveGrid;
    newGrid defensiveGrid;

    Hashtable<newShip, Coordinate> captainsQuarters = new Hashtable<>();

    Hashtable<newShip, ArrayList<Coordinate>> shipCoordinates = new Hashtable<>();

    Hashtable<newShip, String> shipDirections = new Hashtable<>();
    
    public Map(){
        offensiveGrid = new newGrid();
        defensiveGrid = new newGrid();
    }

    public abstract String getName();

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
            shipCoordinates.put(ship, coords);
            captainsQuarters.put(ship, captsQuart);
            shipDirections.put(ship, direction);
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

    public boolean validateShip(ArrayList<Coordinate> coords){

        for (int i = 0; i < coords.size(); i++) {
            if (coords.get(i).x < 0 || coords.get(i).y < 0 || coords.get(i).x > 10 || coords.get(i).y > 10)
            {
                return false;
            }
            if (defensiveGrid.checkCellStatus(coords.get(i).x, coords.get(i).y) == 1) {
                return false;
            }
        }
        return true;
    }

}
