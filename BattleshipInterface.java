//put code and or requirements here 

//Copied from Assignment Description
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

enum ShipType
{
	DESTROYER
	CRUISER
	BATTLESHIP
	AIRCRAFT_CARRIER
}

/**
* Represents the board for both players
*/
public class Board 
{
	//Should set game state to Setup and clear hit/miss board and ship board
	public void reset()
	
	public bool isInSetupMode() 

	public bool isInPlayMode()

	/**
	* Attempts to place the the ship at the given location
	*
	* @param consumedCells List of cells consumed in Column Row style (ie. E5, E6,E7)
	* @param shipType the type of ship you're trying to place
	* @param player, 1 for player, 2 for player two, all other should throw some kind of error?
	* @throws IllegalArugmentException if condisitions for is valid placement fail
	*/
	public boolean attemptPlaceShip(ShipType shipType, byte player, params ,String[] consumedCells) 

	
/**
*Method checks that 
* cells are next to each other
* cells are next to each other in a horizontal, vertical or diagonal fashion
* cells are not already consumed on the given board
* cells are all on the board
* cells do not conflict with another ship in a diagonal manner
* the amount of cells consumes matches the ship type being placed.
* false is game is not in setup mode
*
	* @param consumedCells List of cells consumed in Column Row style (ie. E5, E6,E7)
	* @param shipType the type of ship your trying to place
*/
	public bool isValidShipPlacement(string[] consumedCells,  ShipType shipType)

	/**
	* @param startingPlayer the player that should start the game (must be a 1 or a 2)
* @throws exception (Maybe IllegalState?) if areAllShipsPlaced Invalid
*/
	public void moveToSetup(byte startingPlayer) 

	/**
	* Should return true/false if all the correct ships have been placed for the given player
	* @param player (1,2) valid, throw exception for anything else?
	*/
	public bool areAllShipsPlaced( byte player)


	play methods?

	getActivePlayer?
	
	fireAtSquare

	isGameOver
	
	isLegalFireShot

	
	
	

	
}

