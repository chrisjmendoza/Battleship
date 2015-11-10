/**
 * The programatic interface for the Battleship Model class.
 * This interface supports communication with both the view
 * and controller classes in the Battleship application.
 * 
 * @author Casey Riggin
 * @author Christopher Mendoza
 * @author Peter Kim
 * @author Sai Chang
 */
public interface BattleshipModelInterface {
	
	/*
	 * Suggesting an array of Tiles to store and access board
	 * Tiles being a class that stores areas hit and points to
	 * ships in the Tile
	 */
	
	/**
	 * Get-er for if the game is in Setup Mode.
	 * @return true if game is in setup mode.
	 */
	public boolean isInSetupMode();
	
	/**
	 * Get-er for if the game is Play Mode.
	 * @return true if game is in play mode.
	 */
	public boolean isInSetupMode();
	
	/**
	* @param the player the game shall start with, true being player 1, false being player 2
	* @throws IllegalStateException if not all ships have been placed for both players or if game is already in play mode
	*/
	public void moveToSetup(boolean player);
	
	/**
	* Returns true/false if all the correct ships have been placed for the given player
	* @param player true for player 1, false for player 2
	* @return true/false if all ships have been placed for the given player
	*/
	public boolean areAllShipsPlaced(boolean player);
	
	/**
	 * Places Ship at the given location
	 * @param col column position to place ship
	 * @param row row position to place the ship
	 * @param ship the type of ship being placed, used to test validity of input
	 * @player true for player 1, false for player 2
	 * @throws IllegalArgumentException if same arguments return false for isValidShipPlacement method
	 * @throws IllegalStateException is not in setup mode
	 */
	public void placeShip(int col, char row, Ship ship, boolean player);
	
	/**
	 * Method checks that placement of the given ship is valid by meeting the following conditions:
	 * 1.	That the column is between A-K (inclusive)
	 * 2.	That the row is bewtween 1-10 (inclusive)
	 * 3.	That the ship does not overlap with any ships by sharing cells
	 * 4.	That all of the peices of a ship are on the board
	 * 5.	That the ships do not overlap in an diagnoal fashion
	 * 6.	That the allowed amount of that ship type have not been already placed by
	 * that player.
	 * 
	 * @param col column position to place ship
	 * @param row row position to place the ship
	 * @param ship the type of ship being placed, used to test validity of input
	 * @player true for player 1, false for player 2
	 * @throws IllegalStateException is not in setup mode
	 * @returns true if placement is valid
	 */
	public boolean isValidShipPlacement(int col, char row, Ship ship, boolean player);
	
	/**
	 * Sends an attack to given position. If the attack returns a miss, switch the active turn
	 * 
	 * @param col column you wish to attack on the opposing players board
	 * @param row row you wish to attack on the opposing players board
	 * @throws IllegalStateException if game is not in Play Mode or if game is over
	 * @throws IllegalArgumentException if the same arguments would cause isValidAttackLocation to return false
	 * @return returns an Status  enum representing Status of attack
	 */
	public Status attackLocation(int col, char row);
	
	/**
	 * This method returns if the arguments result in a valid place to attack
	 * 1.	The player has not attacked the given square before
	 * 2.	The square is within the board (A-J & 1-10)
	 * 
	 * @param col column you wish to attack on the opposing players board
	 * @param row row you wish to attack on the opposing players board
	 * @throws IllegalStateException if game is not in Play Mode or if game is over
	 * @return if the place is a valid way to attack the ship
	 */
	public boolean isValidAttackLocation(int col, char row);
	
	/**
	 * Checks if the current players turn is that of player one.
	 * @return true if the active turn is player 1.
	 */
	public boolean isPlayerOneTurn();
	
	
	/**
	 * Checks if the current players turn is that of player two.
	 * @return true if the active turn is player 2.
	 */
	public boolean isPlayerTwoTurn();
	
	/**
	 * Allows view of player 1's board
	 * @return an array of Strings representing player 2's attack board
	 */
	public Tile[][] getBoardStatePlayerOne();
	
	/**
	 * Allows view of player 2's board
	 * @return an array of Strings representing player 2's board
	 */
	public Tile[][] getBoardStatePlayerTwo();
	
	/**
	 * Tests to see if game is over
	 * @return true if game is over and false if not
	 */
	public boolean isGameOver();
	
	/**
	 * Sets board state so that game may begin anew
	 */
	public void reset();
}

/**
 * A helper class storing a ships type, length, and damage level
 */
class Ship{
	private String type;
	private int length;
	private int hits;
}

class Tile{
	private boolean hit;
	private boolean ship;
}

enum Status {
	// return status, a miss
	MISS, 
	// return status, a hit, doesn't sink a ship
	HIT, 
	// return status, a hit, sunk destroyer 
	SUNK_DESTROYER, 
	// return status, a hit, sunk cruiser
	SUNK_CRUISER, 
	// return status, a hit, sunk battleship
	SUNK_BATTLESHIP, 
	// return status, a hit, sunk aircraft carrier
	SUNK_AIRCRAFT_CARRIER, 
	// return status, location was already played or invalid
	DO_OVER
};
