package edu.colorado.binarybuffs;

public class Jaws extends Animal{

    private String name = "Jaws";

    public Jaws() {

    }

    public String getName() {return this.name;}
    public void biteShip(newPlayer curr_player, Map curr_player_map) {
        Weapon jaws_bomb = new Bomb();
        jaws_bomb.deployWeapon(curr_player_map.animal_coordinates.get(this).x, curr_player_map.animal_coordinates.get(this).y, curr_player, curr_player_map, curr_player_map, curr_player, 1);

    }

    public void useAnimal(newPlayer curr_player, Map curr_player_map) {
        biteShip(curr_player, curr_player_map);
    }

}
