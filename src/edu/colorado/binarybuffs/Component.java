package edu.colorado.binarybuffs;

import java.util.ArrayList;

/**
 * This interface is inherited by Ship and Fleet, representing the composite design pattern
 * This class allows them to be used interchangeably.
 */
public interface Component {
    //Getter method to get the size of the ship

    /**
     * Retreives the size of the fleet
     * @return fleetSize()
     */
    int getFleetSize();

    /**
     * Retrieves and arraylist of all the names of ships
     * @return arraylist of shipnames
     */
    ArrayList<String> getShipNames();
}
