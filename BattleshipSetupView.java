import java.util.Scanner;

/**
 * The Controller and View for Battleship's setup phase
 * 
 * @author Casey Riggin
 * @author Christopher Mendoza
 * @author Peter Kim
 * @author Sai Chang
 */

/**
 * Allows a player to place their fleet. Asks for input on ship location
 *
 */
class BattleshipSetupView {

	private BattleshipModel model;
	private Scanner input;
	// set to true to allow diagonal placement and false otherwise
	private boolean allowDiagonal;

	public BattleshipSetupView(BattleshipModel model, boolean allowDiagonal) {
		this.allowDiagonal = allowDiagonal;
		input = new Scanner(System.in);
		this.model = model;
	}

	/**
	 * Runs through the setup phase for the current player to set up their board
	 * 
	 * @param player
	 *            the current player setting up their board
	 */
	public void collectDataForPlayer(boolean player) {
		System.out.println("");
		System.out.println("");
		System.out.println("Let's do the board set up for Player "
				+ (player ? 1 : 2) + "!");
		System.out.println("Here's your empty board for reference:");
		printBoard(player);

		// DO THIS
		// Figure out new abstraction for ships here



		for (int shipNum = 0; shipNum < model.getAllowedTypes().length; shipNum++) {
			Ship s = null;
			do {
				if (s != null) {
					System.out.println("");
					System.out
							.println("Invalid Ship Placement. Please try again. :(");
				}
				String origin = getPosition(model.getAllowedTypes()[shipNum].getShipName());
				Direction direction = getDirection(model.getAllowedTypes()[shipNum]
						.getShipName());
				s = new Ship(origin, direction, model.getRegexPattern(),
						model.getAllowedTypes()[shipNum]);
			} while (!s.isValidShipValues()
					|| !model.isValidShipPlacement(s, player));
			// Place the ship and print the board
			model.placeShip(s, player);
			printBoard(player);
		}
	}

	/**
	 * Consults with the model to find out which direction the ship is facing
	 * and at what angle.
	 * 
	 * @param ship
	 *            The ship object being passed
	 * @return Returns a Direction enum.
	 * 
	 */
	public Direction getDirection(String shipName) {
		boolean validInput = false;
		Direction direction = null;
		while (!validInput) {
			String in;
			if (allowDiagonal) {
				System.out
						.print("What direction shall we place our " + shipName
								+ "? (Example: N, NE, E, SE, S, SW, W, NW) ");
				in = input.nextLine();
				in = (in == null ? "" : in).toUpperCase().trim();
				switch (in) {
				case "N":
					direction = Direction.N;
					validInput = true;
					break;
				case "NE":
					direction = Direction.NE;
					validInput = true;
					break;
				case "E":
					direction = Direction.E;
					validInput = true;
					break;
				case "SE":
					direction = Direction.SE;
					validInput = true;
					break;
				case "S":
					direction = Direction.S;
					validInput = true;
					break;
				case "SW":
					direction = Direction.SW;
					validInput = true;
					break;
				case "W":
					direction = Direction.W;
					validInput = true;
					break;
				case "NW":
					direction = Direction.NW;
					validInput = true;
					break;
				default:
					System.out.println("Invalid direction try again.");
					break;
				}
			} else {
				System.out.print("What direction shall we place our "
						+ shipName + "? (Example: N, E, S, W) ");
				in = input.nextLine();
				in = (in == null ? "" : in).toUpperCase().trim();
				switch (in) {
				case "N":
					direction = Direction.N;
					validInput = true;
					break;
				case "E":
					direction = Direction.E;
					validInput = true;
					break;
				case "S":
					direction = Direction.S;
					validInput = true;
					break;
				case "W":
					direction = Direction.W;
					validInput = true;
					break;
				default:
					System.out.println("Invalid direction try again.");
					break;
				}
			}
		}
		return direction;
	}

	/**
	 * Consults with the model to find out where the ship is positioned on the
	 * board.
	 * 
	 * @param ship
	 *            The ship object being passed to the player for placement
	 * @return Returns a String example: "B9" translating to the position of the
	 *         ship.
	 */
	public String getPosition(String shipName) {
		System.out.print("Where shall we place " + shipName + "? (Ex: A1): ");
		return input.nextLine();
	}

	/**
	 * Prints the board according to all the ships direction and position.
	 * 
	 * @param player
	 *            Boolean value that determines which player's board is set up.
	 */
	public void printBoard(boolean player) {
		System.out.println("\n\nCurrent Setup Board for Player: "
				+ (player ? 1 : 2));
		DefenseTileStatus[][] defense = model.getDefenseBoard(player);

		System.out.println("Compass:");
		System.out.println(" N ");
		System.out.println("W+E");
		System.out.println(" S ");
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
					tile = "" + model.getShipTypeAtCell(i, j, player).getShipChar();
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
}
