package edu.colorado.binarybuffs;

import java.util.ArrayList;
import java.util.Random;

/**
 * AsteroidField extends Disaster superclass that randomly fire asteroids at different coordinates on player's space map
 * */
public class AsteroidField extends Disaster {

    private ArrayList<Coordinate> asteroids = new ArrayList<>();
    private ArrayList<Coordinate> shuttle_coordinates = new ArrayList<>();
    private String [][] asteroid_map = new String[10][10];

    /**
     * AsteroidField() constructor generates random coordinates for asteroids on space map
     * Traverses through number of rows of the grid, randomly creates up 10 asteroids in each row
     * Adds each to overall asteroids array list
     */
    public AsteroidField() {
        for (int i = 0; i < 10; i++) {
            Random rand = new Random();
            int column = rand.nextInt(10);
            Coordinate coordinate = new Coordinate(column, i);
            this.asteroids.add(coordinate);
        }
    }

    public ArrayList<Coordinate> getCoords() {
        return this.asteroids;
    }

    /**
     * applyDisaster() method to initiate disaster on the current player
     * Traverses through the coordinates of player's space shuttle as well as asteroid coordinates
     * If shuttle coordinate overlaps with asteroid coordinate, perform space laser functionality on that cell
     * @param current_player the current player who's map's will be affected by the disaster event
     */
    public void applyDisaster(Player current_player) {
        Ship space_ship = current_player.getPlayerMaps().get(2).existing_ships.get(0);
        shuttle_coordinates = current_player.getPlayerMaps().get(2).ship_coordinates.get(space_ship);

        SpaceLaser asteroid_hit = new SpaceLaser();

        System.out.println(current_player.getName() + "'s Space shuttle has encountered an asteroid field!");
        System.out.println(this);

        for (Coordinate shuttle_coordinate : this.shuttle_coordinates) {
            for (Coordinate asteroid : this.asteroids) {
                if (shuttle_coordinate.x == asteroid.x
                        && shuttle_coordinate.y == asteroid.y) {
                    // Call SpaceLaser on Player1 Space Map at this coordinate
                    System.out.println("Your spaceshuttle has been bombarded by asteroids at " + asteroid.toString());
                    asteroid_hit.deployWeapon(asteroid.x, asteroid.y, current_player,
                            current_player.getPlayerMaps().get(2), current_player.getPlayerMaps().get(2), current_player, 3);
                }
            }
        }
    }

    /**
     * toString() method that prints out location of asteroids on respective map
     * Traverses through the list of added asteroid coordinates
     * Prints out the character "A" to denote an asteroid fire by row/column
     * @return A printed map representation of the coordinates affected by the AsteroidField disaster
     */
    public String toString() {

        for (int i = 0; i < this.asteroid_map.length; i++) {
            for (int j = 0; j < this.asteroid_map.length; j++) {
                this.asteroid_map[i][j] = " ";
            }
        }

        for (Coordinate asteroid : this.asteroids) {
            this.asteroid_map[asteroid.x][asteroid.y] = "A";
        }

        String result = "";
        String axis_label;
        for (int axis = 0; axis < 10; axis++) {
            if (axis == 0) {
                System.out.print("X  " + axis);
            }
            else {
                System.out.print("   " + axis);
            }
        }
        System.out.println("\nY ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        for (int row = 0; row < asteroid_map.length; row++) {
            for (int col = 0; col < asteroid_map[row].length; col++) {
                if (col == 0) {
                    axis_label = row + "┃ ";
                }
                else {
                    axis_label = "";
                }
                result += axis_label + asteroid_map[col][row] + " │ ";
            }
            result += "\n" + " ┃----------------------------------------" + "\n";
        }
        return result;
    }
}