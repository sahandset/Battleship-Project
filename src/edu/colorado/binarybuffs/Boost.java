package edu.colorado.binarybuffs;

public abstract class Boost {
    public int num_uses;
    private String name;

    public Boost(){

    }
    public String getName(){
        return this.name;
    }

    public abstract boolean equipBoost(Ship ship, Map current_player_map, Player current_player);

    public abstract boolean checkAvailability(int num_used);
}
