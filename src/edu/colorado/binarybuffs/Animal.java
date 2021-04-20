package edu.colorado.binarybuffs;

public abstract class Animal {
    private String name = "Animal";
    public Animal() {

    }

    public String getName() {return this.name;}
    public abstract void useAnimal(newPlayer curr_player, Map curr_player_map);
}
