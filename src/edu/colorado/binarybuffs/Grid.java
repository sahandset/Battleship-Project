package edu.colorado.binarybuffs;

public class Grid {
    //put some attributes here
    private static int length_x = 10;
    private static int length_y = 10;
    public int [][] playerGrid;

    //put the constructor that initializes some attributes here
    //Cell status
    //  1: empty, not hit
    //  2: empty, hit (missed)
    //  3: occupied, not hit
    //  4: occupied, hit
    public Grid() {
        int [][] grid = new int [length_x][length_y];

        for (int i = 0; i < length_x; i++) {
            for (int j = 0; j < length_y; j++) {
                grid[i][j] = 1;
            }
        }

        this.playerGrid = grid;
    }
    //add some methods here
    public int getSize(){
        //return length_x;
        return playerGrid.length;
    }

    public int checkCellStatus(int x, int y) {
    return playerGrid[x][y];
    }
}
