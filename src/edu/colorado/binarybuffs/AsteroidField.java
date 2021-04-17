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

    public void applyDisaster(newPlayer Player1, newPlayer Player2) {
         ArrayList<Coordinate> shuttle_coordinates1 = Player1.getPlayerMaps().get(2).ship_coordinates.get(0);
         ArrayList<Coordinate> shuttle_coordinates2 = Player2.getPlayerMaps().get(2).ship_coordinates.get(0);

         for (int i = 0; i < this.asteroids.size(); i++) {
             if (shuttle_coordinates1.contains(this.asteroids.get(i))){
                 // Call SpaceLaser on Player1 Space Map at this coordinate
             }
             if (shuttle_coordinates2.contains(this.asteroids.get(i))) {
                 // Call SpaceLaser on Player2 Space Map at this coordinate
             }
         }
    }
}

//Game
    //probability of a disaster
    //suppose a AsteroidField is generated
    //AsteroidField.apply(player1, player2)