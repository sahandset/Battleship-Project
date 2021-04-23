package edu.colorado.binarybuffs;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
        Scanner input = new Scanner(System.in);
        int turn_number = 0;

        boolean game_has_ended = false;

        ArrayList<newShip> player1_ship_objects = new ArrayList<>();
        ArrayList<newShip> player2_ship_objects = new ArrayList<>();

        Minesweeper sweeper1 = new Minesweeper();
        Destroyer dest1 = new Destroyer();
        Battleship bat1 = new Battleship();
        Submarine sub1 = new Submarine();
        Spaceshuttle shut1 = new Spaceshuttle();

        Minesweeper sweeper2 = new Minesweeper();
        Destroyer dest2 = new Destroyer();
        Battleship bat2 = new Battleship();
        Submarine sub2 = new Submarine();
        Spaceshuttle shut2 = new Spaceshuttle();

        player1_ship_objects.add(sweeper1);
        player1_ship_objects.add(bat1);
        player1_ship_objects.add(dest1);
        player1_ship_objects.add(sub1);
        player1_ship_objects.add(shut1);
        player2_ship_objects.add(sweeper2);
        player2_ship_objects.add(bat2);
        player2_ship_objects.add(dest2);
        player2_ship_objects.add(sub2);
        player2_ship_objects.add(shut2);

        displayStartingMenu(player1, player1_ship_objects);
        displayStartingMenu(player2, player2_ship_objects);

        while(!game_has_ended) {
            if (turn_number % 2 == 0) {
//                System.out.println(game_has_ended);
                game_has_ended = checkEndGame(player1, player2, player1_ship_objects);
//                System.out.println(turn_number);
//                System.out.println(game_has_ended);

                if (game_has_ended == false) {
                    Scanner s = new Scanner(System.in);

                    System.out.println("Press enter to continue to next turn.....");

                    s.nextLine();
                    turn_number++;
                }
//                System.out.println(turn_number);
            }
            else {
                game_has_ended = checkEndGame(player2, player1, player2_ship_objects);

                if (game_has_ended == false) {
                    Scanner s = new Scanner(System.in);

                    System.out.println("Press enter to continue to next turn.....");

                    s.nextLine();
                    turn_number++;
                }
            }

        }

        boolean invalid_input;

        do {
            invalid_input = false;
            try {
                System.out.print("The game has ended. Want to play again? (Y/N): ");
                String play_again = input.next();
                play_again = play_again.toLowerCase();
                if (play_again.equals("y")) {
                    startGame();
                }
                else {
                    System.out.println("Thanks for playing Battleship!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice, please enter a valid option (Y/N)");
                input.nextLine();
            }

        } while (invalid_input);

    }

    public void preTurn(newPlayer current_player, newPlayer opponent_player, ArrayList<newShip> ship_objects) {
        System.out.println(current_player.getName().toUpperCase() + "'S TURN");
        createDisaster(current_player);
        turn(current_player, opponent_player, ship_objects);
        System.out.println(current_player.getName() + "'s turn has ended. \n\n");
    }

    public void turn(newPlayer current_player, newPlayer opponent_player, ArrayList<newShip> ship_objects) {

        boolean invalid_input;
        Scanner input = new Scanner(System.in);
        int user_choice = 0;

        do {
            invalid_input = false;
            try {
                showTurnMenu();
                System.out.print("Enter your option: ");
                //take in user choice
                user_choice = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number from the menu!");
                invalid_input = true;
                input.nextLine();
            }
        } while (invalid_input  || validateInt(user_choice, 1, 7) == false);

        System.out.print("\n");

        //switch statements for user choice
        switch (user_choice) {
            case 1: //show current grid status
                int map_choice = displayMapMenu(current_player, opponent_player, ship_objects);
                if (map_choice == current_player.player_maps.size()) {
                    turn(current_player, opponent_player, ship_objects);
                }
                else {
                    current_player.player_maps.get(map_choice).printDefensiveGrid();
                    current_player.player_maps.get(map_choice).printOffensiveGrid();
                    turn(current_player, opponent_player, ship_objects);
                }
                break;
            case 2: //show score
                showStatus(current_player, opponent_player, ship_objects);
                turn(current_player, opponent_player, ship_objects);
                break;
            case 3: //use weapon
                int user_weapon_choice = displayWeaponMenu(current_player, opponent_player, ship_objects);
                if (user_weapon_choice == current_player.player_weapons.size()) {
                    turn(current_player, opponent_player, ship_objects);
                }
                else {
                    if ((current_player.player_weapons.get(user_weapon_choice) instanceof Bomb) || (current_player.player_weapons.get(user_weapon_choice) instanceof SonarPulse)) {
                        map_choice = displayMapMenu(current_player, opponent_player, ship_objects);
                    }
                    else {
                        map_choice = 2;
                    }

                    if (map_choice == current_player.player_maps.size()) {
                        turn(current_player, opponent_player, ship_objects);
                    }
                    else {
                        current_player.player_maps.get(map_choice).printOffensiveGrid();

                        do {
                            invalid_input = false;
                            try {
                                System.out.println("Which coordinate would you like to deploy your weapon?");
                                System.out.print("X: ");
                                int coord_choice_x = input.nextInt();
                                System.out.print("Y: ");
                                int coord_choice_y = input.nextInt();
                                System.out.println("You are using " + current_player.player_weapons.get(user_weapon_choice).getName() + " on (" + coord_choice_x + ", " + coord_choice_y + ")");
                                current_player.useWeapon(user_weapon_choice, coord_choice_x, coord_choice_y, opponent_player, map_choice, 2);
                                System.out.println("\n");
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid target coordinate(s) detected! Please re-enter your coordinates");
                                invalid_input = true;
                                input.nextLine();
                            }
                        } while (invalid_input);
                    }
                }
                break;
            case 4: //use boost
                int user_boost_choice = displayBoostMenu(current_player, opponent_player, ship_objects);
                if (user_boost_choice == current_player.player_boosts.size()) {
                    turn(current_player, opponent_player, ship_objects);
                }
                else {
                    map_choice = displayMapMenu(current_player, opponent_player, ship_objects);
                    if (map_choice == current_player.player_maps.size()) {
                        turn(current_player, opponent_player, ship_objects);
                    }
                    else {
                        while (current_player.getPlayerMaps().get(map_choice).sunk_ships.size() == 0) {
                            System.out.println("There are no sunken ships on this map yet. You may only use Lifesaver boost on a sunken ship. Try another map!\n");
                            map_choice = displayMapMenu(current_player, opponent_player, ship_objects);
                            if (map_choice == current_player.player_maps.size()) {
                                break;
                            }
                        }
                        if (map_choice == current_player.player_maps.size()) {
                            turn(current_player, opponent_player, ship_objects);
                        }
                        else {
                            current_player.player_maps.get(map_choice).printDefensiveGrid();
                            System.out.println("Which ship would you like to use your Lifesaver boost on?");
                            for (int i = 0; i < current_player.getPlayerMaps().get(map_choice).sunk_ships.size(); i++) {
                                System.out.println( i+1 + ". " + current_player.getPlayerMaps().get(map_choice).sunk_ships.get(i));
                            }
                            System.out.print("Enter your option: ");
                            int lifesaver_choice = input.nextInt() - 1;
                            current_player.useBoost(user_boost_choice, lifesaver_choice, map_choice);
                            int player_sunk_ships = current_player.getShipsSunk();
                            player_sunk_ships-= 1;
                        }
                    }
                }

                break;
            case 5: //Move fleet
//                System.out.print("Which direction will you move fleet? (N, S, E, or W): ");
                String fleet_direction_choice = "";
                boolean fleet_moved = false;
                boolean fleet_fail = false;
                do {
                    System.out.print("Which direction will you move fleet? (N, S, E, or W): ");
                    fleet_direction_choice = input.next();
                    fleet_moved = current_player.playerMoveFleet(fleet_direction_choice);

                    if (!current_player.playerMoveFleet(fleet_direction_choice)) {
                        if ((!current_player.playerMoveFleet("N")) && (!current_player.playerMoveFleet("S")) &&
                                (!current_player.playerMoveFleet("E")) && (!current_player.playerMoveFleet("W"))) {
                            System.out.println("Looks like moving your fleet in any direction will cause ships to go out of bounds...better luck next time!");
                            fleet_fail = true;
                            break;
                        }
                        else {
                            System.out.println("You cannot move your fleet, your ships are out of bounds! Try again!");
                        }
                    }
                    else {
                        System.out.println("You have successfully moved your fleet!");
                    }
                } while (!fleet_moved);
                if (fleet_fail) {
                    turn(current_player, opponent_player, ship_objects);
                    break;
                }
                else {
                    System.out.print("Would you like to view your updated maps? (Y/N): ");
                    String view_choice = input.next();
                    view_choice = view_choice.toLowerCase();
                    if (view_choice.equals("y")) {
                        current_player.player_maps.get(0).printDefensiveGrid();
                        current_player.player_maps.get(1).printDefensiveGrid();
                        current_player.player_maps.get(2).printDefensiveGrid();
                    } else {
                        System.out.println("You may view your maps at a later point in the game.\n");
                    }
                }
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
                break;
            case 7: // surrender
                System.out.print("Are you sure you want to surrender? (Y/N): ");
                String surrender_choice = input.next();
                surrender_choice = surrender_choice.toLowerCase();
                if (surrender_choice.equals("y")) {
                    //end game
                    current_player.setSurrenderStatus();
//                    System.out.println(current_player.getSurrenderStatus());
                }
                else {
                    turn(current_player, opponent_player, ship_objects);
                }
                break;
        }

    }

    public boolean checkEndGame(newPlayer curr_player, newPlayer opponent_player, ArrayList<newShip> ship_objects) {
        boolean game_has_ended;
        if (curr_player.getSurrenderStatus()) {
            game_has_ended = true;
            System.out.println(curr_player.getName() + " has surrendered, " + opponent_player.getName() + " is the winner!");
        }
        else if (opponent_player.getSurrenderStatus()) {
            game_has_ended = true;
            System.out.println(opponent_player.getName() + " has surrendered, " + curr_player.getName() + " is the winner!");
        }
        else {
            game_has_ended = false;
            preTurn(curr_player, opponent_player, ship_objects);
        }
        return game_has_ended;
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
        boolean invalid_input;
        int user_choice = 0;
        Scanner input = new Scanner(System.in);

        do {
            invalid_input = false;
            try {
                System.out.println(curr_player.getName() + ", create your fleet!");
                System.out.println("How would you like to create your ship fleet?");
                System.out.println("1. Create a random fleet");
                System.out.println("2. Manually place fleet");
                System.out.print("Enter your option: ");
                user_choice = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number from the menu!");
                System.out.print("Enter your option: ");
                invalid_input = true;
                input.nextLine();
            }
        } while (invalid_input || validateInt(user_choice, 1, 2) == false);

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
        System.out.print("You have successfully placed all your ships! Would you like to view your maps? (Y/N): ");
        String view_choice = input.next();
        System.out.println("\n");
        view_choice = view_choice.toLowerCase();
        if (view_choice.equals("y")) {
            curr_player.player_maps.get(0).printDefensiveGrid();
            curr_player.player_maps.get(1).printDefensiveGrid();
            curr_player.player_maps.get(2).printDefensiveGrid();
        }
        else {
            System.out.println("You may view your maps at a later point in the game.\n");
        }
    }

    public int displayMapMenu(newPlayer curr_player, newPlayer opponent_player, ArrayList<newShip> ship_objects) {
        Scanner input = new Scanner(System.in);
        int map_choice = 0;
        boolean invalid_input;
        System.out.println("----MAPS----");
        for (int i = 0; i < curr_player.player_maps.size(); i++) {
            System.out.println( i+1 + ". " + curr_player.player_maps.get(i).getName());
        }
//        if (map_choice == curr_player.player_maps.size()) {
//            turn(curr_player, opponent_player, ship_objects);
//        }
        do {
            invalid_input = false;
            try {
                System.out.println(curr_player.player_maps.size() + 1 + ". Go back");
                System.out.print("Enter your option: ");
                map_choice = input.nextInt() - 1;
                System.out.println("\n");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number from the menu!");
            }
        } while (invalid_input || validateInt(map_choice, 0, curr_player.player_maps.size()) == false);

        return map_choice;
    }

    public int displayWeaponMenu(newPlayer curr_player, newPlayer opponent_player, ArrayList<newShip> ship_objects) {
        Scanner input = new Scanner(System.in);
        boolean invalid_input;
        int user_weapon_choice = 0;
//        if (user_weapon_choice == curr_player.player_weapons.size()) {
//            turn(curr_player, opponent_player, ship_objects);
//        }
        do {
            invalid_input = false;
            try {
                System.out.println("----WEAPON CHOICES----");
                for (int i = 0; i < curr_player.player_weapons.size(); i++) {
                    System.out.println( i+1 + ". " + curr_player.player_weapons.get(i).getName());
                }
                System.out.println(curr_player.player_weapons.size() + 1 + ". Go back");
                System.out.print("Enter your option: ");
                user_weapon_choice = input.nextInt() - 1;
                System.out.println("\n");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number from the menu!");
                invalid_input = true;
                System.out.print("Enter your option: ");
                input.nextLine();
            }
        } while (invalid_input || validateInt(user_weapon_choice, 0, curr_player.player_weapons.size()) == false);

        return user_weapon_choice;
    }

    public int displayBoostMenu(newPlayer curr_player, newPlayer opponent_player, ArrayList<newShip> ship_objects) {
        Scanner input = new Scanner(System.in);
        System.out.println("----BOOST CHOICES----");
        for (int i = 0; i < curr_player.player_boosts.size(); i++) {
            System.out.println( i+1 + ". " + curr_player.player_boosts.get(i).getName());
        }
        System.out.println(curr_player.player_boosts.size() + 1 + ". Go back");
        System.out.print("Enter your option: ");
        int user_boost_choice = input.nextInt() - 1;
        System.out.println("\n");
//        if (user_boost_choice == curr_player.player_boosts.size()) {
//            turn(curr_player, opponent_player, ship_objects);
//        }
        if (validateInt(user_boost_choice, 0, curr_player.player_boosts.size()) == false) {
            displayBoostMenu(curr_player, opponent_player, ship_objects);
        }
        return user_boost_choice;
    }

    public void createDisaster(newPlayer curr_player) {
        Random rand = new Random();
        int rand_disaster = rand.nextInt(7);
        if (rand_disaster == 1) {
            Disaster hurr = new Hurricane();
            hurr.applyDisaster(curr_player);
            System.out.println("\n");
        }
        else if (rand_disaster == 2) {
            Disaster ghost = new GhostZone();
            ghost.applyDisaster(curr_player);
            System.out.println("\n");
        }
        else if (rand_disaster == 3) {
            Disaster asteroid = new AsteroidField();
            asteroid.applyDisaster(curr_player);
            System.out.println("\n");
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
        System.out.println("Total ships sunk:  " + ship_sunk_2 + repeated_space1 + "| " + ship_sunk_1 + repeated_space2);
        System.out.println("|---------Your Ship Healths-------|");

        for (int j = 0; j < ship_objects.size(); j++) {
            if (ship_objects.get(j) instanceof OrbitableShip) {
                System.out.println(ship_objects.get(j).getName() + ": " + player1.getPlayerMaps().get(2).ship_health.get(ship_objects.get(j)) + "/" + ship_objects.get(j).getShipSize());
            }
            else if (ship_objects.get(j) instanceof SubmersibleShip) {
                System.out.println(ship_objects.get(j).getName() + ": " + player1.getPlayerMaps().get(1).ship_health.get(ship_objects.get(j)) + "/" + ship_objects.get(j).getShipSize());
            }
            else {
                System.out.println(ship_objects.get(j).getName() + ": " + player1.getPlayerMaps().get(0).ship_health.get(ship_objects.get(j)) + "/" + ship_objects.get(j).getShipSize());
            }
        }
        System.out.println("\n");
    }
    public boolean validateInt(int user_input,int lower_bound, int upper_bound) {
        if (user_input >= lower_bound && user_input <= upper_bound) {
            return true;
        }
        System.out.println("That is not an option! Please enter an option again.");
        System.out.println("Please enter a valid number between " + lower_bound + " and " + upper_bound);
        return false;
    }
    public boolean validateString(String user_input, String [] options) {
        for (String s: options) {
            if (s.equals(user_input)) {
                return true;
            }
        }
        System.out.println("That is not an option! Please enter an option again.");
        System.out.println("Please enter valid characters or words (" + options.toString() + ")");
        return false;
    }
}
