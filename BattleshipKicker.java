import java.util.ArrayList;

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
		int boardSize = 10;
		boolean switchPlayer = false;
		boolean allowDiagonal = false;
		
		
		BattleshipModel model = new BattleshipModel(boardSize,switchPlayer );
		BattleshipSetupView setup = new BattleshipSetupView(model, allowDiagonal);
		BattleshipPlayView play = new BattleshipPlayView(model);

		System.out.println("Welcome to Battleship (V 2)!");
		System.out
				.println("Created by: Casey Riggin, Christopher Mendoza, Peter Kim, Sai Chang");
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
