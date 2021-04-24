package edu.colorado.binarybuffs;

public class Jaws extends Animal{

    private String name = "Jaws";

    public Jaws() {

    }

    public String getName() {return this.name;}

    /** biteShip() is where, once created and validated, the shark will bite off a cell of the player's ship
     * Creates a new Bomb object and calls bomb functionality on current player, since shark "bite" is similar action to "hit" on ship
     */
    public void biteShip(Player curr_player, Map curr_player_map) {
        Weapon jaws_bomb = new Bomb();
        jaws_bomb.deployWeapon(curr_player_map.animal_coordinates.get(this).x, curr_player_map.animal_coordinates.get(this).y, curr_player, curr_player_map, curr_player_map, curr_player, 1);

    }

    /** useAnimal() is a function to be called within the Map class, which in turn calls the biteShip function */
    public void useAnimal(Player curr_player, Map curr_player_map) {
        biteShip(curr_player, curr_player_map);
    }

}
