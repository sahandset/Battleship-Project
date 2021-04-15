package edu.colorado.binarybuffs;

import java.util.ArrayList;

public class Fleet{

    private ArrayList<newShip> myShips = new ArrayList<newShip>();

    public Fleet(String mapType){
        if (mapType == "UnderwaterMap"){
            Submarine sub = new Submarine();
            myShips.add(sub);
        }
        else if (mapType == "OceanMap"){
            Minesweeper mine = new Minesweeper();
            Battleship battle = new Battleship();
            Destroyer dest = new Destroyer();
            myShips.add(mine);
            myShips.add(battle);
            myShips.add(dest);
        }
        else if (mapType == "SpaceMap"){
            Spaceshuttle shuttle = new Spaceshuttle();
            myShips.add(shuttle);
        }
    }

    public ArrayList<newShip> getShips(){
        return myShips;
    }
}
