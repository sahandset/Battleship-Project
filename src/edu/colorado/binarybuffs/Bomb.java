package edu.colorado.binarybuffs;

import java.util.ArrayList;

public class Bomb extends Weapon {

    private int num_uses;
    private String name = "Bomb";

    public Bomb(){
        this.num_uses = 2147483647; // Constant set num times we can use this
    }

    public String getName() {
        return this.name;
    }

    public boolean deployWeapon(int x, int y, Player opponent, Map attacked_map, Map current_player_map, Player current_player, int method_choice) {
        Ship temp_ship = new Minesweeper();
        if (current_player.player_weapons.contains(this) && (attacked_map.getName() == "UnderwaterMap" || attacked_map.getName() == "SpaceMap")) {
            //System.out.println("You cannot use the bomb on " + attacked_map.getName());
            bombOutputs(method_choice, 1, attacked_map, temp_ship, x, y);
            return false;
        }

        if (x > 10 || x < 0 || y > 10 || y < 0) {
            //System.out.println("You cannot attack outside of the grid! (Attempted an attack at (" + x + "," + y + ")) on " + attacked_map.getName() + ".");
            bombOutputs(method_choice, 2, attacked_map, temp_ship, x, y);
            return false;
        }

        //int has_been_attacked = current_player_map.offensiveGrid.checkCellStatus(x,y);
        int is_occupied = attacked_map.defensiveGrid.checkCellStatus(x,y);

        if (is_occupied == 0) {
            //System.out.println("You've attempted an attack on " + attacked_map.getName() + ", but you've missed!");
            bombOutputs(method_choice, 3, attacked_map, temp_ship, x, y);

            if (method_choice == 2) {
                current_player_map.offensiveGrid.setCellStatus(1, x, y);
            }
        } else if (is_occupied == 1) {
            Ship attacked_ship = new Minesweeper();

            for (int i = 0; i < attacked_map.existing_ships.size(); i++){
                Ship shipy = attacked_map.existing_ships.get(i);
                ArrayList<Coordinate> coordsList = attacked_map.ship_coordinates.get(shipy);
                for (int j = 0; j < coordsList.size(); j++){
                    if (coordsList.get(j).x == x && coordsList.get(j).y == y){
                        attacked_ship = shipy;
                    }
                }
            }

            Coordinate capt_quart = attacked_map.captains_quarters.get(attacked_ship);
            if (capt_quart.x == x && capt_quart.y == y) {
                if (attacked_ship instanceof ArmoredShip) {
                    //System.out.print("This ship is armoured");
                    if (((ArmoredShip) attacked_ship).getHitCount() == 0) {
                        //System.out.println("You've attempted an attack on " + attacked_map.getName() + ", but you've missed!");
                        bombOutputs(method_choice, 3, attacked_map, attacked_ship, x, y);
                        if (method_choice == 2) {
                            current_player_map.offensiveGrid.setCellStatus(1, x, y);
                        }
                        ((ArmoredShip) attacked_ship).updateHitCount();
                    }
                    else if (((ArmoredShip) attacked_ship).getHitCount() == 1){
                        //System.out.println("You've already attacked there on the " + attacked_map.getName() + ".");
                        bombOutputs(method_choice, 4, attacked_map, attacked_ship, x, y);
                        //System.out.println("-- But you've hit a captain's quarters! You've sunk a " + attacked_ship.getName() + "!");
                        bombOutputs(method_choice, 5, attacked_map, attacked_ship, x, y);
                        attacked_map.sinkShip(attacked_ship);
                        if (method_choice != 4) {
                            current_player.incrementShipSunkCount();
                        }
                        current_player.hasSunkFirstShip();
                        //int current_health = attacked_map.ship_health.get(attacked_ship);
                        attacked_map.ship_health.replace(attacked_ship, 0);
                        ArrayList<Coordinate> coordsList = attacked_map.ship_coordinates.get(attacked_ship);
                        for (int j = 0; j < coordsList.size(); j++){
                            if (method_choice == 2) {
                                current_player_map.offensiveGrid.setCellStatus(2, coordsList.get(j).x, coordsList.get(j).y);
                            }
                            attacked_map.defensiveGrid.setCellStatus(2, coordsList.get(j).x, coordsList.get(j).y);
                        }
                        ((ArmoredShip) attacked_ship).updateHitCount();
                    }
                }
                else {
                    //System.out.println("You've hit a captain's quarters on " + attacked_map.getName() + "! You've sunk a " + attacked_ship.getName() + "!");
                    bombOutputs(method_choice, 6, attacked_map, attacked_ship, x, y);
                    attacked_map.sinkShip(attacked_ship);
                    if (method_choice != 4) {
                        current_player.incrementShipSunkCount();
                    }
                    current_player.hasSunkFirstShip();
                    //int current_health = attacked_map.ship_health.get(attacked_ship);
                    attacked_map.ship_health.replace(attacked_ship, 0);
                    ArrayList<Coordinate> coordsList = attacked_map.ship_coordinates.get(attacked_ship);
                    for (int i = 0; i < coordsList.size(); i++){
                        if (method_choice == 2) {
                            current_player_map.offensiveGrid.setCellStatus(2, coordsList.get(i).x, coordsList.get(i).y);
                        }
                        attacked_map.defensiveGrid.setCellStatus(2, coordsList.get(i).x, coordsList.get(i).y);
                    }
                }
            }
            else {
                int current_health = attacked_map.ship_health.get(attacked_ship);
                current_health -= 1;
                attacked_map.ship_health.replace(attacked_ship, current_health);
                //System.out.println("You've attempted an attack on " + attacked_map.getName() + "- it's a hit!");
                bombOutputs(method_choice, 7, attacked_map, temp_ship, x, y);
                if (method_choice == 2) {
                    current_player_map.offensiveGrid.setCellStatus(2, x, y);
                }
                attacked_map.defensiveGrid.setCellStatus(2, x, y);
            }
        } else if (is_occupied == 2) {
            //System.out.println("You've already attacked and hit a ship here.");
            bombOutputs(method_choice, 8, attacked_map, temp_ship, x, y);
        }

        return true;
    }

    public void bombOutputs(int method_choice, int print_choice, Map attacked_map, Ship attacked_ship, int x, int y) {

        switch (method_choice) {
            case 1: //jaws
                if (print_choice == 1) {
                    System.out.println("The shark can't bite your ship on " + attacked_map.getName());
                }
                else if (print_choice == 2){
                    System.out.println("The shark cannot bite you outside of the grid! (Attempted a shark attack at (" + x + "," + y + ")) on " + attacked_map.getName() + ".");
                }
                else if (print_choice == 3){
                System.out.println("You barely escaped a shark attack on " + attacked_map.getName() + "!");
                }
                else if (print_choice == 4) {
                    System.out.println("The shark bit one of your captain's quarters...luckily it was armored!");
                }
                else if (print_choice == 5){
                    System.out.println("-- But it attacked a captain's quarters! It sunk a " + attacked_ship.getName() + "!");
                }
                else if (print_choice == 6){
                    System.out.println("The shark hit a captain's quarters on " + attacked_map.getName() + "! It sunk a " + attacked_ship.getName() + "!");
                }
                else if (print_choice == 7){
                    System.out.println("The shark attacked you on the " + attacked_map.getName() + " and bit off a part of your ship!");
                }
                else if (print_choice == 8){
                    System.out.println("The shark already attacked and bit a ship here.");
                }
                break;
            case 2: //attack
                if (print_choice == 1) {
                    System.out.println("You cannot use the bomb on " + attacked_map.getName());
                }
                else if (print_choice == 2){
                    System.out.println("You cannot attack outside of the grid! (Attempted an attack at (" + x + "," + y + ")) on " + attacked_map.getName() + ".");
                }
                else if (print_choice == 3){
                    System.out.println("You've attempted an attack on " + attacked_map.getName() + ", but you've missed!");
                }
                else if (print_choice == 4){
                    System.out.println("You've attempted an attack on " + attacked_map.getName() + ", but you've missed!");
                }
                else if (print_choice == 5){
                    System.out.println("You've already attacked there on the " + attacked_map.getName() + ".");
                }
                else if (print_choice == 6){
                    System.out.println("-- But you've hit a captain's quarters! You've sunk a " + attacked_ship.getName() + "!");
                }
                else if (print_choice == 7){
                    System.out.println("You've hit a captain's quarters on " + attacked_map.getName() + "! You've sunk a " + attacked_ship.getName() + "!");
                }
                else if (print_choice == 8){
                    System.out.println("You've attempted an attack on " + attacked_map.getName() + "- it's a hit!");
                }
                else if (print_choice == 9){
                    System.out.println("You've already attacked and hit a ship here.");
                }
                break;
            case 3: //shuttle attack
                if (print_choice == 1) {
                    System.out.println("You cannot use the space laser on " + attacked_map.getName());
                }
                else if (print_choice == 2){
                    System.out.println("You cannot attack outside of the grid! (Attempted an attack at (" + x + "," + y + ")) on " + attacked_map.getName() + ".");
                }
                else if (print_choice == 3){
                    System.out.println("You've attempted an attack on " + attacked_map.getName() + ", but you've missed!");
                }
                else if (print_choice == 4){
                    System.out.println("You've attempted an attack on " + attacked_map.getName() + ", but you've missed!");
                }
                else if (print_choice == 5){
                    System.out.println("You've already attacked there on the " + attacked_map.getName() + ".");
                }
                else if (print_choice == 6){
                    System.out.println("-- But you've hit a captain's quarters! You've sunk a " + attacked_ship.getName() + "!");
                }
                else if (print_choice == 7){
                    System.out.println("You've hit a captain's quarters on " + attacked_map.getName() + "! You've sunk a " + attacked_ship.getName() + "!");
                }
                else if (print_choice == 8){
                    System.out.println("You've attempted an attack on " + attacked_map.getName() + "- it's a hit!");
                }
                else if (print_choice == 9){
                    System.out.println("You've already attacked and hit a ship here.");
                }
                break;
            case 4: //asteroids
                if (print_choice == 1) {
                    System.out.println("Asteroids cannot attack on " + attacked_map.getName());
                }
                else if (print_choice == 2){
                    System.out.println("The asteroids can't fire outside of the grid! (They attempted a hit at (" + x + "," + y + ")) on " + attacked_map.getName() + ".");
                }
                else if (print_choice == 3){
                    System.out.println("The asteroids fired on " + attacked_map.getName() + ", but luckily missed your ship!");
                }
                else if (print_choice == 4){
                    System.out.println("The asteroids fired on " + attacked_map.getName() + ", but luckily missed your ship!");
                }
                else if (print_choice == 5){
                    System.out.println("There had been attack earlier on " + attacked_map.getName() + ".");
                }
                else if (print_choice == 6){
                    System.out.println("-- But the asteroids managed to hit a captain's quarters and sunk a " + attacked_ship.getName() + "!");
                }
                else if (print_choice == 7){
                    System.out.println("The asteroids hit a captain's quarters on " + attacked_map.getName() + "! They sunk a " + attacked_ship.getName() + "!");
                }
                else if (print_choice == 8){
                    System.out.println("The asteroids fired on " + attacked_map.getName() + "- your ships have been hit!");
                }
                else if (print_choice == 9){
                    System.out.println("A ship has already been hit here.");
                }
                break;
        }

    }

    public boolean checkAvailability(int num_used) {
        if (num_used == this.num_uses) {
            return false;
        }
        return true;
    }
}
