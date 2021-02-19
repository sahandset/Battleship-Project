package edu.colorado.binarybuffs;

public class Grid {
    //put some attributes here
    private static int length_x = 10;
    private static int length_y = 10;
    public int [][] player_grid;
    public int [][] offensive_grid;

    //put the constructor that initializes some attributes here
    //Cell status
    //  1: empty, not attacked
    //  2: empty, missed
    //  3: occupied, not hit
    //  4: occupied, hit

    public Grid() {
        int [][] grid = new int [length_x][length_y];

        for (int i = 0; i < length_x; i++) {
            for (int j = 0; j < length_y; j++) {
                grid[i][j] = 1;
            }
        }

        this.offensive_grid = grid;
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
