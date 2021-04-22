package edu.colorado.binarybuffs;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class AsteroidField extends Disaster {

    private ArrayList<Coordinate> asteroids = new ArrayList<Coordinate>();
    private ArrayList<Coordinate> shuttle_coordinates = new ArrayList<Coordinate>();
    private String [][] asteroid_map = new String[10][10];

    public AsteroidField() {
        // Generate random coordinates for asteroids in the AsteroidField Disaster
        ArrayList<Coordinate> asteroids = new ArrayList<>();

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

    public void applyDisaster(newPlayer curr_player) {
        newShip space_ship = curr_player.getPlayerMaps().get(2).existing_ships.get(0);
        shuttle_coordinates = curr_player.getPlayerMaps().get(2).ship_coordinates.get(space_ship);

         SpaceLaser asteroid_hit = new SpaceLaser();

        System.out.println(curr_player.getName() + "'s Space shuttle has encountered an asteroid field!");
        System.out.println(this);

         for (int i = 0; i < this.shuttle_coordinates.size(); i++) {
             for (int j = 0; j < this.asteroids.size(); j++) {
                 if (this.shuttle_coordinates.get(i).x == this.asteroids.get(j).x
                         && this.shuttle_coordinates.get(i).y == this.asteroids.get(j).y){
                     // Call SpaceLaser on Player1 Space Map at this coordinate
                     System.out.println("Your spaceshuttle has been bombarded by asteroids at " + this.asteroids.get(j).toString());
                     asteroid_hit.deployWeapon(this.asteroids.get(j).x, this.asteroids.get(j).y, curr_player,
                             curr_player.getPlayerMaps().get(2), curr_player.getPlayerMaps().get(2), curr_player, 3);
                 }
             }
         }
    }

    public String toString() {

        for (int i = 0; i < this.asteroid_map.length; i++) {
            for (int j = 0; j < this.asteroid_map.length; j++) {
                this.asteroid_map[i][j] = " ";
            }
        }

        for (int i = 0; i < this.asteroids.size(); i++) {
            this.asteroid_map[this.asteroids.get(i).x][this.asteroids.get(i).y] = "A";
        }


        String result = "";
        for (int row = 0; row < asteroid_map.length; row++) {
            for (int col = 0; col < asteroid_map[row].length; col++) {
                result += " | " + asteroid_map[col][row];
            }
            result += " | ";
            result += "\n" + " -----------------------------------------" + "\n";
        }
        return result;
    }
}