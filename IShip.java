/**
 * A helper interface storing ships type, length, and damage level
 */
interface IShip {
	/**
	 * Get the type of the ship that this instance is.
	 * @return ShipType get the Ship. 
	 */
	ShipType getShipType();
	
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
}
