/** BATTLESHIP!!!
 * How to Play:
 * 	The object of the game is to sink all the ships
 *  first player to sink all the ships wins!
 *  Optional: keep track of how many tries it takes 
 *  to find all the ships.*/

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Battleship extends Ships  {
	
	// Constructor; same as Ships class
	public Battleship(String name, int size, String initials, int eposx, int eposy, int fposx, int fposy) {
		super(name, size, initials, eposx, eposy, fposx, fposy);
	}
	
	// Fields for Player 1
	private static ArrayList<Ships> ships = new ArrayList<Ships>();
	private static int rows = 10;
	private static int columns = 10;
	private static int ship_count;
	private static int position;
	private static int remaining_ships;
	private static String gameboard[][] = new String[rows][columns];
	
	// Fields for Player 2
	private static ArrayList<Ships> ships2 = new ArrayList<Ships>();
	private static int rows2 = 10;
	private static int columns2 = 10;
	private static int position2;
	private static int remaining_ships2;
	private static String gameboard2[][] = new String[rows][columns];
	
	private static String Player1;
	private static String Player2;
	
	// Creates/resets gameboard for Player 1
	public static void ResetBoard() {
		for(int i = 0; i < rows; i++) {
			System.out.println();
			for(int j = 0; j < columns; j++) {
				gameboard[i][j] = " O";
				
			}
		}
	}
	
	// Creates/resets gameboard for Player 2
	public static void ResetBoard2() {
		for(int i = 0; i < rows2; i++) {
			System.out.println();
			for(int j = 0; j < columns2; j++) {
				gameboard2[i][j] = " O";
			}
		}
	}
	
	// Displays Player 1's gameboard; Use this method throughout program
	public static void DisplayBoard() {
		System.out.println(" 1 2 3 4 5 6 7 8 9 10 ");
		
		for(int i = 0; i < rows; i++) {
			System.out.println();
			
			for(int j = 0; j < columns; j++) {
				
				System.out.print(gameboard[i][j]);
				
			}
		}	
		System.out.println();
		System.out.println();
	}
	
	// Displays Player 1's gameboard; Use this method throughout program
	public static void DisplayBoard2() {
		System.out.println(" 1 2 3 4 5 6 7 8 9 10 ");
		for(int i = 0; i < rows2; i++) {
			System.out.println();
			for(int j = 0; j < columns2; j++) {
				System.out.print(gameboard2[i][j]);
			}
		}	
		System.out.println();
		System.out.println();
	}
	
/*==========================================================================================================================================
 * =======================================================SET UP PROCESS STARTS HERE========================================================
 * ========================================================================================================================================= */
	
	/** SetUp() Function: Place ships on the game board 
	 *  Minor bug: if there's an invalid input/placement of ships anywhere, ALL ships have to be placed again.
	 * 	
	 * 	First, it creates and adds all the ships to the ships ArrayList
	 *  Scanners are made to ask for positioning of ships as determined by user
	 *  
	 *  Start placing each ship on gameboard via for loop
	 *  	User enters coordinates to define where the FRONT of the ship is going to be
	 *  	These coordinates are copied to row_end and column_end variables to help define the BACK of the ship in the nested for loop
	 *  	Ask for user input again, this time whether to place ships horizontally to the right or vertically down
	 *  	Ships are created/placed via for loop that loops the same amount of times as the ship's size.
	 *  		Initials parameter in constructor defines where each ship is.
	 *  		Coordinates of the END of the ship are saved during this loop.
	 * 		
	 * The purpose of saving the coordinates is to remember where each of the ships are when the board is reset
	 * 
	 * 	After all the ships have been placed, the board is reset and Play() starts.
	 *  */
	
	public static void SetUp() {
		ResetBoard();
		ships.clear();
		DisplayBoard();
			
		// Create ships
		Ships Aircraft_Carrier = new Ships("Aircraft Carrier", 5, " A", 0, 0, 0, 0);
		Ships Battleship = new Ships("Battleship", 4, " B", 0, 0, 0, 0);
		Ships Submarine = new Ships("Submarine", 3, " S", 0, 0, 0, 0);
		Ships Destroyer = new Ships("Destroyer", 3, " D", 0, 0, 0, 0);
		Ships Patrol_Boat = new Ships("Patrol Boat", 2, " P", 0, 0, 0, 0);
		
		// Add ships to Ships ArrayList
		ships.add(Aircraft_Carrier);
		ships.add(Battleship);
		ships.add(Submarine);
		ships.add(Destroyer);
		ships.add(Patrol_Boat);
		
		Scanner scan = new Scanner(System.in); 		// Scanner for Row Number
		Scanner scan2 = new Scanner(System.in);		// Scanner for Column Number
		Scanner scan3 = new Scanner(System.in);		// Scanner to place ship horizontally right or vertically down
		
		// Loop through every ship in ships ArrayList
		outerloop:
		for(ship_count = 0; ship_count < ships.size(); ship_count++) {
			int row_end = 0;
			int column_end = 0;
			
			// First, choose coordinates to place the STERN OF THE SHIP (not middle/anywhere else)
			System.out.println(Player1 + ", place your " + ships.get(ship_count).getName());
			System.out.println("Size: " + ships.get(ship_count).getSize());
			System.out.println("Ships on board: " + ship_count);
			System.out.println();
			
			// Select Row Number
			System.out.println("Select Row Number (1 through 10): ");
			String s = scan.nextLine();
			int row_front = Integer.parseInt(s);
			row_front--;
			
			// Select Column Number
			System.out.println("Select Column Number (1 through 10): ");
			String s2 = scan.nextLine();
			int column_front = Integer.parseInt(s2);
			column_front--;
			
			ships.get(ship_count).setXCoordinateBow(row_front);		// Save BOW coordinates of ship.
			ships.get(ship_count).setYCoordinateBow(column_front);	// Save BOW coordinates of ship.
			
			row_end = row_front;						
			column_end = column_front;
			
			// Out of bounds exception (TODO: (Bug) maybe figure out a way to not start the whole thing over?)
			if((row_front > gameboard.length) || (column_front > gameboard.length)) {
				System.out.println("Out of bounds!");
				SetUp();
			}
			
			else {
				
				DisplayBoard();
				
				// Place it Horizontally/Vertically? 
				System.out.println("Select direction of ship (Enter 1 for horizontally to the right "
						+ "or 2 for vertically downwards)");
				String s3 = scan3.nextLine();
				position = Integer.parseInt(s3);
				
				//Place ship horizontally to the right
				if(position == 1) {
					
					// Out of bounds exception
					if(column_end + ships.get(ship_count).getSize() > gameboard.length) {
						System.out.println("Out of bounds! Start over!");
						ship_count = 0;
						System.out.println("Ship count is now: " + ship_count);
						SetUp();
					}
					
					// Place initials of ship on gameboard horizontally
					for(int i = 0; i <= ships.get(ship_count).getSize() - 1; i++) {
						
						// Prevents overlapping ships
						if(gameboard[row_end][column_end + i] != " O") {
							System.out.println("There's already a ship there!");
							SetUp();
						}
						
						gameboard[row_end][column_end + i] = ships.get(ship_count).getInitials();	
						
						ships.get(ship_count).setXCoordinateStern(row_end);					// Save STERN coordinates of ship
						ships.get(ship_count).setYCoordinateStern(column_end + i);			// This is important when trying to find them later
								
					}
					
					DisplayBoard();
					
				}
				
				// Place ship vertically downwards
				else if(position == 2) {
					System.out.println("You chose vertically!");
					
					// Out of bounds exception
					if(row_end + ships.get(ship_count).getSize() > gameboard.length) {
						System.out.println("Out of bounds! Start over!");
						ship_count = 0;
						System.out.println("Ship count is now: " + ship_count);
						SetUp();
					}
					
					// Place initials of ship on gameboard vertically downwards
					for(int i = 0; i <= ships.get(ship_count).getSize() - 1; i++) {
						
						// Prevents overlapping ships.
						if(gameboard[row_end + i][column_end] != " O") {
							System.out.println("There's already a ship there!");
							SetUp();
						}
						
						gameboard[row_end + i][column_end] = ships.get(ship_count).getInitials();
						
						ships.get(ship_count).setXCoordinateStern(row_end + i);			// Save STERN coordinates of ship
						ships.get(ship_count).setYCoordinateStern(column_end);			// This is important when trying to find them later
					}
					DisplayBoard();
				}
				
				else {
					System.out.println("Invalid option!");
					SetUp();
				}
			}
		}
		
		System.out.println("Time to set up the other board");
		
		ResetBoard();
		SetUp2();
	}
	
	/** Literally the exact same thing as SetUp(), except all variables have a 2 in front of them. */
	
	public static void SetUp2() {
		ResetBoard2();
		ships2.clear();
		
		System.out.println(Player2 + ", place your ships!");
		
		// Create gamepieces and add them to ships ArrayList
		Ships Aircraft_Carrier = new Ships("Aircraft Carrier", 5, " A", 0, 0, 0, 0);
		Ships Battleship = new Ships("Battleship", 4, " B", 0, 0, 0, 0);
		Ships Submarine = new Ships("Submarine", 3, " S", 0, 0, 0, 0);
		Ships Destroyer = new Ships("Destroyer", 3, " D", 0, 0, 0, 0);
		Ships Patrol_Boat = new Ships("Patrol Boat", 2, " P", 0, 0, 0, 0);
		
		ships2.add(Aircraft_Carrier);
		ships2.add(Battleship);
		ships2.add(Submarine);
		ships2.add(Destroyer);
		ships2.add(Patrol_Boat);
		
		Scanner scan = new Scanner(System.in); 		// Scanner for x coordinate
		Scanner scan2 = new Scanner(System.in);		// Scanner for y coordinate
		Scanner scan3 = new Scanner(System.in);		// Scanner to place ship horizontally or vertically
		
		// Loop through every ship in ships ArrayList
		for(ship_count = 0; ship_count < ships.size(); ship_count++) {
			
			int row_end = 0;
			int column_end = 0;
			
			// First, choose coordinates to place the END OF THE SHIP (not middle/anywhere else)
			System.out.println(Player2 + ", place your " + ships2.get(ship_count).getName());
			System.out.println("Size: " + ships2.get(ship_count).getSize());
			System.out.println();
			
			// X coordinate input
			System.out.println("Select Row Number (1 through 10): ");
			String s = scan.nextLine();
			int row_front = Integer.parseInt(s);
			row_front--;
			
			// Y coordinate input
			System.out.println("Select Column Number (1 through 10): ");
			String s2 = scan.nextLine();
			int column_front = Integer.parseInt(s2);
			column_front--;
			
			ships2.get(ship_count).setXCoordinateBow(row_front);	// Save FRONT coordinates of ship.
			ships2.get(ship_count).setYCoordinateBow(column_front);	// Save FRONT coordinates of ship.
			
			row_end = row_front;						
			column_end = column_front;
			
			int count = ships2.get(ship_count).getSize();
			System.out.println("Count: " + count);
			
			// Out of bounds exception (Bug: maybe figure out a way to not start the whole thing over?)
			if((row_front > gameboard2.length) || (column_front > gameboard2.length)) {
				System.out.println("Out of bounds!");
				SetUp();
			}
			
			else {
				
				DisplayBoard2();
				
				// Place it Horizontally/Verically? 
				System.out.println("Horizontally or Vertically? (Enter 1 for Horizontally or 2 for Vertically)");
				String s3 = scan3.nextLine();
				position2 = Integer.parseInt(s3);
				
				//Place ship horizontally to the right
				if(position2 == 1) {
					
					if(column_end + ships2.get(ship_count).getSize() >= gameboard2.length) {
						System.out.println("Out of bounds! Start over!");
						ship_count = 0;
						System.out.println("Ship count is now: " + ship_count);
						SetUp2();
					}
					
					// Place initials of ship on gameboard horizontally
					for(int i = 0; i <= ships2.get(ship_count).getSize() - 1; i++) {
						
						
						// Prevents overlapping ships
						if(gameboard2[row_end][column_end + i] != " O") {
							System.out.println("There's already a ship there! Start Over!");
							SetUp2();
						}
						
						gameboard2[row_end][column_end + i] = ships2.get(ship_count).getInitials();	
						
						ships2.get(ship_count).setXCoordinateStern(row_end);					// Save END coordinates of ship
						ships2.get(ship_count).setYCoordinateStern(column_end + i);				// This is important when trying to find them later
								
					}
					
					DisplayBoard2();
					
				}
				
				// Place ship vertically downwards
				else if(position2 == 2) {
					System.out.println("You chose vertically!");
					
					// Out of bounds exception
					if(row_end + ships.get(ship_count).getSize() >= gameboard.length) {
						System.out.println("Out of bounds! Start over!");
						ship_count = 0;
						System.out.println("Ship count is now: " + ship_count);
						SetUp2();
					}
					
					// Place initials of ship on gameboard vertically downwards
					for(int i = 0; i <= ships2.get(ship_count).getSize() - 1; i++) {
						
						// Prevents overlapping ships.
						if(gameboard2[row_end + i][column_end] != " O") {
							System.out.println("There's already a ship there! Start over!");
							SetUp2();
						}
						
						gameboard2[row_end + i][column_end] = ships2.get(ship_count).getInitials();
						
						ships2.get(ship_count).setXCoordinateStern(row_end + i);			// Save END coordinates of ship
						ships2.get(ship_count).setYCoordinateStern(column_end);			// This is important when trying to find them later
					}
					DisplayBoard2();
				}
				
				else {
					System.out.println("Invalid option!");
					SetUp2();
				}
			}
		}
		
		System.out.println("Time to play the game!!");
		
		DisplayBoard2();
		ResetBoard2();
		
		// Decide who goes first via coin flip
		Random coin_flip = new Random();
		int num = coin_flip.nextInt(2);
		
		if(num == 0) {
			System.out.println(Player1 + " will go first!");
			Play();
		}
		
		if(num == 1) {
			System.out.println(Player2 + " will go first!");
			Play2();
		}
	}
	
/*========================================================END SET UP FOR PLAYERS=======================================================================
 * ==================================================================================================================================================== 
 * =========================================================GAME STARTS HERE===========================================================================*/	
	
	
	/** Play() Function: Play the actual game.
	 *  BONUS: add a 'tries' variable to see how many spots on gameboard you hit at the end.
	 *  
	 *  Keeps looping while there are ships in the ArrayList to be sunk.
	 *  
	 *  First, asks user for coordinates to hit
	 *  Nested loop searches x axis first, then y axis.
	 *  
	 *  So basically,
	 *  	if(x-input - bow x coordinate == 0 [for if the ship is placed horizontally] [bow x coordinate == stern x coordinate for horizontal ships]
	 *  	OR
	 *  	(x-input >= bow x coordinate AND x-input < stern x coordinate [for if the ship is placed vertically)
	 *  		then you guessed the right x coordinate
	 *  
	 *  		if(y-input - bow y coordinate == 0 [for if the ship is placed vertically]
	 *  		OR
	 *  		if(y-input >= bow y coordinate AND y-input <= stern y coordinate [for if the ship is placed horizontally] [bow y coordinate == stern y coordinate for vertical ships]
	 *  			You hit a ship!
	 *  			Initials of ship will be displayed on gameboard
	 *  			Size of ship will be decremented until it hits 0, where it will be DELETED from the ships ArrayList
	 *  			run Play() again to ask for new coordinates
	 *  	
	 *  	If none of those conditions are met, then it's a miss, and Player 2's turn starts (AKA Play2() runs)
	 *  	
	 *   */
	
	// Play method for player 1; uses player 2's board
	
	public static void Play() {
		
		System.out.println("It's " + Player1 + "'s turn!");
		
		try {
			// Keep track of how many ships are left throughout
			remaining_ships2 = ships2.size();
			System.out.println("Remaining ships: " + remaining_ships2 + "\n");

			RemainingShips2();

			// Keep playing while there are still ships to be sunk.
			while(remaining_ships2 != 0) {
				int count = 0;

				DisplayBoard2();

				System.out.println("Enter coordinates to see if a ship is there or not");

				// Ask for x coordinate
				System.out.println("Enter Row Number (1 through 10)");
				Scanner s = new Scanner(System.in);
				String x_input = s.nextLine();
				int x = Integer.parseInt(x_input);
				x--;

				// Ask for y coordinate
				System.out.println("Enter Column Number (1 through 10)");
				Scanner s2 = new Scanner(System.in);
				String y_input = s.nextLine();
				int y = Integer.parseInt(y_input);
				y--;

				if(gameboard2[x][y] != " O") {
					System.out.println("You tried those coordinates already! Try again");
					Play();
				}

				// Loop through every ship in ships2 ArrayList to check if you hit one of them or not
				// Checks ships2 ArrayList (because Player 1 is aiming for Player 2's ships)
				// Searches row first, then column
				while(count < ships2.size()) {

					if(x - ships2.get(count).getXCoordinateBow() == 0 || (x >= ships2.get(count).getXCoordinateBow() && x <= ships2.get(count).getXCoordinateStern())) {

						if(y - ships2.get(count).getYCoordinateBow() == 0 || (y >= ships2.get(count).getYCoordinateBow() && y <= ships2.get(count).getYCoordinaiteStern())) {

							System.out.println("Hit! You hit the " + ships2.get(count).getName() + " Go again!");
							ships2.get(count).setSize(ships2.get(count).getSize() - 1);														// Decrement size of ship (because you guessed the right coordinates)
							System.out.println("Remaining parts of " + ships2.get(count).getName() + ": " + ships2.get(count).getSize());

							gameboard2[x][y] = ships2.get(count).getInitials();

							// Notification for if you sunk a ship
							if(ships2.get(count).getSize() == 0) {
								System.out.println("You sunk the " + ships2.get(count).getName());
								remaining_ships2--;
								ships2.remove(ships2.get(count));
								
								if(remaining_ships2 == 0) {
									DisplayResults();
								}

								Play();
							}

							/*Run Play again so that it asks for new x and y inputs
							 * and resets count so that it can search through ships ArrayList
							 * again. */
							Play();
						}
						else {
							System.out.println("Miss!");
							gameboard2[x][y] = " X";
						}
					}

					else {
						System.out.println("Miss!");
						gameboard2[x][y] = " X";
					}
					count++;
				}

				/*If program gets here, all the ships have been sunk */
				if(remaining_ships2 == 0) {
					DisplayResults();
				}
				
				else {
					Play2();
				}
			}
		}
		catch(Exception e) {
			System.out.println("Wrong input! Try again.");
		}
	}

	/** Similar to Play(), except variables have a 2 in front of them. */
	public static void Play2() {
		
		System.out.println("It's " + Player2 + "'s turn!");
		try {
			// Keep track of how many ships are left throughout
			remaining_ships = ships.size();
			System.out.println("Remaining ships: " + remaining_ships + "\n");

			RemainingShips();

			// Keep playing while there are still ships to be sunk.
			while(remaining_ships != 0) {
				int count = 0;

				DisplayBoard();

				System.out.println("Enter coordinates to see if a ship is there or not");

				// Ask for x coordinate
				System.out.println("Enter Row Number (1 through 10)");
				Scanner s = new Scanner(System.in);
				String x_input = s.nextLine();
				int x = Integer.parseInt(x_input);
				x--;

				// Ask for y coordinate
				System.out.println("Enter Colummn Number (1 through 10)");
				Scanner s2 = new Scanner(System.in);
				String y_input = s.nextLine();
				int y = Integer.parseInt(y_input);
				y--;

				if(gameboard[x][y] != " O") {
					System.out.println("You tried those coordinates already! Try again");
					Play2();
				}

				// Loop through every ship in ships ArrayList to check if you hit one of them or not
				// Checks ships ArrayList (because Player 2 is aiming for Player 1's ships)
				// Searches row first, then column
				while(count < ships.size()) {

					if(x - ships.get(count).getXCoordinateBow() == 0 || (x >= ships.get(count).getXCoordinateBow() && x <= ships.get(count).getXCoordinateStern())) {

						if(y - ships.get(count).getYCoordinateBow() == 0 || (y >= ships.get(count).getYCoordinateBow() && y <= ships.get(count).getYCoordinaiteStern())) {

							System.out.println("Hit! You hit the " + ships.get(count).getName() + " Go again!");
							ships.get(count).setSize(ships.get(count).getSize() - 1);	// Decrement size of ship (because you guessed the right coordinates)
							System.out.println("Remaining parts of " + ships.get(count).getName() + ": " + ships.get(count).getSize());

							gameboard[x][y] = ships.get(count).getInitials();

							// Notification for if you sunk a ship
							if(ships.get(count).getSize() == 0) {
								System.out.println("You sunk the " + ships.get(count).getName());
								remaining_ships--;
								ships.remove(ships.get(count));
								
								if(remaining_ships == 0) {
									DisplayResults();
								}

								Play2();
							}

							/*Run Play again so that it asks for new x and y inputs
							 * and resets count so that it can search through ships ArrayList
							 * again. */
							Play2();
						}
						else {
							System.out.println("Miss!");
							gameboard[x][y] = " X";
						}
					}

					else {
						System.out.println("Miss!");
						gameboard[x][y] = " X";
					}
					count++;
				}

				/*If program gets here, all the ships have been sunk */
				if(remaining_ships == 0) {
					DisplayResults();
				}
				
				else {
					Play();
				}
			}
		}
		catch(Exception e) {
			System.out.println("Wrong input! Try again.");
		}
	}

	// Prints Player 1's remaining ships
	public static void RemainingShips() {
		System.out.println("Remaining Ships: ");
		
		for(int i = 0; i < ships.size(); i++) {
			System.out.println(ships.get(i).getName());
		}
	}
	
	// Prints Player 2's remaining ships
	public static void RemainingShips2() {
		System.out.println("Remaining Ships: ");
		
		for(int i = 0; i < ships2.size(); i++) {
			System.out.println(ships2.get(i).getName());
		}
		System.out.println();
	}
	
	// Program ends here.
	public static void DisplayResults() {
		
		// if Player 2 sinks all of Player 1's ships
		if(remaining_ships == 0) {
			DisplayBoard();
			System.out.println(Player2 + " wins!");
			System.exit(0);
		}
		
		// if Player 1 sinks all of Player 2's ships
		else if(remaining_ships2 == 0) {
			DisplayBoard2();
			System.out.println(Player1 + " wins!");
			System.exit(0);
		}
		System.exit(0);	// Terminates whole program; without it, Play method keeps looping.
	}
	
	public static void main(String[] args) {
		
		System.out.println("*****************************************************************************************************");
		System.out.println("                                           Welcome to Battleship!!");
		System.out.println("*****************************************************************************************************");
		
		System.out.println();
		System.out.println("Battleship is a 2-Player guessing game.");
		System.out.println("Each Player must continue to guess the coordinates of their opponent's ships.");
		System.out.println("Whoever sinks the other guy's ships first wins!!");
		System.out.println();
		
		System.out.println("*****************************************************************************************************");
		System.out.println("                                           How to Play: Setup");
		System.out.println("*****************************************************************************************************");
		System.out.println();
		System.out.println("1. First, each player must place their game pieces (ships) on the board, starting with Player 1.");
		System.out.println("\t" + "To do this, each player must enter coordinates to place ONE END of the ship.");
		System.out.println();
		System.out.println("The coordinate system (the game board) is set up as follows: ");
		System.out.println();
		
		System.out.println("	    1 2 3 4 5 6 7 8 9 10");
		System.out.println(" 	  1 # # # # # # # # # # ");
		System.out.println("	  2 # # # # # # # # # # ");
		System.out.println(" 	  3 # # # # # # # # # # ");
		System.out.println("	  4 # # # # # # # # # # ");
		System.out.println(" 	  5 # # # # # # # # # # ");
		System.out.println("	  6 # # # # # # # # # # ");
		System.out.println(" 	  7 # # # # # # # # # # ");
		System.out.println("	  8 # # # # # # # # # # ");
		System.out.println(" 	  9 # # # # # # # # # # ");
		System.out.println("	 10 # # # # # # # # # # ");
		
		System.out.println();
		
		System.out.println("2. Next, the Player must choose to place the rest of the ship HORIZONTALLY TO THE RIGHT or VERTICALLY DOWNWARDS (via input).");
		System.out.println("\t" + "This is the only way to place ships. If any of them overlap or go out of bounds," + "\n" + "\t" 
				+ "you have to start placing all of them from the beginning, so choose carefully!"
				+"\n" + "\t" + "(Hint: Pay attention to the size of the ship so that mistakes don't happen.)");
		System.out.println();
		System.out.println("3. Once all of Player 1's ships have been placed, Player 2 does the same.");
		System.out.println();
		System.out.println("4. Once Player 2's ships have been placed, the game can begin!");
		System.out.println();
		System.out.println();
		
		System.out.println("*****************************************************************************************************");
		System.out.println("                                           How to Play: Gameplay");
		System.out.println("*****************************************************************************************************");
		System.out.println();
		System.out.println("To guess where a ship is, the Player must enter an X-coordinate and a Y-coordinate.");
		System.out.println("If the Player misses, an 'X' will be displayed at the guessed coordinates, that Player's turn is over, and the opponent's turn starts.");
		System.out.println("If the Player hits something, the initals of the ship will be displayed, and the Player can guess again.");
		System.out.println("This continues until a Player successfully sinks all of their opponent's ships.");
		System.out.println();
		System.out.println("Have Fun! =)");
		System.out.println();
		
		System.out.println("*****************************************************************************************************");
		System.out.println("*****************************************************************************************************");
		System.out.println();
		
		System.out.println("Player 1, enter your name:");
		Scanner p1 = new Scanner(System.in);
		Player1 = p1.nextLine();
		
		System.out.println("Player 2, enter your name:");
		Scanner p2 = new Scanner(System.in);
		Player2 = p2.nextLine();
		
		System.out.println();
		ResetBoard();
		SetUp();	
	}
}
