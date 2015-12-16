/**
 * A helper interface storing ships type, length, and damage level
 */
interface IShip {
	/**
	 * 
	 * @return a list of cells the board is consuming with the letter (i.e. B7)
	 */
	String[] getConsumingCells();
	
	
	/**
	 * Would this ship even float?
	 * @return Makes sure that the values given for the ship are valid.
	 */
	boolean isValidShipValues();
	
	/**
	 * What should this ship be called?
	 * @return Ship Name
	 */
	public String getName();
	
	/**
	 * How should the ship be represented on the board?
	 * @return return the character that represents the ship.
	 */
	public char getShipChar();
	
	
	/**
	 * Gets the current ship type
	 * @return
	 */
	public ShipType getShipType();
	
}
