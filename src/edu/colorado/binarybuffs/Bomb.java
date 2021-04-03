package edu.colorado.binarybuffs;

import java.util.*;
import java.util.ArrayList;

public class Bomb extends Weapon {

    private int num_uses;
    private String name = "Bomb";

    public Bomb(){
        this.num_uses = 2147483647; // Constant set num times we can use this
    }

    public String getName() {
        return this.name;
    }

    public boolean deployWeapon(int x, int y, newPlayer opponent, Map attacked_map, Map current_player_map, newPlayer current_player) {

        if (current_player.player_weapons.contains(this) && (attacked_map.getName() == "UnderwaterMap" || attacked_map.getName() == "SpaceMap")) {
            System.out.println("You cannot use the bomb on " + attacked_map.getName());
            return false;
        }

        if (x > 10 || x < 0 || y > 10 || y < 0) {
            System.out.println("You cannot attack outside of the grid! (Attempted an attack at (" + x + "," + y + ")) on " + attacked_map.getName() + ".");
            return false;
        }

        int has_been_attacked = current_player_map.offensiveGrid.checkCellStatus(x,y);
        int is_occupied = attacked_map.defensiveGrid.checkCellStatus(x,y);

        if (is_occupied == 0) {
            System.out.println("You've attempted an attack on " + attacked_map.getName() + ", but you've missed!");
            current_player_map.offensiveGrid.setCellStatus(1, x, y);
        } else if (is_occupied == 1) {
            newShip attacked_ship = new Minesweeper();

            for (int i = 0; i < attacked_map.existing_ships.size(); i++){
                newShip shipy = attacked_map.existing_ships.get(i);
                ArrayList<Coordinate> coordsList = attacked_map.ship_coordinates.get(shipy);
                for (int j = 0; j < coordsList.size(); j++){
                    if (coordsList.get(j).x == x && coordsList.get(j).y == y){
                        attacked_ship = shipy;
                    }
                }
            }

            Coordinate capt_quart = attacked_map.captains_quarters.get(attacked_ship);
            if (capt_quart.x == x && capt_quart.y == y) {
                if (attacked_ship instanceof ArmoredShip) {
                    if (((ArmoredShip) attacked_ship).getHitCount() == 0) {
                        System.out.println("You've attempted an attack on " + attacked_map.getName() + ", but you've missed!");
                        current_player_map.offensiveGrid.setCellStatus(1, x, y);
                        ((ArmoredShip) attacked_ship).updateHitCount();
                    }
                    else if (((ArmoredShip) attacked_ship).getHitCount() == 1){
                        System.out.println("You've already attacked there on the " + attacked_map.getName() + ".");
                        for (int i = 0; i < attacked_map.captains_quarters.size(); i++) {
                            if (attacked_map.captains_quarters.get(attacked_ship).x == x && attacked_map.captains_quarters.get(attacked_ship).y == y) {
                                System.out.println("-- But you've hit a captain's quarters! You've sunk a " + attacked_ship.getName() + "!");
                                attacked_map.sinkShip(attacked_ship);
                                current_player.incrementShipSunkCount();
                                current_player.hasSunkFirstShip();
                                int current_health = attacked_map.ship_health.get(attacked_ship);
                                attacked_map.ship_health.replace(attacked_ship, current_health, 0);
                                ArrayList<Coordinate> coordsList = attacked_map.ship_coordinates.get(attacked_ship);
                                for (int j = 0; j < coordsList.size(); j++){
                                    current_player_map.offensiveGrid.setCellStatus(2, coordsList.get(j).x, coordsList.get(j).y);
                                }
                            }
                        }
                        ((ArmoredShip) attacked_ship).updateHitCount();
                    }
                }
                else {
                    System.out.println("You've hit a captain's quarters on " + attacked_map.getName() + "! You've sunk a " + attacked_ship.getName() + "!");
                    attacked_map.sinkShip(attacked_ship);
                    current_player.incrementShipSunkCount();
                    current_player.hasSunkFirstShip();
                    int current_health = attacked_map.ship_health.get(attacked_ship);
                    attacked_map.ship_health.replace(attacked_ship, current_health, 0);
                    ArrayList<Coordinate> coordsList = attacked_map.ship_coordinates.get(attacked_ship);
                    for (int i = 0; i < coordsList.size(); i++){
                        current_player_map.offensiveGrid.setCellStatus(2, coordsList.get(i).x, coordsList.get(i).y);
                    }
                }
            }
            else {
                int current_health = attacked_map.ship_health.get(attacked_ship);
                attacked_map.ship_health.replace(attacked_ship, current_health, current_health--);
                System.out.println("You've attempted an attack on " + attacked_map.getName() + "- it's a hit!");
                current_player_map.offensiveGrid.setCellStatus(2, x, y);
            }
        }

//        else {
//            System.out.println("You've already attacked there on the " + attacked_map.getName() + ".");
//            for (int i = 0; i < attacked_map.captains_quarters.size(); i++) {
//                newShip attacked_ship = attacked_map.existing_ships.get(i);
//                if (attacked_map.captains_quarters.get(attacked_ship).x == x && attacked_map.captains_quarters.get(attacked_ship).y == y) {
//                    System.out.println("-- But you've hit a captain's quarters! You've sunk a " + attacked_ship.getName() + "!");
//                    attacked_map.sinkShip(attacked_ship);
//                    current_player.incrementShipSunkCount();
//                    current_player.hasSunkFirstShip();
//                    int current_health = attacked_map.ship_health.get(attacked_ship);
//                    attacked_map.ship_health.replace(attacked_ship, current_health, 0);
//                    ArrayList<Coordinate> coordsList = attacked_map.ship_coordinates.get(attacked_ship);
//                    for (int j = 0; j < coordsList.size(); j++){
//                        current_player_map.offensiveGrid.setCellStatus(2, coordsList.get(j).x, coordsList.get(j).y);
//                    }
//                }
//            }
//        }
        return true;
    }

    public boolean checkAvailability(int num_used) {
        if (num_used == this.num_uses) {
            return false;
        }
        return true;
    }
}
