package edu.colorado.binarybuffs;

public class Narwhal extends Animal{

    private String name = "Narwhal";

    //Constructor for Narwhal Class
    public Narwhal() {

    }

    //Gets the name of the current narwhal
    public String getName() {return this.name;}

     /**
     * grantUses() is where, once created and validated, the narwhal will increment the player's sonar pulse uses by 1
     * Checks if the user has reached the point of acquiring a sonar pulse
     * Accesses the player's current number of uses and decrements that by 1
     * Removes the narwhal after it has granted its uses
     * @param curr_player object of type Player class which is the current player on the turn
     * @param curr_player_map object of type Map class which is the current player's map where functionality occurs
     */
    public void grantUses(Player curr_player, Map curr_player_map) {
        if (curr_player.player_weapons.size() == 2) {
            Weapon sonar_pulse = curr_player.player_weapons.get(1);
            int current_uses = curr_player.weapon_uses.get(sonar_pulse);
            curr_player.weapon_uses.replace(sonar_pulse, current_uses - 1);
            curr_player_map.animal_coordinates.remove(this);
            curr_player_map.animals.remove(this);
            System.out.println("There is a magic narwhal under your ship! You have been granted an extra use for sonar pulse!");
        }
        else {
            System.out.println("There is a magic narwhal under your ship!");
            System.out.println("Narwhal tried to give you more sonar pulse uses, but you don't have a sonar pulse\n");
        }

    }


     /**
     * useAnimal() is a function to be called within the Map class, which in turn calls the grantUses function
     * @param curr_player object of type Player class which is the current player on the turn
     * @param curr_player_map object of type Map class which is the current player's map where functionality occurs
     */
    public void useAnimal(Player curr_player, Map curr_player_map) {
        grantUses(curr_player, curr_player_map);
    }

}
