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
	 * Places Ship at the given location
	 * @param location position to place ship
	 * @param ship the type of ship being placed, used to test validity of input
	 * @throws IllegalArgumentException thrown if location is filled or not on board
	 */
	public void placeShip(int location, Ship ship);
	
	/**
	 * Sends an attack to given position
	 * @param location position of attack
	 * @return returns an int representing Status of attack based off the enum
	 */
	public int attackLocation(int location);
	
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
