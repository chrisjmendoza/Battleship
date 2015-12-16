import java.util.ArrayList;

/**
 * Class manages the dynamic nature of a ship "type". Class is used to hold general
 * information about a type from configuration to the Ship class during setup.
 * 
 * @author Casey
 */
public class ShipType {
	private String shipName;
	private int shipLength;
	private char shipChar;
	
	/**
	 * Private Constructor so the array list can map properly
	 */
	private ShipType() {}
	
	/**
	 * Constructor for setting up Ship type
	 * 
	 * @param shipName must be a non null or whitespace string representing the full name of the ship
	 * @param shipLength must be an int greater than zero for the ship length
	 * @param shipChar the character that should be used to represent the ship on the printed board
	 */
	public ShipType(String shipName, int shipLength, char shipChar)
	{
		if (shipLength < 1) {
			throw new IllegalArgumentException(
					"Length must be greater than zero");
		}
		if (shipName == null || shipName.trim() == "") {
			throw new IllegalArgumentException(
					"Name must be non null or whitespace");
		}
		this.shipName = shipName;
		this.shipLength = shipLength;
		this.shipChar = shipChar;
	}
	
	/**
	 * This method generates an array of the given object type
	 * from the configuration text
	 * 
	 * Should be set up in the following manner
	 * ShipName|Length|Char|Frequency  - with one ship per line
	 * @param configText
	 * @return
	 */
	public static ShipType[] parseFromConfig(String configText)
	{
		ArrayList<ShipType> dynamicTypes = new ArrayList<ShipType>();
		String[] brokenLines = configText.split("\n");
		for(int i = 0; i< brokenLines.length; i++)
		{
			String[] brokenLine = brokenLines[i].split("\\|");
			String shipName = brokenLine[0];
			int shipLength = Integer.parseInt(brokenLine[1]);
			char shipChar = brokenLine[2].charAt(0);
			int frequency = Integer.parseInt(brokenLine[3]);
			for(int f = 1; f <= frequency; f++)
			{
				dynamicTypes.add(new ShipType(shipName,shipLength,shipChar));
			}
			
		}
		return dynamicTypes.toArray(new ShipType[0]);
	}
	
	/**
	 * Gets the current ship type's name
	 * @return
	 */
	public String getShipName() {
		return this.shipName;
	}
	
	/**
	 * Returns the current ship type's length
	 * @return
	 */
	public int getShipLength() {
		return this.shipLength;
	}
	
	/**
	 * Returns the current ships type
	 * @return
	 */
	public char getShipChar() {
		return this.shipChar;
	}
}
