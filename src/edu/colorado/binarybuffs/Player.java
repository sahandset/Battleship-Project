package edu.colorado.binarybuffs;

import java.util.ArrayList;

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

    public ArrayList<Ship> createFleet() {

        ArrayList<Ship> Fleet = new ArrayList<Ship>();

        Ship Minesweeper = new Ship("Minesweeper", 2);
        Ship Destroyer = new Ship("Destroyer", 3);
        Ship Battleship = new Ship("Battleship", 4);
        Fleet.add(Minesweeper);
        Fleet.add(Destroyer);
        Fleet.add(Battleship);

        return Fleet;
    }
}
