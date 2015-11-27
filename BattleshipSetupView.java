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
 * Allows a player to place their fleet
 * Asks for input on ship location and sends 
 * @param player set to true to setup for player 1 and false for player 2
 * @param model pass the model the setup is to interact with
*/
public class BattleshipSetupView {
			
	private BattleshipModel model;
	private Scanner input;
	
	public BattleshipSetupView(BattleshipModel model) {
		input = new Scanner(System.in);
	}
	
	
	
	
	/**
	 * Consults with the model to find out which direction the ship is facing and at what angle.
	 * @param shipName Name of the ship being set.
	 * @param length Length of the ship being set.
	 * @return Returns a Direction enum.
	 * 
	*/
	public Direction getDirection(ShipType ship) {
		boolean validInput = false;
		Direction direction = null;
		while(!validInput) {
			String in;
			System.out.print("What direction shall we place our " + ship.toString() + "? (Example: N, NE, E, SE, S, SW, W, NW) ");
			in = input.nextLine();
			in = (in == null ? "" : in).toUpperCase().trim();
			if(in.equals("N")) {
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
	 * Consults with the model to find out where the ship is positioned on the board.
	 * @param shipName Name of the ship being set.
	 * @param length Length of the ship being set.
	 * @return Returns a String example: "B9" translating to the position of the ship.
	 */
	public String getPosition(ShipType ship) {
		System.out.print("Where shall we place " + ship.toString() + "? (Ex: A1): ");
		return input.nextLine();
	}

	/**
	 * Prints the board according to all the ships direction and position.
	 * @param player Boolean value that determines which player's board is set up.
	 */
	public void printBoard(boolean player) {
		System.out.println("\n\nCurrent board\n");
		DefenseTileStatus[][] defense = model.getDefenseBoard(player);
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				System.out.println("+---+");
			}
			for(int j = 0; j < 10; j++) {
				String tile = "";
				switch (defense[j][i]) {
		        	case OCEAN:
		        		tile = " ";
		                break;
		            case MISS:
		            	tile = "O";
		                break;        
		            case HIT:
		            	tile = "X";
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
				System.out.print("| " + tile + " ");
			}
			System.out.println("|");
		}
	}
}