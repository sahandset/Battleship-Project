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
}
