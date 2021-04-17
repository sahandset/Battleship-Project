package edu.colorado.binarybuffs;

import java.awt.*;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Stack;

public class newPlayer {
    private String player_name;
    public ArrayList<Map> player_maps = new ArrayList<Map>();
    ArrayList<Weapon> player_weapons = new ArrayList<>();
    ArrayList<Boost> player_boosts = new ArrayList<>();
    Hashtable<Weapon, Integer> weapon_uses = new Hashtable<Weapon, Integer>();
    Hashtable<Boost, Integer> boost_uses = new Hashtable<Boost, Integer>();

//    Stack<Coordinate> fleet_moves = new Stack<Coordinate>();
//    Stack<Coordinate> undo_moves = new Stack<Coordinate>();

    Stack<Action> fleet_move_actions = new Stack<>();
    Stack<Action> undo_move_actions = new Stack<>();

    private int ships_sunk = 0;
    private boolean surrender = false;


    public newPlayer(String name) {
        this.player_name = name;
        player_maps.add(new OceanMap());
        player_maps.add(new UnderwaterMap());
        player_maps.add(new SpaceMap());
        Bomb b = new Bomb();
        Lifesaver boo = new Lifesaver();
        player_boosts.add(boo);
        player_weapons.add(b);
        weapon_uses.put(b, 0);
        boost_uses.put(boo, 0);
    }

    public String getName() {
        return this.player_name;
    }

    public ArrayList<Map> getPlayerMaps() {
        return this.player_maps;
    }

    public int getShipsSunk() {
        return this.ships_sunk;
    }

    public void incrementShipSunkCount(){
        this.ships_sunk++;
    }

    public boolean getSurrenderStatus() {
        return this.surrender;
    }

    public void setSurrenderStatus() {
        surrender = true;
    }

    public boolean useWeapon(int weapon_choice, int x, int y, newPlayer opponent, int map_choice, int method_choice) {
        if (weapon_choice >= 0 && weapon_choice < this.player_weapons.size()) {
            Weapon weapon = this.player_weapons.get(weapon_choice);
            Map attacked_map = opponent.player_maps.get(map_choice);
            Map curr_players_map = this.player_maps.get(map_choice);
            boolean result = weapon.deployWeapon(x, y, opponent, attacked_map, curr_players_map, this);
            if (result && weapon_uses.containsKey(weapon)) {
                int current_uses = weapon_uses.get(weapon);
                weapon_uses.replace(weapon, current_uses + 1);
                if (weapon.checkAvailability(weapon_uses.get(weapon)) == false) {
                    player_weapons.remove(weapon);
                    weapon_uses.remove(weapon);
                }

                if (opponent.surrender()) {
                    System.out.println("You've sunk all of " + opponent.getName() + "'s boats! You are the winner.");
                    System.out.println(opponent.getName() + " surrenders.");
                    opponent.surrender = true;
                }
            }
            return result;
        }
        System.out.println("You cannot use that weapon!");
        return false;
    }

    public boolean useBoost(int boost_choice, int ship_choice, int map_choice) {
        if (boost_choice >= 0 && boost_choice < this.player_boosts.size()) {
            Boost boost = this.player_boosts.get(boost_choice);
            Map curr_players_map = this.player_maps.get(map_choice);
            newShip ship = curr_players_map.existing_ships.get(ship_choice);
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

    public boolean deployShip(newShip ship, int x, int y, String direction, int map_choice) {
        Map deploy_map = this.player_maps.get(map_choice);
        if (deploy_map.validateDeployment(ship)) {
            boolean deployed_successfully = deploy_map.placeShip(ship, x, y, direction);
            deploy_map.checkForAnimal(this);
            return deployed_successfully;
        } else {
            System.out.println("You cannot place a " + ship.getName() + " on " + deploy_map.getName());
            return false;
        }
    }

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

//    public Coordinate reverseMove(Coordinate c) {
//        return new Coordinate(c.x * -1, c.y * -1);
//    }

    public boolean playerMoveFleet(String direction) {
        Coordinate offset_coord = getOffsetCoord(direction);
        undo_move_actions.clear();
        boolean success = moveFleet(offset_coord);
        if (success) {
            Action newAction = new Action(offset_coord.x, offset_coord.y);
            fleet_move_actions.push(newAction);
            System.out.println("You have successfully moved your fleet!");
        }
        else {
            System.out.println("You cannot move your fleet, your ships are out of bounds!");
        }
        return success;
    }

    public boolean undo(){
        if (!fleet_move_actions.empty()) {
            Action temp_action = fleet_move_actions.pop();
            undo_move_actions.push(temp_action);
            boolean success = temp_action.undoAction(this);
            //Coordinate reversed_move = reverseMove(temp_move);
            //boolean success = moveFleet(reversed_move);
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

//    public boolean undoMove() {
//        if (!fleet_moves.empty()) {
//            Coordinate temp_move = fleet_moves.pop();
//            undo_moves.push(temp_move);
//            Coordinate reversed_move = reverseMove(temp_move);
//            boolean success = moveFleet(reversed_move);
//            if (success) {
//                System.out.println("You have successfully undoed your move!");
//            }
//            else {
//                System.out.println("You cannot undo this move!");
//            }
//            return success;
//        }
//        else {
//            System.out.println("You have no moves to undo!");
//            return false;
//        }
//
//    }

//    public boolean redoMove() {
//        //check if stack is empty
//        if (!undo_moves.empty()) {
//            Coordinate temp_move = undo_moves.pop();
//            fleet_moves.push(temp_move);
//            boolean success = moveFleet(temp_move);
//            if (success) {
//                System.out.println("You have successfully redone your move!");
//            }
//            else {
//                System.out.println("You cannot redo this move!");
//            }
//            return success;
//        }
//        else{
//            System.out.println("You have no moves to redo!");
//            return false;
//        }
//    }

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
            //curr_map.defensiveGrid.setAllCellStatus(0);
            newGrid new_defense_grid = new newGrid();
            for (int i = 0; i < curr_map.existing_ships.size(); i++) {
                newShip shipy = curr_map.existing_ships.get(i); //get the ship
                ArrayList<Coordinate> coordsList = curr_map.ship_coordinates.get(shipy);
                ArrayList<Coordinate> movedCoordsList = new ArrayList<Coordinate>();
                for (int j = 0; j < coordsList.size(); j++) {
                    moved_x = coordsList.get(j).x + offset_coord.x;
                    moved_y = coordsList.get(j).y + offset_coord.y;
                    movedCoordsList.add(new Coordinate(moved_x, moved_y));
                    //curr_map.defensiveGrid.setCellStatus(1, moved_x, moved_y);
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

    public boolean surrender() {
        int total = 0;
        //go through all the player maps and check their ships alive
        for (int i = 0; i < player_maps.size(); i++) {
            total += this.player_maps.get(i).getShipsAlive();
        }
        //if all ships alive == 0
        //then return true
        if (total == 0) {
            return true;
        }
        return false;
    }
}
