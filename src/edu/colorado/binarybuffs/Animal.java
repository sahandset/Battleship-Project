package edu.colorado.binarybuffs;

/**
 * Animal is an abstract super class which creates 2 different types of Animal subclasses that can appear on player's maps
 * Narwhal
 * Jaws
 */
public abstract class Animal {
    private String name = "Animal";

    //Constructor for Animal Class
    public Animal() {

    }

    public String getName() {return this.name;}

    /**
     * useAnimal() is used in both sub classes of Narwhal and Jaws, is used to call function to perform respective functionality
     * @param curr_player object of type Player class which is the current player on the turn
     * @param curr_player_map object of type Map class which is the current player's map where functionality occurs
     * @return void
     */
    public abstract void useAnimal(Player curr_player, Map curr_player_map);

}
