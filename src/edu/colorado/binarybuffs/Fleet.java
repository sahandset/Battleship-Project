package edu.colorado.binarybuffs;

import java.util.ArrayList;
import java.util.Random;

public class Fleet{

    private ArrayList<Ship> myShips;

    /**
     * Constructor for creating Bomb object
     * @param shipsFleet the ships in the fleet that will be placed randomly
     *                   can include ships of all types
     */
    public Fleet(ArrayList<Ship> shipsFleet){
        this.myShips = shipsFleet;
    }

    /**
     * Retrieves a private variable
     * @return myShips class variable name
     */
    public ArrayList<Ship> getShips(){
        return myShips;
    }

    /**
     * Randomly generates a direction N,S,E, or W
     * @return String with direction
     */
    public String getRandDirection(){
        Random rand = new Random();
        int rand_direction_choice = rand.nextInt(4);
        switch (rand_direction_choice){
            case 0:
                return "N";
            case 1:
                return "S";
            case 2:
                return "E";
            case 3:
                return "W";
        }
        return "N";
    }

    /**
     * @param curr_player the player who chooses to place the fleet on their map
     * @return boolean : the success of placing the entire fleet
     */
    public boolean placeFleet(Player curr_player){
        for (int i = 0; i < myShips.size(); i++){
            boolean placed = false;
            while (!placed) {
                //call placeShipRandomly until all ships are placed properly!
                placed = placeShipRandomly(curr_player, i);
            }
        }
        return true;
    }

    /**
     * Places a ship on the designated map: makes the executive decision
     *      to place any submarine on the underwater map
     *
     * Uses a random number generator to calculate the ship's starting coordinate
     *      and gets a random direction from getRandDirection()
     * @param curr_player the player who's maps the ship is being placed on
     * @param i an index value of the ship (in shipFleets) that is being placed
     * @return boolean which is the success of placing the ship
     */
    public boolean placeShipRandomly(Player curr_player, int i){
        String rand_direction = getRandDirection();

        Ship shipy = myShips.get(i);

        //If a ship should be placed underwater
        if (shipy instanceof SubmersibleShip){
            Random rand = new Random();
            int rand_x = rand.nextInt(10);
            int rand_y = rand.nextInt(10);
            return curr_player.deployShip(myShips.get(i), rand_x, rand_y, rand_direction, 1);
        }
        //If the ship should be placed in space
        else if (shipy instanceof OrbitableShip){
            Random rand = new Random();
            int rand_num = rand.nextInt(10);
            int rand_x_or_y = rand.nextInt(2);
            int rand_x;
            int rand_y;
            if (rand_x_or_y == 1){
                rand_x = rand_num;
                int[] ya = new int[]{0,9};
                rand_y = ya[rand.nextInt(2)];
            }
            else{
                rand_y = rand_num;
                int[] ya = new int[]{0,9};
                rand_x = ya[rand.nextInt(2)];
            }

            return curr_player.deployShip(myShips.get(i), rand_x, rand_y, rand_direction, 2);
        }
        //If the ship should be placed on the ocean
        else {
            Random rand = new Random();
            int rand_x = rand.nextInt(10);
            int rand_y = rand.nextInt(10);
            return curr_player.deployShip(myShips.get(i), rand_x, rand_y, rand_direction, 0);
        }
    }
}
