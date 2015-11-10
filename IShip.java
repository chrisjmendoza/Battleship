/**
 * A helper interface storing ships type, length, and damage level
 */
public interface IShip {
	/**
	 * Get the type of the ship that this instance is.
	 * @return ShipType get the Ship. 
	 */
	public ShipType getShipType(); 
	
	/**
	 * 
	 * @return a list of cells the board is consuming with the letter (i.e. B7)
	 */
	public String[] getConsumingCells();
}
