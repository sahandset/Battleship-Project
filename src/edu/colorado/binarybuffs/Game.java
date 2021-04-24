package edu.colorado.binarybuffs;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private Player player1;
    private Player player2;

    /**
     * Constructor for Game class
     * creates 2 player objects by taking in both player names
     * @param player1_name string value for name of player 1
     * @param player2_name string value for name of player 2
     */
    public Game(String player1_name, String player2_name) {
        player1 = new Player(player1_name);
        player2 = new Player(player2_name);
    }

    //Getter method to get player 1 object
    public Player getPlayer1() {
        return this.player1;
    }

    //Getter method to get player 2 object
    public Player getPlayer2() {
        return this.player2;
    }

    /**
     * startGame() is the function that runs turns of the game
     * Creates an array of ship objects for each player
     * Calls the displayStartingMenu function to allow users to create fleet (array of ship objects)
     * While the game has not ended (calls checkEndGame() function), call turn function on each player and increment turn respectively
     * Even turns are player 1, while odd turns are player 2
     * Uses validateInt(), validateString(), and InputMismatchException functions to validate all string and int user inputs before performing function
     */
    public void startGame() {
        Scanner input = new Scanner(System.in);
        int turn_number = 0;

        boolean game_has_ended = false;

        ArrayList<Ship> player1_ship_objects = new ArrayList<>();
        ArrayList<Ship> player2_ship_objects = new ArrayList<>();

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
                game_has_ended = checkEndGame(player1, player2, player1_ship_objects);

                if (game_has_ended == false) {
                    Scanner s = new Scanner(System.in);

                    System.out.println("Press enter to continue to next turn.....");

                    s.nextLine();
                    turn_number++;
                }
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
                System.out.println("Invalid choice, please enter a valid option (Y/N)\n");
                invalid_input = true;
                input.nextLine();
            }

        } while (invalid_input);

    }

    /**
     * preTurn() function performs functions that need to happen before the turn
     * Calls createDisaster function to randomly initiate disasters on player
     * Checks if there is an animal under any ship by calling checkForAnimal() on player's map
     * Calls turn function on current player
     * @param current_player player that is currently playing in the turn
     * @param opponent_player opponent player that is not currently playing the turn
     * @param ship_objects ArrayList of player's ships
     */
    public void preTurn(Player current_player, Player opponent_player, ArrayList<Ship> ship_objects) {
        System.out.println(current_player.getName().toUpperCase() + "'S TURN");
        createDisaster(current_player);
        current_player.getPlayerMaps().get(0).checkForAnimal(current_player);
        turn(current_player, opponent_player, ship_objects);
        System.out.println(current_player.getName() + "'s turn has ended. \n\n");
    }

    /** turn() function runs a full turn for one player in the game
     * After displaying menu before turn, take in a user input for menu choice
     * Enters a series of switch cases to perform respective functions
     * Case 1: Shows player's grid status
            * Displays menu for possible maps, takes in user's map choice
            * Uses getter methods to access that player's maps with the map choice
            * Prints out both defensive and offensive grids
     * Case 2: Show player's score
            * calls showStatus() function which displays score table
            * recursively calls turn function so user can perform another action
     * Case 3: Player uses a weapon
            * Displays menu of possible weapons a player has
            * Depending on the type of weapon, output a certain map's offensive grid
            * Prompts the user for coordinates and calls deployWeapon with those coordinates
     * Case 4: Player uses a boost
            * Displays menu of possible boosts a player has
            * Print out list of ships that can be revived and ask for user to choose from the list
            * Calls useBoost with that ship choice and provides error message if ship can't be revived
     * Case 5: Player moves fleet
            * Validates whether the ship can be moved in all directions first --> let the user know that move fleet can't be done
            * Asks user to input direction of move, continuously prompts user for input if move fleet doesn't work in a certain direction
            * calls moveFleet function to perform action
     * Case 6: Player undoes/redoes fleet move
            * Display 2 options for user, undo or redo move, takes in user's choice
            * Calls respective undo and redo function based on user choice
     * Case 7: Player chooses to surrender
            * Prompts user for confirmation for surrendering
            * Sets their surrender status to true
     * Uses validateInt(), validateString(), and InputMismatchException functions to validate all string and int user inputs before performing function
     * @param current_player player that is currently playing in the turn
     * @param opponent_player opponent player that is not currently playing the turn
     * @param ship_objects ArrayList of player's ships
     */
    public void turn(Player current_player, Player opponent_player, ArrayList<Ship> ship_objects) {

        boolean invalid_input;
        Scanner input = new Scanner(System.in);
        int user_choice = 0;

        do {
            invalid_input = false;
            try {
                showTurnMenu();
                System.out.print("Enter your option: ");
                user_choice = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number from the menu!\n");
                invalid_input = true;
                input.nextLine();
            }
        } while (invalid_input  || validateInt(user_choice, 1, 7) == false);

        System.out.print("\n");

        //switch cases for user choice
        switch (user_choice) {
            case 1: // Show current grid status of player
                int map_choice = displayMapMenu(current_player);
                if (map_choice == current_player.player_maps.size()) {
                    turn(current_player, opponent_player, ship_objects);
                }
                else {
                    current_player.player_maps.get(map_choice).printDefensiveGrid();
                    current_player.player_maps.get(map_choice).printOffensiveGrid();
                    turn(current_player, opponent_player, ship_objects);
                }
                break;
            case 2: // Show player's current score
                showStatus(current_player, opponent_player, ship_objects);
                turn(current_player, opponent_player, ship_objects);
                break;
            case 3: // Player chooses to use a weapon
                int user_weapon_choice = displayWeaponMenu(current_player);
                if (user_weapon_choice == current_player.player_weapons.size()) {
                    turn(current_player, opponent_player, ship_objects);
                }
                else {
                    if ((current_player.player_weapons.get(user_weapon_choice) instanceof Bomb)){
                        map_choice = 0;
                    }
                    else if ((current_player.player_weapons.get(user_weapon_choice) instanceof SonarPulse)) {
                        map_choice = displayMapMenu(current_player);
                    }
                    else {
                        map_choice = 2;
                    }

                    if (map_choice == current_player.player_maps.size()) {
                        turn(current_player, opponent_player, ship_objects);
                    }
                    else {
                        if (current_player.player_weapons.get(user_weapon_choice) instanceof SpaceLaser){
                            current_player.player_maps.get(0).printOffensiveGrid();
                            current_player.player_maps.get(1).printOffensiveGrid();
                            current_player.player_maps.get(2).printOffensiveGrid();
                        }
                        else{
                            current_player.player_maps.get(map_choice).printOffensiveGrid();
                        }

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
                                System.out.println("Invalid target coordinate(s) detected! Please re-enter your coordinates\n");
                                invalid_input = true;
                                input.nextLine();
                            }
                        } while (invalid_input);
                    }
                }
                break;
            case 4: // Player chooses to use a boost
                int user_boost_choice = displayBoostMenu(current_player);
                if (user_boost_choice == current_player.player_boosts.size()) {
                    turn(current_player, opponent_player, ship_objects);
                }
                else {
                    map_choice = displayMapMenu(current_player);
                    if (map_choice == current_player.player_maps.size()) {
                        turn(current_player, opponent_player, ship_objects);
                    }
                    else {
                        while (current_player.getPlayerMaps().get(map_choice).sunk_ships.size() == 0) {
                            System.out.println("There are no sunken ships on this map yet. You may only use Lifesaver boost on a sunken ship. Try another map!\n");
                            map_choice = displayMapMenu(current_player);
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
                        }
                    }
                }
                break;
            case 5: // Player chooses to move their fleet

                if(current_player.validateAllDirections()){
                    String []  valid_inputs= {"N", "S", "E", "W"};
                    String fleet_direction_choice = "";
                    boolean fleet_moved;
                    do {
                        do {
                            invalid_input = false;
                            try {
                                System.out.print("Which direction will you move fleet? (N, S, E, or W): ");
                                fleet_direction_choice = input.next();
                                fleet_direction_choice = fleet_direction_choice.toUpperCase();
                            } catch (InputMismatchException e){
                                System.out.println("Invalid input, please enter a valid direction!\n");
                                invalid_input = true;
                                input.nextLine();
                            }
                        } while (invalid_input || validateString(fleet_direction_choice, valid_inputs) == false);

                        fleet_moved = current_player.playerMoveFleet(fleet_direction_choice);

                        if (!fleet_moved) {
                            System.out.println("You cannot move your fleet that direction, Try again!");
                            System.out.println("Make sure every ship can be moved in a chosen direction!");
                        }
                    } while (!fleet_moved);

                    System.out.println("You have successfully moved your fleet!");

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
                    break;
                }
                else{
                    System.out.println("Looks like moving your fleet in any direction will cause ships to go out of bounds...better luck next time! \n");
                    turn(current_player, opponent_player, ship_objects);
                    break;
                }

            case 6: //Player chooses to undo or redo their move fleet action
                int move_choice = 0;
                do {
                    invalid_input = false;
                    try {
                        System.out.println("1. Undo Move Fleet");
                        System.out.println("2. Redo Move Fleet");
                        System.out.println("3. Go back");
                        System.out.print("Enter your option: ");
                        move_choice = input.nextInt();
                        System.out.println();

                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input, please enter a number from the menu!\n");
                        invalid_input = true;
                        input.nextLine();
                    }
                } while (invalid_input || validateInt(move_choice, 1, 3) == false);

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
            case 7: // Player chooses to surrender
                System.out.print("Are you sure you want to surrender? (Y/N): ");
                String surrender_choice = input.next();
                surrender_choice = surrender_choice.toLowerCase();
                if (surrender_choice.equals("y")) {
                    current_player.setSurrenderStatus();
                }
                else {
                    turn(current_player, opponent_player, ship_objects);
                }
                break;
        }

    }

    /**
     * checkEndGame() function checks conditions that terminate the game
     * Checks if current player or opponent player have a surrender status of true at any point in the game
     * Prints out statements to display who won and terminates the game
     * @param curr_player player that is currently playing in the turn
     * @param opponent_player opponent player that is not currently playing the turn
     * @param ship_objects ArrayList of player's ships
     * @return boolean whether game has ended
     */
    public boolean checkEndGame(Player curr_player, Player opponent_player, ArrayList<Ship> ship_objects) {
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

    /**
     * showTurnMenu() displays main menu for each player's turn
     * Prints out different menu options that user can select throughout the game
     */
    public void showTurnMenu() {
        System.out.println(" -- MAIN MENU -- ");
        System.out.println("1. Show my grid status");
        System.out.println("2. Show current score");
        System.out.println("3. Use Weapon");
        System.out.println("4. Use Boost");
        System.out.println("5. Move Fleet");
        System.out.println("6. Undo/Redo Fleet Move");
        System.out.println("7. Surrender");
    }

    /** displayStartingMenu() function shows initial menu for user fleet creation
     * Displays two options for user fleet creation, takes in user choice
     * Enters 2 switch cases to perform respective functions
     * Case 1: Fleet is randomly created
             * Creates a new fleet object by taking in player's array list of ships created at the beginning of the game
             * Calls placeFleet() function
     * Case 2: Player manually creates fleet
             * Traverses through player's ship objects and places ships one by one
             * Prompts user for x/y coordinate and direction ship should face
             * Calls deployShip() function with those inputs and provides error message if not deployed properly
     * Uses validateInt(), validateString(), and InputMismatchException functions to validate all string and int user inputs before performing function
     * @param curr_player player that is currently playing in the turn
     * @param ship_objects ArrayList of player's ships
     */
    public void displayStartingMenu(Player curr_player, ArrayList<Ship> ship_objects) {
        int map_choice = 0;
        boolean invalid_input;
        int user_choice = 0;
        Scanner input = new Scanner(System.in);
        System.out.println(curr_player.getName() + ", create your fleet!");
        do {
            invalid_input = false;
            try {
                System.out.println("How would you like to create your ship fleet?");
                System.out.println("1. Create a random fleet");
                System.out.println("2. Manually place fleet");
                System.out.print("Enter your option: ");
                user_choice = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number from the menu!\n");
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

                for (Ship ship_object : ship_objects) {

                    if (ship_object instanceof OrbitableShip) {
                        map_choice = 2;
                    } else if (ship_object instanceof SubmersibleShip) {
                        do {
                            invalid_input = false;
                            try {
                                System.out.println("Which map do you want to deploy the submarine? Note: if there is a ship existing there on the ocean, your sub will automatically be placed underwater");
                                System.out.println("1. Ocean");
                                System.out.println("2. Underwater");
                                System.out.print("Enter your option: ");
                                map_choice = input.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input, please enter a number from the menu!\n");
                                invalid_input = true;
                                input.nextLine();
                            }

                        } while (invalid_input || validateInt(map_choice, 1, 2) == false);

                        map_choice = map_choice - 1;

                    } else {
                        map_choice = 0;
                    }
                    int coord_choice_x = 0;
                    int coord_choice_y = 0;

                    System.out.println("Place your " + ship_object.getName());

                    boolean success = false;

                    while (success == false) {
                        do {
                            invalid_input = false;
                            try {
                                System.out.print("X: ");
                                coord_choice_x = input.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input, please enter a valid coordinate!\n");
                                invalid_input = true;
                                input.nextLine();
                            }
                        } while (invalid_input || validateInt(coord_choice_x, 0, 9) == false);

                        do {
                            invalid_input = false;
                            try {
                                System.out.print("Y: ");
                                coord_choice_y = input.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input, please enter a valid coordinate!\n");
                                invalid_input = true;
                                input.nextLine();
                            }
                        } while (invalid_input || validateInt(coord_choice_y, 0, 9) == false);

                        String[] options = {"N", "S", "E", "W"};
                        String direction_choice = "";

                        do {
                            invalid_input = false;
                            try {
                                System.out.print("Ship direction (N, S, E, W): ");
                                direction_choice = input.next();
                                direction_choice = direction_choice.toUpperCase();
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input, please enter a valid coordinate!\n");
                                invalid_input = true;
                                input.nextLine();
                            }
                        } while (invalid_input || validateString(direction_choice, options) == false);

                        success = curr_player.deployShip(ship_object, coord_choice_x, coord_choice_y, direction_choice, map_choice);
                        if (!success) {
                            System.out.println("You can't place the " + ship_object.getName() + " there! Try again.");
                        }
                    }
                }
                break;
        }
        String view_choice = "";
        String [] options = {"Y", "N"};
        do {
            invalid_input = false;
            try {
                System.out.print("You have successfully placed all your ships! Would you like to view your maps? (Y/N): ");
                view_choice = input.next();
                System.out.println("\n");
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice, please enter a valid option (Y/N)\n");
                invalid_input = true;
                input.nextLine();
            }
        } while (invalid_input || validateString(view_choice, options) == false);

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

    /**
     * displayMapMenu() displays menu of all the maps a player has
     * Traverses through player's current maps, prints out menu number plus name of map
     * Prompts the user for choice input and validates it based on type, bounds, etc.
     * @param curr_player player that is currently playing in the turn
     * @return integer of user's choice of map
     */
    public int displayMapMenu(Player curr_player) {
        Scanner input = new Scanner(System.in);
        int map_choice = 0;
        boolean invalid_input;

        do {
            invalid_input = false;
            try {
                System.out.println("----MAPS----");
                for (int i = 0; i < curr_player.player_maps.size(); i++) {
                    System.out.println( i+1 + ". " + curr_player.player_maps.get(i).getName());
                }
                System.out.println(curr_player.player_maps.size() + 1 + ". Go back");
                System.out.print("Enter your option: ");
                map_choice = input.nextInt();
                System.out.println("\n");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number from the menu!");
                invalid_input = true;
                input.nextLine();
            }
        } while (invalid_input || validateInt(map_choice, 1, curr_player.player_maps.size() + 1) == false);

        return map_choice - 1;
    }

    /**
     * displayWeaponMenu() displays menu of all the weapons a player has
     * Traverses through player's current weapons, prints out menu number plus name of weapon
     * Prompts the user for choice input and validates it based on type, bounds, etc.
     * @param curr_player player that is currently playing in the turn
     * @return integer of user's choice of weapon
     */
    public int displayWeaponMenu(Player curr_player) {
        Scanner input = new Scanner(System.in);
        boolean invalid_input;
        int user_weapon_choice = 0;
        do {
            invalid_input = false;
            try {
                System.out.println("----WEAPON CHOICES----");
                for (int i = 0; i < curr_player.player_weapons.size(); i++) {
                    System.out.println( i+1 + ". " + curr_player.player_weapons.get(i).getName());
                }
                System.out.println(curr_player.player_weapons.size() + 1 + ". Go back");
                System.out.print("Enter your option: ");
                user_weapon_choice = input.nextInt();
                System.out.println();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number from the menu!\n");
                invalid_input = true;
                input.nextLine();
            }
        } while (invalid_input || validateInt(user_weapon_choice, 1, curr_player.player_weapons.size() + 1) == false);

        return user_weapon_choice - 1;
    }

    /**
     * displayBoostMenu() displays menu of all the boosts a player has
     * Traverses through player's current boosts, prints out menu number plus name of boost
     * Prompts the user for choice input and validates it based on type, bounds, etc.
     * @param curr_player player that is currently playing in the turn
     * @return integer of user's choice of boost
     */
    public int displayBoostMenu(Player curr_player) {
        Scanner input = new Scanner(System.in);
        boolean invalid_input;
        int user_boost_choice = 0;
        do {
            invalid_input = false;
            try {
                System.out.println("----BOOST CHOICES----");
                for (int i = 0; i < curr_player.player_boosts.size(); i++) {
                    System.out.println( i+1 + ". " + curr_player.player_boosts.get(i).getName());
                }
                System.out.println(curr_player.player_boosts.size() + 1 + ". Go back");
                System.out.print("Enter your option: ");
                user_boost_choice = input.nextInt();
                System.out.println("\n");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number from the menu!\n");
                invalid_input = true;
                System.out.print("Enter your option: ");
                input.nextLine();
            }
        } while (invalid_input || validateInt(user_boost_choice, 1, curr_player.player_boosts.size() + 1) == false);

        return user_boost_choice - 1;
    }

    /**
     * createDisaster() creates 1 of 3 disasters randomly before every turn
     * Creates a random integer between 0 and 10
     * If random value is 0, create a hurricane object and apply disaster on the player
     * If random value is 1, create a ghost zone object and apply disaster on the player
     * If random value is 2, create a asteroid field object and apply disaster on the player
     * @param curr_player player that is currently playing in the turn
     */
    public void createDisaster(Player curr_player) {
        Random rand = new Random();
        int rand_disaster = rand.nextInt(10);

        if (rand_disaster == 0) {
            Disaster hurr = new Hurricane();
            hurr.applyDisaster(curr_player);
            System.out.println("\n");
        }
        else if (rand_disaster == 1) {
            Disaster ghost = new GhostZone();
            ghost.applyDisaster(curr_player);
            System.out.println("\n");
        }
        else if (rand_disaster == 2) {
            Disaster asteroid = new AsteroidField();
            asteroid.applyDisaster(curr_player);
            System.out.println("\n");
        }
    }


    /**
     * showStatus() displays the current score of the game
     * Uses player's getter and setter methods to access how many of player's ships are alive and which are sunk
     * Prints out formatted table with player 1 and player 2 ship sunk/ship alive values
     * Traverses through player's ships and displays ship healths by using ship health public variable
     * @param player1 player that is currently playing in the turn
     * @param player2 opponent player that is not currently playing the turn
     * @param ship_objects ArrayList of player's ships
     */
    public void showStatus(Player player1, Player player2, ArrayList<Ship> ship_objects) {
        int total_ships_alive1 = player1.getPlayerMaps().get(0).getShipsAlive() + player1.getPlayerMaps().get(1).getShipsAlive() + player1.getPlayerMaps().get(2).getShipsAlive();
        int total_ships_alive2 = player2.getPlayerMaps().get(0).getShipsAlive() + player2.getPlayerMaps().get(1).getShipsAlive() + player2.getPlayerMaps().get(2).getShipsAlive();
        int ultimate_total_ships1 = player1.getPlayerMaps().get(0).getExistingShips().size() + player1.getPlayerMaps().get(1).getExistingShips().size() + player1.getPlayerMaps().get(2).getExistingShips().size();
        int ultimate_total_ships2 = player2.getPlayerMaps().get(0).getExistingShips().size() + player1.getPlayerMaps().get(1).getExistingShips().size() + player1.getPlayerMaps().get(2).getExistingShips().size();
        int ship_sunk_1 = ultimate_total_ships1 - total_ships_alive1;
        int ship_sunk_2 = ultimate_total_ships2 - total_ships_alive2;

        int name_length1 = player1.getName().length();
        int name_length2 = player2.getName().length();
        String repeated_space1 = new String(new char[name_length1]).replace('\0', ' ');
        String repeated_space2 = new String(new char[name_length2]).replace('\0', ' ');

        System.out.println("|-----------Current Score---------|");
        System.out.println("                   " + player1.getName() + " | " + player2.getName());
        System.out.println("Total ships alive: " + total_ships_alive1 + repeated_space1 + "| " + total_ships_alive2 + repeated_space2);
        System.out.println("Total ships sunk:  " + ship_sunk_1 + repeated_space1 + "| " + ship_sunk_2 + repeated_space2);
        System.out.println("|---------Your Ship Healths-------|");

        for (Ship ship_object : ship_objects) {
            if (ship_object instanceof OrbitableShip) {
                System.out.println(ship_object.getName() + ": " + player1.getPlayerMaps().get(2).ship_health.get(ship_object) + "/" + ship_object.getShipSize());
            } else if (ship_object instanceof SubmersibleShip) {
                System.out.println(ship_object.getName() + ": " + player1.getPlayerMaps().get(1).ship_health.get(ship_object) + "/" + ship_object.getShipSize());
            } else {
                System.out.println(ship_object.getName() + ": " + player1.getPlayerMaps().get(0).ship_health.get(ship_object) + "/" + ship_object.getShipSize());
            }
        }
        System.out.println("\n");
    }

    /**
     * validateInt() validates any integer input user must provide
     * Checks if user input is within bounds of menu options
     * Gives user an error message if input is invalid
     * @param user_input integer input that user provided
     * @param lower_bound smallest integer value that user can input based on menu choice
     * @param upper_bound largest integer value that user can input based on menu choice
     * @return boolean whether user input is valid
     */
    public boolean validateInt(int user_input,int lower_bound, int upper_bound) {
        if (user_input >= lower_bound && user_input <= upper_bound) {
            return true;
        }
        System.out.println("Please enter a valid number between " + lower_bound + " and " + upper_bound);
        return false;
    }

    /**
     * validateString() validates any string input user must provide
     * Traverses through array of valid options, checks if user input is equal to any option
     * Gives user an error message if input is invalid
     * @param user_input String input that user provided
     * @param options array of options that a user can input
     * @return boolean whether user input is valid
     */
    public boolean validateString(String user_input, String [] options) {
        for (String s: options) {
            if (s.equals(user_input)) {
                return true;
            }
        }
        System.out.println("That is not an option! Please enter an option again.");
        return false;
    }
}
