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
		in = new Scanner(new File("battleship.bshp"));
		} catch(FileNotFoundException e) {
			System.out.println("File not found, terminating program (looking for battleship.bshp)");
			System.exit(1);
		}
		in.nextLine();	//Skip Board Size label	
		int boardSize = Integer.parseInt(in.nextLine());
		in.nextLine(); //Skip Switch player label
		boolean switchPlayer = (in.nextLine().charAt(0) != 'f');
		in.nextLine(); //Skip Allow Diagonal label
		boolean allowDiagonal = (in.nextLine().charAt(0) != 'f');
		in.nextLine(); // Skip Ships label
		String shipConfigText = in.nextLine();
		BattleshipModel model = new BattleshipModel(boardSize, switchPlayer, ShipType.parseFromConfig(shipConfigText));
		BattleshipSetupView setup = new BattleshipSetupView(model, allowDiagonal);
		BattleshipPlayView play = new BattleshipPlayView(model);

		System.out.println("Welcome to Battleship (V2)!");
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
		
		boolean playAgain = true;
		while(playAgain) {
			setup.collectDataForPlayer(true);
			setup.collectDataForPlayer(false);
			playAgain = play.runGame();
			model.reset();
		}
		System.out.println("");
		System.out.println("Thanks for playing!");
	}
	
	
}
