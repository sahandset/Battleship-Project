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
    public String getName(){
        return this.name;
    }

    /** equipBoost() performs the functionality of reviving the chosen ship
     * Checks if the passed in ship object is of status sunken
     * Traverses through coordinates of the sunken ship, resets its cell status to 1 - exists on the map once again
     * Calls the revive function to update ship health, remove the ship from sunken ships array
     * returns a boolean whether the ship has been successfully revived
     */
    public boolean equipBoost(Ship ship, Map current_player_map, Player current_player) {
        if (current_player_map.checkIfSunk(ship)) {
            ArrayList <Coordinate> sunkShipCoords = current_player_map.ship_coordinates.get(ship);
            for (int i = 0; i < sunkShipCoords.size(); i++) {
                current_player_map.defensiveGrid.setCellStatus(1, sunkShipCoords.get(i).x, sunkShipCoords.get(i).y);
            }
            current_player_map.reviveShip(ship);
            System.out.println("You have successfully revived your ship!");
            return true;
        }
        System.out.println("You can't revive this ship!");
        return false;
    }

    /** checkAvailability() checks uses of boost
     * Checks if the number of times a player has already used is the same as maximum uses
     * returns a boolean whether the boost is able to be used
     */
    public boolean checkAvailability(int num_used) {
        if (num_used == this.num_uses) {
            return false;
        }
        return true;
    }
}
