package edu.colorado.binarybuffs;

/** Boost is an abstract superclass which can create Boost subclasses that can benefit the player
 * Lifesaver
 */
public abstract class Boost {
    public int num_uses;
    private String name;

    public Boost(){

    }
    public String getName(){
        return this.name;
    }

    //function implemented in subclass, performs boost functionality
    public abstract boolean equipBoost(Ship ship, Map current_player_map, Player current_player);

    //function implemented in subclass, checks boost uses
    public abstract boolean checkAvailability(int num_used);
}
