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
		// TODO Auto-generated method stub
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

	@Override
	public void placeShip(int col, char row, IShip ship, boolean player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValidShipPlacement(int col, char row, IShip ship,
			boolean player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int attackLocation(int col, char row) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isValidAttackLocation(int col, char row) {
		// TODO Auto-generated method stub
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

