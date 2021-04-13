package edu.colorado.binarybuffs;
import java.util.Scanner;

public class Game {

    private newPlayer player1;
    private newPlayer player2;

    public Game(String player1_name, String player2_name) {

        newPlayer player1 = new newPlayer(player1_name);
        newPlayer player2 = new newPlayer(player2_name);

    }

    public void startGame() {

        int turn_number = 1;
        boolean game_has_ended = false;

        while(!game_has_ended) {

            turn_number++;
        }
    }

    public void turn(newPlayer current_player, newPlayer opponent_player) { //return bool?
        //call function to display menu
        showTurnMenu();
        //take in user choice
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your option: ");
        int user_choice = input.nextInt();

        //switch statements for user choice
        switch (user_choice) {
            case 1: //show current grid status
                int map_choice = displayMapMenu(current_player);
                current_player.player_maps.get(map_choice).printDefensiveGrid();
                current_player.player_maps.get(map_choice).printOffensiveGrid();
                turn(current_player, opponent_player);
                break;
            case 2: //show score
                // Show ships alive
                // Show ships sunk
                turn(current_player, opponent_player);
                break;
            case 3: //use weapon
                for (int i = 0; i < current_player.player_weapons.size(); i++) {
                    System.out.println( i+1 + ". " + current_player.player_weapons.get(i));
                }
                System.out.println(current_player.player_weapons.size() + 1 + ". Go back");

                int user_weapon_choice = input.nextInt();

                if (user_weapon_choice == 1) { //Using bomb
                    map_choice = displayMapMenu(current_player);
                    current_player.player_maps.get(map_choice).printOffensiveGrid();
                    System.out.println("Which coordinate would you like to attack?");
                    System.out.print("X: ");
                    int coord_choice_x = input.nextInt();
                    System.out.print("Y: ");
                    int coord_choice_y = input.nextInt();
                    System.out.println("You are attacking on (" + coord_choice_x + ", " + coord_choice_y + ")");
                    //Attack with bomb
                    current_player.useWeapon(0, coord_choice_x, coord_choice_y, current_player, map_choice);


                }
                else if (user_weapon_choice == 2) { //Using Sonar Pulse
                    map_choice = displayMapMenu(current_player);
                    current_player.player_maps.get(map_choice).printOffensiveGrid();
                    System.out.println("Which coordinate would you like to use Sonar Pulse?");
                    System.out.print("X: ");
                    int coord_choice_x = input.nextInt();
                    System.out.print("Y: ");
                    int coord_choice_y = input.nextInt();
                    System.out.println("You are using sonar pulse on (" + coord_choice_x + ", " + coord_choice_y + ")");
                    //use sonar pulse
                    current_player.useWeapon(1, coord_choice_x, coord_choice_y, current_player, map_choice);
                }
                else if (user_weapon_choice == 3){
                    turn(current_player, opponent_player);
                }
                break;
            case 4: //use boost
                for (int i = 0; i < current_player.player_boosts.size(); i++) {
                    System.out.println( i+1 + ". " + current_player.player_boosts.get(i));
                }
                System.out.println(current_player.player_boosts.size() + 1 + ". Go back");

                int user_boost_choice = input.nextInt();

                if (user_boost_choice == 1) { //Using lifesaver
                    map_choice = displayMapMenu(current_player);
                    current_player.player_maps.get(map_choice).printDefensiveGrid();
                    System.out.println("Which ship would you like to use your Lifesaver on?");
                    for (int i = 0; i < current_player.player_maps.get(map_choice).sunk_ships.size(); i++) {
                        System.out.println( i+1 + ". " + current_player.player_maps.get(map_choice).sunk_ships.get(i));
                    }
                    int lifesaver_choice = input.nextInt();
                    //use lifesaver boost
                    current_player.useBoost(0, lifesaver_choice, map_choice);
                }
                else if (user_boost_choice == 2) {
                    turn(current_player, opponent_player);
                }
                break;
            case 5: //Move fleet
                System.out.println("Which direction will you move fleet?");
                String fleet_direction_choice = input.next();
                System.out.println("You are moving your fleet in the direction: " + fleet_direction_choice);
                //move fleet function
                current_player.playerMoveFleet(fleet_direction_choice);
                //go back
                turn(current_player, opponent_player);
                break;
            case 6: //Undo and redo move fleet
                System.out.println("1. Undo Move Fleet");
                System.out.println("2. Redo Move Fleet");
                System.out.println("3. Go back");

                int move_choice = input.nextInt();

                if (move_choice == 1) {
                    //undo move function
                }
                else if (move_choice == 2) {
                    //redo move function
                }
                else if (move_choice == 3) {
                    turn(current_player, opponent_player);
                }
                break;
            case 7: // surrender
                System.out.println("Are you sure you want to surrender? (Y/N)");
                String surrender_choice = input.next();
                if (surrender_choice == "Y") {
                    boolean surrender = true;
                    //end game
                }
                else {
                    turn(current_player, opponent_player);
                }
                break;
        }


    }

    public void showTurnMenu() {
        //Print out menu for each player's turn
        System.out.println("1. Show my grid status");
        System.out.println("2. Show current score");
        System.out.println("3. Use Weapon");
        System.out.println("4. Use Boost");
        System.out.println("5. Move Fleet");
        System.out.println("6. Undo/Redo Fleet Move");
        System.out.println("7. Surrender");

    }

    public int displayMapMenu(newPlayer curr_player) {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < curr_player.player_maps.size(); i++) {
            System.out.println( i+1 + ". " + curr_player.player_maps.get(i));
        }
        System.out.println(curr_player.player_maps.size() + 1 + ". Go back");
        int map_choice = input.nextInt();
        return map_choice;
    }

    public void getStatus() {

    }

    public void attack(int x, int y) {

    }

    public void moveFleet() {

    }


}
