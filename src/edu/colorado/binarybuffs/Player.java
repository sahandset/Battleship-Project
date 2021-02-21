package edu.colorado.binarybuffs;

public class Player {
    private String player_name;
    private int num_boats;
    private boolean turn;
    //public array shipFleet;

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

    public void attack(int x, int y, Grid grid1, Grid grid2, Player player2){
        //first check if cell has already been attacked
        //if yes --> be like this is invalid
        //if no --> be like okur
            //okay do the attack thing
            //see if that cell is occupied
                //decrease length of ship by 1
                    //if length of ship is 0: print "sunk ship"
            //change status of current player offensive grid
            //print you hit a cell

        int status = grid1.offensive_grid[x][y];
        if (status % 2 != 0){
            if (status == 1){
                System.out.println("You've attempted attack, but there's nothing at this location");
                grid1.offensive_grid[x][y] = 2;
            }
            else if (status == 3){
                System.out.println("You've attempted attack. Congrats! You hit a ship!");
                grid1.offensive_grid[x][y] = 4;
                //access ships and change their health
            }
        }
        else {
            System.out.println("You've already attacked here");
        }
    }

    //we need to:
        //check if ship has been sunk
}
