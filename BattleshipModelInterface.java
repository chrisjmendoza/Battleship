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
	public bool isInSetupMode();
	
	/**
	 * Get-er for if the game is Play Mode.
	 * @return true if game is in play mode.
	 */
	public bool isInSetupMode();
	
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
	 * Method checks that placement of the give ship is valid by meeting the following conditions:
	 * 1.	That the column is between A-K (inclusive)
	 * 2.	That the row is bewtween 1-10 (inclusive)
	 * 3.	That the ship does not overlap with any ships by sharing cells
	 * 4.	That all of the peices of a ship are on the board
	 * 5.	That the ships do not overlap in an diagnoal fashion
	 * 6.	That the allowed amount of that ship type have not been already placed by
	 * that player.
	 * 
	 * @throws IllegalStateException is not in setup mode
	 * @returns true if placement is valid
	 */
	public boolean isValidShipPlacement(int col, char row, Ship ship, boolean player);
	
	/**
	 * Sends an attack to given position
	 * @param location position of attack
	 * @return returns an int representing Status of attack based off the enum
	 */
	public int attackLocation(int location);
	
	/**
	 * Changes turns between the players
	 */
	public boolean playerTurn();
	
	/**
	 * Allows view of player 1's board
	 * @return an array of Strings representing player 2's attack board
	 */
	public Tile[][] getBoardStatePlayer1();
	
	/**
	 * Allows view of player 2's board
	 * @return an array of Strings representing player 2's board
	 */
	public Tile[][] getBoardStatePlayer2();
	
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
