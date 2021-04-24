package edu.colorado.binarybuffs;

/**
 * Disaster is an abstract super class which creates 3 different types of Disaster subclasses that can each
 * randomly occur on player's maps at the start of every player's respective turn
 * Ghost Zone Disaster - Affects OceanMap and UnderwaterMap
 * Hurricane Disaster - Affects OceanMap
 * Asteroid Field Disaster - Affects SpaceMap
 */

public abstract class Disaster {

    public Disaster() {

    }
    /**
     * Initiates the disaster on a current player's map
     * @param current_player the current player who's map's will be affected by the disaster event
    */
    public abstract void applyDisaster(Player current_player);
}
