package edu.colorado.binarybuffs;

public class newGrid {
    public static int length_x = 10;
    public static int length_y = 10;

    public int [][] grid = new int [length_x][length_y];

    public newGrid(){
        for (int i = 0; i < length_x; i++) {
            for (int j = 0; j < length_y; j++) {
                grid[i][j] = 0;
            }
        }
    }

    public int checkCellStatus(int x, int y) {
        return grid[x][y];
    }
}
