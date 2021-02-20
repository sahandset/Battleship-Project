package edu.colorado.binarybuffs;

public class Player {
    private String player_name;
    private int num_boats;
    private boolean turn;

    public Player(String name) {
        this.player_name = name;
        this.turn = false;
    }

    public String getName(Player player) {
        return this.player_name;
    }

    public boolean getTurn(Player player) {
        return this.turn;
    }

    public Ship createShip(int ship_length, int start_x, int start_y, int end_x, int end_y) {
        String ship_name = "";
        if(ship_length == 2) {
            ship_name = "Minesweeper";
            boolean validated = validateShip(ship_length, start_x, start_y, end_x, end_y);
            if(validated == false)
            {
                return null;
            }
        }

        else if(ship_length == 3) {
            ship_name = "Destroyer";
            boolean validated = validateShip(ship_length, start_x, start_y, end_x, end_y);
            if(validated == false)
            {
                return null;
            }
        }
        else if(ship_length == 4) {
            ship_name = "Battleship";
            boolean validated = validateShip(ship_length, start_x, start_y, end_x, end_y);
            if(validated == false)
            {
                return null;
            }
        }

        else {
            System.out.println("This is an invalid length");
            return null;
        }
        Ship ship1 = new Ship(ship_name, ship_length, start_x, start_y, end_x, end_y);
        return ship1;
    }

    public boolean validateShip(int ship_length, int start_x, int start_y, int end_x, int end_y)
    {
        if(start_x == end_x)
        {
            if(start_y + ship_length == end_y || start_y - ship_length == end_y )
            {
                return true;
            }
        }
        else if(start_y == end_y)
        {
            if(start_x + ship_length == end_x || start_x - ship_length == end_x )
            {
                return true;
            }
        }
        System.out.println("This is invalid");
        return false;
    }

    public void attack(int x, int y){

    }
}
