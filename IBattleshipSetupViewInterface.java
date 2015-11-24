public interface IBattleshipSetupViewInterface {

    /**
     * Consults with the model to find out which direction the ship is facing and at what angle.
     * @param shipName Name of the ship being set.
     * @param length Length of the ship being set.
     * @return Returns a Direction enum.
     * 
     */
    public Direction getDirection(String shipName, int length);

    /**
     * Consults with the model to find out where the ship is positioned on the board.
     * @param shipName Name of the ship being set.
     * @param length Length of the ship being set.
     * @return Returns a String example: "B9" translating to the position of the ship.
     */
    public String getPosition(String shipName, int length);

    /**
     * Prints the board according to all the ships direction and position.
     * @param player Boolean value that determines which player's board is set up.
     */
    public void printBoard(boolean player);

}
