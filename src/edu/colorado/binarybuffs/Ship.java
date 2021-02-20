package edu.colorado.binarybuffs;
// This is the  baseclass for your ship.  Modify accordingly
// TODO: practice good OO design
public class Ship {
    private String ship_name;
    private int ship_length;
    private int start_x;
    private int start_y;
    private int end_x;
    private int end_y;
    private int direction;
    private int health_value;
    private String status;

    public Ship(String ship_name, int ship_length, int start_x, int start_y, int end_x, int end_y) {
        this.ship_name = ship_name;
        this.ship_length = ship_length;
        this.health_value = ship_length;
        this.status = "alive";
        this.start_x = start_x;
        this.start_y = start_y;
        this.end_x = end_x;
        this.end_y = end_y;
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
