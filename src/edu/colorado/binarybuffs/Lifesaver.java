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

    public boolean equipBoost(newShip ship, Map current_player_map, newPlayer current_player) {
        if (current_player_map.checkIfSunk(ship)) {
            ArrayList <Coordinate> sunkShipCoords = current_player_map.ship_coordinates.get(ship);

//            for (newShip shipy: current_player_map.existing_ships) {
//                ArrayList<Coordinate> aliveShipCoords = current_player_map.ship_coordinates.get(shipy);
//
//                for (int i = 0; i < aliveShipCoords.size(); i++) {
//                    int alive_x = aliveShipCoords.get(i).x;
//                    int alive_y = aliveShipCoords.get(i).y;
//
//                    for (int j = 0; j < sunkShipCoords.size(); j++) {
//                        int sunk_x = sunkShipCoords.get(i).x;
//                        int sunk_y = sunkShipCoords.get(i).y;
//
//                        if (alive_x == sunk_x && alive_y == sunk_y) {
//                            System.out.println("There is another existing ship there, you can not revive your ship!");
//                            return false;
//                        }
//
//                    }
//                }
//
//            }
            current_player_map.reviveShip(ship);
            System.out.println("You have successfully revived your ship!");
            return true;
        }
        return false;
    }

    public boolean checkAvailability(int num_used) {
        if (num_used == this.num_uses) {
            return false;
        }
        return true;
    }
}
