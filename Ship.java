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
	private Direction direction;
	private String origin;
	private String allowedRegex;
	private ShipType shipType;

	/**
	 * Ship constructor
	 * 
	 * @param origin
	 *            The starting grid point of the ship
	 * @param dir
	 *            The direction the ship is facing from the origin
	 */
	public Ship(String origin, Direction dir, String allowedRegex, ShipType shipType) {
		this.direction = dir;
		this.origin = (origin == null ? "" : origin).toUpperCase().trim();
		this.allowedRegex = allowedRegex;
		this.shipType = shipType;

	}

	/**
	 * Getter for ship origin
	 */
	private String getOrigin() {
		return this.origin;
	}

	/**
	 * Getter for ship's direction
	 */
	public Direction getDirection() {
		return this.direction;
	}

	public String getName() {
		return this.shipType.getShipName();
	}
	
	public char getShipChar() {
		return this.shipType.getShipChar();
	}
	
	/**
	 * Returns the current ship type
	 */
	public ShipType getShipType() {
		return this.shipType;
	}

	public String[] getConsumingCells() {
		String[] consumedCells = new String[shipType.getShipLength()];
		int row = (((int) getOrigin().toUpperCase().toCharArray()[0]) - 'A');
		int col = Integer.parseInt(getOrigin().substring(1));
		for (int i = 0; i < shipType.getShipLength(); i++) {
			if (i != 0) {
				if (direction == Direction.N) {
					row = row - 1;
				} else if (direction == Direction.NW) {
					row = row - 1;
					col = col - 1;
				} else if (direction == Direction.W) {
					col = col - 1;
				} else if (direction == Direction.SW) {
					col = col - 1;
					row = row + 1;
				} else if (direction == Direction.S) {
					row = row + 1;
				} else if (direction == Direction.SE) {
					row = row + 1;
					col = col + 1;
				} else if (direction == Direction.E) {
					col = col + 1;
				} else if (direction == Direction.NE) {
					col = col + 1;
					row = row - 1;
				}
			}
			consumedCells[i] = "" + (char) ('A' + row) + Integer.toString(col);
		}
		return consumedCells;
	}

	public boolean isValidShipValues() {
		return Pattern.matches(allowedRegex, this.getOrigin());
	}
}
