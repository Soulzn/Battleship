import java.util.*;

public class Main {
	public static int numRows = 10;//defining the number of rows for the map
    public static int numCols = 10;//defining the number of columns for the map
    public static int computerShips;//the number of ships that will be used
    public static String[][] grid = new String[numRows][numCols];
    public static int[][] missedGuesses = new int[numRows][numCols];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println("Right now, sea is empty\n");

        //Step 1 – Create the game map
        createOceanMap();

        //Step 2 - Spawn computer's ships
        deployComputerShips();

        //Step 3 - Battle
        do {
            Battle();
        }while(Main.computerShips != 0);

        //Step 4 - Game Over
        gameOver();

	}
	
	public static void createOceanMap(){
        //First section of Ocean Map
        System.out.print("  ");
        for(int i = 0; i < numCols; i++)
                System.out.print(i);
        System.out.println();

        //Middle section of Ocean Map
        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = " ";
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

            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && (grid[x][y] == " "))
            {
                grid[x][y] =   "x";
                System.out.println(i + ". ship DEPLOYED");
                i++;
            }
        }
        printOceanMap();
    }
	public static void Battle(){
        playerTurn();
        printOceanMap();

        System.out.println();
        System.out.println("Computer ships: " + Main.computerShips);
        System.out.println();
    }
	public static void playerTurn(){
        System.out.println("\nshoot again");
        int x = -1, y = -1;
        do {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter Y coordinate: ");
            x = input.nextInt();
            System.out.print("Enter X coordinate: ");
            y = input.nextInt();

            if ((x >= 0 && x < numRows) && (y >= 0 && y < numCols)) //valid guess
            {
                if (grid[x][y] == "x") //if computer ship is already there; it sinks
                {
                    System.out.println("My ship was hit!");
                    grid[x][y] = "!"; //Hit mark
                    --Main.computerShips;
                }
                else if (grid[x][y] == " ") {
                    System.out.println("you missed!");
                    grid[x][y] = "-";
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
            System.out.print(x + "|");

            for (int y = 0; y < grid[x].length; y++){
                System.out.print(grid[x][y]);
            }

            System.out.println("|" + x);
        }

        //Last section of Ocean Map
        System.out.print("  ");
        for(int i = 0; i < numCols; i++)
            System.out.print(i);
        System.out.println();
    }
	public static void gameOver(){
        System.out.println( "Computer ships: " + Main.computerShips);
        if(Main.computerShips <= 0)
            System.out.println("Hooray! You won the battle :)");
        else
            System.out.println("Sorry, you lost the battle");
        System.out.println();
   }
	
}
