package edu.colorado.binarybuffs;

public class Game {

    private Player player1;
    private Player player2;

    public Game(String player1_name, String player2_name) {

        Player player1 = new Player(player1_name);
        Player player2 = new Player(player2_name);

    }

    public void startGame() {

        int turn_number = 1;
        boolean game_has_ended = false;

        while(!game_has_ended) {

            turn_number++;
        }
    }

    public void turn(Player current_player, Player opponent_player, String user_input) {
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
