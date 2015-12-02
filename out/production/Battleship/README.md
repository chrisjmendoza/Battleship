# Battleship
AD310 Group Project - Battleship
Assignment 5: Battleship, Interface Design

Educational Objectives

Design subsystem-level interfaces for a larger project
Gain experience with Model-View-Controller design pattern
Instructions

In this assignment, each team will begin the implementation of the Battleship game that we examined earlier. For this phase, the teams will determine the primary boundaries in the system: between the model and the view(s) and between the model and the controller(s). The definition of the boundaries shall take the form of a Java interface for the model; specifically, the methods that will be available to the view(s) to access data needed to render the game state and the methods that will be available to the controller(s) to manipulate the game state. Please notice that the discussion leaves the possibility of multiple views and multiple controllers; for example, the two operational "modes" of the game: set-up and play, could be supported by separate controller and view subsystems, also the play mode consists of offensive and defensive grids, which could be constructed as separate view subsystems. In any case, the API for the model is the goal of this assignment.

An initial (top-level) design for the complete system forms the core needed to define the interface for the model. That is, decisions about where functionality will be implemented changes the method signatures. As an example, the locus for the various levels of input validation may change the characteristics of the model-controller interface.

Also, it is not necessary, at this point, to have made a decision about how separate the view and controller are. As noted in the in-class discussion, the view and controller may merge into a single graphical element in the final implementation. However, the interface requirements wouldn't necessarily be any different: the view-model interface still must support rendering the current game state and the controller-model interface must support manipulating the current game state. In fact, even in cases where the controller and view are constructed as independent entities, there are view-like aspects of the controller; if the availability of certain aspects of functionality change during the course of the game; the controller elements associated with unavailable operations may become hidden or disabled to indicate that the associated commands are not available.

As you go through the process of designing this interface for the model, you will probably have ideas and concepts for the implementation. Jot those down. They do not belong in the deliverable for this assignment, but you will want to capture those thoughts for later.

Notes

The board is ten by ten, rows A-J and columns 1 - 10, totaling 100 squares.
In Setup Mode, each player (user) places 5 vessels in the defensive grid:
one aircraft carrier
one battleship
one cruiser
two destroyers
Each vessel is represented by a given number of squares in the board. These squares for any given vessel are arranged in a single line of contiguous squares which does not intersect or overlap those of any other vessel in the defensive grid. The squares representing a vessel may be arranged vertically, horizontally, or diagonally.
aircraft carrier - 5 squares
battleship - 4 squares
cruiser - 3 squares
destroyer - 2 squares
In Play Mode, the players alternate turns, making "shots" at specific locations. 
For example, one player (A) makes a "shot" a some location on the board, designated by a letter (A-J) and a number (1-10). The corresponding location on other player's (B's) defensive grid is checked for a vessel in that square. The outcome of the play is a "hit" or "miss" reported back to the player A. The return status of the "shot" is recorded on A's offensive grid. All "hits" are recorded on B's defensive grid. If the "hit" marks the final unmarked square for a vessel, the "hit" return status includes an indication of the vessel type which has been sunk: "hit, you sunk my _____." If player A's "shot" was a "hit", player A's turn continues, by taking another "shot". The turn continues as long as player A makes "hits". When player A's "shot" is a "miss", player A's turn is over and it's player B's turn. Player B makes a "shot". As long as player B's "shots" are reported as "hits", player B's turn continues.
The game shall disallow making a "shot" at a location already played. That is, a player cannot make a shot at a location that has already been reported as a "miss" or "hit".
The game terminates when all of the vessels for one player have been "sunk". The other player is declared the winner.
Please note: these specifications are subject to change.

The Java interface for the Battleship model shall be documented. This documentation shall occur as JavaDoc comments for the methods in the interface. The method JavaDoc comments shall include a short description of the function of the method and any tags as appropriate: @param to explain the use of the parameter, @return to explain the return value, @throws to explain the cause for the exception which may be thrown. It is preferable to avoid exceptions.

Here is an example:

/**
 * Makes a shot during Play Mode.
 * @param row The letter indicating the row for the shot, case-sensitive
 * @param col The number indicating the column for the shot
 * @return The status of the shot. See the status constants
 * @throws IllegalStateException The game is not in Play Mode
 */
public int makeShot(char row, int col);

// return status from makeShot, a miss

public static final int MISS = 0;

//return status from makeShot, a hit, doesn't sink a ship

public static final int HIT = 1;

//return status from makeShot, a hit, sunk destroyer

public static final int SUNK_DESTROYER = 2;

//return status from makeShot, a hit, sunk cruiser

public static final int SUNK_CRUISER = 3;

//return status from makeShot, a hit, sunk battleship

public static final int SUNK_BATTLESHIP = 4;

//return status from makeShot, a hit, sunk aircraft carrier

public static final int SUNK_AIRCRAFT_CARRIER = 5;

//return status from makeShot, location was already played or was invalid

public static final int DO_OVER = -1;

Here is an alternate example for the same method. Notice for this version, there are helper types declared: a class Location and an enum Status.

/**
 * Makes a shot during Play Mode.
 * @param loc The designator for the shot
 * @return The status of the shot. See the status constants
 * @throws IllegalStateException The game is not in Play Mode
 */
public Status makeShot(Location loc);

//----- separate top-level declarations

//A helper class for location

class Location {
   /**
    * The row
    */
   char row;
   /**
    * The column
    */
   int col;
}

/**
 * Helper enumerated type for return status The SUNK_XXX
 * values indicate HIT. The current player's turn continues
 * until the return status is MISS.
 */
enum Status {
    // return status, a miss
    MISS, 
    // return status, a hit, doesn't sink a ship
    HIT, 
    // return status, a hit, sunk destroyer 
    SUNK_DESTROYER, 
    // return status, a hit, sunk cruiser
    SUNK_CRUISER, 
    // return status, a hit, sunk battleship
    SUNK_BATTLESHIP, 
    // return status, a hit, sunk aircraft carrier
    SUNK_AIRCRAFT_CARRIER, 
    // return status, location was already played or invalid
    DO_OVER
};

In this example, the Location and Status types are declare outside of the interface. Here is an example file. This class and enum type are available to the classes in the same package. This is the default, or package private, access level. It indicates a level of access not as restricted as private (this class only), while not open to everyone, public. Notice that the fields in the Location class are also package private access. They will be accessible to any class in this package. There is no compelling reason to create getter and setter (accessor and mutator) methods for these fields, since their use is limited to the current package.

For more information about package, package private access or enum types, ask in class next week.

Deliverable

One Java source code file per team, a Java interface for the model.

Some notes:

Every method, field, class, etc. shall have an appropriate JavaDoc comment, with appropriate tags.
The public interface, with its JavaDoc comment, shall be the first thing in the source code file.
The JavaDoc comment for the public interface shall include an @author tag for each member of the team. See the sample code for an example.
The relative order of the methods within the interface does not matter. It can be helpful to group them according to use.
The relative order of the helper classes within the file does not matter. It can be helpful to group them according to use.
Grading Rubric

Check
There are appropriate methods to support the view and the controller in both Setup and Play modes. There are JavaDoc comments for all of the public and package private entities in the source code.
Minus
There are minor gaps in coverage of the functionality within the source code, for example: there is no support for return status from taking a shot.
Poor
There are significant gaps in functionality covered by the interface, for example: there is no method for taking a shot.
Plus
The interface (and supporting classes / enums, if any) are thoughtfully constructed. The Java code has consistent readable style.
Consistent style would include such things as:

Uniform indent amount
Consistent placement of opening and closing punctuation: braces, parentheses, brackets
Uniform spacing
