package edu.colorado.binarybuffs;

import java.util.ArrayList;

public class SonarPulse extends Weapon{

    private int num_uses;
    private String name = "Sonar Pulse";

    public SonarPulse(){
        this.num_uses = 2; //constant set num times we can use this
    }

    public String getName() {
        return this.name;
    }

    public boolean deployWeapon(int x, int y, Player opponent, Map attacked_map, Map current_player_map, Player currentPlayer, int method_choice){
            // Reveal horizontal layer - (x-1, y) and (x-2, y)         (x+1, y) and (x + 2, y)
            // Reveal vertical layer - (x, y-1) and (x, y-2)         (x, y+1) and (x, y + 2)
            // Reveal diagonals - (x-1, y-1) and (x-1, y+1)      (x+1, y-1) and (x+1, y+1)

            if (x > 10 || x < 0 || y > 10 || y < 0) {
                System.out.println("You cannot reveal outside of the grid! (Attempted reveal at (" + x + "," + y + "))");
                return false;
            }

            ArrayList<Coordinate> revealed_cells = new ArrayList<Coordinate>();
            String [][] vision = new String[10][10];

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    vision[i][j] = "~  | ";
                }
            }

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

            ArrayList<Coordinate> to_remove = new ArrayList<Coordinate>();

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
            String result = "";
            String axis_label = "";
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

            return true;
    }

    public boolean checkAvailability(int num_used) {
        if (num_used == this.num_uses) {
            return false;
        }
        return true;
    }

}

