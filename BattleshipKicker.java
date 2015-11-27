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
public class BattleshipKicker {
	public static void main(String[] args) {

		BattleshipModel model = new BattleshipModel();
		BattleshipSetupView setup = new BattleshipSetupView(model);
		BattleshipPlayView play = new BattleshipPlayView(model);

		System.out.println("Welcome to Battleship!");
		System.out
				.println("Created by: Casey Riggin, Christopher Mendoza, Peter Kim, Sai Chang");
		System.out.println("AKA: Team Rocket.");
		System.out.println("Class: AD310");
		System.out.println("Quarter: Fall '15");

		// For player one.
		System.out.println("");
		System.out.println("");
		System.out.println("Let's start with the board setup for Player 1!");

		// Start with aircraft carrier
		ShipType[] toPlace = new ShipType[] { ShipType.AIRCRAFT_CARRIER,
				ShipType.BATTLESHIP, ShipType.CRUISER, ShipType.DESTROYER,
				ShipType.DESTROYER };
		for (int person = 1; person <= 2; person++) {
			for (int shipNum = 0; shipNum < toPlace.length; shipNum++) {
				Ship s = null;
				do {
					if (s != null) {
						System.out.println("");
						System.out
								.println("Invalid Ship Placement. Please try again. :(");
					}
					String origin = setup.getPosition(toPlace[shipNum]);
					Direction direction = setup.getDirection(toPlace[shipNum]);
					s = new Ship(toPlace[shipNum], origin, direction);
				} while (!s.isValidShipValues()
						|| !model.isValidShipPlacement(s, person == 1 ? true
								: false));
				
				//Place the ship and 
				model.placeShip(s, person == 1);
				setup.printBoard(person == 1);
			}
		}
	}
}
