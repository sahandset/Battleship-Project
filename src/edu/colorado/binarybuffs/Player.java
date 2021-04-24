package edu.colorado.binarybuffs;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Stack;


public class Player {
    private String player_name;
    public ArrayList<Map> player_maps = new ArrayList<Map>();
    ArrayList<Weapon> player_weapons = new ArrayList<>();
    ArrayList<Boost> player_boosts = new ArrayList<>();
    Hashtable<Weapon, Integer> weapon_uses = new Hashtable<Weapon, Integer>();
    Hashtable<Boost, Integer> boost_uses = new Hashtable<Boost, Integer>();

    Stack<Action> fleet_move_actions = new Stack<>();
    Stack<Action> undo_move_actions = new Stack<>();

    private int ships_sunk = 0;
    private boolean surrender = false;

    /**
     * Constructor for Player class
     * Creates new Map objects for each type of map and adds them to player_maps array list
     * Creates new Bomb and Lifesaver objects and adds to respective array lists
     * @param name player's name
     */
    public Player(String name) {
        this.player_name = name;
        player_maps.add(new OceanMap());
        player_maps.add(new UnderwaterMap());
        player_maps.add(new SpaceMap());
        Bomb b = new Bomb();
        Lifesaver l = new Lifesaver();
        player_boosts.add(l);
        player_weapons.add(b);
        weapon_uses.put(b, 0);
        boost_uses.put(l, 0);
    }

    //Getter method to get player's name
    public String getName() {
        return this.player_name;
    }

    //Getter method to access player's current maps
    public ArrayList<Map> getPlayerMaps() {
        return this.player_maps;
    }

    //Getter method to get how many ships the player has sunk
    public int getShipsSunk() {
        return this.ships_sunk;
    }

    /**
     * incrementShipSunkCount() increments the amount of ships the player sunk after sinking opponent's ships
     */
    public void incrementShipSunkCount(){
        this.ships_sunk++;
    }

    //Getter method to get player surrender status
    public boolean getSurrenderStatus() {
        return this.surrender;
    }

    //Setter method to set player's surrender status to true when all their ships are sunk
    public void setSurrenderStatus() {
        this.surrender = true;
    }

    /**
     * useWeapon() is performing weapon functionality on desired location
     * Sets a weapon and map object based on user input
     * Calls deployWeapon() on those objects
     * Checks number of uses for weapon, provides error message if uses are exceeded, etc.
     * @param weapon_choice user's choice of which weapon they would like to use
     * @param x x-coordinate of weapon use
     * @param y y-coordinate of weapon use
     * @param opponent player who is being attacked on
     * @param map_choice corresponds to user choice of map where they want to deploy a weapon
     * @param method_choice corresponds to the type of print statements that need to be shown for a bomb attack
     * @return boolean whether weapon was successfully used
     */
    public boolean useWeapon(int weapon_choice, int x, int y, Player opponent, int map_choice, int method_choice) {
        if (weapon_choice >= 0 && weapon_choice < this.player_weapons.size()) {
            Weapon weapon = this.player_weapons.get(weapon_choice);
            Map attacked_map = opponent.player_maps.get(map_choice);
            Map curr_players_map = this.player_maps.get(map_choice);
            boolean result = weapon.deployWeapon(x, y, opponent, attacked_map, curr_players_map, this, method_choice);
            if (result && weapon_uses.containsKey(weapon)) {
                int current_uses = weapon_uses.get(weapon);
                weapon_uses.replace(weapon, current_uses + 1);
                if (weapon.checkAvailability(weapon_uses.get(weapon)) == false) {
                    player_weapons.remove(weapon);
                    weapon_uses.remove(weapon);
                }

                if (opponent.surrender()) {
                    System.out.println("You've sunk all of " + opponent.getName() + "'s boats!");
                    opponent.surrender = true;
                }
            }
            return result;
        }
        System.out.println("You cannot use that weapon!");
        return false;
    }

    /**
     * useBoost() is performing weapon functionality on desired location
     * Checks whether player is able to use boost based on number of sunk ships and number of uses
     * Calls equipBoost() on the ship
     * Checks number of uses for weapon, provides error message if uses are exceeded, etc.
     * @param boost_choice user's choice of which boost they would like to use
     * @param ship_choice ship which boost will be used on
     * @param map_choice corresponds to user choice of map where they want to deploy a weapon
     * @return boolean whether boost was successfully used
     */
    public boolean useBoost(int boost_choice, int ship_choice, int map_choice) {
        if (boost_choice >= 0 && boost_choice < this.player_boosts.size() && this.player_boosts.size() != 0) {
            Boost boost = this.player_boosts.get(boost_choice);
            Map curr_players_map = this.player_maps.get(map_choice);
            if (curr_players_map.sunk_ships.size() == 0) {
                return false;
            }
            Ship ship = curr_players_map.sunk_ships.get(ship_choice);
            System.out.println(ship.getName());
            boolean result = boost.equipBoost(ship, curr_players_map, this);
            if (result && boost_uses.containsKey(boost)) {
                int current_uses = boost_uses.get(boost);
                boost_uses.replace(boost, current_uses + 1);
                if (boost.checkAvailability(boost_uses.get(boost)) == false) {
                    player_boosts.remove(boost);
                    boost_uses.remove(boost);
                }
            }
            return result;
        }
        System.out.println("You cannot use this boost!");
        return false;
    }

    /**
     * hasSunkFirstShip() is performing all the actions to update weapon uses
     * Goes through all the areas that weapons and their uses are kept track of and updates them accordingly
     */
    public void hasSunkFirstShip() {
        if (this.getShipsSunk() == 1) {
            Weapon remove_bomb = player_weapons.get(0);
            player_weapons.remove(0);
            weapon_uses.remove(remove_bomb);
            SpaceLaser sl = new SpaceLaser();
            player_weapons.add(sl);
            weapon_uses.put(sl, 0);
            SonarPulse sp = new SonarPulse();
            player_weapons.add(sp);
            weapon_uses.put(sp, 0);
        }
    }

    /**
     * deployShip() places the ship on a specific map
     * Gets user's map choice and calls placeShip on inputs
     * @param ship ship that needs to be deployed
     * @param x x coordinate for where ship is deployed
     * @param y y coordinate for where ship is deployed
     * @param direction 1 of 4 directions which ship could be facing
     * @param map_choice user choice for which map ship should be deployed on
     * @return boolean whether ship was deployed successfully
     */
    public boolean deployShip(Ship ship, int x, int y, String direction, int map_choice) {
        Map deploy_map = this.player_maps.get(map_choice);
        if (deploy_map.validateDeployment(ship)) {
            boolean deployed_successfully = deploy_map.placeShip(ship, x, y, direction);
            return deployed_successfully;
        } else {
            return false;
        }
    }

     /**
     * getOffsetCoord() gets the value for which the x and y value coordinates need to be moved
     * Takes in a direction and enters a series of if conditions that assigns a coordinate to each direction
     * @param direction user's choice of which direction they would like to move fleet
     * @return Coordinate offset coordinate direction which ship should be moved
     */
    public Coordinate getOffsetCoord(String direction) {
        Coordinate offset_coord = new Coordinate(0, 0);

        direction = direction.toLowerCase();

        if ((direction.equals("north")) || (direction.equals("n"))) {
            offset_coord = new Coordinate(0, -1);
        } else if ((direction.equals("south")) || (direction.equals("s"))) {
            offset_coord = new Coordinate(0, 1);

        } else if ((direction.equals("east")) || (direction.equals("e"))) {
            offset_coord = new Coordinate(1, 0);

        } else if ((direction.equals("west")) || (direction.equals("w"))) {
            offset_coord = new Coordinate(-1, 0);
        }

        return offset_coord;
    }


    /**
     * playerMoveFleet() is performing move fleet functionality
     * Every time a player wants to perform a new fleet move, clear any old moves in the undo actions stack
     * Call moveFleet() function to perform move
     * Add new move to fleet move actions stack
     * @param direction user's choice of which direction they would like to move fleet
     * @return boolean whether fleet was successfully moved
     */
    public boolean playerMoveFleet(String direction) {
        Coordinate offset_coord = getOffsetCoord(direction);
        undo_move_actions.clear();
        boolean success = moveFleet(offset_coord);

        if (success) {
            Action newAction = new Action(offset_coord.x, offset_coord.y);
            fleet_move_actions.push(newAction);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * redo() function redoes the player move fleet action
     * Check if there are any undone actions in the undo_move_actions stack
     * Pop it off of the undo stack and add it to redo stack, which should undo the last move
     * @return boolean whether player's fleet was successfully redoed
     */
    public boolean undo(){
        if (!fleet_move_actions.empty()) {
            Action temp_action = fleet_move_actions.pop();
            undo_move_actions.push(temp_action);
            boolean success = temp_action.undoAction(this);
            if (success) {
                System.out.println("You have successfully undoed your move!");
            }
            else {
                System.out.println("You cannot undo this move!");
            }
            return success;
        }
        else {
            System.out.println("You have no moves to undo!");
            return false;
        }
    }

    /**
     * redo() function redoes the player move fleet action
     * Check if there are any undone actions in the undo_move_actions stack
     * Pop it off of the undo stack and add it to redo stack, which should undo the last move
     * @return boolean whether player's fleet was successfully redoed
     */
    public boolean redo(){
        if (!undo_move_actions.empty()) {
            Action temp_action = undo_move_actions.pop();
            fleet_move_actions.push(temp_action);
            boolean success = temp_action.redoAction(this);
            if (success) {
                System.out.println("You have successfully redone your move!");
            }
            else {
                System.out.println("You cannot redo this move!");
            }
            return success;
        }
        else{
            System.out.println("You have no moves to redo!");
            return false;
        }
    }

    /**
     * moveFleet() function moves the player's fleet in a desired direction
     * If the fleet move is validated, traverse through player's maps and existing ships
     * Add the offset coordinate to current ship coordinate and reset its original coordinate to the new coordinate
     * Reset cell status after moving
     * @param offset_coord takes in coordinate player wants to move to
     * @return boolean whether player's fleet was successfully moved
     */
    public boolean moveFleet(Coordinate offset_coord) {
        int moved_x;
        int moved_y;
        if (validateMoveFleet(offset_coord)) {
            for (int k = 0; k < this.player_maps.size(); k++) {
                Map curr_map = this.player_maps.get(k);
                Grid new_defense_grid = new Grid();
                for (int i = 0; i < curr_map.existing_ships.size(); i++) {
                    Ship shipy = curr_map.existing_ships.get(i); //get the ship
                    ArrayList<Coordinate> coordsList = curr_map.ship_coordinates.get(shipy);
                    ArrayList<Coordinate> movedCoordsList = new ArrayList<>();
                    for (int j = 0; j < coordsList.size(); j++) {
                        moved_x = coordsList.get(j).x + offset_coord.x;
                        moved_y = coordsList.get(j).y + offset_coord.y;
                        movedCoordsList.add(new Coordinate(moved_x, moved_y));
                        int updated_status = curr_map.defensiveGrid.checkCellStatus(coordsList.get(j).x, coordsList.get(j).y);
                        new_defense_grid.setCellStatus(updated_status, moved_x, moved_y);
                    }
                    curr_map.ship_coordinates.replace(shipy, movedCoordsList);
                    Coordinate old_Capts_Coords = curr_map.captains_quarters.get(shipy);
                    Coordinate new_Capts_Coords = new Coordinate(old_Capts_Coords.x + offset_coord.x, old_Capts_Coords.y + offset_coord.y);
                    curr_map.captains_quarters.replace(shipy, new_Capts_Coords);
                }
                curr_map.defensiveGrid = new_defense_grid;
            }
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * validateMoveFleet() function checks whether player's fleet is able to move in at least 1 of 4 directions
     * Traverses through all of the player's maps and existing ships
     * Adds offset coordinate to current coordinate and checks if moving fleet will cause it to go out of bounds
     * @param offset_coord takes in coordinate player wants to move to
     * @return boolean whether player's ship can move in desired direction
     */
    public boolean validateMoveFleet(Coordinate offset_coord){
        int moved_x;
        int moved_y;
        for (Map curr_map : this.player_maps) {
            for (int i = 0; i < curr_map.existing_ships.size(); i++) {
                Ship shipy = curr_map.existing_ships.get(i);
                ArrayList<Coordinate> coordsList = curr_map.ship_coordinates.get(shipy);
                for (Coordinate coordinate : coordsList) {
                    moved_x = coordinate.x + offset_coord.x;
                    moved_y = coordinate.y + offset_coord.y;
                    if (moved_x < 0 || moved_x > 9 || moved_y < 0 || moved_y > 9) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * validateAllDirections() function checks whether player's fleet is able to move in at least 1 of 4 directions
     * Creates coordinates for each direction by calling getOffsetCoord
     * Calls validateMoveFleet on each direction and checks that all of them return true or false
     * @return boolean whether all player's ships are able to be moved in at least one direction
     */
    public boolean validateAllDirections(){
        Coordinate north = getOffsetCoord("N");
        Coordinate south = getOffsetCoord("S");
        Coordinate east = getOffsetCoord("E");
        Coordinate west = getOffsetCoord("W");
        if (!validateMoveFleet(north) && !validateMoveFleet(south) && !validateMoveFleet(west) && !validateMoveFleet(east)){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * surrender() function checks whether player should surrender
     * Traverses through all player's maps and checks how many ships are alive on each map
     * If no existing ships, then player has surrendered
     * @return boolean whether player has surrendered or not
     */
    public boolean surrender() {
        int total = 0;
        for (int i = 0; i < this.player_maps.size(); i++) {
            total += this.player_maps.get(i).getShipsAlive();
        }

        if (total == 0) {
            return true;
        }
        return false;
    }
}
