package edu.colorado.binarybuffs;

import java.lang.Double;
import java.util.ArrayList;

public class Bomb extends Weapon {

    public Bomb(){
        this.num_uses = 100; //constant set num times we can use this
    }

    public void attack(int x, int y, Grid playerGrid, Grid opponentGrid, Player opponent){
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




}
