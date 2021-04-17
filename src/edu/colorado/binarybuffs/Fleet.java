package edu.colorado.binarybuffs;

import java.util.ArrayList;
import java.util.Random;

public class Fleet{

    private ArrayList<newShip> myShips = new ArrayList<newShip>();

    public Fleet(String mapType){
        Minesweeper mine = new Minesweeper();
        Battleship battle = new Battleship();
        Destroyer dest = new Destroyer();
        Submarine sub = new Submarine();
        Spaceshuttle shuttle = new Spaceshuttle();
        myShips.add(mine);
        myShips.add(battle);
        myShips.add(dest);
        myShips.add(sub);
        myShips.add(shuttle);
    }

    public ArrayList<newShip> getShips(){
        return myShips;
    }

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

    public boolean placeFleet(newPlayer curr_player){
        for (int i = 0; i < myShips.size(); i++){
            boolean placed = false;
            while (!placed) {
                placed = placeShipRandomly(curr_player, i);
            }
        }
        return true;
    }

    public boolean placeShipRandomly(newPlayer curr_player, int i){
        String rand_direction = getRandDirection();

        newShip shipy = myShips.get(i);

        if (shipy instanceof SubmersibleShip){
            Random rand = new Random();
            int rand_x = rand.nextInt(10);
            int rand_y = rand.nextInt(10);
            return curr_player.deployShip(myShips.get(i), rand_x, rand_y, rand_direction, 1);
        }
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
        else {
            Random rand = new Random();
            int rand_x = rand.nextInt(10);
            int rand_y = rand.nextInt(10);
            return curr_player.deployShip(myShips.get(i), rand_x, rand_y, rand_direction, 0);
        }

    }

}
