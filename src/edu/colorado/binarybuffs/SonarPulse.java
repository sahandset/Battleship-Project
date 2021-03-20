package edu.colorado.binarybuffs;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class SonarPulse extends Weapon{

//    public SonarPulse(){
//        this.num_uses = 2; //constant set num times we can use this
//        this.possible_uses = 2; //how many uses we have left
//    }
//
//    public Hashtable<String, String> sonarPulse(int x, int y, Grid player1Grid, Player player2){
//        if (checkAvailability(player2) == true) {
//           // Reveal horizontal layer - (x-1, y) and (x-2, y)         (x+1, y) and (x + 2, y)
//           // Reveal vertical layer - (x, y-1) and (x, y-2)         (x, y+1) and (x, y + 2)
//           // Reveal diagonals - (x-1, y-1) and (x-1, y+1)      (x+1, y-1) and (x+1, y+1)
//
//            ArrayList<Coordinate> revealed_cells = new ArrayList<>();
//            Hashtable<String, String> vision = new Hashtable<>();
//
//            revealed_cells.add(new Coordinate(x, y-2));
//            revealed_cells.add(new Coordinate(x-1, y-1 ));
//            revealed_cells.add(new Coordinate(x, y-1));
//            revealed_cells.add(new Coordinate(x+1, y-1));
//            revealed_cells.add(new Coordinate(x-2, y));
//            revealed_cells.add(new Coordinate(x-1, y));
//            revealed_cells.add(new Coordinate(x, y));
//            revealed_cells.add(new Coordinate(x+1, y));
//            revealed_cells.add(new Coordinate(x+2, y));
//            revealed_cells.add(new Coordinate(x-1, y+1));
//            revealed_cells.add(new Coordinate(x, y+1));
//            revealed_cells.add(new Coordinate(x+1, y+1));
//            revealed_cells.add(new Coordinate(x, y+2));
//
//            for (Coordinate c: revealed_cells) {
//                if (c.x < 0 || c.x > 10) {
//                    revealed_cells.remove(c);
//                }
//                if (c.y < 0 || c.y > 10) {
//                    revealed_cells.remove(c);
//                }
//                else {
//                    if(player1Grid.checkCellStatus(c.x, c.y) == 1) {
//                        vision.put(c.toString(), "Black");
//                    } else {
//                        vision.put(c.toString(), "Grey");
//                    }
//                }
//            }
//            possible_uses--;
//            System.out.println("You have successfully revealed a portion of your opponent's map! You have " + possible_uses + " sonar pulses left.");
//            return vision;
//        }
//        return null;
//    }

    public SonarPulse(){
        this.num_uses = 2; //constant set num times we can use this
    }

    public boolean deployWeapon(int x, int y, newPlayer opponent, Map attacked_map, Map current_player_map){
            // Reveal horizontal layer - (x-1, y) and (x-2, y)         (x+1, y) and (x + 2, y)
            // Reveal vertical layer - (x, y-1) and (x, y-2)         (x, y+1) and (x, y + 2)
            // Reveal diagonals - (x-1, y-1) and (x-1, y+1)      (x+1, y-1) and (x+1, y+1)

            ArrayList<Coordinate> revealed_cells = new ArrayList<Coordinate>();
            String [][] vision = new String[10][10];

//            opponent.player_maps.get(0).defensiveGrid;

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    vision[i][j] = "Hidden | ";
                }
            }

            revealed_cells.add(new Coordinate(x, y-2));
            revealed_cells.add(new Coordinate(x-1, y-1 ));
            revealed_cells.add(new Coordinate(x, y-1));
            revealed_cells.add(new Coordinate(x+1, y-1));
            revealed_cells.add(new Coordinate(x-2, y));
            revealed_cells.add(new Coordinate(x-1, y));
            revealed_cells.add(new Coordinate(x, y));
            revealed_cells.add(new Coordinate(x+1, y));
            revealed_cells.add(new Coordinate(x+2, y));
            revealed_cells.add(new Coordinate(x-1, y+1));
            revealed_cells.add(new Coordinate(x, y+1));
            revealed_cells.add(new Coordinate(x+1, y+1));
            revealed_cells.add(new Coordinate(x, y+2));

            ArrayList<Coordinate> to_remove = new ArrayList<Coordinate>();

            for (Coordinate c: revealed_cells) {
                if (c.x >= 0 && c.x <= 9 && c.y >= 0 && c.y <= 9) {
                    if(attacked_map.defensiveGrid.checkCellStatus(c.x, c.y) == 1) {
                        vision[c.y][c.x] = "Black  | ";
                    } else {
                        vision[c.y][c.x] = " Grey  | ";
                    }
                }
            }

            String result = "";
            for (int row = 0; row < vision.length; row++) {
                for (int col = 0; col < vision[row].length; col++) {
                    result += " " + vision[row][col];
                }
                result += "\n" + "---------------------------------------------------------------------------------------------------" + "\n";
            }
            System.out.println(result);

            return true;
    }
}

