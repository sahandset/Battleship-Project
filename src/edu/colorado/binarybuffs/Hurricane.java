package edu.colorado.binarybuffs;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Arrays;

public class Hurricane extends Disaster{

    private Hashtable<Coordinate, String> hurricane_border_coordinates = new Hashtable<>();
    private ArrayList<Coordinate> hurricane_coordinate_keys = new ArrayList<>();
    private ArrayList<newShip> hurricane_ships = new ArrayList<>();
    private Hashtable<newShip, String> hurricane_ship_directions = new Hashtable<>();
    private String [][] hurricane_map = new String [10][10];
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
            this.category = rand.nextInt(4) + 2;
            Coordinate starting_coordinate = new Coordinate(0, 0);
            if (this.category == 2) {
                starting_coordinate = new Coordinate(rand.nextInt(9), rand.nextInt(9));
            }
            else if(this.category == 3){
                starting_coordinate = new Coordinate(rand.nextInt(8), rand.nextInt(8));
            }
            else if(this.category == 4){
                starting_coordinate = new Coordinate(rand.nextInt(7), rand.nextInt(7));
            }
            else if(this.category == 5){
                starting_coordinate = new Coordinate(rand.nextInt(6), rand.nextInt(6));
            }
            for (int i = starting_coordinate.y; i < starting_coordinate.y + this.category; i++) {
                Coordinate coord1 = new Coordinate(starting_coordinate.x, i);
                this.hurricane_border_coordinates.put(coord1, "north");
                this.hurricane_coordinate_keys.add(coord1);
                Coordinate coord2 = new Coordinate(starting_coordinate.x + (this.category - 1), i);
                this.hurricane_border_coordinates.put(coord2, "south");
                this.hurricane_coordinate_keys.add(coord2);
            }
            for (int i = starting_coordinate.x + 1; i < starting_coordinate.x - 1 + this.category; i++) {
                Coordinate coord1 = new Coordinate(i, starting_coordinate.y);
                this.hurricane_border_coordinates.put(coord1, "east");
                this.hurricane_coordinate_keys.add(coord1);
                Coordinate coord2 = new Coordinate(i, starting_coordinate.y + (this.category - 1));
                this.hurricane_border_coordinates.put(coord2, "west");
                this.hurricane_coordinate_keys.add(coord2);
            }

        } while (this.validateHurricane() == false);

        System.out.println("There is a Hurricane a'blowing! It is classified as a category " + this.category + " Hurricane!");
        System.out.println(this);
        System.out.println("Any ship caught in the storm could be tossed and turned!");

    }

    public ArrayList<Coordinate> getHurricaneCoordinates(){
        return hurricane_coordinate_keys;
    }

    public String toString() {

        for (int i = 0; i < this.hurricane_map.length; i++) {
            for (int j = 0; j < this.hurricane_map.length; j++) {
                this.hurricane_map[i][j] = "~";
            }
        }

        for (int i = 0; i < this.hurricane_coordinate_keys.size(); i++) {
            this.hurricane_map[this.hurricane_coordinate_keys.get(i).x][this.hurricane_coordinate_keys.get(i).y] = "@";
        }


        String result = "";
        for (int row = 0; row < hurricane_map.length; row++) {
            for (int col = 0; col < hurricane_map[row].length; col++) {
                result += " | " + hurricane_map[col][row];
            }
            result += " | ";
            result += "\n" + " -----------------------------------------" + "\n";
        }
        return result;
    }

    public boolean validateHurricane() {
//        for (int i = 0; i < this.hurricane_border_coordinates.size(); i++) {
//            this.hurricane_coordinate_keys.add(this.hurricane_border_coordinates.keys().nextElement());
//        }
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
                        //check to see if ship is in hurricane_ships
                        if (!hurricane_ships.contains(shipy)) {
                            this.hurricane_ships.add(shipy);
                            String direction = hurricane_border_coordinates.get(hurricane_coordinate_keys.get(i));
                            this.hurricane_ship_directions.put(shipy, direction);
                            System.out.println("The " + shipy.getName() + " got caught in the hurricane!");
                        }
                    }
                }
            }
        }

        // Check if each ship caught in the hurricane can move in a certain direction (not overlap or go out of bounds), and if it can, set ALL of its coordinates to the offset
        for (int i = 0; i < this.hurricane_ships.size(); i++) {
            newShip ship_to_move = this.hurricane_ships.get(i);
            String direction_to_move = hurricane_ship_directions.get(ship_to_move);
            Coordinate offset_coord = current_player.getOffsetCoord(direction_to_move);
            ArrayList<Coordinate> coordsList = ocean_map.ship_coordinates.get(ship_to_move);
            ArrayList<Coordinate> movedCoordsList = new ArrayList<Coordinate>();
            int moved_x = 0;
            int moved_y = 0;
            boolean movable = true;
            for (int j = 0; j < coordsList.size(); j++) {
                moved_x = coordsList.get(j).x + offset_coord.x;
                moved_y = coordsList.get(j).y + offset_coord.y;
                if (moved_x < 0 || moved_x > 9 || moved_y < 0 || moved_y > 9) {
                    movable = false;
                }
                else if (ocean_map.defensiveGrid.checkCellStatus(moved_x, moved_y) == 1 || ocean_map.defensiveGrid.checkCellStatus(moved_x, moved_y) == 2){
                    newShip ship_found = new Minesweeper();

                    for (int k = 0; i < ocean_map.existing_ships.size(); k++){
                        newShip shipy = ocean_map.existing_ships.get(k);
                        ArrayList<Coordinate> coordsList2 = ocean_map.ship_coordinates.get(shipy);
                        for (int p = 0; j < coordsList2.size(); p++){
                            if (coordsList2.get(p).x == moved_x && coordsList2.get(p).y == moved_y){
                                ship_found = shipy;
                            }
                        }
                    }

                    if (ship_found == ship_to_move){
                        movable = true;
                    }
                    else{
                        movable = false;
                    }
                }
            }
            if (movable){
                //MOVE THAT SHIP
                Hashtable<Coordinate, Integer> moved_coords_stati = new Hashtable<Coordinate, Integer>();
                ArrayList<Coordinate> old_coords_keys = new ArrayList<Coordinate>();
                for (int j = 0; j < coordsList.size(); j++) {
                    int status = ocean_map.defensiveGrid.checkCellStatus(coordsList.get(j).x, coordsList.get(j).y);
                    old_coords_keys.add(coordsList.get(j));

                    moved_x = coordsList.get(j).x + offset_coord.x;
                    moved_y = coordsList.get(j).y + offset_coord.y;
                    Coordinate moved_coord = new Coordinate(moved_x, moved_y);
                    movedCoordsList.add(moved_coord);
                    moved_coords_stati.put(moved_coord, status);
                }
                for (int j = 0; j < movedCoordsList.size(); j++){
                    int status = moved_coords_stati.get(movedCoordsList.get(j));
                    ocean_map.defensiveGrid.setCellStatus(status, movedCoordsList.get(j).x, movedCoordsList.get(j).y);
                }
                for (int j = 0; j < movedCoordsList.size(); j++){
                    if (!(movedCoordsList.contains(old_coords_keys.get(j)))){
                        ocean_map.defensiveGrid.setCellStatus(0, old_coords_keys.get(j).x, old_coords_keys.get(j).y);
                    }
                }
                ocean_map.ship_coordinates.replace(ship_to_move, movedCoordsList);
                Coordinate old_Capts_Coords = ocean_map.captains_quarters.get(ship_to_move);
                Coordinate new_Capts_Coords = new Coordinate(old_Capts_Coords.x + offset_coord.x, old_Capts_Coords.y + offset_coord.y);
                ocean_map.captains_quarters.replace(ship_to_move, new_Capts_Coords);
            }
//            System.out.println(movedCoordsList);
            System.out.println("Ships have been moved around! Display your grid to see their updated locations.\n");
        }
    }
}
