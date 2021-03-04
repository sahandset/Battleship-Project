package edu.colorado.binarybuffs;

public class Weapon {
    public int num_uses;
    public int possible_uses;

    public Weapon(){

    }

    public boolean checkAvailability(Player opponent_player){
        if (possible_uses >= 0)  {
            if (opponent_player.getNumBoats(opponent_player) < 3){
                return true;
            }
        }
        return false;
    }
}

