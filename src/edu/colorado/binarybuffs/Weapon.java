package edu.colorado.binarybuffs;

public abstract class Weapon {
    public int num_uses;

    public Weapon(){

    }

    public abstract boolean deployWeapon(int x, int y, newPlayer opponent, Map attacked_map, Map current_player_map);
}

