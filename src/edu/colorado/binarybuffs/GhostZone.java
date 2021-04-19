package edu.colorado.binarybuffs;

public class GhostZone extends Disaster {
    public GhostZone() {
        // generate random number for ghost zone size -> 1-5
        // Based on this number n, create a nxn ghost zone
        // Generate a random number pair starting_coordinate between 0-9 (x,y), keep generating if ghost zone would end up out of bounds.
        // Create ghost zone coordinates ArrayList<Coordinates>
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

    }

    public boolean checkScramble() {
        // iterate through new grid cells and check if the new one has ONE different status:
        // return true;
        // else:
    return false;
    }
}
