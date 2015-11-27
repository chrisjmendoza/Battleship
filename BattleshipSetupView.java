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
 * Allows a player to place their fleet Asks for input on ship location and
 * sends
 * 
 * @param player
 *            set to true to setup for player 1 and false for player 2
 * @param model
 *            pass the model the setup is to interact with
 */
public class BattleshipSetupView {

	private BattleshipModel model;
	private Scanner input;

	public BattleshipSetupView(BattleshipModel model) {
		input = new Scanner(System.in);
		this.model = model;
	}

	public void collectDataForPlayer(boolean player) {
		System.out.println("");
		System.out.println("");
		System.out.println("Let's do the board set up for Player "
				+ (player ? 1 : 2) + "!");
		System.out.println("Here's your empty board for reference:");
		printBoard(player);
		ShipType[] toPlace = new ShipType[] { ShipType.AIRCRAFT_CARRIER,
				ShipType.BATTLESHIP, ShipType.CRUISER, ShipType.DESTROYER,
				ShipType.DESTROYER };
		for (int shipNum = 0; shipNum < toPlace.length; shipNum++) {
			Ship s = null;
			do {
				if (s != null) {
					System.out.println("");
					System.out
							.println("Invalid Ship Placement. Please try again. :(");
				}
				String origin = getPosition(toPlace[shipNum]);
				Direction direction = getDirection(toPlace[shipNum]);
				s = new Ship(toPlace[shipNum], origin, direction);
			} while (!s.isValidShipValues()
					|| !model.isValidShipPlacement(s, player));
			// Place the ship and
			model.placeShip(s, player);
			printBoard(player);
		}
	}

	/**
	 * Consults with the model to find out which direction the ship is facing
	 * and at what angle.
	 * 
	 * @param shipName
	 *            Name of the ship being set.
	 * @param length
	 *            Length of the ship being set.
	 * @return Returns a Direction enum.
	 * 
	 */
	public Direction getDirection(ShipType ship) {
		boolean validInput = false;
		Direction direction = null;
		while (!validInput) {
			String in;
			System.out.print("What direction shall we place our "
					+ ship.toString()
					+ "? (Example: N, NE, E, SE, S, SW, W, NW) ");
			in = input.nextLine();
			in = (in == null ? "" : in).toUpperCase().trim();
			if (in.equals("N")) {
				direction = Direction.N;
				validInput = true;
			} else if (in.equals("NE")) {
				direction = Direction.N;
				validInput = true;
			} else if (in.equals("E")) {
				direction = Direction.E;
				validInput = true;
			} else if (in.equals("SE")) {
				direction = Direction.SE;
				validInput = true;
			} else if (in.equals("S")) {
				direction = Direction.S;
				validInput = true;
			} else if (in.equals("SW")) {
				direction = Direction.SW;
				validInput = true;
			} else if (in.equals("W")) {
				direction = Direction.W;
				validInput = true;
			} else if (in.equals("NW")) {
				direction = Direction.NW;
				validInput = true;
			} else {
				System.out.println("Invalid direction try again.");
			}
		}
		return direction;
	}

	/**
	 * Consults with the model to find out where the ship is positioned on the
	 * board.
	 * 
	 * @param shipName
	 *            Name of the ship being set.
	 * @param length
	 *            Length of the ship being set.
	 * @return Returns a String example: "B9" translating to the position of the
	 *         ship.
	 */
	public String getPosition(ShipType ship) {
		System.out.print("Where shall we place " + ship.toString()
				+ "? (Ex: A1): ");
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
		for (int i = 0; i < 10; i++) {
			System.out.println("  +---+---+---+---+---+---+---+---+---+---+");
			for (int j = 0; j < 10; j++) {
				String tile = "";
				switch (defense[i][j]) {
				case OCEAN:
					tile = "~";
					break;
				case SHIP_DESTROYER:
					tile = "D";
					break;
				case SHIP_CRUISER:
					tile = "C";
					break;
				case SHIP_BATTLESHIP:
					tile = "B";
					break;
				case SHIP_CARRIER:
					tile = "C";
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
		System.out.println("  +---+---+---+---+---+---+---+---+---+---+");
		System.out.println("    1   2   3   4   5   6   7   8   9   10 ");
	}
}