package edu.colorado.binarybuffs;

import org.w3c.dom.ranges.Range;

import java.util.Random;

public class Hurricane extends Disaster{
    public Hurricane(){
        Random rand = new Random();
        Coordinate coordinate = new Coordinate(rand.nextInt(10), rand.nextInt(10));


    }
    public void applyDisaster(newPlayer Player1, newPlayer Player2){
        //
    }
}
