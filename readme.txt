This is an implementation of minesweeper.

STARTING THE GAME:
- Run Minesweeper.jar (\out\artifacts\Minesweeper_jar) to start the game.
- To start a new game, exit out of the current one and run jar again

HOW TO PLAY:
- Left click a covered tile to uncover it
- Right click a covered tile to flag it
- Right click a flagged tile to unflag it
- You win when all mines have been flagged and all non-mine tiles have been uncovered
- You lose if you left click a mine tile

:::DOCUMENTATION:::

//gui.java//

This file contains the GUI code for the game. It handles interactions with the user, listening for click events
    and uncovering the appropriate tiles.

Here is how it works:
    1) Create a virtual grid of Tile objects using the VirtualGrid data type
    2) Create a GUI representation of the grid, listen for user clicks
    3) When a user clicks on a tile, the GUI takes the appropriate action
    4) Since the tiles are implemented as JButtons, the "uncover" operation simply sets the icon of the
       current JButton to whatever it should be. For example, if the cell being uncovered is next to 1 mine,
       the "uncover" ("open" in the actual code) method simply sets the icon of the JButton to a picture of a 1.



//VirtualGrid.java//

This file defines the VirtualGrid data type used to store a grid of Tile objects.

Its constructor takes three parameters:
    1) Number of rows in the grid
    2) Number of columns in the grid
    3) Number of mines in the grid

During construction, the appropriate number of mines are set randomly throughout the grid. The mines' locations are stored
in an array. Tiles adjacent to mines are then set according to the location of the mine tiles (this step utilizes the mine
location array). Finally, the blank tiles are set by iterating through the whole grid and inserting a blank tile whenever
the cell is empty (null).

//Tile.java//

The tile class is an abstract class that contains methods and constants needed by the BombTile, AdjacentTile, and
SafeTile classes. Here are some things to take note of:

    1) There is a static hashmap called "icons" that contains the filenames for the icons used on each tile.
       This is utilized by the GUI class to obtain the proper picture file to set as an icon for each uncovered tile.
    2) There is a boolean called "isOpen" that tells the GUI whether or not a given tile has been uncovered yet
    3) Each tile subtype should implement the "open()" method. This method returns a value to the GUI to indicate
       what action the GUI should take after uncovering the tile. For instance, a value of 0 would indicate a mined
       tile has been uncovered, and the game should end.

//BombTile.java//

Its open() method returns 0, which tells the GUI to end the game and reveal all tiles still covered.

//AdjacentTile.java//

Its open() method returns 1, which tells the GUI to do nothing after opening. When an adjacent tile is uncovered,
nothing else happens.
This class also contains a counter variable called "bombNeighbors" to store the number of mines that are next to
each AdjacentTile.

//SafeTile.java//

Its open() method returns 2, which tells the GUI to open up all tiles around it.


