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

    public void createShip(Ship ship_name, Ship ship_length, Grid grid1) {
        Ship ship1 = new Ship("Minesweeper", 2);
        Ship ship2 = new Ship("Destroyer", 3);
        Ship ship3 = new Ship("Battleship", 4);

        //grid1.placeShip(ship1, ship2, ship3);
    }
}
