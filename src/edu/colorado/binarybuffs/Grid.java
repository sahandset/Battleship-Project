package edu.colorado.binarybuffs;

public class Grid {
    //put some attributes here
    private static int length_x = 10;
    private static int length_y = 10;

    //put the constructor that initializes some attributes here
    //Cell status
    //  1: empty, not hit
    //  2: empty, hit
    //  3: occupied, not hit
    //  4: occupied, hit
    public Grid() {
        int [][] grid = new int [length_x][length_y];

        for (int i = 0; i < length_x; i++) {
            for (int j = 0; j < length_y; j++) {
                grid[i][j] = 1;
            }
        }
    }
    //add some methods here

}
