package edu.colorado.binarybuffs;

public class Player {
    private String player_name;
    private int num_boats;
    private boolean turn;

    public Player(String name) {
        this.player_name = name;
        this.turn = false;
    }

    public String getName(Player player) {
        return this.player_name;
    }

    public boolean getTurn(Player player) {
        return this.turn;
    }

    public Ship createShip(String ship_name, int ship_length, int start_x, int start_y, int end_x, int end_y) {
        Ship ship1 = new Ship(ship_name, ship_length, start_x, start_y, end_x, end_y);

        return ship1;
    }
}
