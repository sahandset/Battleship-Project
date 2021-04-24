package edu.colorado.binarybuffs;

/** Animal is an abstract super class which creates 2 different types of Animal subclasses that can appear on player's maps
 * Narwhal
 * Jaws
 */
public abstract class Animal {
    private String name = "Animal";
    public Animal() {

    }

    public String getName() {return this.name;}

    //useAnimal() is used in both sub classes of Narwhal and Jaws, is used to call function to perform respective functionality
    public abstract void useAnimal(newPlayer curr_player, Map curr_player_map);
}
