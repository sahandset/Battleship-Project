package edu.colorado.binarybuffs;

public class SpaceLaser extends Weapon {

    private int num_uses;
    private String name = "Space Laser";

    public SpaceLaser() {

    }

    public String getName() {
        return this.name;
    }

    public boolean deployWeapon(int x, int y, newPlayer opponent, Map attacked_map, Map current_player_map) {
        return true;
    }

    public boolean checkAvailability(int num_used) {
        if (num_used == this.num_uses) {
            return false;
        }
        return true;
    }
}
