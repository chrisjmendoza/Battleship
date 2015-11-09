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
public interface BattleshipModelInterface {
	
	/*
	 * Suggesting a HashMap to store and access Ships
	 * board represented by an array of Strings of 1 or 0 representing
	 * if the location has been hit followed by a hash code
	 * as location, hits, and ship must be stored by board
	 */
	
	/**
	 * Places Ship at the given location
	 * @param location position to place ship
	 * @param ship the type of ship being placed, used to test validity of input
	 * @throws IllegalArgumentException thrown if location is filled or not on board
	 */
	public void placeShip(int location, Ship ship);
	
	/**
	 * Sends an attack to given position
	 * @param location position of attack
	 * @throws IllegalArgumentException thrown if location is not on board or already picked
	 */
	public void attackLocation(int location);
	
	/**
	 * Allows view of player 1's board
	 * @return an array of Strings representing player 2's attack board
	 */
	public String[] getBoardStatePlayer1();
	
	/**
	 * Allows view of player 2's board
	 * @return an array of Strings representing player 2's board
	 */
	public String[] getBoardStatePlayer2();
}

/**
 * A helper class storing a ships type, length, and damage level
 */
class Ship{
	private String type;
	private int length;
	private int hits;
}
