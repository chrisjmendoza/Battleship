 /**
  * The programmatic interface for the Battleship Model class.
  * This interface supports communication with both the view
  * and controller classes in the Battleship application.
  * 
  * @author Casey Riggin
  * @author Christopher Mendoza
  * @author Peter Kim
  * @author Sai Chang
  */
 interface IBattleshipModel {
 	
 	/*
 	 * Suggesting an array of Tiles to store and access board
 	 * Tiles being a class that stores areas hit and points to
 	 * ships in the Tile
 	 */
 	
 	/**
 	 * Get-er for if the game is in Setup Mode.
 	 * @return true if game is in setup mode.
 	 */
	boolean isInSetupMode();
 	
 	/**
 	 * Get-er for if the game is Play Mode.
 	 * @return true if game is in play mode.
 	 */
	boolean isInPlayMode();
 	
 	/**
 	* @param player the game shall start with, true being player 1, false being player 2
 	* @throws IllegalStateException if not all ships have been placed for both players or if game is already in play mode
 	*/
	void moveToPlay(boolean player);
 	
 	/**
 	* Returns true/false if all the correct ships have been placed for the given player
 	* @param player true for player 1, false for player 2
 	* @return true/false if all ships have been placed for the given player
 	*/
	boolean areAllShipsPlaced(boolean player);
 	
 	/**
 	 * Places Ship at the given location
 	 * @param ship the type of ship being placed, used to test validity of input
 	 * @param player true for player 1, false for player 2
 	 * @throws IllegalArgumentException if same arguments return false for isValidShipPlacement method
 	 * @throws IllegalStateException is not in setup mode
 	 */
	void placeShip(IShip ship, boolean player);
 	
 	/**
 	 * Method checks that placement of the given ship is valid by meeting the following conditions:
 	 * 1.	That the ship does not overlap with any ships by sharing cells
 	 * 2.	That all of the pieces of a ship are on the board
 	 * 3.	That the ships do not overlap in an diagonal fashion
 	 * 4.	That the allowed amount of that ship type have not been already placed by
 	 * that player.
 	 * 
  	 * @param ship the type of ship being placed, used to test validity of input
 	 * @param player true for player 1, false for player 2
 	 * @throws IllegalStateException is not in setup mode
 	 * @returns true if placement is valid
 	 */
	boolean isValidShipPlacement(IShip ship, boolean player);
 	
 	/**
 	 * Sends an attack to given position. If the attack returns a miss, switch the active turn
 	 * 
 	 * @param col column you wish to attack on the opposing players board
 	 * @param row row you wish to attack on the opposing players board
 	 * @throws IllegalStateException if game is not in Play Mode or if game is over
 	 * @throws IllegalArgumentException if the same arguments would cause isValidAttackLocation to return false
 	 * @return returns an FireResult  representing Status of attack
 	 */
	FireResult attackLocation(int col, char row);
 	
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
	boolean isValidAttackLocation(int col, char row);
 	
 	/**
 	 * Checks if the current players turn is that of player one.
 	 * @return true if the active turn is player 1.
 	 * @return false if the active turn is player 2.
 	 * @throws IllegalStateException if not in play mode or game is over
 	 */
	boolean isPlayerTurn();
 	
 	/**
 	 * Allows view of defensive boards
 	 * @param player true for player 1, false for player 2
 	 * @return an array of Strings representing player 2's attack board
 	 */
	DefenseTileStatus[][] getDefenseBoard(boolean player);
 	
 	/**
 	 * Allows view of the offensive boards
 	 * @param player true for player 1, false for player 2
 	 * @return an array of Strings representing player 2's board
 	 */
	OffensiveTileStatus[][] getOffensiveBoard(boolean player);
 	
 	/**
 	 * Tests to see if game is over
 	 * @return true if game is over and false if not
 	 */
	boolean isGameOver();
 	
 	/**
 	 * Sets board state so that game may begin anew
 	 */
	void reset();
 }
 


 


