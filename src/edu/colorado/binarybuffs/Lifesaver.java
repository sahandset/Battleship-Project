package edu.colorado.binarybuffs;
import java.util.*;

public class Lifesaver extends Boost{

    public int num_uses;
    private String name = "Lifesaver";

    public Lifesaver(){
        this.num_uses = 2;
    }
    public String getName(){
        return this.name;
    }

    public boolean equipBoost(Ship ship, Map current_player_map, Player current_player) {
        if (current_player_map.checkIfSunk(ship)) {
            ArrayList <Coordinate> sunkShipCoords = current_player_map.ship_coordinates.get(ship);
            current_player_map.reviveShip(ship);
            System.out.println("You have successfully revived your ship!");
            return true;
        }
        System.out.println("You can't revive this ship!");
        return false;
    }

    public boolean checkAvailability(int num_used) {
        if (num_used == this.num_uses) {
            return false;
        }
        return true;
    }
}
