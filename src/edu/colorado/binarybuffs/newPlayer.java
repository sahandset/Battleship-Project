package edu.colorado.binarybuffs;

import java.util.Hashtable;
import java.util.ArrayList;

public class newPlayer {
    private String player_name;
    ArrayList<Map> player_maps = new ArrayList<Map>();
    Hashtable<Weapon, Integer> player_weapons = new Hashtable<Weapon, Integer>();

    public newPlayer(String name) {
        this.player_name = name;
        player_maps.add(new OceanMap());
        player_maps.add(new UnderwaterMap());
    }

    public boolean useWeapon(Weapon weapon, int x, int y, newPlayer opponent, int map_choice) {
        Map attacked_map = opponent.player_maps.get(map_choice);
        Map curr_players_map = this.player_maps.get(map_choice);
        return weapon.deployWeapon(x, y, opponent, attacked_map, curr_players_map);

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


}
