package edu.colorado.binarybuffs;

public class Action {
    private int x_coord;
    private int y_coord;

    /**
     * Constructor for Action object
     * The action is created whenever a user moves their fleet (or undo/redo)
     * Takes in the x and y that identifies the direction of "action"
     * @param x the x direction of the movement/action
     * @param y the y direction of the movement/action
     */
    public Action(int x, int y){
        x_coord = x;
        y_coord = y;
    }

    /**
     * Retrieves private variables and puts it into a Coordinate object
     * @return Coordinate of the x,y move
     */
    public Coordinate getCoordinate(){
        return new Coordinate(x_coord, y_coord);
    }

    /**
     * Retrieves private variables, calculates the opposite move (aka the undo)
     * and puts it into a Coordinate object
     * @return oordinate of the reverse x,y move
     */
    public Coordinate getReversedCoordinate() {
        return new Coordinate(x_coord * -1, y_coord * -1);
    }

    /**
     * Allows a player to undo an action of move fleet that they committed.
     * Calls the designated moveFleet function with the reversedCoordinate
     * @param player the player who is performing the action
     * @return boolean : the success of the undo action
     */
    public boolean undoAction(Player player){
        return player.moveFleet(getReversedCoordinate());
    }

    /**
     * Allows a player to red an action of move fleet that they committed
     * Calls the designated moveFleet function with the initial coordinate
     * @param player the player who is performing the action
     * @return boolean : the success of the redo action
     */
    public boolean redoAction(Player player){
        return player.moveFleet(getCoordinate());
    }
}
