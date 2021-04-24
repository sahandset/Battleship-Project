package edu.colorado.binarybuffs;

import java.util.ArrayList;

public class Bomb extends Weapon {

    private int num_uses;
    private String name = "Bomb";

    /**
     * Constructor for creating Bomb object
     * sets num_uses to max int value (max number of times a SonarPulse can be used)
     */
    public Bomb(){
        this.num_uses = 2147483647; // Constant set num times we can use this
    }

    /**
     * Retrieves a private variable
     * @return String class variable name
     */
    public String getName() {
        return this.name;
    }

    /**
     * deployWeapon() sends a bomb to a specific designated coordinate on the opponent's map.
     *      It counts as an attack on that players grid and either hits or misses depending on if there
     *      is a ship there.
     *
     * Includes check to see if weapon can be used, ship exists at location of coordinates, if ship
     *      is armoured, and if the attack was on a captain's quarters. Calls other methods in order to
     *      deliver the consequences of the attack. bombOutputs() is used throughout to designate the
     *      proper print statements.
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
        Ship temp_ship = new Minesweeper();
        //Catches if current player is trying to use a bomb on an underwater map or space map, returns false for not successful
        if (current_player.player_weapons.contains(this) && (attacked_map.getName().equals("UnderwaterMap") || attacked_map.getName().equals("SpaceMap"))) {
            bombOutputs(method_choice, 1, attacked_map, temp_ship, x, y);
            return false;
        }

        //Checks if coordinate is in bounds
        if (x > 10 || x < 0 || y > 10 || y < 0) {
            bombOutputs(method_choice, 2, attacked_map, temp_ship, x, y);
            return false;
        }

        int is_occupied = attacked_map.defensiveGrid.checkCellStatus(x,y);

        //Checks if there is a ship at the attacked location: 0 = no ship, 1 = ship exists, 2 = ship exists and already hit
        if (is_occupied == 0) {
            //no ship: miss!
            bombOutputs(method_choice, 3, attacked_map, temp_ship, x, y);

            if (method_choice == 2 || method_choice == 3) {
                current_player_map.offensiveGrid.setCellStatus(1, x, y);
            }
        } else if (is_occupied == 1) {
            //ship there: first time attacking!
            Ship attacked_ship = new Minesweeper();

            for (int i = 0; i < attacked_map.existing_ships.size(); i++){
                Ship shipy = attacked_map.existing_ships.get(i);
                ArrayList<Coordinate> coordsList = attacked_map.ship_coordinates.get(shipy);
                for (Coordinate coordinate : coordsList) {
                    if (coordinate.x == x && coordinate.y == y) {
                        attacked_ship = shipy;
                        break;
                    }
                }
            }

            //Check if captain's quarters at location
            Coordinate capt_quart = attacked_map.captains_quarters.get(attacked_ship);
            if (capt_quart.x == x && capt_quart.y == y) {
                //Check for armoured captain's quarters
                if (attacked_ship instanceof ArmoredShip) {
                    //Armoured!
                    //Armoured captains quarters hasn't been hit before
                    if (((ArmoredShip) attacked_ship).getHitCount() == 0) {
                        //Prints out a miss - some sneaky captain's quarters here
                        bombOutputs(method_choice, 4, attacked_map, attacked_ship, x, y);
                        if (method_choice == 2 || method_choice == 3) {
                            current_player_map.offensiveGrid.setCellStatus(1, x, y);
                        }
                        ((ArmoredShip) attacked_ship).updateHitCount();
                    }
                    //Armoured captains quarters has been hit before
                    else if (((ArmoredShip) attacked_ship).getHitCount() == 1){
                        //Destroys entire ship!
                        bombOutputs(method_choice, 5, attacked_map, attacked_ship, x, y);
                        bombOutputs(method_choice, 6, attacked_map, attacked_ship, x, y);

                        attacked_map.sinkShip(attacked_ship);

                        if (method_choice == 2 || method_choice == 3) {
                            current_player.incrementShipSunkCount();
                            current_player.hasSunkFirstShip();
                        }

                        attacked_map.ship_health.replace(attacked_ship, 0);
                        ArrayList<Coordinate> coordsList = attacked_map.ship_coordinates.get(attacked_ship);
                        for (Coordinate coordinate : coordsList) {
                            if (method_choice == 2 || method_choice == 3) {
                                current_player_map.offensiveGrid.setCellStatus(2, coordinate.x, coordinate.y);
                            }
                            attacked_map.defensiveGrid.setCellStatus(2, coordinate.x, coordinate.y);
                        }
                        ((ArmoredShip) attacked_ship).updateHitCount();
                    }
                }
                //Hit a captain's quarters but not armoured
                else {
                    //Destroy the ship!
                    bombOutputs(method_choice, 7, attacked_map, attacked_ship, x, y);
                    attacked_map.sinkShip(attacked_ship);
                    if (method_choice == 2 || method_choice == 3) {
                        current_player.incrementShipSunkCount();
                        current_player.hasSunkFirstShip();
                    }

                    attacked_map.ship_health.replace(attacked_ship, 0);
                    ArrayList<Coordinate> coordsList = attacked_map.ship_coordinates.get(attacked_ship);
                    for (Coordinate coordinate : coordsList) {
                        if (method_choice == 2 || method_choice == 3) {
                            current_player_map.offensiveGrid.setCellStatus(2, coordinate.x, coordinate.y);
                        }
                        attacked_map.defensiveGrid.setCellStatus(2, coordinate.x, coordinate.y);
                    }
                }
            }
            //Not a captain's quarters there
            else {
                //Attack and hit!
                int current_health = attacked_map.ship_health.get(attacked_ship);
                current_health -= 1;
                attacked_map.ship_health.replace(attacked_ship, current_health);

                bombOutputs(method_choice, 8, attacked_map, temp_ship, x, y);
                if (method_choice == 2 || method_choice == 3) {
                    current_player_map.offensiveGrid.setCellStatus(2, x, y);
                }
                attacked_map.defensiveGrid.setCellStatus(2, x, y);
            }
        } else if (method_choice == 2 || method_choice == 3) {
            //Already attacked, already hit a ship!
            bombOutputs(method_choice, 9, attacked_map, temp_ship, x, y);
        }

        return true;
    }

    /**
     * bombOutputs() allows versatility in the use of deployWeapon() in bomb. Since deployWeapon() is called
     *      to simulate an attack on an opponent's map, it is used by bomb, jaws, asteroids, and space laser,
     *      where all different methods of using deployWeapon() have different print statements.
     *
     * The switch case implementation allows deployWeapon() to print out the necessary information based on the
     *      method choice
     * @param method_choice the desired set of print statements: differs for bomb, jaws, asteroids, and spacelaser
     * @param print_choice the print statement in the set that needs to printed out
     * @param attacked_map the map being attacked on
     * @param attacked_ship the ship being attacked on
     * @param x the x-coordinate being attacked on
     * @param y the y-coordinate being attacked on
     */
    public void bombOutputs(int method_choice, int print_choice, Map attacked_map, Ship attacked_ship, int x, int y) {

        switch (method_choice) {
            case 1: //jaws
                if (print_choice == 1) {
                    System.out.println("The shark can't bite your ship on " + attacked_map.getName());
                }
                else if (print_choice == 2){
                    System.out.println("The shark cannot bite you outside of the grid! (Attempted a shark attack at (" + x + "," + y + ")) on " + attacked_map.getName() + ".");
                }
                else if (print_choice == 3){
                System.out.println("You barely escaped a shark attack on " + attacked_map.getName() + "!");
                }
                else if (print_choice == 4) {
                    System.out.println("The shark bit one of your captain's quarters...luckily it was armored!");
                }
                else if (print_choice == 5){
                    System.out.println("-- But it attacked a captain's quarters! It sunk a " + attacked_ship.getName() + "!");
                }
                else if (print_choice == 6){
                    System.out.println("The shark hit a captain's quarters on " + attacked_map.getName() + "! It sunk a " + attacked_ship.getName() + "!");
                }
                else if (print_choice == 7){
                    System.out.println("The shark attacked you on the " + attacked_map.getName() + " and bit off a part of your ship!");
                }
                else if (print_choice == 8){
                    System.out.println("The shark already attacked and bit a ship here.");
                }
                break;
            case 2: //attack
                if (print_choice == 1) {
                    System.out.println("You cannot use the bomb on " + attacked_map.getName());
                }
                else if (print_choice == 2){
                    System.out.println("You cannot attack outside of the grid! (Attempted an attack at (" + x + "," + y + ")) on " + attacked_map.getName() + ".");
                }
                else if (print_choice == 3){
                    System.out.println("You've attempted an attack on " + attacked_map.getName() + ", but you've missed!");
                }
                else if (print_choice == 4){
                    System.out.println("You've attempted an attack on " + attacked_map.getName() + ", but you've missed!");
                }
                else if (print_choice == 5){
                    System.out.println("You've already attacked there on the " + attacked_map.getName() + ".");
                }
                else if (print_choice == 6){
                    System.out.println("-- But you've hit a captain's quarters! You've sunk a " + attacked_ship.getName() + "!");
                }
                else if (print_choice == 7){
                    System.out.println("You've hit a captain's quarters on " + attacked_map.getName() + "! You've sunk a " + attacked_ship.getName() + "!");
                }
                else if (print_choice == 8){
                    System.out.println("You've attempted an attack on " + attacked_map.getName() + "- it's a hit!");
                }
                else if (print_choice == 9){
                    System.out.println("You've already attacked and hit a ship here.");
                }
                break;
            case 3: //space laser
                if (print_choice == 1) {
                    System.out.println("You cannot use the space laser on " + attacked_map.getName());
                }
                else if (print_choice == 2){
                    System.out.println("You cannot attack outside of the grid! (Attempted an attack at (" + x + "," + y + ")) on " + attacked_map.getName() + ".");
                }
                else if (print_choice == 3){
                    System.out.println("You've attempted an attack on " + attacked_map.getName() + ", but you've missed!");
                }
                else if (print_choice == 4){
                    System.out.println("You've attempted an attack on " + attacked_map.getName() + ", but you've missed!");
                }
                else if (print_choice == 5){
                    System.out.println("You've already attacked there on the " + attacked_map.getName() + ".");
                }
                else if (print_choice == 6){
                    System.out.println("-- But you've hit a captain's quarters! You've sunk a " + attacked_ship.getName() + "!");
                }
                else if (print_choice == 7){
                    System.out.println("You've hit a captain's quarters on " + attacked_map.getName() + "! You've sunk a " + attacked_ship.getName() + "!");
                }
                else if (print_choice == 8){
                    System.out.println("You've attempted an attack on " + attacked_map.getName() + "- it's a hit!");
                }
                else if (print_choice == 9){
                    System.out.println("You've already attacked and hit a ship here.");
                }
                break;
            case 4: //asteroids
                if (print_choice == 1) {
                    System.out.println("Asteroids cannot attack on " + attacked_map.getName());
                }
                else if (print_choice == 2){
                    System.out.println("The asteroids can't fire outside of the grid! (They attempted a hit at (" + x + "," + y + ")) on " + attacked_map.getName() + ".");
                }
                else if (print_choice == 3){
                    System.out.println("The asteroids fired on " + attacked_map.getName() + ", but luckily missed your ship!");
                }
                else if (print_choice == 4){
                    System.out.println("The asteroids fired on " + attacked_map.getName() + ", but luckily missed your ship!");
                }
                else if (print_choice == 5){
                    System.out.println("There had been attack earlier on " + attacked_map.getName() + ".");
                }
                else if (print_choice == 6){
                    System.out.println("-- But the asteroids managed to hit a captain's quarters and sunk a " + attacked_ship.getName() + "!");
                }
                else if (print_choice == 7){
                    System.out.println("The asteroids hit a captain's quarters on " + attacked_map.getName() + "! They sunk a " + attacked_ship.getName() + "!");
                }
                else if (print_choice == 8){
                    System.out.println("The asteroids fired on " + attacked_map.getName() + "- your ships have been hit!");
                }
                else if (print_choice == 9){
                    System.out.println("A ship has already been hit here.");
                }
                break;
            case 5: // Space Laser Debris
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
            case 6: // Asteroid Debris
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

    /**
     * Checks the availability of the bomb by comparing a number passed in to how many
     *      times a bomb can be used (num_uses)
     * @param num_used an int value of how many times the weapon has been used
     * @return boolean returns availability of weapon
     */
    public boolean checkAvailability(int num_used) {
        return num_used != this.num_uses;
    }
}
