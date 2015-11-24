/**
 * Created by Peter on 11/23/2015.
 */
public interface IBattleShipPlayViewInterface{

/**
 * The Battleship's view and controller during its play phase
 */

    /**
     * Prints current player's attack and defense boards.
     * @param player, Boolean value that determines which players board is printed. If argument is "TRUE"
     * Then print player one's attack and defense board. if argumnet is "FALSE" then print player two's 
     * attach and defense board.
     *
     */
    public void printBoard(boolean player);

    /**
     * Promps the player for an attack location. If that location is out of bounds then player is promped to
     * attack again. This method consults with model and controller to implement the attack.
     * 
     */
    public void askForFireShot();
    
    /**
     * When one of the two players have won "Game over, Admiral Player " + player + " has achieved victory!"
     * is printed in the console. Else IllegalStateException is thrown.
     */
    public void printGameOver();
}
