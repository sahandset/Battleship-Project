package edu.colorado.binarybuffs;

import java.util.ArrayList;

public class Player {
    private String player_name;
    private int num_boats;
    private boolean turn;
    //public array shipFleet;

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

    public void attack(int x, int y, Grid grid1, Grid grid2, Player player2){
        //first check if cell has already been attacked
        //if yes --> be like this is invalid
        //if no --> be like okur
            //okay do the attack thing
            //see if that cell is occupied
                //decrease length of ship by 1
                    //if length of ship is 0: print "sunk ship"
            //change status of current player offensive grid
            //print you hit a cell

        int status = grid1.offensive_grid[x][y];
        if (status % 2 != 0){
            if (status == 1){
                System.out.println("You've attempted attack, but there's nothing at this location");
                grid1.offensive_grid[x][y] = 2;
            }
            else if (status == 3){
                System.out.println("You've attempted attack. Congrats! You hit a ship!");
                grid1.offensive_grid[x][y] = 4;
                //access ships and change their health
            }
        }
        else {
            System.out.println("You've already attacked here");
        }
    }

    //we need to:
        //check if ship has been sunk
}
