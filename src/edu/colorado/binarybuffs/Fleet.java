package edu.colorado.binarybuffs;

import java.util.ArrayList;

public class Fleet{
    public Fleet(String mapType){
        if (mapType == "UnderwaterMap"){
            Submarine sub = new Submarine();
        }
        else if (mapType == "OceanMap"){
            Minesweeper mine = new Minesweeper();
            Battleship battle = new Battleship();
            Destroyer dest = new Destroyer();
        }
        else if (mapType == "SpaceMap"){
            Spaceshuttle shuttle = new Spaceshuttle();
        }
    }
}
