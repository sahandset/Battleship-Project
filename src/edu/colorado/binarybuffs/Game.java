package edu.colorado.binarybuffs;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private newPlayer player1;
    private newPlayer player2;

    public Game(String player1_name, String player2_name) {

        player1 = new newPlayer(player1_name);
        player2 = new newPlayer(player2_name);

    }

    public newPlayer getPlayer1() {
        return this.player1;
    }
    public newPlayer getPlayer2() {
        return this.player2;
    }

    public void startGame() {

        int turn_number = 0;
        boolean game_has_ended = false;

        while(!game_has_ended) {
            if (turn_number % 2 == 0) {
                game_has_ended = turn(player1, player2);
            }
            else {
                game_has_ended = turn(player2, player1);
            }
            turn_number++;
        }
    }

    public boolean turn(newPlayer current_player, newPlayer opponent_player) {
        boolean game_has_ended = false;
        newShip sweeper = new Minesweeper();
        newShip dest = new Destroyer();
        newShip bat = new Minesweeper();
        newShip sub = new Submarine();
        newShip shut = new Spaceshuttle();
        ArrayList<newShip> ship_objects = new ArrayList<>();
        ship_objects.add(sweeper);
        ship_objects.add(bat);
        ship_objects.add(dest);
        ship_objects.add(sub);
        ship_objects.add(shut);

        displayStartingMenu(current_player, ship_objects);
        System.out.println(current_player.getName() + "'s turn");
        createDisaster(current_player);
        showTurnMenu();
        System.out.print("Enter your option: ");
        //take in user choice
        Scanner input = new Scanner(System.in);
        int user_choice = input.nextInt();

        //switch statements for user choice
        switch (user_choice) {
            case 1: //show current grid status
                int map_choice = displayMapMenu(current_player);
                current_player.player_maps.get(map_choice).printDefensiveGrid();
                current_player.player_maps.get(map_choice).printOffensiveGrid();
                break;
            case 2: //show score
                showStatus(current_player, opponent_player, ship_objects);
                break;
            case 3: //use weapon
                int user_weapon_choice = displayWeaponMenu(current_player);

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
                    current_player.useWeapon(0, coord_choice_x, coord_choice_y, current_player, map_choice, 2);


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
                    current_player.useWeapon(1, coord_choice_x, coord_choice_y, current_player, map_choice, 2);
                }
                else if (user_weapon_choice == 3){
                    turn(current_player, opponent_player);
                }
                break;
            case 4: //use boost
                int user_boost_choice = displayBoostMenu(current_player);

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
                break;
            case 6: //Undo and redo move fleet
                System.out.println("1. Undo Move Fleet");
                System.out.println("2. Redo Move Fleet");
                System.out.println("3. Go back");

                int move_choice = input.nextInt();

                if (move_choice == 1) {
                    //undo move function
                    current_player.undo();
                }
                else if (move_choice == 2) {
                    //redo move function
                    current_player.redo();
                }
                else if (move_choice == 3) {
                    turn(current_player, opponent_player);
                }
                break;
            case 7: // surrender
                System.out.println("Are you sure you want to surrender? (Y/N)");
                String surrender_choice = input.next();
                if (surrender_choice == "Y") {
                    //end game
                    game_has_ended = true;
                    current_player.setSurrenderStatus();
                }
                else {
                    turn(current_player, opponent_player);
                }
                break;
        }
        //check surrender variable for players --> end game
        if (current_player.getSurrenderStatus()) {
            game_has_ended = true;
        }
        return game_has_ended;
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

    public void displayStartingMenu(newPlayer curr_player, ArrayList<newShip> ship_objects) {
        int map_choice = 0;

        System.out.println("How would you like to create your ship fleet?");
        System.out.println("1. Create a random fleet");
        System.out.println("2. Manually place fleet");

        Scanner input = new Scanner(System.in);
        int user_choice = input.nextInt();

        switch (user_choice) {
            case 1: //create random fleet
                Fleet myFleet = new Fleet(ship_objects);
                myFleet.placeFleet(curr_player);
                break;
            case 2: //manually place fleet

                System.out.println("The following ships will be deployed based on a starting coordinate that faces either N, S, E, or W. ");

                    for (int j = 0; j < ship_objects.size(); j++) {

                        if (ship_objects.get(j) instanceof OrbitableShip) {
                            map_choice = 2;
                        }
                        if (ship_objects.get(j) instanceof SubmersibleShip) {
                            System.out.println("Which map do you want to deploy the submarine? Note: if there is a ship existing there on the ocean, your sub will automatically be placed underwater");
                            System.out.println("1. Ocean");
                            System.out.println("2. Underwater");
                            map_choice = input.nextInt();
                        }
                        else {
                            map_choice = 0;
                        }
                        boolean success;
                        do {
                            System.out.println("Place your " + ship_objects.get(j).getName());
                            System.out.print("X: ");
                            int coord_choice_x = input.nextInt();
                            System.out.print("Y: ");
                            int coord_choice_y = input.nextInt();
                            System.out.print("Ship direction: ");
                            String direction_choice = input.next();
                            success = curr_player.deployShip(ship_objects.get(j), coord_choice_x, coord_choice_y, direction_choice, map_choice);
                        } while (!success);
                        System.out.println(ship_objects.get(j));
                    }
                break;
        }
        System.out.println("You have successfully placed all your ships! View your ships on all 3 maps below:");
        curr_player.player_maps.get(0).printOffensiveGrid();
        curr_player.player_maps.get(1).printOffensiveGrid();
        curr_player.player_maps.get(2).printOffensiveGrid();
    }

    public int displayMapMenu(newPlayer curr_player) {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < curr_player.player_maps.size(); i++) {
            System.out.println( i+1 + ". " + curr_player.player_maps.get(i).getName());
        }
        System.out.println(curr_player.player_maps.size() + 1 + ". Go back");
        int map_choice = input.nextInt();
        return map_choice;
    }

    public int displayWeaponMenu(newPlayer curr_player) {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < curr_player.player_weapons.size(); i++) {
            System.out.println( i+1 + ". " + curr_player.player_weapons.get(i).getName());
        }
        System.out.println(curr_player.player_weapons.size() + 1 + ". Go back");
        int user_weapon_choice = input.nextInt();
        return user_weapon_choice;
    }

    public int displayBoostMenu(newPlayer curr_player) {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < curr_player.player_boosts.size(); i++) {
            System.out.println( i+1 + ". " + curr_player.player_boosts.get(i).getName());
        }
        System.out.println(curr_player.player_boosts.size() + 1 + ". Go back");

        int user_boost_choice = input.nextInt();
        return user_boost_choice;
    }

    public void createDisaster(newPlayer curr_player) {
        Random rand = new Random();
        int rand_disaster = rand.nextInt(6);
        if (rand_disaster == 1) {
            Disaster hurr = new Hurricane();
            hurr.applyDisaster(curr_player);
        }
        else if (rand_disaster == 2) {
            Disaster ghost = new GhostZone();
            ghost.applyDisaster(curr_player);
        }
        else if (rand_disaster == 3) {
            Disaster asteroid = new AsteroidField();
            asteroid.applyDisaster(curr_player);
        }

    }

    //prints out current score of both players
    public void showStatus(newPlayer player1, newPlayer player2, ArrayList<newShip> ship_objects) {
        int total_ships1 = player1.getPlayerMaps().get(0).getShipsAlive() + player1.getPlayerMaps().get(1).getShipsAlive() + player1.getPlayerMaps().get(2).getShipsAlive();
        int total_ships2 = player2.getPlayerMaps().get(0).getShipsAlive() + player2.getPlayerMaps().get(1).getShipsAlive() + player2.getPlayerMaps().get(2).getShipsAlive();
        int ship_sunk_1 = player1.getShipsSunk();
        int ship_sunk_2 = player2.getShipsSunk();


        System.out.println("|-----------Current Score---------|");
        System.out.println("|---------------------------------|");
        System.out.println("|      " + player1.getName() + "  |  " + player2.getName() + "     |");
        System.out.println("Total ships alive: " + total_ships1 + "   |   " + total_ships2);
        System.out.println("Total ships sunk: " + ship_sunk_1 + "   |   " + ship_sunk_2);
        System.out.println("|-----------Your Ship Healths---------|");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < ship_objects.size(); j++) {
                System.out.println(ship_objects.get(j).getName() + ": " + player1.getPlayerMaps().get(i).ship_health.get(ship_objects.get(j)));
            }
        }


    }


}
