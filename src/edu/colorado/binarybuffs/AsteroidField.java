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

         for (int i = 0; i < this.shuttle_coordinates.size(); i++) {
             for (int j = 0; j < this.asteroids.size(); j++) {
                 if (this.shuttle_coordinates.get(i).x == this.asteroids.get(j).x
                         && this.shuttle_coordinates.get(i).y == this.asteroids.get(j).y){
                     // Call SpaceLaser on Player1 Space Map at this coordinate
                     System.out.println(curr_player.getName() + "'s Space shuttle has encountered an asteroid field! They have been " +
                             " bombarded at " + this.asteroids.get(j).toString());
                     asteroid_hit.deployWeapon(this.asteroids.get(j).x, this.asteroids.get(j).y, curr_player,
                             curr_player.getPlayerMaps().get(2), curr_player.getPlayerMaps().get(2), curr_player, 1);
                 }
             }
         }
    }
}

//Game
    //probability of a disaster
    //suppose a AsteroidField is generated
    //AsteroidField.applyDisaster(player1, player2)