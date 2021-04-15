package edu.colorado.binarybuffs;

public class Jaws extends Animal{

    private String name = "Jaws";

    public Jaws() {

    }

    public void biteShip(newPlayer curr_player, Map curr_player_map) {
//        if (curr_player.player_weapons.size() == 2) {
//            Weapon sonar_pulse = curr_player.player_weapons.get(1);
//            int current_uses = curr_player.weapon_uses.get(sonar_pulse);
//            curr_player.weapon_uses.replace(sonar_pulse, current_uses - 1);
//            curr_player_map.animal_coordinates.remove(this);
//            curr_player_map.animals.remove(this);
//        }
//        else {
//            System.out.println("Narwhal tried to give you more sonar pulse uses, but you don't have a sonar pulse");
//        }

    }

    public void useAnimal(newPlayer curr_player, Map curr_player_map) {
        biteShip(curr_player, curr_player_map);
    }
}
