/** Main class; execution starts here */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	static Scanner s1;
	static Scanner s2;
	
	public static void main(String[] args) {
		
		System.out.println("*****************************************************************************************************");
		System.out.println("                                           Welcome to Battleship!!");
		System.out.println("*****************************************************************************************************");
		
		System.out.println();
		System.out.println("Battleship is a 2-Player guessing game.");
		System.out.println("Each Player must continue to guess the coordinates of their opponent's ships.");
		System.out.println("Whoever sinks the other player's ships first wins!!");
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
		
		System.out.println("2. Next, the Player must choose to place the rest of the ship HORIZONTALLY TO THE RIGHT or VERTICALLY DOWNWARDS.");
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
		System.out.println("Have Fun!");
		System.out.println();
		
		System.out.println("*****************************************************************************************************");
		System.out.println("*****************************************************************************************************");
		System.out.println();
		
		// Generate ships for player instances
		ArrayList<Ships> player1ships = new ArrayList<Ships>();
		ArrayList<Ships> player2ships = new ArrayList<Ships>();
		
		System.out.println("Player 1, enter your name:");
		s1 = new Scanner(System.in);
		Players player1 = new Players(s1.nextLine(), 0, 0, player1ships);
		
		System.out.println("Player 2, enter your name:");
		s2 = new Scanner(System.in);
		Players player2 = new Players(s2.nextLine(), 0, 0, player2ships);
		
		// Place ships for player 1
		Battleship.setUp(player1, player1.getShips());
		System.out.println(player1.getName() + " is done");
		
		// Place ships for player 2
		Battleship.setUp(player2, player2.getShips());
		System.out.println("Setup is done, time to play the game!");
		
		// Randomly select which player will go first
		ArrayList<Players> names = new ArrayList<Players>();
		Random random = new Random();
		
		names.add(player1);
		names.add(player2);
		
		Players firstpick = names.get(random.nextInt(names.size()));
		names.remove(firstpick);
		
		System.out.println(firstpick.getName() + " will go first");
		
		// Play game after ships have been placed
		Battleship.play(firstpick, names.get(0));

	}

}
