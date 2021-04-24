package edu.colorado.binarybuffs;

public class Action {
    private int x_coord;
    private int y_coord;

    public Action(int x, int y){
        x_coord = x;
        y_coord = y;
    }

    public Coordinate getCoordinate(){
        return new Coordinate(x_coord, y_coord);
    }

    public Coordinate getReversedCoordinate() {
        return new Coordinate(x_coord * -1, y_coord * -1);
    }

    public boolean undoAction(Player player){
        return player.moveFleet(getReversedCoordinate());
    }

    public boolean redoAction(Player player){
        return player.moveFleet(getCoordinate());
    }
}
