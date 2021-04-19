package edu.colorado.binarybuffs;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class AsteroidField extends Disaster {

    private ArrayList<Coordinate> asteroids = new ArrayList<Coordinate>();
    private ArrayList<Coordinate> shuttle_coordinates = new ArrayList<Coordinate>();

    public AsteroidField() {
        // Generate random coordinates for asteroids in the AsteroidField Disaster
        ArrayList<Coordinate> asteroids = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Random rand = new Random();
            int column = rand.nextInt(10);
            Coordinate coordinate = new Coordinate(i, column);
            asteroids.add(coordinate);
            System.out.println(coordinate.toString());
        }
    }

    public ArrayList<Coordinate> getCoords() {
        return this.asteroids;
    }

    public void applyDisaster(newPlayer curr_player) {
        for (int k = 0; k < curr_player.getPlayerMaps().get(2).ship_coordinates.size(); k++) {
            for (int s = 0; s < curr_player.getPlayerMaps().get(2).ship_coordinates.get(curr_player.getPlayerMaps().get(2).existing_ships.get(0)).size(); s++) {
                shuttle_coordinates.add(curr_player.getPlayerMaps().get(2).ship_coordinates.get(k).get(s));
            }
        }
//        shuttle_coordinates = curr_player.getPlayerMaps().get(2).ship_coordinates.get(0);
         System.out.println(shuttle_coordinates);
         SpaceLaser asteroid_hit = new SpaceLaser();

         for (int i = 0; i < this.asteroids.size(); i++) {
             if (shuttle_coordinates.contains(this.asteroids.get(i))){
                 // Call SpaceLaser on Player1 Space Map at this coordinate
                 System.out.println(curr_player.getName() + "'s Space shuttle has encountered an asteroid field! They have been " +
                         " bombarded at " + this.asteroids.get(i).toString());
                 asteroid_hit.attackUnderSpaceShuttle(this.asteroids.get(i).x, this.asteroids.get(i).y,
                         curr_player.getPlayerMaps().get(2), curr_player.getPlayerMaps().get(2), curr_player);
             }
         }
    }
}

//Game
    //probability of a disaster
    //suppose a AsteroidField is generated
    //AsteroidField.applyDisaster(player1, player2)