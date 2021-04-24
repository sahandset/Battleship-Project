package edu.colorado.binarybuffs;

import java.util.ArrayList;

public class SonarPulse extends Weapon{

    private int num_uses;
    private String name = "Sonar Pulse";

    /**
     * Constructor for creating SonarPulse object
     * sets num_uses to 2 (max number of times a SonarPulse can be used)
     */
    public SonarPulse(){
        this.num_uses = 2; //constant set num times we can use this
    }

    /**
     * Retrieves a private variable
     * @return String class variable name
     */
    public String getName() {
        return this.name;
    }

    /**
     * deployWeapon() sends a sonar pulse to a specific designated coordinate on the opponent's map.
     *      It reveals a portion of the opponent's map around the cooridinate to the current player,
     *      indicating if there are ships present there or not.
     *
     * Method prints out a grid with hidden or uncovered cells
     *
     * @param x the x-coordinate where the weapon is being deployed
     * @param y the y-coordinate where the weapon is being deployed
     * @param opponent the player being "attacked" on
     * @param attacked_map the map of player being "attacked"
     * @param current_player_map the map of player using weapon
     * @param current_player the player attacking
     * @param method_choice the method of use of deployWeapon (bomb, asteroids, etc.)
     * @return boolean returns success of deployment
     */
    public boolean deployWeapon(int x, int y, Player opponent, Map attacked_map, Map current_player_map, Player current_player, int method_choice){
        // Reveal horizontal layer - (x-1, y) and (x-2, y)         (x+1, y) and (x + 2, y)
        // Reveal vertical layer - (x, y-1) and (x, y-2)         (x, y+1) and (x, y + 2)
        // Reveal diagonals - (x-1, y-1) and (x-1, y+1)      (x+1, y-1) and (x+1, y+1)

        //Check to see if coordinate is in bounds!
        if (x > 10 || x < 0 || y > 10 || y < 0) {
            System.out.println("You cannot reveal outside of the grid! (Attempted reveal at (" + x + "," + y + "))");
            return false;
        }

        ArrayList<Coordinate> revealed_cells = new ArrayList<>();
        String [][] vision = new String[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                vision[i][j] = "~  | ";
            }
        }

        //Build the cells that will be in your revealed set
        revealed_cells.add(new Coordinate(x, y-2));
        revealed_cells.add(new Coordinate(x-1, y-1 ));
        revealed_cells.add(new Coordinate(x, y-1));
        revealed_cells.add(new Coordinate(x+1, y-1));
        revealed_cells.add(new Coordinate(x-2, y));
        revealed_cells.add(new Coordinate(x-1, y));
        revealed_cells.add(new Coordinate(x, y));
        revealed_cells.add(new Coordinate(x+1, y));
        revealed_cells.add(new Coordinate(x+2, y));
        revealed_cells.add(new Coordinate(x-1, y+1));
        revealed_cells.add(new Coordinate(x, y+1));
        revealed_cells.add(new Coordinate(x+1, y+1));
        revealed_cells.add(new Coordinate(x, y+2));

        //Update the cells with B or G if there is a ship there
        for (Coordinate c: revealed_cells) {
            if (c.x >= 0 && c.x <= 9 && c.y >= 0 && c.y <= 9) {
                if((attacked_map.defensiveGrid.checkCellStatus(c.x, c.y) == 1) || (attacked_map.defensiveGrid.checkCellStatus(c.x, c.y) == 2)) {
                    vision[c.y][c.x] = "B  | ";
                } else {
                    vision[c.y][c.x] = "G  | ";
                }
            }
        }

        System.out.println("B: Black -- Ship Exists Here, G: Grey -- Ship Does Not Exist Here");

        //PRINT OUT SONAR PULSE
        printOutSonarGrid(vision);

        return true;
    }

    /**
     * Checks the availability of the sonarpulse by comparing a number passed in to how many
     *      times a sonarpulse can be used
     * @param num_used an int value of how many times the weapon has been used
     * @return boolean returns availability of weapon
     */
    public boolean checkAvailability(int num_used) {
        return num_used != this.num_uses;
    }

    /**
     * Prints out the 10x10 2D grid created by deployWeapon()
     * @param vision a 10x10 2D grid with values indicated ship present or not
     */
    public void printOutSonarGrid(String[][] vision){
        String result = "";
        String axis_label;
        for (int axis = 0; axis < 10; axis++) {
            if (axis == 0) {
                System.out.print("X   " + axis + " ");
            }
            else {
                System.out.print("    " + axis + " ");
            }
        }
        System.out.println("\nY ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        for (int row = 0; row < vision.length; row++) {
            for (int col = 0; col < vision[row].length; col++) {
                if (col == 0) {
                    axis_label = row + "┃ ";
                }
                else {
                    axis_label = "";
                }
                result += axis_label + " " + vision[row][col];
            }
            result += "\n ┃------------------------------------------------------------" + "\n";
        }
        System.out.println(result);
    }

}

