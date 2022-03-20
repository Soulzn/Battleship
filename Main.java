package qweadsasdadasdf;
import java.util.*;

public class Main {
	public static int numRows = 10;//defining the number of rows for the map
    public static int numCols = 10;//defining the number of columns for the map
    public static int Ships; //the ships
    public static Dictionary shipInfo = new Hashtable();
    public static int Cetus; //
    public static int Kraken;
    public static String[][] grid = new String[numRows][numCols];
    public static int[][] missedGuesses = new int[numRows][numCols];
    public static int Score;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println("Right now, sea is empty\n");

        //Step 1 - Create the game map by calling for createOceanMap method
        createOceanMap();

        //Step 2 - Spawn computer's ships by calling for deployShips method
        deployShips();
        
        //Step 3 - Spawn monsters (Kraken and Cetus)
        deployMonsters();
        
        //Step 4 - Battle by calling the Battle method as long as there aren't 0 Main.Ships
        do {
            Battle();
        }while(Main.Ships != 0);
        
        //Step 5 - Score 
        Score();
        
        //Step 6 - Ending game by calling the gameOver method
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
	public static void deployShips(){
        System.out.println("\nComputer is deploying ships");
        //Deploying five ships for computer
        List<Integer> shipSizes = new ArrayList<>();
        Collections.addAll(shipSizes, 5, 4, 3, 3, 2);
        List<Integer> listContainer = new ArrayList<>();
        Main.Ships = 5;
        for (int i = 1; i <= Main.Ships; ) {
            int x = (int)(Math.random() * 10);
            int y = (int)(Math.random() * 10);
            int direction = (int)(Math.random() * 4);
            int deleteShip = (int)(Math.random() * shipSizes.size());
            int shipSize = shipSizes.get(deleteShip);
            
            shipSizes.stream()
  		    .skip(deleteShip)
  		    .forEachOrdered(listContainer::add);
            
            listContainer.stream()
  		    .forEachOrdered(shipSizes::add);
            for (int peep = 0; peep < shipSize; peep++) {
            	if (peep == 0) {
            		//haha empty code so it doesn't shit itself and move the original coordinate
            	}
            	else {
            		switch (direction) { // x and y are inverted because haha funny monkey
            		case 0:
            			x = x-1;
            		case 1:
            			y = y+1;
            		case 2:
            			x = x+1;
            		case 3:
            			y = y-1;
        			}
            	}
            	
            	if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && (grid[x][y] == " "))
                {
                    grid[x][y] =   "x";//puts an x on the grid
                    System.out.println(i + ". ship DEPLOYED");//shows the user that the ship is deployed
                    i++;//i +1 and the for loop is run again
                }
            	
            	}
            
            
        }        
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
                System.out.println(i + " Cetus has arrived");
                i++;
            }
        }
        
        //Spawning in a Kraken
        for (int i = 1; i <= Main.Kraken; ) {
            int x = (int)(Math.random() * 10);
            int y = (int)(Math.random() * 10);

            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && (grid[x][y] == " "))
            {
                grid[x][y] =   "K";//puts a K on the grid
                System.out.println(i + " Kraken has arrived");
                i++;
            }
        }
        printOceanMap();//calls to the printOceanMap method
    }
	
	public static void Score() {
		var isHit = false;
		if (Main.Ships == 5) {//1 ship sunk after cetus hit
			++Main.Score;//add 1
		}
		if (Main.Ships == 4) {//1 ship sunk
			++Main.Score;//add 1
		}
		if (Main.Ships == 3) {//2 ships sunk
			++Main.Score;//add 1
		}
		if (Main.Ships == 2) {//3...
			++Main.Score;
		}
		if (Main.Ships == 1) {//4
			++Main.Score;
		}
		if (Main.Ships == 0) {//5
			++Main.Score;
		}
		if (Main.Kraken == 0) {//Kraken is hit
			Main.Score = 0;//Kraken eats all your score
			isHit = true;
		}
		if (Main.Cetus == 0) {//Cetus is hit
			isHit = true;
			++Main.Ships;//unsinks ship
			int x = (int)(Math.random() * 10);//random number horizontally
            int y = (int)(Math.random() * 10);//random number vertically
            System.out.println(Main.Cetus);

            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && (grid[x][y] == " "))//checks if everything is correct (x and y aren't outside the boundary and there's nothing on the grid)
            {
                grid[x][y] =   "x";
                printOceanMap();
            }
		}
		if (isHit = true) {
			Main.Cetus = 1;
			Main.Kraken = 1;
		}
	}
	//Score is a work in progress
	public static void Battle(){
        playerTurn();
        printOceanMap();
        Score();

        System.out.println();
        System.out.println("Ships: " + Main.Ships);
        System.out.println("Score: " + Main.Score);
        System.out.println();
    }
	public static void playerTurn(){
        System.out.println("\nshoot");
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
                    --Main.Ships;
                }
                else if (grid[x][y] == " ") {
                    System.out.println("you missed!");
                    grid[x][y] = "M";
                    --Main.Score;
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
                    --Main.Score;
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
                    --Main.Score;
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
        System.out.println("No more ships left!");
        if(Main.Ships <= 0)//displays the number of ships left
            System.out.println("Hooray! You won the battle :)");//prints when all ships are sunk
   }
	
}