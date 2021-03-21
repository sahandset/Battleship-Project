package edu.colorado.binarybuffs;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Stack;

public class newPlayer {
    private String player_name;
    ArrayList<Map> player_maps = new ArrayList<Map>();
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

    public boolean useWeapon(int weapon_choice, int x, int y, newPlayer opponent, int map_choice) {
        if (weapon_choice >= 0 && weapon_choice < player_weapons.size()) {
            Weapon weapon = player_weapons.get(weapon_choice);
            Map attacked_map = opponent.player_maps.get(map_choice);
            Map curr_players_map = this.player_maps.get(map_choice);
            boolean result = weapon.deployWeapon(x, y, opponent, attacked_map, curr_players_map);
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

        public boolean deployShip (newShip ship,int x, int y, String direction,int map_choice){
            Map deploy_map = this.player_maps.get(map_choice);
            if (deploy_map.validateDeployment(ship)) {
                boolean deployed_successfully = deploy_map.placeShip(ship, x, y, direction);
                return deployed_successfully;
            } else {
                System.out.println("You cannot place a " + ship.getName() + " on " + deploy_map.getName());
                return false;
            }
        }

        public boolean moveFleet(String direction, int map_choice) {
            Coordinate offset_coord = new Coordinate(0, 0);
            int moved_x = 0;
            int moved_y = 0;
            if ((direction.toLowerCase() == "north") || (direction.toLowerCase() == "n")) {
                offset_coord = new Coordinate(0, -1);
            }
            else if ((direction.toLowerCase() == "south") || (direction.toLowerCase() == "s")) {
                offset_coord = new Coordinate(0, 1);

            } else if ((direction.toLowerCase() == "east") || (direction.toLowerCase() == "e")) {
                offset_coord = new Coordinate(1, 0);

            } else if ((direction.toLowerCase() == "west") || (direction.toLowerCase() == "w")) {
                offset_coord = new Coordinate(-1, 0);
            }
            Map curr_players_map = this.player_maps.get(map_choice);
            for (int i = 0; i < curr_players_map.existing_ships.size(); i++){
                newShip shipy = curr_players_map.existing_ships.get(i);
                ArrayList<Coordinate> coordsList = curr_players_map.ship_coordinates.get(shipy);
                for (int j = 0; j < coordsList.size(); j++){
                    moved_x = coordsList.get(j).x + offset_coord.x;
                    moved_y = coordsList.get(j).y + offset_coord.y;
                    if (moved_x < 0 || moved_x > 9 || moved_y < 0 || moved_y > 9) {
                        return false;
                    }
                }
            }

        }
}








