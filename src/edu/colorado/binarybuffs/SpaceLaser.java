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

    /**
     * deployWeapon() uses a space laser to attack the opponents maps. The space laser attacks the
     *      designated coordinate on every map.
     *
     * If a space shuttle is hit and sunk on the space map, every location below the shuttle is attacked
     *      by space shuttle debris therefore calling attackUnderSpaceShuttle()
     * @param x the x-coordinate where the weapon is being deployed
     * @param y the y-coordinate where the weapon is being deployed
     * @param opponent the player being "attacked" on
     * @param attacked_map the map of player being "attacked"
     * @param current_player_map the map of player using weapon
     * @param current_player the player attacking
     * @param method_choice the method of use of deployWeapon (bomb, asteroids, etc.)
     * @return boolean returns success of deployment
     */
    public boolean deployWeapon(int x, int y, Player opponent, Map attacked_map, Map current_player_map, Player current_player, int method_choice) {

        //Checks if coordinate is out of bounds
        if (x > 10 || x < 0 || y > 10 || y < 0) {
            System.out.println("You cannot attack outside of the grid! (Attempted an attack at (" + x + "," + y + "))");
            return false;
        }

        /* What can happen after hitting on the space map:
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

        Map curr_surface = current_player.player_maps.get(0);
        Map curr_underwater = current_player.player_maps.get(1);
        Map curr_space = current_player.player_maps.get(2);

        Bomb b = new Bomb();

        //Attack in space!
        Ship attack_ship = new Minesweeper();
        spaceLaserOutputs(method_choice, 1, attacked_map, attack_ship);
        b.deployWeapon(x, y, opponent, opp_space, curr_space, current_player, method_choice+1);

        //Check to see if you hit a space shuttle
        int value = opp_space.defensiveGrid.checkCellStatus(x, y);
        if (value == 2) {
            //Get ship at the coordinate
            Ship attacked_ship = new Spaceshuttle();

            for (int i = 0; i < opp_space.existing_ships.size(); i++) {
                Ship shipy = opp_space.existing_ships.get(i);
                ArrayList<Coordinate> coordsList = opp_space.ship_coordinates.get(shipy);
                for (int j = 0; j < coordsList.size(); j++) {
                    if (coordsList.get(j).x == x && coordsList.get(j).y == y) {
                        attacked_ship = shipy;
                    }
                }
            }

            //With the ship, check if its sunk
            if (opp_space.sunk_ships.contains(attacked_ship)) {
                //If ship sank, called attackUnderSpaceShuttle()
                spaceLaserOutputs(method_choice, 2, attacked_map, attacked_ship);
                //get the coords of that row
                ArrayList<Coordinate> coords = opp_space.ship_coordinates.get(attacked_ship);
                for (Coordinate coord : coords){
                    this.attackUnderSpaceShuttle(coord.x, coord.y, opp_surface, curr_surface, current_player, 0);
                }
            }
        }

        //Attack on surface!
        spaceLaserOutputs(method_choice, 3, attacked_map, attack_ship);
        b.deployWeapon(x, y, opponent, opp_surface, curr_surface, current_player, method_choice+1);

        //Check to see if you hit an armoured captains quarters (if you did, space laser cannot penetrate through)
        if (opp_surface.defensiveGrid.checkCellStatus(x, y) == 2) {
            //Attack underwater
            spaceLaserOutputs(method_choice, 4, attacked_map, attack_ship);
            b.deployWeapon(x, y, opponent, opp_underwater, curr_underwater, current_player, method_choice+1);
        } else if (opp_surface.defensiveGrid.checkCellStatus(x, y) == 0) {
            //Attack underwater
            spaceLaserOutputs(method_choice, 5, attacked_map, attack_ship);
            b.deployWeapon(x, y, opponent, opp_underwater, curr_underwater, current_player, method_choice+1);
        }
        return true;
    }

    public boolean attackUnderSpaceShuttle(int x, int y, Map attacked_map, Map current_player_map, Player current_player, int method_choice){
        //similar to the functionality of a bomb
        int is_occupied = attacked_map.defensiveGrid.checkCellStatus(x,y);

        //Checks if there is a ship at the attacked location: 0 = no ship, 1 = ship exists, 2 = ship exists and already hit
        if (is_occupied == 0) {
            //no ship: miss!
            current_player_map.offensiveGrid.setCellStatus(1, x, y);
        } else if (is_occupied == 1) {
            Ship attacked_ship = new Minesweeper();

            for (int i = 0; i < attacked_map.existing_ships.size(); i++) {
                Ship shipy = attacked_map.existing_ships.get(i);
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

    public void spaceLaserOutputs(int method_choice, int print_choice, Map attacked_map, Ship attacked_ship) {
        switch (method_choice) {
            case 2: // Space Laser Attack + Debris
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
                break;
            case 3: // Asteroid Attack + Debris
                if (print_choice == 1) {
                    System.out.println("The asteroids are firing in space!");
                }
                if (print_choice == 2) {
                    System.out.println("WOW! The asteroids sunk the " + attacked_ship.getName() + ", and some of the debris fell to the surface!");
                }
                if (print_choice == 3) {
                    System.out.println("Asteroids is hitting the surface! ");
                }
                if (print_choice == 4) {
                    System.out.print("Asteroids are landing underwater! ");
                }
                if (print_choice == 5) {
                    System.out.print("Asteroids are landing underwater! ");
                }
                if (print_choice == 6) {
                    System.out.println("The debris hit a captain's quarters! The" + attacked_ship.getName() + " sunk!");
                }
                if (print_choice == 7) {
                    System.out.println("The debris hit a captain's quarters on " + attacked_map.getName() + "! The " + attacked_ship.getName() + " sunk!");
                }
                if (print_choice == 8) {
                    System.out.println("The debris hit a part of a ship!");
                }
                break;
        }
    }
}
