package edu.colorado.binarybuffs;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
         //Ship ship = new Ship();
         //ship.show();
        System.out.println("Welcome to the Battleship Game! In this 2 player game, you and your opponent will each be given a fleet of 5 ships \n" +
                "that will be placed on 3 different maps - on the Ocean, Underwater, and in Space. Each player will take turns attacking \n" +
                "the opponent's board, in which some ships may be attacked, some will go missed, and others can make or break the game. \n" +
                "Additionally, each player will have a defensive and offensive grid on each map. The defensive grid shows locations of all \n" +
                "your ships, while the offensive grid shows your attempted hits and misses on the opponent. Every time a ship is attacked, its \n" +
                "health decreases. However, some ships are armored, and have a cpatain's quarters, which will take two hits to get fully attacked. \n" +
                "The first player who has lost all their ships loses the game. Sounds easy enough right? Well, turns out there's more than one twist to the game...");
        System.out.println("WEAPONS & BOOSTS--------------------------");
        System.out.println("Each player will start with 2 weapons - a bomb and a sonar pulse. A sonar pulse may only be used after you have successfully sunk \n" +
                "one of your opponents ships, for a maximum of 2 times. Additionally, when you have sunk one of your opponent's ships, your bomb \n" +
                "will be upgraded to a space laser, which can be used to penetrate through the surface and shoot underwater as well. You are given \n" +
                "a Lifesaver boost, which can be used to revive up to 2 sunken ships on your map.");
        System.out.println("DISASTERS--------------------------");
        System.out.println("There are 3 different types of disasters that can occur on your map - an Asteroid field, Ghost zone, or a Hurricane. The asteroid \n" +
                "field will only occur in space, and may hit a spaceshuttle in the area. The ghost zone may appear either on the ocean or underwater \n" +
                "where ghosts will take over your offensive grid and may scramble some of your data. The hurricane will only occur on the ocean, which \n" +
                "will toss and turn ships caught on its borders. These disasters will be randomized before every turn.");
        System.out.println("ANIMALS--------------------------");
        System.out.println("What else could this game possibly have left? Oh yeah, animals! Before every turn, you may encounter a shark or a narwhal under one of your \n" +
                "ships. A shark may bite off a part of your ship, while a narwhal will give you an extra use for your sonar pulse.");
        System.out.println("---------------------------------");
        System.out.println("Let's get this game started!");
        System.out.println("\n");

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter name for player 1:");
        String player1_name = keyboard.nextLine();
        System.out.println("Enter name for player 2: ");
        String player2_name = keyboard.nextLine();
        Game new_game = new Game(player1_name, player2_name);

        new_game.startGame();

    }
}
