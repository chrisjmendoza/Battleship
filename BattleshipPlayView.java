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
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * The Battleship's view and controller during its play phase
 * 
 * @author Casey Riggin
 * @author Christopher Mendoza
 * @author Peter Kim
 * @author Sai Chang
 */
class BattleshipPlayView {
	private BattleshipModel model;
	Scanner input;

	/**
	 * Runs a game of Battleship and prompts user to play again
	 * 
	 * @return returns true if Battleship will be played again or false
	 *         otherwise
	 */
	public boolean runGame() {
		model.moveToPlay(true);
		boolean playAgain = true;
		while (!model.isGameOver()) {
			printBoard(model.isPlayerTurn());
			askForFireShot();
		}

		printGameOver();

		boolean askPrompt = true;
		while (askPrompt) {
			System.out.println("Play Again? y/n ");
			String yes = input.nextLine();
			if (yes.equalsIgnoreCase("n")) {
				playAgain = false;
				askPrompt = false;
			}
			if (yes.equalsIgnoreCase("y")) {
				askPrompt = false;
			} 
			
			if(askPrompt)
			{
				System.out.println("Sorry, you're mumbling. I'll ask again.");
			}
		}

		return playAgain;
	}

	/**
	 * Constructor for the Battleship's play view
	 * 
	 * @param model
	 *            the model that the view and controller interact with
	 */
	public BattleshipPlayView(BattleshipModel model) {
		this.model = model;
		input = new Scanner(System.in);
	}

	/**
	 * Prints current player's attack and defense boards
	 * 
	 * @param player
	 *            true to set current player to 1 false to 2
	 */
	public void printBoard(boolean player) {
		OffensiveTileStatus[][] offense = model.getOffensiveBoard(player);
		DefenseTileStatus[][] defense = model.getDefenseBoard(player);
		// Prints Offense board
		System.out.println("\n\nPlayer " + (player ? "1" : "2")
				+ "'s Offensive Grid\n");
		for (int i = 0; i < model.getBoardSize(); i++) {
			System.out.print("  ");
			for (int j = 0; j < model.getBoardSize(); j++) {
				System.out.print("+---");
			}
			System.out.println("+");
			for (int j = 0; j < model.getBoardSize(); j++) {
				String tile = " ";
				switch (offense[i][j]) {
				case UNKNOWN:
					tile = "~";
					break;
				case MISS:
					tile = "O";
					break;
				case HIT_SHIP:
					tile = "X";
					break;
				case SUNK_SHIP:
					tile = ""
							+ model.getShipTypeAtCell(i, j, !player)
									.getShipChar();
					break;
				}
				if (j == 0) {
					System.out.print((char) ('A' + i));
					System.out.print(" ");
				}
				System.out.print("| " + tile + " ");
			}
			System.out.println("|");
		}
		System.out.print("  ");
		for (int j = 0; j < model.getBoardSize(); j++) {
			System.out.print("+---");
		}
		System.out.println("+");
		System.out.print("   ");
		for (int i = 1; i <= model.getBoardSize(); i++) {
			if (i < 10) {
				System.out.print(" " + i + "  ");
			} else {
				System.out.print(" " + i + " ");
			}
		}

		// Prints Defense board
		System.out.println("\n\nPlayer " + (player ? "1" : "2")
				+ "'s Defensive Grid\n");
		for (int i = 0; i < model.getBoardSize(); i++) {
			System.out.print("  ");
			for (int j = 0; j < model.getBoardSize(); j++) {
				System.out.print("+---");
			}
			System.out.println("+");
			for (int j = 0; j < model.getBoardSize(); j++) {
				String tile = "";
				switch (defense[i][j]) {
				case OCEAN:
					tile = "~";
					break;
				case SHIP:
					tile = ""
							+ model.getShipTypeAtCell(i, j, player)
									.getShipChar();
					break;
				}
				if (j == 0) {
					System.out.print((char) ('A' + i));
					System.out.print(" ");
				}
				System.out.print("| " + tile + " ");
			}
			System.out.println("|");
		}
		System.out.print("  ");
		for (int j = 0; j < model.getBoardSize(); j++) {
			System.out.print("+---");
		}
		System.out.println("+");
		System.out.print("   ");
		for (int i = 1; i <= model.getBoardSize(); i++) {
			if (i < 10) {
				System.out.print(" " + i + "  ");
			} else {
				System.out.print(" " + i + " ");
			}
		}
		System.out.println("");
	}

	/**
	 * Asks for the shot from the current player
	 */
	public void askForFireShot() {
		boolean attack = true;
		char row;
		int column;
		int player;
		if (model.isPlayerTurn()) {
			player = 1;
		} else {
			player = 2;
		}
		while (attack) {
			System.out.println("Admiral Player " + player
					+ ", where shall we attack: ");
			String place = input.nextLine();
			place = place == null ? "" : place.toUpperCase().trim(); // make the
																		// response
																		// not
																		// case
																		// sensitive.
			if (Pattern.matches(model.getRegexPattern(), place)) {
				row = place.charAt(0);
				column = Integer.parseInt(place.substring(1));
				if (model.isValidAttackLocation(column, row)) {
					System.out.println("Attacking row " + row + ", column "
							+ column);
					FireResult fireResult = model.attackLocation(column, row);
					if (FireResult.MISS != fireResult) {
						// check if ship sunk and print appropriate message
						if (FireResult.HIT != fireResult) {
							// exit the method now that the game is over.
							// Figure out what kind of ship is at the given
							// location and get its name
							String shipName = model.getShipTypeAtCell(
									("" + row).toUpperCase().charAt(0) - 'A',
									column - 1, player != 1).getShipName();
							System.out.println("Admiral "
									+ (!model.isPlayerTurn() ? "1" : "2")
									+ " says: Hit.. You sunk a my " + shipName
									+ "!");
							if (model.isGameOver()) {
								return;
							}
						} else {
							System.out.println("Hit!");
						}
					} else {
						System.out.println("The attack was a miss...");
					}
					attack = false;
				} else {
					System.out.println("Invalid attack location, try again.");
				}
			} else {
				System.out.println("Invalid attack location, try again.");
			}
		}
	}

	/**
	 * Prints a game over message given the end state
	 */
	public void printGameOver() {
		if (model.isGameOver()) {
			int player;
			player = model.getWinner() ? 1 : 2;
			System.out.println("Game over, Admiral Player " + player
					+ " has achieved victory!");
		} else {
			throw new IllegalStateException();
		}
	}
}
