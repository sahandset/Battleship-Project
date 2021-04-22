package edu.colorado.binarybuffs;
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

        int turn_number = 1;
        boolean game_has_ended = false;


        while(!game_has_ended) {

            turn_number++;
        }
    }

    public void turn(newPlayer current_player, newPlayer opponent_player) { //return bool?
        displayStartingMenu(current_player);
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
                turn(current_player, opponent_player);
                break;
            case 2: //show score
                // Show ships alive
                // Show ships sunk
                turn(current_player, opponent_player);
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
                turn(current_player, opponent_player);
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
                    current_player.setSurrenderStatus();
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

    public void displayStartingMenu(newPlayer curr_player) {

        newShip sweeper = new Minesweeper();
        newShip dest = new Destroyer();
        newShip bat = new Minesweeper();
        newShip sub = new Submarine();
        newShip shut = new Spaceshuttle();
        int map_choice = 0;
        String ship_names[] = {"Minesweeper", "Destroyer", "Battleship", "Submarine", "Spaceshuttle"};
        newShip ship_objects[] = {sweeper, dest, bat, sub, shut};

        System.out.println("Let's get this game started! How would you like to create your ship fleet?");
        System.out.println("1. Create a random fleet");
        System.out.println("2. Manually place fleet");

        Scanner input = new Scanner(System.in);
        int user_choice = input.nextInt();

        switch (user_choice) {
            case 1: //create random fleet
                map_choice = displayMapMenu(curr_player);
//                Fleet player_fleet = new Fleet(ship_names);
                //call placeFleet
                break;
            case 2: //manually place fleet

                System.out.println("The following ships will be deployed based on a starting coordinate that faces either N, S, E, or W. ");

                for (int i = 0; i < ship_names.length; i++) {
                    for (int j = 0; j < ship_objects.length; j++) {
                        if (i >= 0 && i <= 2) { //ships that are only placed on ocean
                            map_choice = 0;
                        }
                        else if (i == 4) {
                            map_choice = 2;
                        }
                        else if (i == 3) {
                            System.out.println("Which map do you want to deploy the submarine? Note: if there is a ship existing there on the ocean, your sub will automatically be placed underwater");
                            System.out.println("1. Ocean");
                            System.out.println("2. Underwater");
                            map_choice = input.nextInt();
                        }
                        System.out.println("Place your " + i);
                        System.out.print("X: ");
                        int coord_choice_x = input.nextInt();
                        System.out.print("Y: ");
                        int coord_choice_y = input.nextInt();
                        System.out.print("Ship direction: ");
                        String direction_choice = input.next();
                        curr_player.deployShip(ship_objects[j], coord_choice_x, coord_choice_y, direction_choice, map_choice);
                    }
                }
                break;
            }
            System.out.println("You have successfully placed all your ships! View your ships on all 3 maps below:");
            curr_player.player_maps.get(0).printOffensiveGrid();
            curr_player.player_maps.get(1).printOffensiveGrid();
            curr_player.player_maps.get(2).printOffensiveGrid();
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

    public void getStatus() {

    }

    public void attack(int x, int y) {

    }

    public void moveFleet() {

    }


}
