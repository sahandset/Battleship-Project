package edu.colorado.binarybuffs;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Hurricane extends Disaster{

    private Hashtable<Coordinate, String> hurricane_border_coordinates;
    private ArrayList<Coordinate> hurricane_coordinate_keys;
    private ArrayList<newShip> hurricane_ships;
    private int category;

    public Hurricane() {

        // generate random number for hurricane size -> 2-5
        // Based on this number n, create a nxn hurricane
        // Generate a random number pair starting_coordinate between 0-9 (x,y), keep generating if hurricane would end up out of bounds.
        // Create Hurricane_coordinates ArrayList<Coordinates>
        // Create Hurricane_border_coordinates -> i, j = starting_coordinate.x, starting_coordinate.y
        // Loop: int i = starting_coordinate.x; i < starting_coordinate.x + n; i++
            // Coordinate new_coord = new Coordinate(starting_coordinate.x, i) (1, 1) -> (1, 4)
            // Coordinate new_coord2 = new Coordinate(starting_coordinate.x + (n - 1), i) 1 + 3 = (4, 1) -> (4, 4)
            // Hurricane_border_coordinates.add(new_coord, "east") Hashtable<Coordinate, String>
            // Hurricane_border_coordinates.add(new_coord2, "west")
        // Loop: int i = starting_coordinate.y; i < starting_coordinate.y + n; i++
            // Coordinate new_coord = new Coordinate(i, starting_coordinate.y)
            // Coordinate new_coord2 = new Coordinate(i, starting_coordinate.y + (n - 1))
            // Hurricane_border_coordinates.add(new_coord, "north") Hashtable<Coordinate, String>
            // Hurricane_border_coordinates.add(new_coord2, "south")
            this.setHurricaneCoordinates();
    }

    public void setHurricaneCoordinates() {

        do {
            Random rand = new Random();
            this.category = rand.nextInt(3) + 2;
            Coordinate starting_coordinate = new Coordinate(rand.nextInt(9), rand.nextInt(9));

            for (int i = starting_coordinate.x; i < starting_coordinate.x + this.category; i++) {
                this.hurricane_border_coordinates.put(new Coordinate(starting_coordinate.x, i), "east");
                this.hurricane_border_coordinates.put(new Coordinate(starting_coordinate.x + (this.category - 1), i), "west");
            }
            for (int i = starting_coordinate.y; i < starting_coordinate.y + this.category; i++) {
                this.hurricane_border_coordinates.put(new Coordinate(i, starting_coordinate.y), "north");
                this.hurricane_border_coordinates.put(new Coordinate(i, starting_coordinate.y + (this.category - 1)), "south");
            }

        } while (this.validateHurricane() == false);

        System.out.println("There is a Hurricane a'blowing! It is classified as a category " + this.category + " Hurricane!");
        System.out.println("Any ship caught in the storm could be tossed and turned!");
        // Print Hurricane location with ꩜ noting where it is
    }

    public boolean validateHurricane() {
        for (int i = 0; i < this.hurricane_border_coordinates.size(); i++) {
            this.hurricane_coordinate_keys.add(this.hurricane_border_coordinates.keys().nextElement());
        }
        for (int i = 0; i < this.hurricane_border_coordinates.size(); i++) {
            if (this.hurricane_coordinate_keys.get(i).x < 0 || this.hurricane_coordinate_keys.get(i).y < 0 ||
                    this.hurricane_coordinate_keys.get(i).x >= 10 || this.hurricane_coordinate_keys.get(i).y >= 10) {
                return false;
            }
        }
        return true;
    }

    public void applyDisaster(newPlayer current_player) {
        // Loop through existing ships of current player, and if they overlap with Hurricane_border_coordinates,
        // add them to hurricane_ships <newShip, ArrayList<Coordinates>>
        // Loop through each ship in hurricane_ships
            // Loop through each ship's coordinates
                // Cross reference ship coordinates with hurricane_border_coordinates
                // get direction of that coordinate (which direction that ship should go)
                // call getOffsetCoords(direction), and set that existing_ship coordinate to this IF
                // the offset coord is not occupied OR the offset coord is not out of bounds

        ArrayList<newShip> player_ships = current_player.getPlayerMaps().get(0).existing_ships;
        Map ocean_map = current_player.getPlayerMaps().get(0);

        for (int i = 0; i < this.hurricane_coordinate_keys.size(); i++) {
            for (int j = 0; j < current_player.getPlayerMaps().get(0).ship_coordinates.size(); j++) {
                newShip shipy = player_ships.get(j);
                for (int k = 0; k < current_player.getPlayerMaps().get(0).ship_coordinates.get(shipy).size(); k++) {
                    Coordinate ship_coordinate = current_player.getPlayerMaps().get(0).ship_coordinates.get(shipy).get(k);
                    if ((this.hurricane_coordinate_keys.get(i).x == ship_coordinate.x) && (this.hurricane_coordinate_keys.get(i).y == ship_coordinate.y)) {
                        this.hurricane_ships.add(shipy);
                        System.out.println("The " + shipy.getName() + " is caught in a category " + this.category + " Hurricane!");
                    }
                }
            }
        }

        // Check if each ship caught in the hurricane can move in a certain direction (not overlap or go out of bounds), and if it can, set ALL of its coordinates to the offset
        for (int i = 0; i < this.hurricane_ships.size(); i++) {
            for (int j = 0; j < this.hurricane_coordinate_keys.size(); j++) {
                Coordinate offset_coord = current_player.getOffsetCoord(this.hurricane_border_coordinates.get(hurricane_coordinate_keys.get(j)));
                if ((offset_coord.x < 0 || offset_coord.y < 0 || offset_coord.x >= 10 || offset_coord.y >= 10) ||
                        ocean_map.defensiveGrid.checkCellStatus(offset_coord.x, offset_coord.y) == 1) {
                    continue;
                } else {
                    newShip ship_to_move = this.hurricane_ships.get(i);
                    }
            }
        }
    }
}
