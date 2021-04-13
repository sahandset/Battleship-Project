package edu.colorado.binarybuffs;
  
public class Main {

    public static void main(String[] args) {
        // write your code here
         //Ship ship = new Ship();
         //ship.show();

        Game new_game = new Game("Tanvi", "Sahand");

        new_game.turn(new_game.getPlayer1(), new_game.getPlayer2());

    }
}
