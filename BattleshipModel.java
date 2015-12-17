import java.util.ArrayList;

public class BattleshipModel implements IBattleshipModel {

	/**
	 * The 1st array represents the player with player 1 being 1 and player 2
	 * being 0, the second two represent the offensive board
	 */
	private DefenseTileStatus[][][] defenseBoards;

	/**
	 * The 1st array represents the player with player 1 being 1 and player 2
	 * being 0, the second two represent the offensive board
	 */
	private OffensiveTileStatus[][][] offenseBoards;

	/**
	 * The array list contain player one's Ships
	 */
	private ArrayList<IShip> playerOneShips;

	/**
	 * The array list contain player two's Ships
	 */
	private ArrayList<IShip> playerTwoShips;

	/**
	 * Represents what state the game is in.
	 */
	private boolean isInPlayMode;

	/**
	 * True is player 1, false is player 2.
	 */
	private boolean currentPlayersTurn;

	/**
	 * stores board size
	 */
	private int boardSize;

	/**
	 * The regular expression for which cells should be accepted as valid input
	 */
	private String regexPattern;

	/**
	 * The ship types that are allowed to be placed
	 */
	private ShipType[] allowedTypes;

	/**
	 * Returns the ship types that are allowed for the given board
	 * 
	 * @return
	 */
	public ShipType[] getAllowedTypes() {
		return this.allowedTypes;
	}

	/**
	 * Gets the current board size
	 * 
	 * @return
	 */
	public int getBoardSize() {
		return this.boardSize;
	}

	/**
	 * Gets the current valid regex pattern for what is valid input for a cell
	 * on the board.
	 * 
	 * @return
	 */
	public String getRegexPattern() {
		return this.regexPattern;
	}

	// set to true if players switch every turn, false if players attack again
	// after a hit
	private boolean switchPlayer;

	/**
	 * Public Constructor gives you a fresh and new board.
	 */
	public BattleshipModel(int boardSize, boolean switchPlayer,
			ShipType[] allowedTypes) {
		this.switchPlayer = switchPlayer;
		this.boardSize = boardSize;
		this.allowedTypes = allowedTypes;
		this.reset();

		if (this.getBoardSize() < 10) {
			regexPattern = "^[A-"
					+ String.valueOf(Character.toChars('A' + (this
							.getBoardSize() - 1))) + "]([1-"
					+ this.getBoardSize() + "])$";
		} else if (this.getBoardSize() == 10) {
			// Note: Does not support a board size greater than 10
			regexPattern = "^[A-J](10|[1-9])$";
		}
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
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public ShipType getShipTypeAtCell(int in_row, int in_col, boolean player) {

		for (IShip s : (player ? this.playerOneShips : this.playerTwoShips)) {
			for (String cell : s.getConsumingCells()) {
				int row = (((int) cell.toLowerCase().toCharArray()[0]) - 'a');
				int col = Integer.parseInt(cell.substring(1)) - 1;
				if (row == in_row && col == in_col) {
					return s.getShipType();
				}
			}
		}
		System.out.println("in row: " + in_row);
		System.out.println("in col: " + in_col);
		return null;
	}

	/**
	 * @param player
	 *            the game shall start with, true being player 1, false being
	 *            player 2
	 * @throws IllegalStateException
	 *             if not all ships have been placed for both players or if game
	 *             is already in play mode
	 */
	public void moveToPlay(boolean player) {
		if (!areAllShipsPlaced(true) || !areAllShipsPlaced(false)) {
			throw new IllegalStateException(
					"Not all ships have been placed yet!");
		} else {
			this.isInPlayMode = true;
			this.currentPlayersTurn = player;
		}
	}

	/**
	 * Returns true/false if all the correct ships have been placed for the
	 * given player
	 * 
	 * @param player
	 *            true for player 1, false for player 2
	 * @return true/false if all ships have been placed for the given player
	 */
	public boolean areAllShipsPlaced(boolean player) {

		// since only valid ships can be placed, you can
		// assume that if the count of ships is five, that all ships have been
		// placed.
		ArrayList<IShip> ships = player ? this.playerOneShips
				: this.playerTwoShips;
		return ships.size() == this.getAllowedTypes().length;

	}

	/**
	 * Places Ship at the given location
	 * 
	 * @param ship
	 *            the type of ship being placed, used to test validity of input
	 * @param player
	 *            true for player 1, false for player 2
	 * @throws IllegalArgumentException
	 *             if same arguments return false for isValidShipPlacement
	 *             method
	 * @throws IllegalStateException
	 *             is not in setup mode
	 */
	public void placeShip(IShip ship, boolean player) {
		if (!isInSetupMode()) {
			throw new IllegalStateException("Cannot be in Play Mode");
		} else if (!isValidShipPlacement(ship, player)) {
			throw new IllegalArgumentException("Not a valid ship placement");

		} else {
			for (String cell : ship.getConsumingCells()) {
				int row = (((int) cell.toLowerCase().toCharArray()[0]) - 'a');
				int col = Integer.parseInt(cell.substring(1)) - 1;
				getDefenseBoard(player)[row][col] = DefenseTileStatus.SHIP;
			}

			// Add the ship to the existing ships
			(player ? this.playerOneShips : this.playerTwoShips).add(ship);
		}
	}

	/**
	 * Method checks that placement of the given ship is valid by meeting the
	 * following conditions: 1. That the ship does not overlap with any ships by
	 * sharing cells 2. That all of the pieces of a ship are on the board 3.
	 * That the ships do not overlap in an diagonal fashion 4. That the allowed
	 * amount of that ship type have not been already placed by that player.
	 * 
	 * @param ship
	 *            the type of ship being placed, used to test validity of input
	 * @param player
	 *            true for player 1, false for player 2
	 * @throws IllegalStateException
	 *             is not in setup mode
	 * @return true if placement is valid
	 */
	public boolean isValidShipPlacement(IShip ship, boolean player) {
		int prevRow = (((int) ship.getConsumingCells()[0].toLowerCase()
				.toCharArray()[0]) - 'a');
		int prevCol = Integer
				.parseInt(ship.getConsumingCells()[0].substring(1)) - 1;
		for (String cell : ship.getConsumingCells()) {
			int row = (((int) cell.toLowerCase().toCharArray()[0]) - 'a');
			int col = Integer.parseInt(cell.substring(1)) - 1;
			if (row < 0 || row > (boardSize - 1) || col < 0 || col > boardSize) {
				// Check that all parts of the ship are on the board
				return false;
			} else if (getDefenseBoard(player)[row][col] != DefenseTileStatus.OCEAN) {
				// Check that the ship does not take up the place of another
				// ship
				return false;
			}

			// checks if ship is placed SE
			// then to see if ship placement is valid
			if (prevRow < row && prevCol < col) {
				if ((getDefenseBoard(player)[row - 1][col] != DefenseTileStatus.OCEAN)
						&& (getDefenseBoard(player)[row][col - 1] != DefenseTileStatus.OCEAN))
					return false;
			}
			// checks if ship is placed SW
			// then to see if ship placement is valid
			if (prevRow < row && prevCol > col) {
				if ((getDefenseBoard(player)[row - 1][col] != DefenseTileStatus.OCEAN)
						&& (getDefenseBoard(player)[row][col + 1] != DefenseTileStatus.OCEAN))
					return false;
			}
			// checks if ship is placed NE
			// then to see if ship placement is valid
			if (prevRow > row && prevCol < col) {
				if ((getDefenseBoard(player)[row + 1][col] != DefenseTileStatus.OCEAN)
						&& (getDefenseBoard(player)[row][col - 1] != DefenseTileStatus.OCEAN))
					return false;
			}
			// checks if ship is placed NW
			// then to see if ship placement is valid
			if (prevRow > row && prevCol > col) {
				if ((getDefenseBoard(player)[row + 1][col] != DefenseTileStatus.OCEAN)
						&& (getDefenseBoard(player)[row][col + 1] != DefenseTileStatus.OCEAN))
					return false;
			}

			prevRow = row;
			prevCol = col;
		}
		return true;
	}

	/**
	 * Sends an attack to given position. If the attack returns a miss, switch
	 * the active turn
	 * 
	 * @throws IllegalStateException
	 *             if game is not in Play Mode or if game is over
	 * @throws IllegalArgumentException
	 *             if the same arguments would cause isValidAttackLocation to
	 *             return false
	 * @return returns an FireResult enum representing Status of attack
	 */
	public FireResult attackLocation(int col, char row) {
		int y = ("" + row).toLowerCase().toCharArray()[0] - 'a';
		if (this.isGameOver() || !this.isInPlayMode()) {
			throw new IllegalStateException("Game is over or not in play mode.");
		}
		// Check the position for validity and throw an exception if invalid
		if (!isValidAttackLocation(col, row)) {
			throw new IndexOutOfBoundsException(
					"That is not a valid attack position!");
		} else {
			// check the status of the attacked tile. If it is an OCEAN tile,
			// report a miss
			if (getDefenseBoard(!isPlayerTurn())[y][col - 1] == DefenseTileStatus.OCEAN) {
				getOffensiveBoard(isPlayerTurn())[y][col - 1] = OffensiveTileStatus.MISS;
				currentPlayersTurn = !currentPlayersTurn; // always swap turns
															// if its a miss
				return FireResult.MISS;
			} else {
				getOffensiveBoard(isPlayerTurn())[y][col - 1] = OffensiveTileStatus.HIT_SHIP;

				// Check to see if the hit was the last of a known ship
				boolean sunkShip = false;
				for (int i = 0; i < playerOneShips.size(); i++) {
					boolean currentShipSunk = true;
					boolean currentShipWasFiredAtThisTurn = false;
					IShip currentShip = (!this.currentPlayersTurn ? playerOneShips
							: playerTwoShips).get(i);
					String[] consumingCells = currentShip.getConsumingCells();
					for (int ii = 0; ii < consumingCells.length; ii++) {
						// if the cell on my defensive board is ocean then set
						// the ship being sunk to false
						int tempRow = (((int) consumingCells[ii].toLowerCase()
								.toCharArray()[0]) - 'a');
						int tempCol = Integer.parseInt(consumingCells[ii]
								.substring(1)) - 1;
						if (getOffensiveBoard(this.isPlayerTurn())[tempRow][tempCol] == OffensiveTileStatus.UNKNOWN) {
							currentShipSunk = false;
						}
						if (y == (tempRow) && (tempCol + 1) == col) {
							currentShipWasFiredAtThisTurn = true;
						}

					}

		
					if (currentShipSunk && currentShipWasFiredAtThisTurn) {
						// set all cells to the sunk ship type
						for (int ii = 0; ii < consumingCells.length; ii++) {
							int tempRow = (((int) consumingCells[ii]
									.toLowerCase().toCharArray()[0]) - 'a');
							int tempCol = Integer.parseInt(consumingCells[ii]
									.substring(1)) - 1;
							getOffensiveBoard(this.isPlayerTurn())[tempRow][tempCol] = OffensiveTileStatus.SUNK_SHIP;
						}
						sunkShip = true;
					}
				}

				if (switchPlayer && !isGameOver()) {
					currentPlayersTurn = !currentPlayersTurn;
				}

				if (sunkShip) {
					return FireResult.SUNK_SHIP;
				} else {
					return FireResult.HIT;
				}
			}
		}
	}

	public boolean isValidAttackLocation(int col, char row) {
		int y = ("" + row).toLowerCase().toCharArray()[0] - 'a';
		return this.getOffensiveBoard(this.isPlayerTurn())[y][col - 1] == OffensiveTileStatus.UNKNOWN;
	}

	/**
	 * Checks if the current players turn is that of player one.
	 * 
	 * @return true if the active turn is player 1. false if the active turn is
	 *         player 2.
	 */
	public boolean isPlayerTurn() {
		return this.currentPlayersTurn;
	}

	/**
	 * Allows view of defensive boards
	 * 
	 * @param player
	 *            true for player 1, false for player 2
	 * @return an array of Strings representing player 2's attack board
	 */
	public DefenseTileStatus[][] getDefenseBoard(boolean player) {
		return this.defenseBoards[player ? 1 : 0];
	}

	/**
	 * Allows view of the offensive boards
	 * 
	 * @param player
	 *            true for player 1, false for player 2
	 * @return an array of Strings representing player 2's board
	 */
	public OffensiveTileStatus[][] getOffensiveBoard(boolean player) {
		return this.offenseBoards[player ? 1 : 0];
	}

	/**
	 * Tests to see if game is over
	 * 
	 * @return true if game is over and false if not
	 */
	public boolean isGameOver() {
		// do player one
		boolean playerOneWonGame = true;
		int[][] playerOneCells = getPlayerShipCells(false);
		for (int i = 0; i < playerOneCells.length; i++) {
			int row = playerOneCells[i][0];
			int col = playerOneCells[i][1];
			if (this.getOffensiveBoard(true)[row][col] == OffensiveTileStatus.MISS
					|| this.getOffensiveBoard(true)[row][col] == OffensiveTileStatus.UNKNOWN) {
				playerOneWonGame = false;
			}
		}

		// Do player two
		boolean playerTwoWonGame = true;
		int[][] playerTwoCells = getPlayerShipCells(true);
		for (int i = 0; i < playerTwoCells.length; i++) {
			int row = playerTwoCells[i][0];
			int col = playerTwoCells[i][1];
			if (this.getOffensiveBoard(false)[row][col] == OffensiveTileStatus.MISS
					|| this.getOffensiveBoard(false)[row][col] == OffensiveTileStatus.UNKNOWN) {
				playerTwoWonGame = false;
			}
		}

		// return true if you got here
		return playerOneWonGame || playerTwoWonGame;
	}

	/**
	 * Helper method is going to get the cells that each ship for a given player
	 * is currently taking.
	 * 
	 * @param player
	 *            gets the cells that the player's ship occupies
	 * @return an array where the 1st is the ship, the second is the row than
	 *         column.
	 */
	private int[][] getPlayerShipCells(boolean player) {
		int totalLength = 0;
		for (IShip s : (player ? this.playerOneShips : this.playerTwoShips)) {
			for (String cell : s.getConsumingCells()) {
				totalLength++;
			}
		}
		int[][] shipCells = new int[totalLength][2];
		int i = 0;
		for (IShip s : (player ? this.playerOneShips : this.playerTwoShips)) {
			for (String cell : s.getConsumingCells()) {
				int row = (((int) cell.toLowerCase().toCharArray()[0]) - 'a');
				int col = Integer.parseInt(cell.substring(1)) - 1;
				shipCells[i] = new int[] { row, col };
				i++;
			}
		}
		return shipCells;
	}

	/**
	 * Allows communication of winner to the view throws an
	 * IllegalStateException if there is no winner
	 * 
	 * @return returns winner of Battleship as a boolean, true for player 1
	 *         false for 2
	 */
	public boolean getWinner() {
		if (this.isGameOver()) {
			return this.isPlayerTurn();
		} else {
			throw new IllegalStateException("Game is not yet over.");
		}
	}

	/**
	 * Sets board state so that game may begin anew
	 */
	public void reset() {
		this.offenseBoards = new OffensiveTileStatus[2][boardSize][boardSize];
		for (int i = 0; i < 2; i++) {
			for (int ii = 0; ii < boardSize; ii++) {
				for (int iii = 0; iii < boardSize; iii++) {
					this.offenseBoards[i][ii][iii] = OffensiveTileStatus.UNKNOWN;
				}
			}
		}
		this.defenseBoards = new DefenseTileStatus[2][boardSize][boardSize];
		for (int i = 0; i < 2; i++) {
			for (int ii = 0; ii < boardSize; ii++) {
				for (int iii = 0; iii < boardSize; iii++) {
					this.defenseBoards[i][ii][iii] = DefenseTileStatus.OCEAN;
				}
			}
		}
		this.playerOneShips = new ArrayList<>();
		this.playerTwoShips = new ArrayList<>();
		this.isInPlayMode = false;
	}
}
