/** The Ships class is responsible for creating the ship game pieces and their attributes */

public class Ships {
	
	private String n; // Name of ship
	private String i; // Initials of ships to be displayed on gameboard to signify location
	private int s;	// Size of ship; number of spaces a ship occupies on the gameboard
	private int xCoordinate_Rear;	// X coordinate of rear of a ship							
	private int yCoordinate_Rear;	// Y coordinate of rear of a ship	
	private int xCoordinate_Front;  // X coordinate of front of a ship	
	private int yCoordinate_Front;  // Y coordinate of front of a ship	
	
	public Ships(String name, int size, String initials, int xpos_rear, int ypos_rear, int xpos_front, int ypos_front) {
		this.n = name;
		this.s = size;
		this.i = initials;
		this.xCoordinate_Rear = xpos_rear;
		this.yCoordinate_Rear = ypos_rear;
		this.xCoordinate_Front = xpos_front;
		this.yCoordinate_Front = ypos_front;
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
	
	public int getXCoordinateRear() {
		return xCoordinate_Rear;
	}

	public void setXCoordinateRear(int x) {
		this.xCoordinate_Rear = x;
	}

	public int getYCoordinateRear() {
		return yCoordinate_Rear;
	}

	public void setYCoordinateRear(int y) {
		this.yCoordinate_Rear = y;
	}
	
	public int getXCoordinateFront() {
		return xCoordinate_Front;
	}

	public void setXCoordinateFront(int fx) {
		this.xCoordinate_Front = fx;
	}

	public int getYCoordinateFront() {
		return yCoordinate_Front;
	}

	public void setYCoordinateFront(int fy) {
		this.yCoordinate_Front = fy;
	}

}
