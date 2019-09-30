public class AdjacentTile extends Tile {
    private int bombNeighbors = 1;
    public void addBombNeighbor()
    {
        ++bombNeighbors;
        setIconPic();
    }
    public int getBombNeighbors() {return bombNeighbors;}
    public AdjacentTile()
    {
        setOpenStatus(false);
        setIconPic();
    }
    protected void setIconPic() {iconPic = icons.get(bombNeighbors);}
    public int open()
    {
        if (!this.isOpen())
        {
            this.setOpenStatus(true);
        }
        return 1; // "do nothing" return value
    }
}
