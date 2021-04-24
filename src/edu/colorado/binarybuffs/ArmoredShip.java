package edu.colorado.binarybuffs;

/** ArmoredShip is an interface that denotes whether a ship is armored, meaning that
       it takes 2 hits on the ship's captain's quarters in order to sink the ship
 * Contains 2 functions that are used to check whether the ship has been hit twice on captain's quarters
 * */
public interface ArmoredShip {
    int getHitCount();
    void updateHitCount();
}
