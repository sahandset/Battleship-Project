package edu.colorado.binarybuffs;

import java.util.ArrayList;
import java.util.Random;

public class Fleet{

    private int map_choice;
    private ArrayList<newShip> myShips = new ArrayList<newShip>();

    public Fleet(String mapType){
        if (mapType == "UnderwaterMap"){
            Submarine sub = new Submarine();
            myShips.add(sub);
            map_choice = 1;
        }
        else if (mapType == "OceanMap"){
            Minesweeper mine = new Minesweeper();
            Battleship battle = new Battleship();
            Destroyer dest = new Destroyer();
            myShips.add(mine);
            myShips.add(battle);
            myShips.add(dest);
            map_choice = 0;
        }
        else if (mapType == "SpaceMap"){
            Spaceshuttle shuttle = new Spaceshuttle();
            myShips.add(shuttle);
            map_choice = 2;
        }
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
        Random rand = new Random();
        int rand_x = rand.nextInt(10);
        int rand_y = rand.nextInt(10);

        String rand_direction = getRandDirection();

        return curr_player.deployShip(myShips.get(i), rand_x, rand_y, rand_direction, map_choice);
    }

}
