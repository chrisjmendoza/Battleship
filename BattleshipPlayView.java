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

/**
 * The Battleship's view and controller during its play phase
 * 
 * @author Casey Riggin
 * @author Christopher Mendoza
 * @author Peter Kim
 * @author Sai Chang
 */
public class BattleshipPlayView {
	private BattleshipModel model;
	boolean currentPlayer;
	Scanner input;
	
	/**
	 * Constructor for the Battleship's play view
	 * @param model the model that the view and controller interact with
	 */
	public BattleshipView(BattleshipModel model) {
		this.model = model;
		input = new Scanner(System.in); // scanner wow!!
	}
	
	/**
	 * Prints current player's attack and defense boards
	 * @param player true to set current player to 1 false to 2
	 */
	public void printBoard(boolean player) {
		OffensiveTileStatus[][] offense = model.getOffensiveBoard(player);
		DefenseTileStatus[][] defense = model.getDefenseBoard(player);
		//Prints Offense board
		System.out.println("\n\nEnemy's Fleet\n");
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				System.out.println("+---+");
			}
			for(int j = 0; j < 10; j++) {
				String tile;
				switch (offense[j][i]) {
		        	case UNKNOWN:
		        		tile = " ";
		                break;
		            case MISS:
		            	tile = "O";
		                break;        
		            case HIT_SHIP:
		            	tile = "X";
		                break;
		            case SUNK_DESTROYER:
		            	tile = "D";
		                break;
		            case SUNK_CRUISER:
		            	tile = "C";
		                break;
		            case SUNK_BATTLESHIP:
		            	tile = "B";
		                break;
		            case SUNK_CARRIER:
		            	tile = "C";
		                break;
		        }
				System.out.print("| " + tile + " ");
			}
			System.out.println("|");
		}
		
		//Prints Defense board
		System.out.println("\n\nYour Fleet\n");
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				System.out.println("+---+");
			}
			for(int j = 0; j < 10; j++) {
				String tile;
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
    
	/**
	 * Asks for the shot, displays a message if this is a retry.
	 * @param retry True if this attempt is a retry attempt.
	*/
	public void askForFireShot(boolean retry) {
		boolean attack = true;
		char row;
		int column;
		int player;
		currentPlayer = model.isPlayerTurn();
		if(currentPlayer) {
			player = 1;
		} else {
			player = 2;
		}
		while(attack) {
			System.out.println("Admiral Player " + player + ", where shall we attack: ");
			String place = input.nextLine();
			row = place.charAt(0);
			int length = place.length();
			column = Integer.parseInt(place.substring(1, length));
			if(model.isValidAttackLocation(column, row)) {
				model.attackLocation(column, row);
				System.out.println("Attacking row " + row + ", column " + column);
				attack = false;
			} else {
				System.out.println("Invalid attack location, try again.");
			}
		}
	}
    
	/**
	 * Prints a game over message given the end state
	 */
	public void printGameOver() {
		if(model.isGameOver()) {
			int player;
			if(currentPlayer) {
				player = 1;
			} else {
				player = 2;
			}
			System.out.println("Game over, Admiral Player " + player + " has achieved victory!");
		} else {
			throw new IllegalStateException(); 
		}
	}
}