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

    public void setCellStatus(int condition, int x, int y) {
        grid[x][y] = condition;
    }

    public void setAllCellStatus(int condition) {
        for (int i = 0; i < length_x; i++) {
            for (int j = 0; j < length_y; j++) {
                grid[i][j] = condition;
            }
        }
    }

    public String toString() {
        String result = "";
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                result += " | " + grid[col][row];
            }
            result += "\n" + "----------------------------------------" + "\n";
        }
        return result;
    }
}
