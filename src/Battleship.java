/** BATTLESHIP!!!
 * How to Play:
 * 	The object of the game is to sink all the ships
 *  first player to sink all the ships wins!
 *  Optional: keep track of how many tries it takes 
 *  to find all the ships.*/

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Battleship  {
	
	private static Scanner getNum;
	private static String next;
	private static int xGuess;
	private static int yGuess;
	private static int row_end;
	private static int column_end;
	private static int row_front;
	private static int column_front;
	private static int direction;
	
	// Constructor; same as Ships class
	public Battleship() {}

/*==========================================================================================================================================
 * =======================================================SET UP PROCESS STARTS HERE========================================================
 * ========================================================================================================================================= */
	
	/** setUp() Function: Place ships on the game board 
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
	
	// Set up game pieces
	public static void setUp(Players player, ArrayList<Ships> ship) {
		player.resetBoard();
		
		getNum = new Scanner(System.in); 		// Scanner for Row Number
		
		// Create game pieces (ships)
		Ships aircraftCarrier = new Ships("Aircraft Carrier", 5, " A", 0, 0, 0, 0);
		Ships battleship = new Ships("Battleship", 4, " B", 0, 0, 0, 0);
		Ships submarine = new Ships("Submarine", 3, " S", 0, 0, 0, 0);
		Ships destroyer = new Ships("Destroyer", 3, " D", 0, 0, 0, 0);
		Ships patrolBoat = new Ships("Patrol Boat", 2, " P", 0, 0, 0, 0);
		
		// Add ships to arraylist
		ship.add(aircraftCarrier);
		ship.add(battleship);
		ship.add(submarine);
		ship.add(destroyer);
		ship.add(patrolBoat);
		
		// Give ships to player instance
		player.setShips(ship);
		
		// Loop through every ship in ships ArrayList	
		for(player.getShip_count(); player.getShip_count() < player.getShips().size(); player.setShip_count(player.getShip_count() + 1)) {
			placeShips(player);
			
		}
		
		player.resetBoard();
	}
		
	private static void placeShips(Players player) {
		
			row_end = 0;
			column_end = 0;
			row_front = 11;
			column_front = 11;
			
			// Choose coordinates to place the bow (front) of the ship 
			System.out.println(player.getName() + ", place your " + player.getShips().get(player.getShip_count()).getName());
			System.out.println("Size: " + player.getShips().get(player.getShip_count()).getSize());
			System.out.println("Ships on board: " + player.getShip_count());
			System.out.println();
			
			// Select Row Number
			do {
				System.out.println("Select Row Number (1 through 10): ");
				next = getNum.next();
				try {
					row_front = Integer.parseInt(next) - 1;
				} catch (NumberFormatException exp) {
					System.out.println("Please select a number 1 through 10");
				}
			} while (row_front > 10);
			
			// Select Column Number
			do {
				System.out.println("Select Column Number (1 through 10): ");
				next = getNum.next();
				try {
					column_front = Integer.parseInt(next) - 1;
				} catch (NumberFormatException exp) {
					System.out.println("Please select a number 1 through 10");
				}
			} while (column_front > 10);
						
			player.getShips().get(player.getShip_count()).setXCoordinateBow(row_front); // Save as bow (front) coordinates of ship.
			player.getShips().get(player.getShip_count()).setYCoordinateBow(column_front); // Save as bow (front) coordinates of ship.
			
			// Set back end of coordinates to front end; 
			row_end = row_front;						
			column_end = column_front;
			
			player.displayBoard();
			
			direction = 3;
			
			do {
				System.out.println("Select direction of ship (Enter 1 for horizontally to the right "
						+ "or 2 for vertically downwards)");
				next = getNum.next();
				try {
					direction = Integer.parseInt(next);
				} catch (NumberFormatException exp) {
					System.out.println("Select direction of ship (Enter 1 for horizontally to the right "
							+ "or 2 for vertically downwards)");
				}
			} while (direction > 2);
			
			// Place ship horizontally to the right
			if(direction == 1) {
				
				// Prevent ships from intersecting
				for(int i = 0; i < player.getShips().get(player.getShip_count()).getSize(); i++) {
					
					if(player.gameboard[row_end][column_end + i] != " O") {
						System.out.println("There's already a ship there!");
						i = player.getShips().get(player.getShip_count()).getSize();  // Had to end the loop so that game doesn't jump here and repeat ships, why does it jump here???
						placeShips(player);
					}

				}
				
				// TODO: setup out of bounds exception
				// BUG: During second try of placing a ship after out of bounds exception, getCoordinates method is placing ship horizontally, regardless if it's already been placed vertically.
				/*try {
					for(test = 0; test < player.getShips().get(player.getShip_count()).getSize(); test++) {
						
						if(player.gameboard[row_end][column_end + test] != " O") {
							System.out.println("There's already a ship there!");
							test = player.getShips().get(player.getShip_count()).getSize();  // Had to end the loop so that game doesn't jump here and repeat ships, why does it jump here???
							getCoordinates(player);
						}

					}
					
				} catch (Exception e) {
					System.out.println("Out of bounds! Try again");
					getCoordinates(player);
				}*/
				
				// Place initials of ship on gameboard horizontally	
				// TODO: getCoordinates is going here after placing ship that previously had out of bounds error, 
				for(int i = 0; i < player.getShips().get(player.getShip_count()).getSize(); i++) {
					System.out.println("Direction is " + direction);
					System.out.println("Test is " + i);
					
					player.gameboard[row_end][column_end + i] = player.getShips().get(player.getShip_count()).getInitials();					
					player.getShips().get(player.getShip_count()).setXCoordinateStern(row_end); 		// Save stern (back) coordinates of ship to player instance			
					player.getShips().get(player.getShip_count()).setYCoordinateStern(column_end + i);  // Used to identify ship location on gameboard
				
				}
				
				player.displayBoard();
				
			}
			
			// Place ship vertically downwards
			else if(direction == 2) {
				System.out.println("You chose vertically!");
				
				// Prevent ships from intersecting
				for(int j = 0; j < player.getShips().get(player.getShip_count()).getSize(); j++) {
					
					if(player.gameboard[row_end + j][column_end] != " O") {
						System.out.println("There's already a ship there!");
						j = player.getShips().get(player.getShip_count()).getSize();  // Had to end the loop so that game doesn't jump here and repeat ships, why does it jump here???
						placeShips(player);
					}

				}
				
				// Catches overlapping and out of bounds ships
				/*try {
					for(int j = 0; j < player.getShips().get(player.getShip_count()).getSize(); j++) {
						
						if(player.gameboard[row_end + j][column_end] != " O") {
							System.out.println("There's already a ship there!");
							j = player.getShips().get(player.getShip_count()).getSize();  // Had to end the loop so that game doesn't jump here and repeat ships, why does it jump here???
							getCoordinates(player);
						}

					}
					
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Out of bounds! Try again");
					getCoordinates(player);
				}*/

				// Place initials of ship on gameboard vertically downwards
				
				for(int i = 0; i < player.getShips().get(player.getShip_count()).getSize(); i++) {
					
					player.gameboard[row_end + i][column_end] = player.getShips().get(player.getShip_count()).getInitials();		
					player.getShips().get(player.getShip_count()).setXCoordinateStern(row_end + i); // Save stern (back) coordinates of ship  to player instance		
					player.getShips().get(player.getShip_count()).setYCoordinateStern(column_end);  // Used to identify ship location on gameboard
					
				}
				
				player.displayBoard();
			}
	}

/*========================================================END SET UP FOR PLAYERS=======================================================================
 * ==================================================================================================================================================== 
 * =========================================================GAME STARTS HERE===========================================================================*/	
	
	
	/** Play() Function: Play the game.
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
	 *  	If none of those conditions are met, then it's a miss, and Player 2's turn starts 
	 *  	
	 *   */
	
	public static void play(Players mainplayer, Players opposingplayer) {
		
		System.out.println("It's " + mainplayer.getName() + "'s turn!");
	
		// Keep track of how many ships are left
		int remaining_ships = opposingplayer.getShips().size();
		System.out.println("Remaining ships: " + remaining_ships + "\n");

		printShips(opposingplayer);
		
		// Keep playing while there are still ships to be sunk.
		while(remaining_ships != 0) {
			int count = 0;
			
			opposingplayer.displayBoard();

			System.out.println("Enter coordinates to see if a ship is there or not");
			
			// Player guesses x coordinate
			do {
				System.out.println("Select Row Number (1 through 10): ");
				next = getNum.next();
				try {
					xGuess = Integer.parseInt(next) - 1;
				} catch (NumberFormatException exp) {
					System.out.println("Please select a number 1 through 10");
				}
			} while (xGuess > 10);
			
			// Player guesses y coordinate
			do {
				System.out.println("Select Column Number (1 through 10): ");
				next = getNum.next();
				try {
					yGuess = Integer.parseInt(next) - 1;
				} catch (NumberFormatException exp) {
					System.out.println("Please select a number 1 through 10");
				}
			} while (yGuess > 10);
			

			// TODO: Handle coordinates already entered.
			/*if(gameboard2[x][y] != " O") {
				System.out.println("You tried those coordinates already! Try again");
				Play();
			}*/
			
			// Loop through all ships in list, check for hits
			while(count < opposingplayer.getShips().size()) {
				// Check if the row number entered has a ship 
				if(xGuess >= opposingplayer.getShips().get(count).getXCoordinateBow() && xGuess <= opposingplayer.getShips().get(count).getXCoordinateStern()) {
					// If successful, check if Y coordinate entered has a ship
					if(yGuess >= opposingplayer.getShips().get(count).getYCoordinateBow() && yGuess <= opposingplayer.getShips().get(count).getYCoordinaiteStern()) {
						
						System.out.println("Hit! You hit the " + opposingplayer.getShips().get(count).getName() + ". Go again!");		// Hit													
						opposingplayer.getShips().get(count).setSize(opposingplayer.getShips().get(count).getSize() - 1); 				// Decrement size of ship after hit
						System.out.println("Remaining parts of " + opposingplayer.getShips().get(count).getName() + 					// Show how much of the damaged ship is left
								": " + opposingplayer.getShips().get(count).getSize());

						opposingplayer.gameboard[xGuess][yGuess] = opposingplayer.getShips().get(count).getInitials();							// Place initials of hit ship on gameboard

						// Notification for if you sunk a ship
						if (opposingplayer.getShips().get(count).getSize() == 0) {
							System.out.println("You sunk the " + opposingplayer.getShips().get(count).getName());
							remaining_ships--;
							opposingplayer.getShips().remove(opposingplayer.getShips().get(count));
							
							// Game ends once mainplayer sinks all of opposingplayer's ships.
							if(remaining_ships == 0) {
								System.out.println("The game has ended");
								displayResults(mainplayer);
							}
							
							// Player gets another turn after hit and sinking a ship
							play(mainplayer, opposingplayer);
						}
						
						// Player gets another turn after hit
						play(mainplayer, opposingplayer);
					}
					
					// Miss; mark gameboard with X to show attempts
					else {
						System.out.println("Miss!");
						opposingplayer.gameboard[xGuess][yGuess] = " X";
					}
				}
				
				// Miss; mark gameboard with X to show attempts
				else {
					System.out.println("Miss!");
					opposingplayer.gameboard[xGuess][yGuess] = " X";
				}
				count++;
			}
			
			// Switch turns if player misses a ship
			play(opposingplayer, mainplayer);

		}
	}

	
	private static void printShips(Players p) {
		for(int i = 0; i < p.getShips().size(); i++) {
			System.out.println(p.getShips().get(i).getName()); 
		}
		System.out.println();
	}

	// Game ends here.
	private static void displayResults(Players winner) {
		
		System.out.println(winner.getName() + " wins!");
		
	}
	
}
