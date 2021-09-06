import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;
import org.junit.Assert;

public class BattleshipTest {
	
	static Scanner s1;
	Players player1;
	
	@Test
	public void playerSetupTest() {
		
		ArrayList<Ships> player1ships = new ArrayList<Ships>();
		
		System.out.println("Player 1, enter your name:");
		s1 = new Scanner(System.in);
		player1 = new Players(s1.nextLine(), 0, 0, player1ships);
		
		Battleship.setUp(player1, player1.getShips());
					
		Assert.assertTrue(player1.getName(), true); 		// Confirm a name is present
		Assert.assertEquals(player1.getShips().size(), 5);  // Confirm 5 ships are present
		
		// Create loop through player1ships that confirms coodinates for all ships (greater than 0, less than 11)
		for (int i = 0; i < player1.getShips().size(); i++) {
			Assert.assertTrue(player1ships.get(i).getXCoordinateFront() >= 0 && player1ships.get(i).getXCoordinateFront() <= 9);
			Assert.assertTrue(player1ships.get(i).getYCoordinateFront() >= 0 && player1ships.get(i).getYCoordinateFront() <= 9);
			Assert.assertTrue(player1ships.get(i).getXCoordinateRear() >= 0 && player1ships.get(i).getXCoordinateRear() <= 9);
			Assert.assertTrue(player1ships.get(i).getYCoordinateRear() >= 0 && player1ships.get(i).getYCoordinateRear() <= 9);
		}
	}
}

