public class BombTile extends Tile {

    public BombTile()
    {
        setOpenStatus(false);
        setIconPic();
    }
    public int open()
    {
        this.setOpenStatus(true);
        return 0; // this return value will tell the engine to end the game and uncover all tiles
    }
    protected void setIconPic() {iconPic = icons.get(BOMB);}
}
