package edu.colorado.binarybuffs;
import java.util.Random;
import java.util.ArrayList;

/** Ghost Zone extends Disaster superclass that scrambles the player's offensive grid information, occuring on either the ocean or underwater map
 */
public class GhostZone extends Disaster {
    private int ghost_zone_dimension;
    private ArrayList<Coordinate> ghost_zone_coords = new ArrayList<Coordinate>();
    boolean ghosted = false;

    private String [][] ghost_zone_map = new String[10][10];

    /** GhostZone() constructor which sets ghost zone coordinates when a Disaster is created in Game class
     */
    public GhostZone() {
        this.setGhostZoneCoordinates();
    }

    public boolean getGhosted(){
        return ghosted;
    }

    public ArrayList<Coordinate> getGhostZoneCoords(){
        return ghost_zone_coords;
    }

    /** setGhostZoneCoordinates() method to create ghost zone on a portion of player's ocean or underwater map
     * Creates a random value for dimension of ghost zone
     * New coordinate is created to act as starting coordinate (top left), traverses through dimensions and creates zone
     * performed in a do while loop in order to validate zone before actually creating it
     * returns void
     */
    public void setGhostZoneCoordinates() {

        do {
            Random ghost_size = new Random();
            this.ghost_zone_dimension = ghost_size.nextInt(5) + 2;
            Coordinate starting_coordinate = new Coordinate(0, 0);
            if (this.ghost_zone_dimension == 2) {
                starting_coordinate = new Coordinate(ghost_size.nextInt(9),ghost_size.nextInt(9));
            }
            else if(this.ghost_zone_dimension == 3){
                starting_coordinate = new Coordinate(ghost_size.nextInt(8), ghost_size.nextInt(8));
            }
            else if(this.ghost_zone_dimension == 4){
                starting_coordinate = new Coordinate(ghost_size.nextInt(7), ghost_size.nextInt(7));
            }
            for (int i = starting_coordinate.x; i < starting_coordinate.x + this.ghost_zone_dimension; i++) {
                for (int j = starting_coordinate.y; j < starting_coordinate.y + this.ghost_zone_dimension; j++) {
                    this.ghost_zone_coords.add(new Coordinate(i, j));
                }
            }

        } while (this.validateGhostZone() == false);

    }

    /** applyDisaster() method to initiate disaster on the current player
     * Gets current statuses of player's offensive grid
     * Creates a random value between 0 and 2for each iteration of the ghost zone coords and updates current cell status to random value
     * returns void
     */
    public void applyDisaster(Player current_player) {
        System.out.println("Watch out, there is a Ghost Zone covering part of your offensive grid!");
        System.out.println("Some of your intel might be scrambled...");
        Grid new_ocean_offensive_grid = current_player.getPlayerMaps().get(0).offensiveGrid;
        Grid new_underwater_offensive_grid = current_player.getPlayerMaps().get(1).offensiveGrid;
        int scramble_num = 0;
        for (int i = 0; i < ghost_zone_coords.size(); i++) {
            Random rand = new Random();
            scramble_num = rand.nextInt(3);
            new_ocean_offensive_grid.setCellStatus(scramble_num, ghost_zone_coords.get(i).x, ghost_zone_coords.get(i).y);
            new_underwater_offensive_grid.setCellStatus(scramble_num, ghost_zone_coords.get(i).x, ghost_zone_coords.get(i).y);
        }

        ghosted = true;
    }

    /** validateGhostZone() method to ensure that ghost zone is not created out of bounds
     * Traverses through the list of added ghost zone coordinates and checks whether coords are either less than 0 or greater than 10
     * returns boolean true for proper validation
     */
    public boolean validateGhostZone() {
        for (int i = 0; i < this.ghost_zone_coords.size(); i++) {
            if (this.ghost_zone_coords.get(i).x < 0 || this.ghost_zone_coords.get(i).y < 0 ||
                    this.ghost_zone_coords.get(i).x >= 10 || this.ghost_zone_coords.get(i).y >= 10) {
                return false;
            }
        }
        return true;
    }

    /** toString method to print out location of ghost zone on respective map
     * Traverses through the list of added ghost zone coordinates
     * Prints out the character "&" to denote a ghost by row/column
     */
    public String toString() {

        for (int i = 0; i < this.ghost_zone_map.length; i++) {
            for (int j = 0; j < this.ghost_zone_map.length; j++) {
                this.ghost_zone_map[i][j] = "~";
            }
        }

        for (int i = 0; i < this.ghost_zone_coords.size(); i++) {
            this.ghost_zone_map[this.ghost_zone_coords.get(i).x][this.ghost_zone_coords.get(i).y] = "&";
        }

        String result = "";
        String axis_label = "";
        for (int axis = 0; axis < 10; axis++) {
            if (axis == 0) {
                System.out.print("X  " + axis);
            }
            else {
                System.out.print("   " + axis);
            }
        }
        System.out.println("\nY ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        for (int row = 0; row < ghost_zone_map.length; row++) {
            for (int col = 0; col < ghost_zone_map[row].length; col++) {
                if (col == 0) {
                    axis_label = row + "┃ ";
                }
                else {
                    axis_label = "";
                }
                result += axis_label + ghost_zone_map[col][row] + " │ ";
            }

            result += "\n" + " -----------------------------------------" + "\n";
        }
        return result;
    }
}
