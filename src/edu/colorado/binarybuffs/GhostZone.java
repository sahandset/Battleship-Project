package edu.colorado.binarybuffs;
import java.util.Random;
import java.util.ArrayList;

public class GhostZone extends Disaster {
    private int ghost_zone_dimension;
    private ArrayList<Coordinate> ghost_zone_coords = new ArrayList<Coordinate>();
    public GhostZone() {

        // generate random number for ghost zone size -> 1-5
        // Based on this number n, create a nxn ghost zone
        // Generate a random number pair starting_coordinate between 0-9 (x,y), keep generating if ghost zone would end up out of bounds.
        // Create ghost zone coordinates ArrayList<Coordinates>
        this.setGhostZoneCoordinates();
    }

    public void setGhostZoneCoordinates() {

        do {
            Random ghost_size = new Random();
            this.ghost_zone_dimension = ghost_size.nextInt(4) + 1;
            Coordinate starting_coordinate = new Coordinate(ghost_size.nextInt(9), ghost_size.nextInt(9));

            for (int i = starting_coordinate.x; i < starting_coordinate.x + this.ghost_zone_dimension; i++) {
                for (int j = starting_coordinate.y; j < starting_coordinate.y + this.ghost_zone_dimension; j++) {
                    this.ghost_zone_coords.add(new Coordinate(i, j));
                }
            }

        } while (this.validateGhostZone() == false);

        System.out.println("Watch out, there is a Ghost Zone covering part of your offensive grid!");
        System.out.println("Some of your intel might be scrambled...");
        // Print Ghost Zone location with ꩜ noting where it is
    }
    public void applyDisaster(newPlayer current_player) {
        // Take the current_player's offensive grid for ocean and underwater
        // For each coordinate status in the ghost zone:
            // create new_offensive_grid_ocean = current_player.getPlayerMaps().get(0)
            // and new_offensive_grid_underwater = current_player.getPlayerMaps().get(1) placeholders
            // generate random cell status number between 0 - 2
            // assign that coordinate to the new cell status
            // if (checkScramble(new_offensive_grid_ocean, current_player.getPlayerMaps().get(0))
            //     && checkScramble(new_offensive_grid_underwater, current_player.getPlayerMaps().get(1))):
            // current_player.getPlayerMaps().get(0) = new_offensive_grid_ocean
            // current_player.getPlayerMaps().get(1) = new_offensive_grid_underwater
            // else :
            // applyDisaster(current_player)
        newGrid new_ocean_offensive_grid = current_player.getPlayerMaps().get(0).offensiveGrid;
        newGrid new_underwater_offensive_grid = current_player.getPlayerMaps().get(1).offensiveGrid;
        int scramble_num = 0;
        for (int i = 0; i < ghost_zone_coords.size(); i++) {
            Random rand = new Random();
            scramble_num = rand.nextInt(3);
            new_ocean_offensive_grid.setCellStatus(scramble_num, ghost_zone_coords.get(i).x, ghost_zone_coords.get(i).y);
            new_underwater_offensive_grid.setCellStatus(scramble_num, ghost_zone_coords.get(i).x, ghost_zone_coords.get(i).y);
        }
        //System.out.println("")
//        if ((checkScramble(new_ocean_offensive_grid, current_player.getPlayerMaps().get(0).offensiveGrid)) &&
//            (checkScramble(new_underwater_offensive_grid, current_player.getPlayerMaps().get(1).offensiveGrid))) {
//            for (int i = 0; i < ghost_zone_coords.size(); i++) {
//                current_player.getPlayerMaps().get(0).offensiveGrid.setCellStatus(scramble_num, ghost_zone_coords.get(i).x, ghost_zone_coords.get(i).y);
//                current_player.getPlayerMaps().get(1).offensiveGrid.setCellStatus(scramble_num, ghost_zone_coords.get(i).x, ghost_zone_coords.get(i).y);
//            }
//       }
//       else {
//            applyDisaster(current_player);
//       }
    }

    public boolean validateGhostZone() {
        for (int i = 0; i < this.ghost_zone_coords.size(); i++) {
            if (this.ghost_zone_coords.get(i).x < 0 || this.ghost_zone_coords.get(i).y < 0 ||
                    this.ghost_zone_coords.get(i).x >= 10 || this.ghost_zone_coords.get(i).y >= 10) {
                return false;
            }
        }
        return true;
    }

//    public boolean checkScramble(newGrid new_grid, newGrid old_grid) {
//        // iterate through new grid cells and check if the new one has ONE different status:
//        // return true;
//        // else:
//        for (int i = 0; i < new_grid.grid.length; i++) {
//            for (int j =0; j < old_grid.grid.length; j++) {
//                if (new_grid.checkCellStatus(i,j) != old_grid.checkCellStatus(i,j)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
}
