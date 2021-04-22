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

        ArrayList<newShip> player1_ship_objects = new ArrayList<>();
        ArrayList<newShip> player2_ship_objects = new ArrayList<>();

        while(!game_has_ended) {
            if (turn_number % 2 == 0) {
                if (turn_number == 0) {
                    Minesweeper sweeper = new Minesweeper();
                    Destroyer dest = new Destroyer();
                    Battleship bat = new Battleship();
                    Submarine sub = new Submarine();
                    Spaceshuttle shut = new Spaceshuttle();

                    player1_ship_objects.add(sweeper);
                    player1_ship_objects.add(bat);
                    player1_ship_objects.add(dest);
                    player1_ship_objects.add(sub);
                    player1_ship_objects.add(shut);

                    displayStartingMenu(player1, player1_ship_objects);
                }
                preTurn(player1, player2, player1_ship_objects);
            }
            else {
                if (turn_number == 1) {
                    Minesweeper sweeper = new Minesweeper();
                    Destroyer dest = new Destroyer();
                    Battleship bat = new Battleship();
                    Submarine sub = new Submarine();
                    Spaceshuttle shut = new Spaceshuttle();

                    player2_ship_objects.add(sweeper);
                    player2_ship_objects.add(bat);
                    player2_ship_objects.add(dest);
                    player2_ship_objects.add(sub);
                    player2_ship_objects.add(shut);

                    displayStartingMenu(player2, player2_ship_objects);
                }
                preTurn(player2, player1, player2_ship_objects);
            }
            turn_number++;

        }
    }

    public void preTurn(newPlayer current_player, newPlayer opponent_player, ArrayList<newShip> ship_objects) {
        System.out.println(current_player.getName().toUpperCase() + "'S TURN");
        createDisaster(current_player);
        turn(current_player, opponent_player, ship_objects);
        System.out.println(current_player.getName() + "'s turn has ended. \n\n");
    }

    public boolean turn(newPlayer current_player, newPlayer opponent_player, ArrayList<newShip> ship_objects) {
        boolean turn_has_ended = false;
        showTurnMenu();
        System.out.print("Enter your option: ");
        //take in user choice
        Scanner input = new Scanner(System.in);
        int user_choice = input.nextInt();
        System.out.print("\n");

        //switch statements for user choice
        switch (user_choice) {
            case 1: //show current grid status
                int map_choice = displayMapMenu(current_player, opponent_player, ship_objects);
                current_player.player_maps.get(map_choice).printDefensiveGrid();
                current_player.player_maps.get(map_choice).printOffensiveGrid();
                turn(current_player, opponent_player, ship_objects);
                break;
            case 2: //show score
                showStatus(current_player, opponent_player, ship_objects);
                turn(current_player, opponent_player, ship_objects);
                break;
            case 3: //use weapon
                int user_weapon_choice = displayWeaponMenu(current_player, opponent_player, ship_objects);
                map_choice = displayMapMenu(current_player, opponent_player, ship_objects);
                current_player.player_maps.get(map_choice).printOffensiveGrid();
                System.out.println("Which coordinate would you like to attack?");
                System.out.print("X: ");
                int coord_choice_x = input.nextInt();
                System.out.print("Y: ");
                int coord_choice_y = input.nextInt();
                System.out.println("You are attacking on (" + coord_choice_x + ", " + coord_choice_y + ")");
                current_player.useWeapon(user_weapon_choice, coord_choice_x, coord_choice_y, current_player, map_choice, 2);

                turn_has_ended = true;
                break;
            case 4: //use boost
                int user_boost_choice = displayBoostMenu(current_player, opponent_player, ship_objects);
                map_choice = displayMapMenu(current_player, opponent_player, ship_objects);
                current_player.player_maps.get(map_choice).printDefensiveGrid();
                System.out.println("Which ship would you like to use your Lifesaver on?");
                for (int i = 0; i < current_player.player_maps.get(map_choice).sunk_ships.size(); i++) {
                    System.out.println( i+1 + ". " + current_player.player_maps.get(map_choice).sunk_ships.get(i));
                }
                int lifesaver_choice = input.nextInt();
                current_player.useBoost(user_boost_choice, lifesaver_choice, map_choice);

                turn_has_ended = true;
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
                    turn(current_player, opponent_player, ship_objects);
                }
                turn_has_ended = true;
                break;
            case 7: // surrender
                System.out.println("Are you sure you want to surrender? (Y/N)");
                String surrender_choice = input.next();
                if (surrender_choice == "Y") {
                    //end game
                    turn_has_ended = true;
                    current_player.setSurrenderStatus();
                }
                else {
                    turn(current_player, opponent_player, ship_objects);
                }
                turn_has_ended = true;
                break;
        }
        //check surrender variable for players --> end game
        if (current_player.getSurrenderStatus()) {
            turn_has_ended = true;
        }
        return turn_has_ended;
    }


    public void showTurnMenu() {
        //Print out menu for each player's turn
        System.out.println(" -- MAIN MENU -- ");
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
        System.out.println(curr_player.getName() + ", create your fleet!");
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
                        else if (ship_objects.get(j) instanceof SubmersibleShip) {
                            System.out.println("Which map do you want to deploy the submarine? Note: if there is a ship existing there on the ocean, your sub will automatically be placed underwater");
                            System.out.println("1. Ocean");
                            System.out.println("2. Underwater");
                            map_choice = input.nextInt() - 1;
                        } else {
                            map_choice = 0;
                        }
                        boolean success = false;

                        while (success == false) {
                            System.out.println("Place your " + ship_objects.get(j).getName());
                            System.out.print("X: ");
                            int coord_choice_x = input.nextInt();
                            System.out.print("Y: ");
                            int coord_choice_y = input.nextInt();
                            System.out.print("Ship direction: ");
                            String direction_choice = input.next();
                            success = curr_player.deployShip(ship_objects.get(j), coord_choice_x, coord_choice_y, direction_choice, map_choice);
                            if (!success) {
                                System.out.println("You can't place the " + ship_objects.get(j).getName()+  " there! Try again.");
                            }
                        }
                    }
            break;
        }
        System.out.print("You have successfully placed all your ships! Would you like to view your maps? (Y/N) \n");
        String view_choice = input.nextLine();
        view_choice = view_choice.toLowerCase();
        if (view_choice.equals("y")) {
            System.out.println("Ocean Defensive Grid: \n");
            curr_player.player_maps.get(0).printDefensiveGrid();
            System.out.println("Underwater Defensive Grid: \n");
            curr_player.player_maps.get(1).printDefensiveGrid();
            System.out.println("Space Defensive Grid: \n");
            curr_player.player_maps.get(2).printDefensiveGrid();
        }
        else if (!view_choice.equals("y")) {
            System.out.println("You may view your maps at a later point in the game.");
        }

    }

    public int displayMapMenu(newPlayer curr_player, newPlayer opponent_player, ArrayList<newShip> ship_objects) {
        Scanner input = new Scanner(System.in);
        System.out.println("----MAPS----");
        for (int i = 0; i < curr_player.player_maps.size(); i++) {
            System.out.println( i+1 + ". " + curr_player.player_maps.get(i).getName());
        }
        System.out.println(curr_player.player_maps.size() + 1 + ". Go back");
        System.out.print("Enter your option: ");
        int map_choice = input.nextInt() - 1;
        System.out.println("\n");
        if (map_choice == curr_player.player_maps.size() + 2) {
            turn(curr_player, opponent_player, ship_objects);
        }
        return map_choice;
    }

    public int displayWeaponMenu(newPlayer curr_player, newPlayer opponent_player, ArrayList<newShip> ship_objects) {
        Scanner input = new Scanner(System.in);
        System.out.println("----WEAPON CHOICES----");
        for (int i = 0; i < curr_player.player_weapons.size(); i++) {
            System.out.println( i+1 + ". " + curr_player.player_weapons.get(i).getName());
        }
        System.out.println(curr_player.player_weapons.size() + 1 + ". Go back");
        int user_weapon_choice = input.nextInt() - 1;
        System.out.println("\n");
        if (user_weapon_choice == curr_player.player_weapons.size()) {
            turn(curr_player, opponent_player, ship_objects);
        }
        else if (user_weapon_choice < 0 || user_weapon_choice > curr_player.player_weapons.size()) {
            System.out.println("That is not an option! Please enter an option again.");
            displayWeaponMenu(curr_player, opponent_player, ship_objects);
        }
        return user_weapon_choice;
    }

    public int displayBoostMenu(newPlayer curr_player, newPlayer opponent_player, ArrayList<newShip> ship_objects) {
        Scanner input = new Scanner(System.in);
        System.out.println("----BOOST CHOICES----");
        for (int i = 0; i < curr_player.player_boosts.size(); i++) {
            System.out.println( i+1 + ". " + curr_player.player_boosts.get(i).getName());
        }
        System.out.println(curr_player.player_boosts.size() + 1 + ". Go back");

        int user_boost_choice = input.nextInt() - 1;
        System.out.println("\n");
        if (user_boost_choice == curr_player.player_boosts.size()) {
            turn(curr_player, opponent_player, ship_objects);
        }
        else if (user_boost_choice < 0 || user_boost_choice > curr_player.player_boosts.size()) {
            System.out.println("That is not an option! Please enter an option again.");
            displayBoostMenu(curr_player, opponent_player, ship_objects);
        }
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

        int name_length1 = player1.getName().length();
        int name_length2 = player2.getName().length();
        String repeated_space1 = new String(new char[name_length1]).replace('\0', ' ');
        String repeated_space2 = new String(new char[name_length2]).replace('\0', ' ');

        System.out.println("|-----------Current Score---------|");
        System.out.println("                   " + player1.getName() + " | " + player2.getName());
        System.out.println("Total ships alive: " + total_ships1 + repeated_space1 + "| " + total_ships2 + repeated_space2);
        System.out.println("Total ships sunk:  " + ship_sunk_1 + repeated_space1 + "| " + ship_sunk_2 + repeated_space2);
        System.out.println("|---------Your Ship Healths-------|");

        for (int j = 0; j < ship_objects.size(); j++) {
            if (ship_objects.get(j) instanceof OrbitableShip) {
                System.out.println(ship_objects.get(j).getName() + ": " + player1.getPlayerMaps().get(2).ship_health.get(ship_objects.get(j)));
            }
            else if (ship_objects.get(j) instanceof SubmersibleShip) {
                System.out.println(ship_objects.get(j).getName() + ": " + player1.getPlayerMaps().get(1).ship_health.get(ship_objects.get(j)));
            }
            else {
                System.out.println(ship_objects.get(j).getName() + ": " + player1.getPlayerMaps().get(0).ship_health.get(ship_objects.get(j)));
            }
        }
        System.out.println("\n");
    }
}
