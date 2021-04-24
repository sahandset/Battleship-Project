package edu.colorado.binarybuffs;

import java.util.ArrayList;

public class SpaceLaser extends Weapon {

    private int num_uses;
    private String name = "Space Laser";

    /**
     * Constructor for creating SpaceLaser object
     * sets num_uses to max int value (max number of times a SonarPulse can be used)
     */
    public SpaceLaser() {
        this.num_uses = 2147483647; //Constant set num times we can use this
    }

    /**
     * Retrieves a private variable
     * @return String class variable name
     */
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
        spaceLaserOutputs(method_choice, 1, attack_ship);
        b.deployWeapon(x, y, opponent, opp_space, curr_space, current_player, method_choice+1);

        //Check to see if you hit a space shuttle
        int value = opp_space.defensiveGrid.checkCellStatus(x, y);
        if (value == 2) {
            //Get ship at the coordinate
            Ship attacked_ship = new Spaceshuttle();

            for (int i = 0; i < opp_space.existing_ships.size(); i++) {
                Ship shipy = opp_space.existing_ships.get(i);
                ArrayList<Coordinate> coordsList = opp_space.ship_coordinates.get(shipy);
                for (Coordinate coordinate : coordsList) {
                    if (coordinate.x == x && coordinate.y == y) {
                        attacked_ship = shipy;
                        break;
                    }
                }
            }

            //With the ship, check if its sunk
            if (opp_space.sunk_ships.contains(attacked_ship)) {
                //If ship sank, called attackUnderSpaceShuttle()
                spaceLaserOutputs(method_choice, 2, attacked_ship);
                //get the coords of that row
                ArrayList<Coordinate> coords = opp_space.ship_coordinates.get(attacked_ship);
                for (Coordinate coord : coords){
                    //this.attackUnderSpaceShuttle(coord.x, coord.y, opp_surface, curr_surface, current_player, 0);
                    b.deployWeapon(coord.x, coord.y, opponent, opp_surface, curr_surface, current_player, method_choice+3);
                }
            }
        }

        //Attack on surface!
        spaceLaserOutputs(method_choice, 3, attack_ship);
        b.deployWeapon(x, y, opponent, opp_surface, curr_surface, current_player, method_choice+1);

        //Check to see if you hit an armoured captains quarters (if you did, space laser cannot penetrate through)
        if (opp_surface.defensiveGrid.checkCellStatus(x, y) == 2) {
            //Attack underwater
            spaceLaserOutputs(method_choice, 4, attack_ship);
            b.deployWeapon(x, y, opponent, opp_underwater, curr_underwater, current_player, method_choice+1);
        } else if (opp_surface.defensiveGrid.checkCellStatus(x, y) == 0) {
            //Attack underwater
            spaceLaserOutputs(method_choice, 5, attack_ship);
            b.deployWeapon(x, y, opponent, opp_underwater, curr_underwater, current_player, method_choice+1);
        }
        return true;
    }

    /**
     * Checks the availability of the spacelaser by comparing a number passed in to how many
     *      times a spacelaser can be used
     * @param num_used an int value of how many times the weapon has been used
     * @return boolean returns availability of weapon
     */
    public boolean checkAvailability(int num_used) {
        return num_used != this.num_uses;
    }

    /**
     * spaceLaserOutputs() allows versatility in the use of deployWeapon() in space laser. Since deployWeapon()
     *      is called to simulate an attack on all opponent's maps, it is used by the a normal space laser attack
     *      as well as an asteroid attack. The different methods of using deployWeapon() have different print statements.
     *
     * The switch case implementation allows deployWeapon() to print out the necessary information based on the
     *      method choice
     * @param method_choice the desired set of print statements: differs for space_laser and asteroids
     * @param print_choice the print statement in the set that needs to printed out
     * @param attacked_ship the ship being attacked on
     */
    public void spaceLaserOutputs(int method_choice, int print_choice, Ship attacked_ship) {
        switch (method_choice) {
            case 2: // Space Laser Attack
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
                break;
            case 3: // Asteroid Attack
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
                break;
        }
    }
}
