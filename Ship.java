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
	String direction;
	String origin; // We need to store the origin somewhere in here, how do we
					// want to accomplish that?

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
	 *            The direction the ship is facing from the origin (N, NE, E,
	 *            SE)
	 */
	public Ship(int length, ShipType type, String origin, String direction) {
		this.length = length;
		this.type = type;
		this.direction = direction;
		this.origin = origin;
	}

	/**
	 * Getter for ship origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * Getter for ship length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Getter for ship's direction
	 */
	public String getDirection() {
		return direction;
	}

	/** 
	 * 
	 */
	public ShipType getShipType() {
		return type;
	}


	public String[] getConsumingCells() {
		
		return null;
	}
}
