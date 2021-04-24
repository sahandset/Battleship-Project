package edu.colorado.binarybuffs;

public class Grid {
    public static int length_x = 10;
    public static int length_y = 10;

    public int [][] grid = new int [length_x][length_y];

    /**
     * Grid() Constructor that contains a 2D Matrix array of length_x columns and length_y rows, all initially set to 0
     * A Grid's matrix array is comprised of cell statuses that can inform of objects and interactions:
     * -- Offensive Grid --
     * 0 = Not Hit
     * 1 = Attacked, Missed
     * 2 = Attacked, Hit
     *
     *  -- Defensive Grid --
     *  0 = Unoccupied
     *  1 = Occupied
     */
    public Grid(){
        for (int i = 0; i < length_x; i++) {
            for (int j = 0; j < length_y; j++) {
                grid[i][j] = 0;
            }
        }
    }

    // 0: not hit
    // 1: hit, empty
    // 2: hit, occupied

    /**
     * Returns the status of a cell given an int X and an int Y
     * @param x an X coordinate specified by the caller function
     * @param y a Y coordinate specified by the caller function
     * @return an int that represents the status of a coordinate
     */
    public int checkCellStatus(int x, int y) {
        return grid[x][y];
    }

    /**
     * Sets the cell status of a cell given an int X and an int Y
     * @param new_status the new cell status number
     * @param x an X coordinate specified by the caller function
     * @param y a Y coordinate specified by the caller function
     */
    public void setCellStatus(int new_status, int x, int y) {
        grid[x][y] = new_status;
    }

    /**
     * Sets all coordinates of a Grid's Matrix array to a new status specified by the caller function
     * @param new_status the status that every coordinate in the Grid is to be set to
     */
    public void setAllCellStatus(int new_status) {
        for (int i = 0; i < length_x; i++) {
            for (int j = 0; j < length_y; j++) {
                grid[i][j] = new_status;
            }
        }
    }

    /** toString() method that prints out cell statuses of the Grid
     * -- Offensive Grid --
     * 0 = Not Hit
     * 1 = Attacked, Missed
     * 2 = Attacked, Hit
     *
     *  -- Defensive Grid --
     *  0 = Unoccupied
     *  1 = Occupied
     * @return A printed map representation of the Grid
     */
    public String toString() {
        String result = "";
        String axis_label = "";
        for (int axis = 0; axis < 10; axis++) {
            System.out.print("   " + axis);
        }
        System.out.println("\n  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        for (int row = 0; row < grid.length; row++) {
//            System.out.print(row + "║");
            for (int col = 0; col < grid[row].length; col++) {
                if (col == 0) {
                    axis_label = row + "┃ ";
                }
                else {
                    axis_label = "";
                }
                result += axis_label + grid[col][row] + " │ ";
            }
            result += "\n ┃---------------------------------------│\n";
        }
        return result;
    }
}
