package edu.colorado.binarybuffs;

public abstract class Weapon {
    public int num_uses;
    private String name;

    public Weapon(){

    }
    public String getName(){
        return this.name;
    }

    public abstract boolean deployWeapon(int x, int y, newPlayer opponent, Map attacked_map, Map current_player_map, newPlayer currentPlayer, int method_choice);

    public abstract boolean checkAvailability(int num_used);

}

