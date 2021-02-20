package edu.colorado.binarybuffs;
// This is the  baseclass for your ship.  Modify accordingly
// TODO: practice good OO design
public class Ship {
    private String ship_name;
    private int ship_length;
    private int health_value;
    private String status;

    public Ship(String ship_name, int ship_length) {
        this.ship_name = ship_name;
        this.ship_length = ship_length;
        this.health_value = health_value;
        this.status = status;
    }


    //Team BinaryBuffs, pair 1 was here
    // Team Binary Buffs, pair 2 was here

    // TODO: create appropriate getter and setter methods
    // TODO: Understand encapsulation
    // TODO: Understand what these todo comments mean

    public  void show()     {     // dunno why this is here maybe it is just an example method
        System.out.println("IF you can't see this then something is severely wrong!!");
    }

    //
    public String getShipName(Ship ship1) {
        return this.ship_name;
    }

    public int getShipLength(Ship ship1) {
        return this.ship_length;
    }

}
