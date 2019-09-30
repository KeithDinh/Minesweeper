public class SafeTile extends Tile
{
    public SafeTile() {
        setOpenStatus(false);
        setIconPic();
    }

    public int open() {
        if (!isOpen()) setOpenStatus(true);
        return 2;
    }

    protected void setIconPic() {iconPic = icons.get(BLANK);}
}
