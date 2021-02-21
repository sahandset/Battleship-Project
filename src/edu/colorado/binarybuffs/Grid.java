package edu.colorado.binarybuffs;

import java.util.ArrayList;

public class Grid {
    //put some attributes here
    private static int length_x = 10;
    private static int length_y = 10;
    public int [][] player_grid = new int [length_x][length_y];
    public int [][] offensive_grid = new int [length_x][length_y];

    //put the constructor that initializes some attributes here
    //Cell status
    //  1: empty, not attacked
    //  2: empty, missed
    //  3: occupied, not hit
    //  4: occupied, hit

    //Player Grid Status of Ships
    //0: Ship does not exist
    //1: Ship exists

    //Offensive Grid Status of Moves
    //Variable 1: Hit/miss
    //Variable 2: Empty/not empty
    //  1: (empty, not hit)
    //  2: (empty, hit and missed)
    //  3: (occupied, not hit)
    //  4: (occupied, hit)

    public Grid() {

        for (int i = 0; i < length_x; i++) {
            for (int j = 0; j < length_y; j++) {
                player_grid[i][j] = 0;
                offensive_grid[i][j] = 1;
            }
        }
    }

    //method for checking offensive grid status
    public int checkOffensiveGridStatus(int x, int y){
        return offensive_grid[x][y];
    }


    //add some methods here
    public int getSize(){
        //return length_x;
        return player_grid.length;
    }

    public int checkCellStatus(int x, int y) {
    return player_grid[x][y];
    }

    public void placeShip(Ship ship, int start_x, int start_y, int end_x, int end_y) {
        if (validateShip(ship.getShipLength(ship), start_x,  start_y,  end_x, end_y)) {
            ship.setShipCoordinates(start_x, start_y, end_x, end_y);

            int num_cells = ship.getShipCoordinates(ship).size();
            ArrayList<Coordinate> ship_cells = ship.getShipCoordinates(ship);
            for (int i = 0; i < num_cells; i++) {
                setCellStatus(1, ship_cells.get(i).x, ship_cells.get(i).y);
            }
        }
    }

    public void setCellStatus(int condition, int x, int y) {
        player_grid[x][y] = 1;
    }

    public boolean validateShip(int ship_length, int start_x, int start_y, int end_x, int end_y)
    {
        if(start_x == end_x)
        {
            if (start_y + ship_length == end_y) {
                for (int i = start_y; i <= end_y; i++) {
                    if (player_grid[start_x][i] == 1) {
                        System.out.println("There's already a ship here!");
                        System.out.println("Make sure to place ships horizontally or vertically, not diagonally!");
                        return false;
                    }
                }
                return true;
            }
            if (start_y - ship_length == end_y) {
                for (int i = end_y; i <= start_y; i++) {
                    if (player_grid[start_x][i] == 1) {
                        System.out.println("There's already a ship here!");
                        return false;
                    }
                }
            }
        }
        else if(start_y == end_y)
        {
            if (start_x + ship_length == end_y) {
                for (int i = start_x; i <= end_x; i++) {
                    if (player_grid[i][start_y] == 1) {
                        System.out.println("There's already a ship here!");
                        System.out.println("Make sure to place ships horizontally or vertically, not diagonally!");
                        return false;
                    }
                }
                return true;
            }
            if (start_x - ship_length == end_y) {
                for (int i = end_x; i <= start_x; i++) {
                    if (player_grid[i][start_y] == 1) {
                        System.out.println("There's already a ship here!");
                        return false;
                    }
                }
            }
        }
        else if (start_x < 0 || start_y < 0 || end_x > length_x || end_y > length_y)
        {
            return false;
        }

        System.out.println("This is invalid");
        return false;
    }
}
