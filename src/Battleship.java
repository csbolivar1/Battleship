/** The Battleship class is responsible for gameplay, including setup process */

/** BATTLESHIP RULES
 * Objective: 'sink' the opposing player's ships (game pieces) 
 * First, players place anonymously places their respective ships onto the 10x10 gameboard
 * Each ship occupies a certain number of spaces; can be placed horizontally or vertically
 * Once both players' ships are placed, each player takes turns calling out a space on their opponent's board (X/Y coordinate) in an attempt to hit a ship
 * If successful, the ship's initials will display, and the player who made the call out goes again.
 * If unsuccessful, it is the opposing player's turn
 * First player to eliminate their opponent's ships wins! */

/** The Ships class is responsible for creating the ship game pieces and their attributes */

import java.util.ArrayList;
import java.util.Scanner;

public class Battleship  {
	
	private static Scanner getNum;
	private static String next;
	private static int xGuess;
	private static int yGuess;
	private static int column_rear;
	private static int column_front;
	private static int row_rear;
	private static int row_front;
	private static int direction;
	
	// Constructor; same as Ships class
	public Battleship() {}

/* =========================================================================================================================================
 * ===========================================================SET UP SHIPS==================================================================
 * ========================================================================================================================================= */
	
	/* setUp() is a series of pre-game steps 
	 * 1. Create ship instances 
	 * 2. Add ship instances to an arraylist and assign them to a player 
	 * 3. Place ships onto the gameboard, done by looping placeShips(). */
	
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
		
		// Loop through every ship in ships ArrayList, 	
		for(player.getShip_count(); player.getShip_count() < player.getShips().size(); player.setShip_count(player.getShip_count() + 1)) {
			placeShips(player);
			
		}
		
		player.resetBoard();
	}
	
	// Place ships onto gameboard
	private static void placeShips(Players player) {
		
			row_rear = 0;
			column_rear = 0;
			row_front = 11;
			column_front = 11;
			
			// Choose coordinates to place front of the ship 
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
						
			player.getShips().get(player.getShip_count()).setXCoordinateFront(row_front); // Save as front coordinates of ship.
			player.getShips().get(player.getShip_count()).setYCoordinateFront(column_front); // Save as front coordinates of ship.
			
			// Set back end of coordinates to front end; 
			row_rear = row_front;						
			column_rear = column_front;
			
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
			
			// Option 1: Place ship horizontally to the right
			if(direction == 1) {
				
				// Prevent ships from intersecting
				for(int i = 0; i < player.getShips().get(player.getShip_count()).getSize(); i++) {
					
					if(player.gameboard[row_rear][column_rear + i] != " O") {
						System.out.println("There's already a ship there!");
						i = player.getShips().get(player.getShip_count()).getSize();  // End loop so flow doesn't jump here and repeat ships.
					}

				}
				
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
				for(int i = 0; i < player.getShips().get(player.getShip_count()).getSize(); i++) {
					System.out.println("Direction is " + direction);
					System.out.println("Test is " + i);
					
					player.gameboard[row_rear][column_rear + i] = player.getShips().get(player.getShip_count()).getInitials();					
					player.getShips().get(player.getShip_count()).setXCoordinateRear(row_rear); 		// Save rear coordinates of ship to player instance			
					player.getShips().get(player.getShip_count()).setYCoordinateRear(column_rear + i);  // Used to identify ship location on gameboard
				
				}
				
				player.displayBoard();
				
			}
			
			// Option 2: Place ship vertically downwards
			else if(direction == 2) {
				System.out.println("You chose vertically!");
				
				// Prevent ships from intersecting
				for(int j = 0; j < player.getShips().get(player.getShip_count()).getSize(); j++) {
					
					if(player.gameboard[row_rear + j][column_rear] != " O") {
						System.out.println("There's already a ship there!");
						j = player.getShips().get(player.getShip_count()).getSize();  // End loop so flow doesn't jump here and repeat ships.
						placeShips(player);
					}

				}
				
				// Catches overlapping and out of bounds ships
				/*try {
					for(int j = 0; j < player.getShips().get(player.getShip_count()).getSize(); j++) {
						
						if(player.gameboard[row_end + j][column_end] != " O") {
							System.out.println("There's already a ship there!");
							j = player.getShips().get(player.getShip_count()).getSize();  // End loop so flow doesn't jump here and repeat ships.
							getCoordinates(player);
						}

					}
					
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Out of bounds! Try again");
					getCoordinates(player);
				}*/

				// Place initials of ship on gameboard vertically downwards
				
				for(int i = 0; i < player.getShips().get(player.getShip_count()).getSize(); i++) {
					
					player.gameboard[row_rear + i][column_rear] = player.getShips().get(player.getShip_count()).getInitials();		
					player.getShips().get(player.getShip_count()).setXCoordinateRear(row_rear + i); // Save rear coordinates of ship  to player instance		
					player.getShips().get(player.getShip_count()).setYCoordinateRear(column_rear);  // Used to identify ship location on gameboard
					
				}
				
				player.displayBoard();
			}
	}

/*========================================================END SET UP FOR PLAYERS=======================================================================
 * ==================================================================================================================================================== 
 * ===========================================================BEGIN PLAY==============================================================================*/	
	
	
/**
 * Once both players' ships are placed, each player takes turns calling out a
 * space on their opponent's board (X/Y coordinate) in an attempt to hit a ship
 * If successful, the ship's initials will display, and the player who made the
 * call out goes again. If unsuccessful, it is the opposing player's turn
 */	
	
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
				if(xGuess >= opposingplayer.getShips().get(count).getXCoordinateFront() && xGuess <= opposingplayer.getShips().get(count).getXCoordinateRear()) {
					// If successful, check if Y coordinate entered has a ship
					if(yGuess >= opposingplayer.getShips().get(count).getYCoordinateFront() && yGuess <= opposingplayer.getShips().get(count).getYCoordinateRear()) {
						
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

	// Game ends.
	private static void displayResults(Players winner) {
		System.out.println(winner.getName() + " wins!");
		
	}
	
}
