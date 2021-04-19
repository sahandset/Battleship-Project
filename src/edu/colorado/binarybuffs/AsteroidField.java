package edu.colorado.binarybuffs;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class AsteroidField extends Disaster {

    private ArrayList<Coordinate> asteroids;

    public AsteroidField() {
        // Generate random coordinates for asteroids in the AsteroidField Disaster
        ArrayList<Coordinate> asteroids = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Random rand = new Random();
            int column = rand.nextInt();
            Coordinate coordinate = new Coordinate(i, column);
            asteroids.add(coordinate);
        }
    }

    public ArrayList<Coordinate> getCoords() {
        return this.asteroids;
    }

    public void applyDisaster(newPlayer current_player) {
         ArrayList<Coordinate> shuttle_coordinates1 = current_player.getPlayerMaps().get(2).ship_coordinates.get(0);
         SpaceLaser asteroid_hit = new SpaceLaser();

         for (int i = 0; i < this.asteroids.size(); i++) {
             if (shuttle_coordinates1.contains(this.asteroids.get(i))){
                 // Call SpaceLaser on Player1 Space Map at this coordinate
                 System.out.println(current_player.getName() + "'s Space shuttle has encountered an asteroid field! They have been " +
                         " bombarded at " + this.asteroids.get(i).toString());
                 asteroid_hit.attackUnderSpaceShuttle(this.asteroids.get(i).x, this.asteroids.get(i).y,
                         current_player.getPlayerMaps().get(2), current_player.getPlayerMaps().get(2), current_player);
             }
         }
    }
}

//Game
    //probability of a disaster
    //suppose a AsteroidField is generated
    //AsteroidField.applyDisaster(player1, player2)