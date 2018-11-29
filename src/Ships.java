/**The Ships class is responsible for everything that has to do with the ships
 * (AKA gamepieces), which are:
 * Name, Initials, Size,
 * X and Y Coordinate of the front of the ship,
 * X and Y Coordinate of the end (back) of the ship. */

public class Ships {
	
	public String n; // Name of ship
	public String i; // Initials of ships to be displayed on gameboard
	public int s;	// Size of ship; also used to see remaining parts of ship if it should be hit during Play
	public int xcoordinate_stern;	// ONLY SAVES STERN (back) X COORDINATE OF SHIP								
	public int ycoordinate_stern;	// ONLY SAVES STERN (back) Y COORDINATE OF SHIP
	public int xcoordinate_bow;  // ONLY SAVES BOW (front) X COORDINATE OF SHIP
	public int ycoordinate_bow;  // ONLY SAVES BOW (front) Y COORDINATE OF SHIP
	
	public Ships(String name, int size, String initials, int xpos_stern, int ypos_stern, int xpos_bow, int ypos_bow) {
		n = name;
		s = size;
		i = initials;
		xcoordinate_stern = xpos_stern;
		ycoordinate_stern = ypos_stern;
		xcoordinate_bow = xpos_bow;
		ycoordinate_bow = ypos_bow;
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
