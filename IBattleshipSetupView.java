 /**
  * The Controller and View for Battleship's setup phase
  * 
  * @author Casey Riggin
  * @author Christopher Mendoza
  * @author Peter Kim
  * @author Sai Chang
  */
 public interface IBattleshipSetupView {
	 
	 
	 /**
	  * Allows a player to place their fleet
	  * Asks for input on ship location and sends information to model
	  * Loops through until desired ships are placed
	  * 
	  * Checks for valid character input and formats it before sending 
	  * given input to the model
	  * 
	  * @param player set to true to setup for player 1 and false for player 2
	  * @param model pass the model the setup is to interact with
	  */
	 public void deployFleet(boolean player, BattleshipModel model);
	 
	 /**
	  * Gives a visual representation of the current board to ease in ship placement
	  * Similar to the print defense board part of the PlayView's printBoard
	  * @param player set to true to setup for player 1 and false for player 2
	  */
	 public void printBoard(boolean player);
}
