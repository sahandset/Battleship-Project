package edu.colorado.binarybuffs;

/**
 * Boost is an abstract superclass which can create Boost subclasses that can benefit the player
 * Lifesaver
 */
public abstract class Boost {
    public int num_uses;
    private String name;

    //Constructor for Boost Class
    public Boost(){
    }
    //Getter function that returns name of boost
    public String getName(){
        return this.name;
    }

    /**
     * equipBoost() is abstract function that performs the functionality of boost
     * @param ship object of type Ship class which is the ship that boost will revive
     * @param current_player_map object of type Map class which is the current player's map where functionality occurs
     * @param current_player object of type Player class which is the current player on the turn
     * @return boolean whether the ship has been successfully revived
     */
    public abstract boolean equipBoost(Ship ship, Map current_player_map, Player current_player);


    /**
     * checkAvailability() is abstract function that checks uses of boost
     * @param num_used integer value that tracks how many times a boost has been used
     * @return boolean whether the boost is able to be used
     */
    public abstract boolean checkAvailability(int num_used);
}
