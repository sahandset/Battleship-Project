package edu.colorado.binarybuffs;

import java.util.*;
import java.util.ArrayList;

public class Bomb extends Weapon {

    public Bomb(){
        this.num_uses = 100; //constant set num times we can use this
    }

    public boolean deployWeapon(int x, int y, newPlayer opponent, Map attacked_map, Map current_player_map) {
        if (attacked_map.getName() != "OceanMap") {
            System.out.println("You cannot use the bomb on " + attacked_map.getName());
            return false;
        }

        int has_been_attacked = current_player_map.offensiveGrid.checkCellStatus(x,y);
        int is_occupied = attacked_map.defensiveGrid.checkCellStatus(x,y);

        if (has_been_attacked == 0) {
            if (is_occupied == 0) {
                System.out.println("You've attempted an attack, but you've missed!");
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
                        System.out.println("You've attempted an attack, but you've missed!");
                        current_player_map.offensiveGrid.setCellStatus(1, x, y);
                    }
                    else {
                        System.out.println("You've hit a captain's quarters! You've sunk a " + attacked_ship.getName() + "!");
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
                    System.out.println("You've attempted an attack - it's a hit!");
                    current_player_map.offensiveGrid.setCellStatus(2, x, y);
                }
            }
        }
        else {
            System.out.println("You've already attacked there!");
            for (int i = 0; i < attacked_map.captains_quarters.size(); i++) {
                newShip attacked_ship = attacked_map.existing_ships.get(i);
                if (attacked_map.captains_quarters.get(attacked_ship).x == x && attacked_map.captains_quarters.get(attacked_ship).y == y) {
                    System.out.println("-- But you've hit a captain's quarters! You've sunk a " + attacked_ship.getName() + "!");
                    int current_health = attacked_map.ship_health.get(attacked_ship);
                    attacked_map.ship_health.replace(attacked_ship, current_health, 0);
                    ArrayList<Coordinate> coordsList = attacked_map.ship_coordinates.get(attacked_ship);
                    for (int j = 0; j < coordsList.size(); j++){
                        current_player_map.offensiveGrid.setCellStatus(2, coordsList.get(j).x, coordsList.get(j).y);
                    }
                }
            }
        }

        //now check some status' of your opponents attacked_map and update opponent player's shipsAlive --> calls a method
            //if this finds that shipsAlive == 0, then current player wins

        return true;
    }

        public void attack(int x, int y, Grid playerGrid, Grid opponentGrid, Player opponent){
        int statusHit = playerGrid.offensive_grid[x][y];
        int statusOccupied = opponentGrid.player_grid[x][y];
        if (statusHit % 2 != 0){
            if (statusOccupied == 0){
                System.out.println("You've attempted attack, but you've missed!");
                playerGrid.offensive_grid[x][y] = 2;
            }
            else if (statusOccupied == 1){
                playerGrid.offensive_grid[x][y] = 4;

                //access ships and change their health
                for(int i = 0; i < opponent.ship_fleet.size(); i++){

                    //see if cell hit was captain's quarters
                    Coordinate capt_quart = opponent.ship_fleet.get(i).getCaptainsQuarters(opponent.ship_fleet.get(i));
                    if (x == capt_quart.x && y == capt_quart.y){
                        if (opponent.ship_fleet.get(i).getArmorStatus(opponent.ship_fleet.get(i))) {
                            playerGrid.offensive_grid[x][y] = 2;
                            System.out.println("You've attempted attack, but you've missed!");
                            return;
                        }
                        else{
                            System.out.println("You've hit a captain's quarters!");
                            opponent.ship_fleet.get(i).sinkShip(opponent.ship_fleet.get(i), opponent);
                            return;
                        }
                    }
                    else {
                        //Array = getShipCoordinates(Ship ship)
                        ArrayList<Coordinate> list_of_coords = opponent.ship_fleet.get(i).getShipCoordinates(opponent.ship_fleet.get(i));
                        for (int j = 0; j < list_of_coords.size(); j++) {
                            if ((x == list_of_coords.get(j).x) && (y == list_of_coords.get(j).y)) {
                                opponent.ship_fleet.get(i).reduceHealth(opponent.ship_fleet.get(i), opponent);

                            }
                        }
                    }
                }
                System.out.println("You've attempted attack. Congrats! You hit a ship!");
            }
        }
        else {
            System.out.println("You've already attacked here");
            for (int i = 0; i < opponent.ship_fleet.size(); i++) {
                //see if cell hit was captain's quarters
                Coordinate capt_quart = opponent.ship_fleet.get(i).getCaptainsQuarters(opponent.ship_fleet.get(i));
                if (x == capt_quart.x && y == capt_quart.y) {
                    System.out.println("You've hit a captain's quarters!");
                    opponent.ship_fleet.get(i).sinkShip(opponent.ship_fleet.get(i), opponent);
                }
            }
        }
    }




}
