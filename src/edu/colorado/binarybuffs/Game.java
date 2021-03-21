package edu.colorado.binarybuffs;

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

    public void turn(newPlayer current_player, newPlayer opponent_player, String user_input) {
        showMenu();
        String attack = "a";
        String move_fleet = "m";

        // user input

        if (user_input == "a") {

        }

        else if (user_input == "m") {

        }

    }

    public void showMenu() {

    }

    public void getStatus() {

    }

    public void attack(int x, int y) {

    }

    public void moveFleet() {

    }
}
