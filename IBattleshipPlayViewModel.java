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
 public interface IBattleshipPlayViewModel {
    
    public void printBoard(boolean player, IBattleshipBoard b);
    
    /**
      * Asks for the shot, displays a message if this is a retry.
      * @param retry True if this attempt is a retry attempt.
      */
    public void askForFireShot(boolean retry);
    
    public void printGameOver(IBattleshipBoard b);
 }
