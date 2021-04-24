package edu.colorado.binarybuffs;

/** Coordinate is a class that generates a coordinate object that is used for any x/y pairings used on the defensive/offensive grids */
public class Coordinate {
    public int x;
    public int y;

    /** Coordinate constructor which takes in integers of x and y, creates a paired coordinate */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //toString function that formats x and y to look like a coordinate pair
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
