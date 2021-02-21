package edu.colorado.binarybuffs;

import java.util.ArrayList;

public class Player {
    private String player_name;
    private int num_boats;
    private boolean turn;
    public ArrayList<Ship> shipFleet;

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

        ArrayList<Ship> fleet = new ArrayList<Ship>();

        Ship Minesweeper = new Ship("Minesweeper", 2);
        Ship Destroyer = new Ship("Destroyer", 3);
        Ship Battleship = new Ship("Battleship", 4);
        fleet.add(Minesweeper);
        fleet.add(Destroyer);
        fleet.add(Battleship);

        this.shipFleet = fleet;
        this.num_boats = fleet.size();
        return fleet;
    }

    public void attack(int x, int y, Grid playerGrid, Grid opponentGrid, Player opponent){
        //first check if cell has already been attacked
        //if yes --> be like this is invalid
        //if no --> be like okay
            //okay do the attack thing
            //see if that cell is occupied
                //decrease length of ship by 1
                    //if length of ship is 0: print "sunk ship"
            //change status of current player offensive grid
            //print you hit a cell

        int statusHit = playerGrid.offensive_grid[x][y];
        int statusOccupied = opponentGrid.player_grid[x][y];
        if (statusHit % 2 != 0){
            if (statusOccupied == 0){
                System.out.println("You've attempted attack, but there's nothing at this location");
                playerGrid.offensive_grid[x][y] = 2;
            }
            else if (statusOccupied == 1){
                System.out.println("You've attempted attack. Congrats! You hit a ship!");
                playerGrid.offensive_grid[x][y] = 4;

                //access ships and change their health

                //for ship in player2 ship fleet
                for(int i = 0; i < opponent.shipFleet.size(); i++){
                    //Array = getShipCoordinates(Ship ship)
                    ArrayList<Coordinate> listOfCoords = opponent.shipFleet.get(i).getShipCoordinates(opponent.shipFleet.get(i));
                    for(int j = 0; j < listOfCoords.size(); j++){
                        if ((x == listOfCoords.get(j).x) && (y == listOfCoords.get(j).y)){
                            opponent.shipFleet.get(i).reduceHealth(opponent.shipFleet.get(i), opponent);

                        }
                    }
                }
            }
        }
        else {
            System.out.println("You've already attacked here");
        }
    }
    public void reduceBoats() {
        this.num_boats--;
        if (this.surrender()) {
            System.out.println("You've sunk all of " + this.player_name + "'s boats! You are the winner." );
            System.out.println(this.player_name + " surrenders.");
        }
    }
    public boolean surrender() {
        if (this.num_boats == 0) {
            return true;
        }
        return false;
    }
}
