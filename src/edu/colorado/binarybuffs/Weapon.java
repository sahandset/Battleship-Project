package edu.colorado.binarybuffs;

public abstract class Weapon {
    public int num_uses;
    private String name;

    /**
     * Constructor for creating Bomb object
,      */
    public Weapon(){}

    /**
     * Retrieves a private variable
     * @return String class variable name
     */
    public String getName(){
        return this.name;
    }

    /**
     * The below function is implemented in each weapon subclass
     * @param x the x-coordinate where the weapon is being deployed
     * @param y the y-coordinate where the weapon is being deployed
     * @param opponent the player being "attacked" on
     * @param attacked_map the map of player being "attacked"
     * @param current_player_map the map of player using weapon
     * @param current_player the player attacking
     * @param method_choice the method of use of deployWeapon (bomb, asteroids, etc.)
     * @return boolean returns success of deployment
     */
    public abstract boolean deployWeapon(int x, int y, Player opponent, Map attacked_map, Map current_player_map, Player current_player, int method_choice);

    /**
     * The below function is implemented in each weapon subclass
     * It checks the availability of the weapon
     * @param num_used an int value of how many times the weapon has been used
     * @return boolean returns availability of weapon
     */
    public abstract boolean checkAvailability(int num_used);

}

