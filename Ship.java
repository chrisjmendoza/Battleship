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
	 * @param type
	 *            The type of ship (eg destroyer, etc)
	 * @param origin
	 *            The starting grid point of the ship
	 * @param direction
	 *            The direction the ship is facing from the origin
	 */
	public Ship(ShipType type, String origin, Direction dir) {
		this.length = length;
		this.type = type;
		this.direction = dir;
		this.origin = (origin == null ? "" : origin).toUpperCase().trim();
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

	public String[] getConsumingCells() {
		int length = 0;
		if (this.type == ShipType.AIRCRAFT_CARRIER) {
			this.length = 5;
		} else if (this.type == ShipType.BATTLESHIP) {
			this.length = 4;
		} else if (this.type == ShipType.CRUISER) {
			this.length = 3;
		} else if (this.type == ShipType.DESTROYER) {
			this.length = 2;
		}

		String[] consumedCells = new String[length];
		int row = (((int) getOrigin().toUpperCase().toCharArray()[0]) - 'A');
		int col = Integer.parseInt(getOrigin().substring(1));
		for (int i = 0; i < length; i++) {
			if (i != 0) {
				if (direction == Direction.N) {
					row = row - 1;
				} else if (direction == Direction.NW) {
					row = row - 1;
					col = col + 1;
				} else if (direction == Direction.W) {
					col = col + 1;
				} else if (direction == Direction.SW) {
					col = col + 1;
					row = row + 1;
				} else if (direction == Direction.S) {
					row = row + 1;
				} else if (direction == Direction.SE) {
					row = row + 1;
					col = col - 1;
				} else if (direction == Direction.E) {
					col = col - 1;
				} else if (direction == Direction.NE) {
					col = col - 1;
					row = row - 1;
				}

			}
			consumedCells[i] = row + " " + col;
		}
		return consumedCells;
	}

	public boolean isValidShipValues() {
		return Pattern.matches("^[A-J]{1}(10|[1-9]{1})$", this.getOrigin());
	}
}
