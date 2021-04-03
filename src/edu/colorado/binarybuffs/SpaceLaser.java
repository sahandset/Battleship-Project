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

    public boolean deployWeapon(int x, int y, newPlayer opponent, Map attacked_map, Map current_player_map, newPlayer currentPlayer) {
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

        b.deployWeapon(x, y, opponent, opp_space, curr_space, currentPlayer);

        //check if you hit a space shuttle, and if it sank
        //we could check the attacked_map's defensive grid and see if there is a ship there
        //get that ship
        //see if its in their sunk ships
        //destroy everything underneath that ship's coords

        //check if ship there (meaning you hit ship)
        int value = opp_space.defensiveGrid.checkCellStatus(x, y);
        if (value == 1) {
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
                //get the coords of that row
                ArrayList<Coordinate> coords = opp_space.ship_coordinates.get(attacked_ship);
                for (Coordinate coord : coords){
                    //this.attackUnderSpaceShuttle(coord.x, coord.y, )
                }
            }
        }

        //if yes: get the coords of the row
        //call a function:
        //iterates through every coordinate, and:
        //basically calls the bomb function, but w/o the print statements


        b.deployWeapon(x, y, opponent, opp_surface, curr_surface, currentPlayer);

        // If you attack a cell and hit a surface ship, check underwater
        if (curr_surface.offensiveGrid.checkCellStatus(x, y) == 2) {
            System.out.print("You are attacking underwater! ");
            b.deployWeapon(x, y, opponent, opp_underwater, curr_underwater, currentPlayer);
            // If you have attacked a cell and there is no surface ship, check underwater
        } else if (curr_surface.offensiveGrid.checkCellStatus(x, y) == 1) {
            if (opp_surface.defensiveGrid.checkCellStatus(x, y) == 0) {
                System.out.print("You are attacking underwater! ");
                b.deployWeapon(x, y, opponent, opp_underwater, curr_underwater, currentPlayer);
            }
        }
        return true;
    }

    //public attackUnderSpaceShuttle{
    //          copy the functionality of a bomb here
    //}

    public boolean checkAvailability(int num_used) {
        if (num_used == this.num_uses) {
            return false;
        }
        return true;
    }
}
