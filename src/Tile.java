import javax.swing.JButton;
import java.util.HashMap;

public abstract class Tile extends JButton {
    protected boolean openStatus;
    protected boolean seal;
    protected String iconPic;
    private boolean sealedOnce = false;
    protected final static int BLANK = 0;
    protected final static int BOMB = -1;
    protected static HashMap<Integer, String> icons = new HashMap<>();
    static
    {
        icons.put(-1, "mine.png");
        icons.put(0, "0.png");
        icons.put(1, "1.png");
        icons.put(2, "2.png");
        icons.put(3, "3.png");
        icons.put(4, "4.png");
        icons.put(5, "5.png");
        icons.put(6, "6.png");
        icons.put(7, "7.png");
        icons.put(8, "8.png");
    }


    public void setSealedOnce() {sealedOnce = true;}
    public boolean getSealedOnce() {return sealedOnce;}
    public void setSealed(boolean setter) {seal = setter;}
    public boolean isSealed() {return seal;}
    public String getIconName() {return iconPic;}
    public boolean isOpen() {return openStatus;}
    public void setOpenStatus(boolean setter) {openStatus = setter;}

    public void addBombNeighbor() {}

    abstract void setIconPic();
    abstract int open();
}
