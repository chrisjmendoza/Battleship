import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main kicker class to start off the battleship game.
 * 
 * @author Casey Riggin
 * @author Christopher Mendoza
 * @author Peter Kim
 * @author Sai Chang
 *
 */

class BattleshipKicker {
	public static void main(String[] args) {
		
		Scanner in = null;
		
		try {
		in = new Scanner(new File("config.txt"));
		} catch(FileNotFoundException e) {
			System.out.println("File not found, terminating program");
			System.exit(1);
		}
		int boardSize;
		boolean switchPlayer;
		boolean allowDiagonal;
		
		in.nextLine();		
		boardSize = Integer.parseInt(in.nextLine());
		
		in.nextLine();
		switchPlayer = (in.nextLine().charAt(0) != 'f');
		
		in.nextLine();
		allowDiagonal = (in.nextLine().charAt(0) != 'f');
		
		in.nextLine();
		while(in.hasNextLine()) {
			//line initialized with ship size as a String
			String line = in.nextLine();
			//line stores ship name
			line = in.nextLine();
			//line stores ship symbol
			line = in.nextLine();
		}
		
		BattleshipModel model = new BattleshipModel(boardSize, switchPlayer);
		BattleshipSetupView setup = new BattleshipSetupView(model, boardSize, allowDiagonal);
		BattleshipPlayView play = new BattleshipPlayView(model, boardSize);

		System.out.println("Welcome to Battleship!");
		System.out.println("Created by: Casey Riggin, Christopher Mendoza, Peter Kim, Sai Chang");
		System.out.println("AKA: Team Rocket.");
		System.out.println("Class: AD310");
		System.out.println("Quarter: Fall 2015");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");

		//Go through player one

		boolean playAgain = true;
		while(playAgain) {
			setup.collectDataForPlayer(true);
			setup.collectDataForPlayer(false);
			playAgain = play.runGame();
			model.reset();
		}
	}
	
	
}
