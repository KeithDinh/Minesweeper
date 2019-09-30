/* **************************************************************************
 * **************************** JAVA Minesweeper ****************************
 *                    Trevor Rybicki, You You, Kiet Dinh
 * ****************************     COSC 1430    ****************************
 * ***************************  Intro Programming ***************************
 * **************************************************************************
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
public class gui {
    private JFrame mainFrame;
    private VirtualGrid mineGrid;
    private tileListener tListener = new tileListener();
    private int numCorrectlyFlagged = 0;
    private int notBombsOpened = 0;

    public gui()
    {
        mineGrid = new VirtualGrid(10,10,12);
        prepareGUI();
    }

    private void prepareGUI()
    {
        mainFrame = new JFrame("Minesweeper");
        mainFrame.setSize(400,400);
        Container cp = mainFrame.getContentPane();
        cp.setLayout(new GridLayout(10, 10,0,0));

        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                mineGrid.getTile(i,j).addMouseListener(tListener);
                mineGrid.getTile(i,j).setBackground(Color.GRAY);
                mineGrid.getTile(i,j).setOpaque(true);
                mineGrid.getTile(i,j).setBorder(BorderFactory.createLineBorder(Color.black));
                cp.add(mineGrid.getTile(i,j));
            }
        }
        cp.setPreferredSize(new Dimension(400, 400));
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private class tileListener implements MouseListener
    {
        public void mouseClicked(MouseEvent e) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (mineGrid.getTile(i,j) == e.getSource()) {
                        Tile currentTile = mineGrid.getTile(i,j);
                        if ((e.getButton() == MouseEvent.BUTTON1) && !currentTile.isSealed()) {
                            openTile(i,j);
                            currentTile.removeMouseListener(tListener);
                        }
                        if (e.getButton() == MouseEvent.BUTTON3) {

                            if (currentTile.isSealed()) {
                                currentTile.setText("");
                                currentTile.setSealed(false);
                                if (currentTile instanceof BombTile) numCorrectlyFlagged--;
                            } else {
                                currentTile.setSealed(true);
                                if (currentTile instanceof BombTile)
                                    numCorrectlyFlagged++;
                                if (!currentTile.getSealedOnce()) {currentTile.setSealedOnce();}
                                currentTile.setText("X");

                            }
                        }

                        if ((mineGrid.getNumberOfBombs() == numCorrectlyFlagged) &&
                                (mineGrid.getNumberNOTBombs() == notBombsOpened)) {
                            JFrame endGame = new JFrame();
                            JOptionPane.showMessageDialog(endGame, "You win!");
                        }
                    }
                }
            }
        }
        public void mousePressed(MouseEvent e){}
        public void mouseReleased(MouseEvent e){}
        public void mouseEntered(MouseEvent e){}
        public void mouseExited(MouseEvent e){}
    }

    private void openTile(int x, int y)
    {
        int doNext = 1;
        if (!mineGrid.getTile(x,y).isOpen()) {
            {
            doNext = mineGrid.getTile(x, y).open();
            notBombsOpened++;}
        }
        mineGrid.getTile(x,y).setIcon(new ImageIcon(getClass().getResource(mineGrid.getTile(x,y).getIconName())));
        mineGrid.getTile(x,y).setBackground(Color.WHITE);
        switch (doNext)
        {
            case 0:
                revealAll();
                JFrame endGame = new JFrame();
                JOptionPane.showMessageDialog(endGame, "Game over");
                break;
            case 1:
                break;
            case 2:
                for (int i = x-1; i <= x+1; i++) {
                    for (int j= y-1; j <= y+1; j++) {
                        if (i >= 0 && i <= 9 && j >=0 && j <=9)
                            if (!mineGrid.getTile(i,j).isOpen() && !mineGrid.getTile(i,j).isSealed()) {
                                this.openTile(i, j);
                            }
                    }
                }
        }
    }

    private void revealAll()
    {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (!mineGrid.getTile(i,j).isOpen()) {
                    mineGrid.getTile(i,j).setIcon(new ImageIcon(getClass().getResource(mineGrid.getTile(i,j).getIconName())));
                    mineGrid.getTile(i,j).setBackground(Color.WHITE);
                }

            }
        }
    }

    public static void main(String[] args)
    {
        gui g = new gui();
    }
}
