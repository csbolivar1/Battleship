/** The Ships class is responsible for creating the ship game pieces and their attributes */

public class Ships {
	
	private String n; // Name of ship
	private String i; // Initials of ships to be displayed on gameboard to signify location
	private int s;	// Size of ship; number of spaces a ship occupies on the gameboard
	private int xcoordinate_stern;	// X coordinate of rear of a ship							
	private int ycoordinate_stern;	// Y coordinate of rear of a ship	
	private int xcoordinate_bow;  // X coordinate of front of a ship	
	private int ycoordinate_bow;  // Y coordinate of front of a ship	
	
	public Ships(String name, int size, String initials, int xpos_stern, int ypos_stern, int xpos_bow, int ypos_bow) {
		this.n = name;
		this.s = size;
		this.i = initials;
		this.xcoordinate_stern = xpos_stern;
		this.ycoordinate_stern = ypos_stern;
		this.xcoordinate_bow = xpos_bow;
		this.ycoordinate_bow = ypos_bow;
	}
	
	// Getters and Setters
	public String getName() {
		return n;
	}

	public void setName(String n) {
		this.n = n;
	}

	public int getSize() {
		return s;
	}

	public void setSize(int s) {
		this.s = s;
	}
	
	public String getInitials() {
		return i;
	}

	public void setInitials(String i) {
		this.i = i;
	}
	
	public int getXCoordinateStern() {
		return xcoordinate_stern;
	}

	public void setXCoordinateStern(int x) {
		this.xcoordinate_stern = x;
	}

	public int getYCoordinaiteStern() {
		return ycoordinate_stern;
	}

	public void setYCoordinateStern(int y) {
		this.ycoordinate_stern = y;
	}
	
	public int getXCoordinateBow() {
		return xcoordinate_bow;
	}

	public void setXCoordinateBow(int fx) {
		this.xcoordinate_bow = fx;
	}

	public int getYCoordinateBow() {
		return ycoordinate_bow;
	}

	public void setYCoordinateBow(int fy) {
		this.ycoordinate_bow = fy;
	}

}
