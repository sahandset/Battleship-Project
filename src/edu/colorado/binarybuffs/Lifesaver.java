package edu.colorado.binarybuffs;
import java.util.*;

/** Lifesaver extends the Boost superclass, and allows the player to revive up to any 2 sunken ships */
public class Lifesaver extends Boost{

    public int num_uses;
    private String name = "Lifesaver";

    /** Lifesaver constructor sets the number of uses to 2, as the player can only use it 2 times */
    public Lifesaver(){
        this.num_uses = 2;
    }

    //Getter method to return name of boost
    public String getName(){
        return this.name;
    }

     /**
     * equipBoost() performs the functionality of reviving the chosen ship
     * Checks if the passed in ship object is of status sunken
     * Traverses through coordinates of the sunken ship, resets its cell status to 1 - exists on the map once again
     * Calls the revive function to update ship health, remove the ship from sunken ships array
     * @param ship object of type Ship class which is the ship that boost will revive
     * @param current_player_map object of type Map class which is the current player's map where functionality occurs
     * @param current_player object of type Player class which is the current player on the turn
     * @return boolean whether the ship has been successfully revived
     */
    public boolean equipBoost(Ship ship, Map current_player_map, Player current_player) {
        if (current_player_map.checkIfSunk(ship)) {
            ArrayList <Coordinate> sunkShipCoords = current_player_map.ship_coordinates.get(ship);
            for (Coordinate sunkShipCoord : sunkShipCoords) {
                current_player_map.defensiveGrid.setCellStatus(1, sunkShipCoord.x, sunkShipCoord.y);
            }
            current_player_map.reviveShip(ship);
            System.out.println("You have successfully revived your ship!");
            return true;
        }
        System.out.println("You can't revive this ship!");
        return false;
    }

    /**
     * checkAvailability() checks uses of boost
     * Checks if the number of times a player has already used is the same as maximum uses
     * @param num_used integer value that tracks how many times a boost has been used
     * @return boolean whether the boost is able to be used
     */
    public boolean checkAvailability(int num_used) {
        return num_used != this.num_uses;
    }
}
