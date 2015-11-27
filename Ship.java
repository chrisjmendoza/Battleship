import java.util.regex.Pattern;

/**
 * The abstract ship class that will be used to define each ship After writing
 * some stuff for this, since each method doesn't need to be different for each
 * ship, maybe we don't need an abstract class
 *
 * @author Casey Riggin
 * @author Christopher Mendoza
 * @author Peter Kim
 * @author Sai Chang
 */

public class Ship implements IShip {

	int length;
	ShipType type;
	Direction direction;
	String origin; 

	/**
	 * Ship constructor
	 * 
	 * @param length
	 *            The length of the ship
	 * @param type
	 *            The type of ship (eg destroyer, etc)
	 * @param origin
	 *            The starting grid point of the ship
	 * @param direction
	 *            The direction the ship is facing from the origin 
	 */
	public Ship(int length, ShipType type, String origin, Direction dir) {
		this.length = length;
		this.type = type;
		this.direction = dir;
		this.origin = (origin == null ?  "" : origin).toUpperCase().trim();
	}

	/**
	 * Getter for ship origin
	 */
	public String getOrigin() {
		return this.origin;
	}

	/**
	 * Getter for ship length
	 */
	public int getLength() {
		return this.length;
	}

	/**
	 * Getter for ship's direction
	 */
	public Direction getDirection() {
		return this.direction;
	}

	/** 
	 * 
	 */
	public ShipType getShipType() {
		return this.type;
	}


	public String[] getConsumingCells() 
	{
		
	}
	
	public boolean isValidShipValues() 
	{
		return Pattern.matches("^[A-J]{1}(10|[1-9]{1})$", this.getOrigin());
	}
}
