package edu.colorado.binarybuffs;

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
    //  1: (empty, not attacked)
    //  2: (empty, missed)
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
}
