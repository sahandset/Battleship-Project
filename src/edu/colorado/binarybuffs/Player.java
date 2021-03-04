package edu.colorado.binarybuffs;

import java.util.ArrayList;
import java.util.Hashtable;

public class Player {
    private String player_name;
    private int num_boats;
    private boolean turn;
    public ArrayList<Ship> ship_fleet;
    SonarPulse my_sonar_pulse = new SonarPulse();

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

    public int getNumBoats(Player player){return this.num_boats;}

    public ArrayList<Ship> createFleet() {

        ArrayList<Ship> fleet = new ArrayList<Ship>();

        Ship Minesweeper = new Ship("Minesweeper", 2);
        Ship Destroyer = new Ship("Destroyer", 3);
        Ship Battleship = new Ship("Battleship", 4);
        fleet.add(Minesweeper);
        fleet.add(Destroyer);
        fleet.add(Battleship);

        this.ship_fleet = fleet;
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
                System.out.println("You've attempted attack, but you've missed!");
                playerGrid.offensive_grid[x][y] = 2;
            }
            else if (statusOccupied == 1){
                playerGrid.offensive_grid[x][y] = 4;

                //access ships and change their health
                for(int i = 0; i < opponent.ship_fleet.size(); i++){

                    //see if cell hit was captain's quarters
                    Coordinate capt_quart = opponent.ship_fleet.get(i).getCaptainsQuarters(opponent.ship_fleet.get(i));
                    if (x == capt_quart.x && y == capt_quart.y){
                        if (opponent.ship_fleet.get(i).getArmorStatus(opponent.ship_fleet.get(i))) {
                            playerGrid.offensive_grid[x][y] = 2;
                            System.out.println("You've attempted attack, but you've missed!");
                            return;
                        }
                        else{
                            System.out.println("You've hit a captain's quarters!");
                            opponent.ship_fleet.get(i).sinkShip(opponent.ship_fleet.get(i), opponent);
                            return;
                        }
                    }
                    else {
                        //Array = getShipCoordinates(Ship ship)
                        ArrayList<Coordinate> list_of_coords = opponent.ship_fleet.get(i).getShipCoordinates(opponent.ship_fleet.get(i));
                        for (int j = 0; j < list_of_coords.size(); j++) {
                            if ((x == list_of_coords.get(j).x) && (y == list_of_coords.get(j).y)) {
                                opponent.ship_fleet.get(i).reduceHealth(opponent.ship_fleet.get(i), opponent);

                            }
                        }
                    }
                }
                System.out.println("You've attempted attack. Congrats! You hit a ship!");
            }
        }
        else {
            System.out.println("You've already attacked here");
            for (int i = 0; i < opponent.ship_fleet.size(); i++) {
                //see if cell hit was captain's quarters
                Coordinate capt_quart = opponent.ship_fleet.get(i).getCaptainsQuarters(opponent.ship_fleet.get(i));
                if (x == capt_quart.x && y == capt_quart.y) {
                    System.out.println("You've hit a captain's quarters!");
                    opponent.ship_fleet.get(i).sinkShip(opponent.ship_fleet.get(i), opponent);
                }
            }
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

    public Hashtable<String, String> useSonarPulse(int x, int y, Grid playerGrid, Player player1){
        Hashtable<String, String> grid_vision = my_sonar_pulse.sonarPulse(x, y, playerGrid, player1);
        if (grid_vision != null) {
            System.out.println(grid_vision);
        }
        else{
            System.out.println("You can't use the sonar pulse! You either have not sunk a ship or already used it more than 2 times.");
        }
        return grid_vision;
    }
}
