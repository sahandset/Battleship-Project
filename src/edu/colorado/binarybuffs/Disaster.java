package edu.colorado.binarybuffs;

/** Disaster is an abstract super class which creates 3 different types of Disaster subclasses that can occur on player's maps
 * Ghost Zone Disaster
 * Hurricane Disaster
 * Asteroid Field Disaster
 */

public abstract class Disaster {

    public Disaster() {

    }

    //applyDisasterFunction() in all 3 subclasses, performs the actual disaster on player maps
    public abstract void applyDisaster(Player current_player);
}
