/** The Players class is responsible for player attributes, including respective gameboards for each player */

import java.util.ArrayList;

public class Players {
	
	public  String gameboard[][];
	private String name;
	private static int rows = 10;
	private static int columns = 10;
	private int ship_count; // Keep track of which ship is being processed in ships arraylist during setup.
	private int remaining_ships;
	private ArrayList<Ships> ships;
	
	public Players(String name, int ship_count, int remaining_ships, ArrayList<Ships> playerships) {
		
		this.name = name;
		this.rows = 10;
		this.columns = 10;
		this.ship_count = ship_count;
		this.remaining_ships = remaining_ships;
		this.gameboard = new String[this.rows][this.columns];
		this.ships = playerships;
		
	}

	// Remove all ships from board
	public void resetBoard() {
		for(int i = 0; i < rows; i++) {
			System.out.println();
			for(int j = 0; j < columns; j++) {
				gameboard[i][j] = " O";
				
			}
		
		}
	
	}
	
	// Show gameboard
	public void displayBoard() {
		System.out.println("Viewing " + this.name + "'s board");
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getShip_count() {
		return ship_count;
	}

	public void setShip_count(int ship_count) {
		this.ship_count = ship_count;
	}

	public int getRemaining_ships() {
		return remaining_ships;
	}

	public void setRemaining_ships(int remaining_ships) {
		this.remaining_ships = remaining_ships;
	}

	public String[][] getGameboard() {
		return gameboard;
	}

	public void setGameboard(String[][] gameboard) {
		this.gameboard = gameboard;
	}

	public ArrayList<Ships> getShips() {
		return ships;
	}

	public void setShips(ArrayList<Ships> ships) {
		this.ships = ships;
	}
	
	public void resetShips() {
		
	}
	
}
