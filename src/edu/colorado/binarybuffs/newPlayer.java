package edu.colorado.binarybuffs;

import java.awt.*;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Stack;

public class newPlayer {
    private String player_name;
    public ArrayList<Map> player_maps = new ArrayList<Map>();
    ArrayList<Weapon> player_weapons = new ArrayList<>();
    Hashtable<Weapon, Integer> weapon_uses = new Hashtable<Weapon, Integer>();
    Stack<Coordinate> fleet_moves = new Stack<Coordinate>();
    Stack<Coordinate> undo_moves = new Stack<Coordinate>();


    public newPlayer(String name) {
        this.player_name = name;
        player_maps.add(new OceanMap());
        player_maps.add(new UnderwaterMap());
        Bomb b = new Bomb();
        player_weapons.add(b);
        weapon_uses.put(b, 0);
    }

    public String getName() {
        return this.player_name;
    }

    public ArrayList<Map> getPlayerMaps() {
        return this.player_maps;
    }

    public boolean useWeapon(int weapon_choice, int x, int y, newPlayer opponent, int map_choice) {
        if (weapon_choice >= 0 && weapon_choice < this.player_weapons.size()) {
            Weapon weapon = this.player_weapons.get(weapon_choice);
            Map attacked_map = opponent.player_maps.get(map_choice);
            Map curr_players_map = this.player_maps.get(map_choice);
            boolean result = weapon.deployWeapon(x, y, opponent, attacked_map, curr_players_map, this);
            if (result) {
                int current_uses = weapon_uses.get(weapon);
                weapon_uses.replace(weapon, current_uses, current_uses++);
                if (weapon.checkAvailability(weapon_uses.get(weapon)) == false) {
                    player_weapons.remove(weapon);
                    weapon_uses.remove(weapon);
                }

                if (attacked_map.getNumSunkShips() > 0) {
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

                if (attacked_map.surrender()) {
                    System.out.println("You've sunk all of " + opponent.getName() + "'s boats! You are the winner.");
                    System.out.println(opponent.getName() + " surrenders.");
                }
                return result;
            }
        }
        System.out.println("You cannot use that weapon yet!");
        return false;
    }

    public boolean deployShip(newShip ship, int x, int y, String direction, int map_choice) {
        Map deploy_map = this.player_maps.get(map_choice);
        if (deploy_map.validateDeployment(ship)) {
            boolean deployed_successfully = deploy_map.placeShip(ship, x, y, direction);
            return deployed_successfully;
        } else {
            System.out.println("You cannot place a " + ship.getName() + " on " + deploy_map.getName());
            return false;
        }
    }

//        public boolean moveFleet(String direction, int map_choice) {
//            Coordinate offset_coord = new Coordinate(0, 0);
//            int moved_x = 0;
//            int moved_y = 0;
//            if ((direction.toLowerCase() == "north") || (direction.toLowerCase() == "n")) {
//                offset_coord = new Coordinate(0, -1);
//            }
//            else if ((direction.toLowerCase() == "south") || (direction.toLowerCase() == "s")) {
//                offset_coord = new Coordinate(0, 1);
//
//            } else if ((direction.toLowerCase() == "east") || (direction.toLowerCase() == "e")) {
//                offset_coord = new Coordinate(1, 0);
//
//            } else if ((direction.toLowerCase() == "west") || (direction.toLowerCase() == "w")) {
//                offset_coord = new Coordinate(-1, 0);
//            }
//            Map curr_players_map = this.player_maps.get(map_choice);
//
//
//            for (int i = 0; i < curr_players_map.existing_ships.size(); i++){
//                newShip shipy = curr_players_map.existing_ships.get(i);
//                ArrayList<Coordinate> coordsList = curr_players_map.ship_coordinates.get(shipy);
//                for (int j = 0; j < coordsList.size(); j++){
//                    moved_x = coordsList.get(j).x + offset_coord.x;
//                    moved_y = coordsList.get(j).y + offset_coord.y;
//                    if (moved_x < 0 || moved_x > 9 || moved_y < 0 || moved_y > 9) {
//                        return false;
//                    }
//                }
//            }
//            //set all the stati to 0
//            for (int i = 0; i < curr_players_map.existing_ships.size(); i++){
//                //get the ship
//                    //get ships direction --> ship_direction
//                    //get ships start  --> curr_players_Map.ship_coordinates.get(shipy).get(0)
//                    //get new start = start + transition
//                //remove ship from basically everything :)
//                //call place ship on this ship w ship object, ship direction, and ship start coords
//
//                //get the ship
//                //get ship coordsList and traverse through it to create a new coordlist w transition
//                    //every time you append this new cord to the new coordlist
//                    //set the cell status at the new coord = 1
//                //replace old coordsList with new coordslist for that ship
//                //get ships captains quarters and create a new captains quarters w transition
//                //replace old captains quarters with new cpatians quarters
//
//                newShip shipy = curr_players_map.existing_ships.get(i); //get the ship
//                ArrayList<Coordinate> coordsList = curr_players_map.ship_coordinates.get(shipy);
//                ArrayList<Coordinate> movedCoordsList = new ArrayList<Coordinate>;
//                for (int j = 0; j < coordsList.size(); j++){
//                    moved_x = coordsList.get(j).x + offset_coord.x;
//                    moved_y = coordsList.get(j).y + offset_coord.y;
//                    movedCoordsList.add(new Coordinate(moved_x, moved_y));
//                    curr_players_map.defensiveGrid.setCellStatus(1, moved_x, moved_y);
//                }
//                curr_players_map.ship_coordinates.replace(shipy, movedCoordsList);
//
//                Coordinate new_Capts_Coords = shipy.getCaptsCoords(moved_x, moved_y, direction);
//                curr_players_map.captains_quarters.replace(shipy, new_Capts_Coords);
//            }
//
//        }

    public Coordinate getOffsetCoord(String direction) {
        Coordinate offset_coord = new Coordinate(0, 0);

        if ((direction.toLowerCase() == "north") || (direction.toLowerCase() == "n")) {
            offset_coord = new Coordinate(0, -1);
        } else if ((direction.toLowerCase() == "south") || (direction.toLowerCase() == "s")) {
            offset_coord = new Coordinate(0, 1);

        } else if ((direction.toLowerCase() == "east") || (direction.toLowerCase() == "e")) {
            offset_coord = new Coordinate(1, 0);

        } else if ((direction.toLowerCase() == "west") || (direction.toLowerCase() == "w")) {
            offset_coord = new Coordinate(-1, 0);
        }

        return offset_coord;
    }

    public Coordinate reverseMove(Coordinate c) {
        return new Coordinate(c.x * -1, c.y * -1);
    }

    public boolean playerMoveFleet(String direction) {
        Coordinate offset_coord = getOffsetCoord(direction);
        undo_moves.clear();
        boolean success = moveFleet(offset_coord);
        fleet_moves.push(offset_coord);
        if (success) {
            System.out.println("You have successfully moved your fleet!");
        }
        else {
            System.out.println("You cannot move your fleet, your ships are out of bounds!");
        }
        return success;
    }

    public boolean undoMove() {
        if (!fleet_moves.empty()) {
            Coordinate temp_move = fleet_moves.pop();
            undo_moves.push(temp_move);
            Coordinate reversed_move = reverseMove(temp_move);
            boolean success = moveFleet(reversed_move);
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

    public boolean redoMove() {
        //check if stack is empty
        if (!undo_moves.empty()) {
            Coordinate temp_move = undo_moves.pop();
            fleet_moves.push(temp_move);
            boolean success = moveFleet(temp_move);
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

    public boolean moveFleet(Coordinate offset_coord) {
        int moved_x = 0;
        int moved_y = 0;
        for (int k = 0; k < this.player_maps.size(); k++) {
            Map curr_map = this.player_maps.get(k);
            for (int i = 0; i < curr_map.existing_ships.size(); i++) {
                newShip shipy = curr_map.existing_ships.get(i);
                ArrayList<Coordinate> coordsList = curr_map.ship_coordinates.get(shipy);
                for (int j = 0; j < coordsList.size(); j++) {
                    moved_x = coordsList.get(j).x + offset_coord.x;
                    moved_y = coordsList.get(j).y + offset_coord.y;
                    if (moved_x < 0 || moved_x > 9 || moved_y < 0 || moved_y > 9) {
                        return false;
                    }
                }
            }
        }
        for (int k = 0; k < this.player_maps.size(); k++) {
            Map curr_map = this.player_maps.get(k);
            curr_map.defensiveGrid.setAllCellStatus(0);
            for (int i = 0; i < curr_map.existing_ships.size(); i++) {
                newShip shipy = curr_map.existing_ships.get(i); //get the ship
                ArrayList<Coordinate> coordsList = curr_map.ship_coordinates.get(shipy);
                ArrayList<Coordinate> movedCoordsList = new ArrayList<Coordinate>();
                for (int j = 0; j < coordsList.size(); j++) {
                    moved_x = coordsList.get(j).x + offset_coord.x;
                    moved_y = coordsList.get(j).y + offset_coord.y;
                    movedCoordsList.add(new Coordinate(moved_x, moved_y));
                    curr_map.defensiveGrid.setCellStatus(1, moved_x, moved_y);
                }
                curr_map.ship_coordinates.replace(shipy, movedCoordsList);
                Coordinate old_Capts_Coords = curr_map.captains_quarters.get(shipy);
                Coordinate new_Capts_Coords = new Coordinate(old_Capts_Coords.x + offset_coord.x, old_Capts_Coords.y + offset_coord.y);
                curr_map.captains_quarters.replace(shipy, new_Capts_Coords);
            }
        }
        //fleet_moves.push(offset_coord);
        return true;
    }
}


//    public Boolean moveFleet(Coordinate offset_coord, int map_choice){
//        Map curr_players_map = this.player_maps.get(map_choice);
//        int moved_x = 0;
//        int moved_y = 0;
//        boolean undo_chosen = false;
//        boolean redo_chosen = false;
//        offset_coord = getOffsetCoord();
//        for (int i = 0; i < curr_players_map.existing_ships.size(); i++){
//            newShip shipy = curr_players_map.existing_ships.get(i);
//            ArrayList<Coordinate> coordsList = curr_players_map.ship_coordinates.get(shipy);
//            for (int j = 0; j < coordsList.size(); j++){
//                moved_x = coordsList.get(j).x + offset_coord.x;
//                moved_y = coordsList.get(j).y + offset_coord.y;
//                if (moved_x < 0 || moved_x > 9 || moved_y < 0 || moved_y > 9) {
//                    return false;
//                }
//            }
//        }
//        for (int i = 0; i < curr_players_map.existing_ships.size(); i++) {
//            newShip shipy = curr_players_map.existing_ships.get(i); //get the ship
//            ArrayList<Coordinate> coordsList = curr_players_map.ship_coordinates.get(shipy);
//            ArrayList<Coordinate> movedCoordsList = new ArrayList<Coordinate>;
//            for (int j = 0; j < coordsList.size(); j++){
//                moved_x = coordsList.get(j).x + offset_coord.x;
//                moved_y = coordsList.get(j).y + offset_coord.y;
//                movedCoordsList.add(new Coordinate(moved_x, moved_y));
//                curr_players_map.defensiveGrid.setCellStatus(0, coordsList.get(j).x, coordsList.get(j).y);
//                curr_players_map.defensiveGrid.setCellStatus(1, moved_x, moved_y);
//            }
//            fleet_moves.push(offset_coord);
//            curr_players_map.ship_coordinates.replace(shipy, movedCoordsList);
//
//            //stop function here
//            if (undo_chosen) {
//                Coordinate temp_move = fleet_moves.pop();
//                undo_moves.push(temp_move);
//                moveFleet(fleet_moves.pop(), map_choice);
//                if (redo_chosen) {
//                    fleet_moves.push(temp_move);
//                    undo_moves.pop();
//                    moveFleet(fleet_moves.pop(), map_choice);
//                }
//                else {
//                    moveFleet(offset_coord, map_choice);
//                }
//            }
//        }
//            Coordinate new_Capts_Coords = shipy.getCaptsCoords(moved_x, moved_y, direction);
//            curr_players_map.captains_quarters.replace(shipy, new_Capts_Coords);
//
//        }
//}


//in ship coordinates hash table, fund shipy, get coordslist -> create a new coords list
// fleetmoves stack --> offset coords
// undo moves stack -->
// as we traverse through coordsList, push(offset coords) to fleetmoves stack
//if undo
    // pop off fleetmoves stacks
    // push it to to undo moves stack

