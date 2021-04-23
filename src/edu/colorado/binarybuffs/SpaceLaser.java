package edu.colorado.binarybuffs;

import java.util.ArrayList;

public class SpaceLaser extends Weapon {

    private int num_uses;
    private String name = "Space Laser";

    public SpaceLaser() {
        this.num_uses = 2147483647; //Constant set num times we can use this
    }

    public String getName() {

        return this.name;
    }

    public boolean deployWeapon(int x, int y, newPlayer opponent, Map attacked_map, Map current_player_map, newPlayer currentPlayer, int method_choice) {
        if (x > 10 || x < 0 || y > 10 || y < 0) {
            System.out.println("You cannot attack outside of the grid! (Attempted an attack at (" + x + "," + y + "))");
            return false;
        }

        /* What can happen:
         *  Miss - Surface and Underwater
         *  Hit - Surface only
         *     Hit - Captains' Quarters
         *     Hit - Non Captain's Quarters
         *  Hit - Underwater only
         *     Hit - Captain's Quarters
         *     Hit - Non Captain's Quarters
         *  Hit - Surface and Underwater
         *     Hit - Armored Surface Captain's Quarters (misses Submarine below)
         *     Hit - Unarmored Surface Captains Quarters (sinks ship, hits Submarine below)
         *     Hit - Surface Non-Captain's Quarters (hits Submarine below)
         *     Hit - Surface Non-Captain's Quarters (hits Submarine Captain's quarters below, sinks Submarine) */

        Map opp_surface = opponent.player_maps.get(0);
        Map opp_underwater = opponent.player_maps.get(1);
        Map opp_space = opponent.player_maps.get(2);

        Map curr_surface = currentPlayer.player_maps.get(0);
        Map curr_underwater = currentPlayer.player_maps.get(1);
        Map curr_space = currentPlayer.player_maps.get(2);

        /* Check curr_player offensive grid cell status at xy to see if it is a miss - either from an actual miss or a result of hitting a armored CQ
         * If it it a miss, check to see if a ship exists on the surface using opp_surface defensive grid */
        Bomb b = new Bomb();

//        System.out.println("Currently attacking in space!");
        newShip attack_ship = new Minesweeper();
        spaceLaserOutputs(method_choice, 1, attacked_map, attack_ship);
        b.deployWeapon(x, y, opponent, opp_space, curr_space, currentPlayer, 4);

        //check if you hit a space shuttle, and if it sank
        //we could check the attacked_map's defensive grid and see if there is a ship there
        //get that ship
        //see if its in their sunk ships
        //destroy everything underneath that ship's coords

        //check if ship there (meaning you hit ship)
        int value = opp_space.defensiveGrid.checkCellStatus(x, y);
        if (value == 2) {
            //get ship at the coordinate
            newShip attacked_ship = new Spaceshuttle();

            for (int i = 0; i < opp_space.existing_ships.size(); i++) {
                newShip shipy = opp_space.existing_ships.get(i);
                ArrayList<Coordinate> coordsList = opp_space.ship_coordinates.get(shipy);
                for (int j = 0; j < coordsList.size(); j++) {
                    if (coordsList.get(j).x == x && coordsList.get(j).y == y) {
                        attacked_ship = shipy;
                    }
                }
            }

            //with the ship, check if its sunk
            if (opp_space.sunk_ships.contains(attacked_ship)) {
//                System.out.println("WOW! By sinking the " + attacked_ship.getName() + ", some of the debris fell to the surface!");
                spaceLaserOutputs(method_choice, 2, attacked_map, attacked_ship);
                //get the coords of that row
                ArrayList<Coordinate> coords = opp_space.ship_coordinates.get(attacked_ship);
                for (Coordinate coord : coords){
                    this.attackUnderSpaceShuttle(coord.x, coord.y, opp_surface, curr_surface, currentPlayer, method_choice);
                }
            }
        }

//        System.out.print("Currently attacking on the surface! ");
        spaceLaserOutputs(method_choice, 3, attacked_map, attack_ship);
        b.deployWeapon(x, y, opponent, opp_surface, curr_surface, currentPlayer, 3);

        // If you attack a cell and hit a surface ship, check underwater
        if (opp_surface.defensiveGrid.checkCellStatus(x, y) == 2) {
//            System.out.print("Currently attacking underwater! ");
            spaceLaserOutputs(method_choice, 4, attacked_map, attack_ship);
            b.deployWeapon(x, y, opponent, opp_underwater, curr_underwater, currentPlayer, 3);
            // If you have attacked a cell and there is no surface ship, check underwater
        } else if (opp_surface.defensiveGrid.checkCellStatus(x, y) == 0) {
//            if (opp_surface.defensiveGrid.checkCellStatus(x, y) == 0) {
//            System.out.print("Currently attacking underwater! ");
            spaceLaserOutputs(method_choice, 5, attacked_map, attack_ship);
            b.deployWeapon(x, y, opponent, opp_underwater, curr_underwater, currentPlayer, 3);
//            }
        }
        return true;
    }

    // REFACTOR THIS METHOD
    public boolean attackUnderSpaceShuttle(int x, int y, Map attacked_map, Map current_player_map, newPlayer current_player, int method_choice){
        //copy the functionality of a bomb here
        int has_been_attacked = current_player_map.offensiveGrid.checkCellStatus(x,y);
        int is_occupied = attacked_map.defensiveGrid.checkCellStatus(x,y);

        if (is_occupied == 0) {
            //System.out.println("You've attempted an attack on " + attacked_map.getName() + ", but you've missed!");
            current_player_map.offensiveGrid.setCellStatus(1, x, y);
        } else if (is_occupied == 1) {
            newShip attacked_ship = new Minesweeper();

            for (int i = 0; i < attacked_map.existing_ships.size(); i++) {
                newShip shipy = attacked_map.existing_ships.get(i);
                ArrayList<Coordinate> coordsList = attacked_map.ship_coordinates.get(shipy);
                for (int j = 0; j < coordsList.size(); j++) {
                    if (coordsList.get(j).x == x && coordsList.get(j).y == y) {
                        attacked_ship = shipy;
                    }
                }
            }

            Coordinate capt_quart = attacked_map.captains_quarters.get(attacked_ship);
            if (capt_quart.x == x && capt_quart.y == y) {
                if (attacked_ship instanceof ArmoredShip) {
                    if (((ArmoredShip) attacked_ship).getHitCount() == 0) {
                        //System.out.println("You've attempted an attack on " + attacked_map.getName() + ", but you've missed!");
                        current_player_map.offensiveGrid.setCellStatus(1, x, y);
                        ((ArmoredShip) attacked_ship).updateHitCount();
                    } else if (((ArmoredShip) attacked_ship).getHitCount() == 1) {
                        for (int i = 0; i < attacked_map.captains_quarters.size(); i++) {
                            if (attacked_map.captains_quarters.get(attacked_ship).x == x && attacked_map.captains_quarters.get(attacked_ship).y == y) {
//                                System.out.println("The debris hit a captain's quarters! You've sunk a " + attacked_ship.getName() + "!");
                                spaceLaserOutputs(method_choice, 6, attacked_map, attacked_ship);
                                attacked_map.sinkShip(attacked_ship);
                                current_player.incrementShipSunkCount();
                                current_player.hasSunkFirstShip();
                                int current_health = attacked_map.ship_health.get(attacked_ship);
                                attacked_map.ship_health.replace(attacked_ship, current_health, 0);
                                ArrayList<Coordinate> coordsList = attacked_map.ship_coordinates.get(attacked_ship);
                                for (int j = 0; j < coordsList.size(); j++) {
                                    current_player_map.offensiveGrid.setCellStatus(2, coordsList.get(j).x, coordsList.get(j).y);
                                }
                            }
                        }
                    }
                } else {
//                    System.out.println("The debris hit a captain's quarters on " + attacked_map.getName() + "! You've sunk a " + attacked_ship.getName() + "!");
                    spaceLaserOutputs(method_choice, 7, attacked_map, attacked_ship);
                    attacked_map.sinkShip(attacked_ship);
                    current_player.incrementShipSunkCount();
                    current_player.hasSunkFirstShip();
                    int current_health = attacked_map.ship_health.get(attacked_ship);
                    attacked_map.ship_health.replace(attacked_ship, current_health, 0);
                    ArrayList<Coordinate> coordsList = attacked_map.ship_coordinates.get(attacked_ship);
                    for (int i = 0; i < coordsList.size(); i++) {
                        current_player_map.offensiveGrid.setCellStatus(2, coordsList.get(i).x, coordsList.get(i).y);
                    }
                }
            } else {
                int current_health = attacked_map.ship_health.get(attacked_ship);
                attacked_map.ship_health.replace(attacked_ship, current_health, current_health--);
                //System.out.println("You've attempted an attack on " + attacked_map.getName() + "- it's a hit!");
//                System.out.println("The debris hit a part of a ship!");
                spaceLaserOutputs(method_choice, 8, attacked_map, attacked_ship);
                current_player_map.offensiveGrid.setCellStatus(2, x, y);
            }
        }
        return true;
    }

    public boolean checkAvailability(int num_used) {
        if (num_used == this.num_uses) {
            return false;
        }
        return true;
    }

    public void spaceLaserOutputs(int method_choice, int print_choice, Map attacked_map, newShip attacked_ship) {
        switch (method_choice) {
            case 0: // Space Laser Attack + Debris
                if (print_choice == 1) {
                    System.out.println("Currently attacking in space!");
                }
                if (print_choice == 2) {
                    System.out.println("WOW! By sinking the " + attacked_ship.getName() + ", some of the debris fell to the surface!");
                }
                if (print_choice == 3) {
                    System.out.println("Currently attacking on the surface! ");
                }
                if (print_choice == 4) {
                    System.out.print("Currently attacking underwater! ");
                }
                if (print_choice == 5) {
                    System.out.print("Currently attacking underwater! ");
                }
                if (print_choice == 6) {
                    System.out.println("The debris hit a captain's quarters! You've sunk a " + attacked_ship.getName() + "!");
                }
                if (print_choice == 7) {
                    System.out.println("The debris hit a captain's quarters on " + attacked_map.getName() + "! You've sunk a " + attacked_ship.getName() + "!");
                }
                if (print_choice == 8) {
                    System.out.println("The debris hit a part of a ship!");
                }
//            case 1: // Asteroid Attack
//                if (print_choice == 1) {
//                    System.out.println("The asteroids are firing in space!");
//                }
//                if (print_choice == 2) {
//                    System.out.println("WOW! The asteroids sunk the " + attacked_ship.getName() + ", and some of the debris fell to the surface!");
//                }
//                if (print_choice == 3) {
//                    System.out.println("Debris is hitting the surface! ");
//                }
//                if (print_choice == 4) {
//                    System.out.print("Debris is landing underwater! ");
//                }
//                if (print_choice == 5) {
//                    System.out.print("Debris is landing underwater! ");
//                }
//                if (print_choice == 6) {
//                    System.out.println("The debris hit a captain's quarters! The" + attacked_ship.getName() + " sunk!");
//                }
//                if (print_choice == 7) {
//                    System.out.println("The debris hit a captain's quarters on " + attacked_map.getName() + "! The " + attacked_ship.getName() + " sunk!");
//                }
//                if (print_choice == 8) {
//                    System.out.println("The debris hit a part of a ship!");
//                }

        }
    }
}
