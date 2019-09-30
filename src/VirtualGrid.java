import java.util.Random;

public class VirtualGrid
{
    private Tile[][] vGrid;
    private Point[] bombLocations;
    private int rows, cols;
    private int numberOfBombs;
    private int numberNotBombs;

    // Used in GUI to set win conditions
    public int getNumberOfBombs() {return numberOfBombs;}
    public int getNumberNOTBombs() {return numberNotBombs;}

    // Constructs a virtual grid
    // Set bomb tiles, then set adjacent tiles, then set safe tiles
    public VirtualGrid(int row, int col, int numBombs)
    {
        rows = row;
        cols = col;
        vGrid = new Tile[rows][cols];
        numberOfBombs = numBombs;
        numberNotBombs = row * col - numBombs;
        bombLocations = setBombTiles(numBombs);
        for (int i = 0; i < bombLocations.length; i++)
        {
            int x = bombLocations[i].getX();
            int y = bombLocations[i].getY();
            setAdjacentTiles(x,y);
        }
        setSafeTiles();
    }

    public Tile getTile(int x, int y) {return vGrid[x][y];}

    // generate random indexes and create bombTiles in these indexes
    // if there is already a bomb tile there, decrement counter and get a new location
    private Point[] setBombTiles(int numBombs)
    {
        int x, y;
        bombLocations = new Point[numBombs];
        Random randos = new Random();
        for (int i = 0; i < numBombs; i++)
        {
            x = randos.nextInt(rows);
            y = randos.nextInt(cols);
            bombLocations[i] = new Point();
            bombLocations[i].setX(x);
            bombLocations[i].setY(y);
            if (vGrid[x][y] == null)
            {
                vGrid[x][y] = new BombTile();
            } else { --i;}
        }
        return bombLocations;
    }

    // Iterate through tiles adjacent to x and y (location of a bomb tile)
    // Check what's there - if nothing, create an adjacent tile; if adjacentTile, increment bombNeghbors
    // if bombTile, do nothing
    private void setAdjacentTiles(int x, int y)
    {
        for (int i = x - 1; i <= x + 1; i++) {
            if (i >= 0 && i < rows) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (j >= 0 && j < cols) {
                        if (vGrid[i][j] == null) {vGrid[i][j] = new AdjacentTile();}
                        else {vGrid[i][j].addBombNeighbor();}
                    }
                }
            }
        }
    }

    // Sets a SafeTile wherever there isn't already a bomb or AdjacentTile
    private void setSafeTiles()
    {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (vGrid[i][j] == null) vGrid[i][j] = new SafeTile();
            }
        }
    }

    // Data type that stores x and y coordinates - used to keep track of mine locations
    private class Point
    {
        private int x, y;

        public int getX() {return x;}
        public void setX(int setx) {x = setx;}
        public int getY() {return y;}
        public void setY(int sety) {y = sety;}
    }
}
