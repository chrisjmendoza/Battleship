import java.util.ArrayList;


public class BattleshipModel implements IBattleshipModel {

	/**
	 * The 1st array represents the player with player 1 being 1 and player 2 being 0, 
	 * the second two represent the offensive board
	 */
	private DefenseTileStatus[][][] defenseBoards;
			
	/**
	 * The 1st array represents the player with player 1 being 1 and player 2 being 0, 
	 * the second two represent the offensive board
	 */
	private OffensiveTileStatus[][][] offenseBoards;
	
	/**
	 * The array list contain player one's Ships
	 */
	public ArrayList<IShip> playerOneShips;
	
	/**
	 * The array list contain player two's Ships
	 */
	public ArrayList<IShip> playerTwoShips;
	
	/**
	 * Represents what state the game is in.
	 */
	private boolean isInPlayMode;
	
	/**
	 * True is player 1, false is player 2.
	 */
	private boolean currentPlayersTurn;
	
	/**
	 * Public Constructor gives you a fresh and new board.
	 */
	public BattleshipModel() 
	{
		this.reset();
	}
	
	@Override
	public boolean isInSetupMode() {
		return !this.isInPlayMode;
	}

	@Override
	public boolean isInPlayMode() {
		return this.isInPlayMode;
	}

 	/**
 	* @param the player the game shall start with, true being player 1, false being player 2
 	* @throws IllegalStateException if not all ships have been placed for both players or if game is already in play mode
 	*/
	public void moveToPlay(boolean player) {
		if(!areAllShipsPlaced(true) || !areAllShipsPlaced(false)) {
			throw new IllegalStateException("Not all ships have been placed yet!");
		} else {
			this.isInPlayMode = true;
			this.currentPlayersTurn = player;
		}
	}

 	/**
 	* Returns true/false if all the correct ships have been placed for the given player
 	* @param player true for player 1, false for player 2
 	* @return true/false if all ships have been placed for the given player
 	*/
	public boolean areAllShipsPlaced(boolean player) {
		//since only valid ships can be placed, you can
		//assume that if the count of ships is five, that all ships have been placed.
		ArrayList<IShip> ships = player ? this.playerOneShips : this.playerTwoShips;
		return ships.size() == 5;
	}

	/**
 	 * Places Ship at the given location
 	 * @param ship the type of ship being placed, used to test validity of input
 	 * @param player true for player 1, false for player 2
 	 * @throws IllegalArgumentException if same arguments return false for isValidShipPlacement method
 	 * @throws IllegalStateException is not in setup mode
 	 */
	public void placeShip(IShip ship, boolean player) 
	{
		if(isInSetupMode()) {
			throw new IllegalStateException("Cannot be in Setup Mode");
		} else if(isValidShipPlacement(ship, player)) {
			throw new IllegalArgumentException("Not a valid ship placement");
			
		} else {
			ShipType shipType = ship.getShipType();
			DefenseTileStatus newTileType = 
				ShipType.DESTROYER == shipType ? DefenseTileStatus.SHIP_DESTROYER :
				ShipType.CRUISER == shipType ? DefenseTileStatus.SHIP_CRUISER :
				ShipType.BATTLESHIP == shipType ? DefenseTileStatus.SHIP_BATTLESHIP :
				DefenseTileStatus.SHIP_CARRIER;
				
			for(String cell : ship.getConsumingCells())
			{
				int row = (((int)cell.toLowerCase().toCharArray()[0]) - 'a' + 1);
				int col = Integer.parseInt(cell.substring(1));
				getDefenseBoard(player)[row][col] = newTileType;
			}      
		}
	}

/**
 	 * Method checks that placement of the given ship is valid by meeting the following conditions:
 	 * 1.	That the ship does not overlap with any ships by sharing cells
 	 * 2.	That all of the pieces of a ship are on the board
 	 * 3.	That the ships do not overlap in an diagonal fashion
 	 * 4.	That the allowed amount of that ship type have not been already placed by
 	 * that player.
 	 * 
 	 * @param ship the type of ship being placed, used to test validity of input
 	 * @player true for player 1, false for player 2
 	 * @throws IllegalStateException is not in setup mode
 	 * @returns true if placement is valid
 	 */
	public boolean isValidShipPlacement(IShip ship, boolean player) 
	{
		//Check that allowed of that ship type have not already been placed.
		ArrayList<IShip> sPointer = (player ? this.playerOneShips : this.playerTwoShips);
		int shipCount = 0;
		for(int i = 0; i < sPointer.size(); i++) {
			if(sPointer.get(i).getShipType() == ship.getShipType()) {
				shipCount++;
			}
		}
		
		int maxAllowed = ship.getShipType() == ShipType.DESTROYER ? 2 : 1;
		if(shipCount >= maxAllowed) 
		{
			return false;
		}
		int prevRow = -1;
		int prevCol = -1;
		for(String cell : ship.getConsumingCells())
		{
			int row = (((int)cell.toLowerCase().toCharArray()[0]) - 'a' + 1);
			int col = Integer.parseInt(cell.substring(1));
			if(row < 0 || row > 10 || col < 0 || col > 10) 
			{
				//Check that all parts of the ship are on the board
				return false;
			} else if(getDefenseBoard(player)[row][col] != DefenseTileStatus.OCEAN) 
			{
				//Check that the ship does not take up the place of another ship
				return false;
			}
			
			//Assuming 0,0 is the top left and 9,9 the bottom right
			//We should probably tidy these up
			
			//checks if ship is placed diagonally bottom on the left, top on the right,
			//then to see if ship placement is valid
			if(prevRow < row && prevCol < col) {
				if((getDefenseBoard(player)[row - 1][col] != DefenseTileStatus.OCEAN) && 
					(getDefenseBoard(player)[row][col + 1] != DefenseTileStatus.OCEAN))
					return false;
			}
			//checks if ship is placed diagonally top on the left, bottom on the right,
			//then to see if ship placement is valid
			if(prevRow < row && prevCol > col) {
				if((getDefenseBoard(player)[row - 1][col] != DefenseTileStatus.OCEAN) && 
					(getDefenseBoard(player)[row][col - 1] != DefenseTileStatus.OCEAN))
					return false;
			}
			prevRow = row;
			prevCol = col;
		}    
		return true;
	}


 	/**
 	 * Sends an attack to given position. If the attack returns a miss, switch the active turn
 	 * 
 	 * @throws IllegalStateException if game is not in Play Mode or if game is over
 	 * @throws IllegalArgumentException if the same arguments would cause isValidAttackLocation to return false
 	 * @return returns an FireResult  enum representing Status of attack
 	 */
	public FireResult attackLocation(int col, char row) {
		if(this.isGameOver() || !this.isInPlayMode()) {
			throw new IllegalStateException("Game is over or not in play mode.");
		}	
		return FireResult.HIT;
	}

	public boolean isValidAttackLocation(int col, char row) {
		int y;
		int player;
		if(row == 'A' || row == 'a')
			y = 0;
		else if(row == 'B' || row == 'b')
			y = 1;
		else if(row == 'C' || row == 'c')
			y = 2;
		else if(row == 'D' || row == 'd')
			y = 3;
		else if(row == 'E' || row == 'e')
			y = 4;
		else if(row == 'F' || row == 'f')
			y = 5;
		else if(row == 'G' || row == 'g')
			y = 6;
		else if(row == 'H' || row == 'h')
			y = 7;
		else if(row == 'I' || row =='i')
			y = 8;
		else if(row == 'J' || row == 'j')
			y = 9;
		else
			return false;
		if(col < 1 || col > 10)
			return false;
		if(currentPlayersTurn)
			player = 1;
		else
			player = 0;
		if(offenseBoards[player][y][col] == OffensiveTileStatus.UNKNOWN)
			return true;
		else
			return false;
	}

 	/**
 	 * Checks if the current players turn is that of player one.
 	 * @return true if the active turn is player 1.
 	 * @return false if the active turn is player 2.
 	 * @throws IllegalStateException if not in play mode or game is over
 	 */
	public boolean isPlayerTurn() {
		if(this.isGameOver() || !this.isInPlayMode()) {
			throw new IllegalStateException("Game is over or not in play mode.");
		}	
		return this.currentPlayersTurn;
	}

 	/**
 	 * Allows view of defensive boards
 	 * @param player true for player 1, false for player 2
 	 * @return an array of Strings representing player 2's attack board
 	 */
	public DefenseTileStatus[][] getDefenseBoard(boolean player) {
		return this.defenseBoards[player ? 1 : 0];
	}

 	/**
 	 * Allows view of the offensive boards
 	 * @param player true for player 1, false for player 2
 	 * @return an array of Strings representing player 2's board
 	 */
	public OffensiveTileStatus[][] getOffensiveBoard(boolean player) {
		return this.offenseBoards[player ? 1 : 0];
	}

 	/**
 	 * Tests to see if game is over
 	 * @return true if game is over and false if not
 	 */
	public boolean isGameOver() {
		//do player one
		int[][] playerOneCells = getPlayerShipCells(true);
		for(int i = 0; i < playerOneCells.length; i++)
		{
			int row = playerOneCells[i][0];
			int col = playerOneCells[i][1];
			if(this.getOffensiveBoard(false)[row][col] == OffensiveTileStatus.MISS || 
					this.getOffensiveBoard(false)[row][col] == OffensiveTileStatus.UNKNOWN)
			{
				return false;
			}
		}
		
		//Do player two
		int[][] playerTwoCells = getPlayerShipCells(false);
		for(int i = 0; i < playerTwoCells.length; i++)
		{
			int row = playerTwoCells[i][0];
			int col = playerTwoCells[i][1];
			if(this.getOffensiveBoard(true)[row][col] == OffensiveTileStatus.MISS || 
					this.getOffensiveBoard(true)[row][col] == OffensiveTileStatus.UNKNOWN)
			{
				return false;
			}
		}
		
		//return true if you got here
		return true;
	}

	/**
	 * Helper method is going to get the cells that each ship
	 * for a given player is currently taking. 
	 * @param player
	 * @return an array where the 1st is the ship, the second is the row than column.
	 */
	private int[][] getPlayerShipCells(boolean player) 
	{
		//We can assume length of 16 because we know the count of the
		//ships and the length of each ship.
		int[][] shipCells = new int[16][2];
		int i = 0;
		for(IShip s : (player ? this.playerOneShips : this.playerTwoShips))
		{
			for(String cell : s.getConsumingCells())
			{
				int row = (((int)cell.toLowerCase().toCharArray()[0]) - 'a' + 1);
				int col = Integer.parseInt(cell.substring(1));
				shipCells[i] = new int[] {row, col};
				i++;
			}
		}
		return shipCells;
	}
	
 	/**
 	 * Sets board state so that game may begin anew
 	 */
 	public void reset()
 	{
		this.offenseBoards = new OffensiveTileStatus [2][10][10];
		this.defenseBoards = new DefenseTileStatus [2][10][10];
		this.playerOneShips = new ArrayList<IShip>();
		this.playerTwoShips = new ArrayList<IShip>();
		this.isInPlayMode = false;
	}
}
