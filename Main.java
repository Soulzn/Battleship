import java.util.*;

public class Main {
	public static int numRows = 10;//defining the number of rows for the map
    public static int numCols = 10;//defining the number of columns for the map
    public static int computerShips;//the number of ships that will be used
    public static int Cetus;
    public static int Kraken;
    public static String[][] grid = new String[numRows][numCols];
    public static int[][] missedGuesses = new int[numRows][numCols];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println("Right now, sea is empty\n");

        //Step 1 – Create the game map by calling for createOceanMap method
        createOceanMap();

        //Step 2 - Spawn computer's ships by calling for deployComputerShips method
        deployComputerShips();
        
        //Step 3 - Spawn monsters (Kraken and Cetus)
        deployMonsters();

        //Step 4 - Battle by calling the Battle method as long as there aren't 0 Main.computerShips
        do {
            Battle();
        }while(Main.computerShips != 0);

        //Step 5 - Ending game by calling the gameOver method
        gameOver();

	}
	
	public static void createOceanMap(){
        //First section of Ocean Map
        System.out.print("  ");
        for(int i = 0; i < numCols; i++)//limits numbers to 0 to 9
                System.out.print(i);
        System.out.println();

        //Middle section of Ocean Map
        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = " ";//creation of the x and y grid that starts from 0 all the way to 9
                if (j == 0)
                    System.out.print(i + "|" + grid[i][j]);
                else if (j == grid[i].length - 1)
                    System.out.print(grid[i][j] + "|" + i);
                else
                    System.out.print(grid[i][j]);
            }
            System.out.println();
        }

        //Last section of Ocean Map
        System.out.print("  ");
        for(int i = 0; i < numCols; i++)
            System.out.print(i);
        System.out.println();
    }
	public static void deployComputerShips(){
        System.out.println("\nComputer is deploying ships");
        //Deploying five ships for computer
        Main.computerShips = 5;
        for (int i = 1; i <= Main.computerShips; ) {
            int x = (int)(Math.random() * 10);
            int y = (int)(Math.random() * 10);

            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && (grid[x][y] == " "))//checks if everything is correct (x and y aren't outside the boundary and there's nothing on the grid)
            {
                grid[x][y] =   "x";//puts an x on the grid
                System.out.println(i + ". ship DEPLOYED");//shows the user that the ship is deployed
                i++;//i +1 and the for loop is run again
            }
        }
        printOceanMap();//calls to the printOceanMap method on line 146
    }
	public static void deployMonsters(){
        System.out.println("\nMonsters creep into existence");
        //determining number of Krakens and Cetuses
        Main.Cetus = 1;
        Main.Kraken= 1;
        //Spawning in a Cetus
        for (int i = 1; i <= Main.Cetus; ) {
            int x = (int)(Math.random() * 10);
            int y = (int)(Math.random() * 10);

            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && (grid[x][y] == " "))
            {
                grid[x][y] =   "C";//puts an M on the grid
                System.out.println(i + ". Cetus has arrived");
                i++;
            }
        }
        printOceanMap();
        //Spawning in a Kraken
        for (int i = 1; i <= Main.Kraken; ) {
            int x = (int)(Math.random() * 10);
            int y = (int)(Math.random() * 10);

            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && (grid[x][y] == " "))
            {
                grid[x][y] =   "K";//puts a K on the grid
                System.out.println(i + ". Kraken has arrived");
                i++;
            }
        }
        printOceanMap();
    }
	public static void Battle(){
        playerTurn();
        printOceanMap();

        System.out.println();
        System.out.println("Ships: " + Main.computerShips);
        System.out.println("Kraken: " + Main.Kraken + "\nCetus: " + Main.Cetus);
        System.out.println();
    }
	public static void playerTurn(){
        System.out.println("\nshoot again");
        int x = -1, y = -1;
        do {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter X coordinate: ");
            y = input.nextInt();
            System.out.print("Enter Y coordinate: ");
            x = input.nextInt();

            if ((x >= 0 && x < numRows) && (y >= 0 && y < numCols)) //valid guess
            {
                if (grid[x][y] == "x") //if computer ship is already there; it sinks
                {
                    System.out.println("My ship was hit!");
                    grid[x][y] = "H"; //Hit mark
                    --Main.computerShips;
                }
                else if (grid[x][y] == " ") {
                    System.out.println("you missed!");
                    grid[x][y] = "M";
                }
                if (grid[x][y] == "C") //if computer ship is already there; it sinks
                {
                    System.out.println("You hit a monster!");
                    grid[x][y] = "H"; //Hit mark
                    --Main.Cetus;
                }
                else if (grid[x][y] == " ") {
                    System.out.println("you missed!");
                    grid[x][y] = "M";
                }
                if (grid[x][y] == "K") //if computer ship is already there; it sinks
                {
                    System.out.println("You hit a monster!");
                    grid[x][y] = "H"; //Hit mark
                    --Main.Kraken;
                }
                else if (grid[x][y] == " ") {
                    System.out.println("you missed!");
                    grid[x][y] = "M";
                }
            }
            else if ((x < 0 || x >= numRows) || (y < 0 || y >= numCols))  //invalid guess
                System.out.println("You can't place ships outside the " + numRows + " by " + numCols + " grid");
        }while((x < 0 || x >= numRows) || (y < 0 || y >= numCols));  //keep re-prompting till valid guess
    }
	public static void printOceanMap(){
        System.out.println();
        //First section of Ocean Map
        System.out.print("  ");
        for(int i = 0; i < numCols; i++)
            System.out.print(i);
        System.out.println();

        //Middle section of Ocean Map
        for(int x = 0; x < grid.length; x++) {
            System.out.print(x + "|");//sets up numbers on the vertical side of the grid

            for (int y = 0; y < grid[x].length; y++){//does the same thing but horizontally
                System.out.print(grid[x][y]);
            }

            System.out.println("|" + x);//sets up numbers on the vertical side on the other side
        }

        //Last section of Ocean Map
        System.out.print("  ");
        for(int i = 0; i < numCols; i++)
            System.out.print(i);
        System.out.println();
    }
	public static void gameOver(){
        System.out.println( "Ships: " + Main.computerShips);
        if(Main.computerShips <= 0)//displays the number of ships left
            System.out.println("Hooray! You won the battle :)");//prints when all ships are sunk
   }
	
}
