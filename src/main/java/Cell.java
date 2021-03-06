package src.main.java;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Cell extends JButton {

    public int row;
    public int column;

    /**
     * State: 0 - Unclicked; 1 - Clicked; 2 - Flagged
     */
    private int state = 0;
    private int adjacentMines = 0;
    private boolean mine;

    private final Icon[] numberIconArray = new ImageIcon[]{
            new ImageIcon(getClass().getResource("../resources/0.png")),
            new ImageIcon(getClass().getResource("../resources/1.png")),
            new ImageIcon(getClass().getResource("../resources/2.png")),
            new ImageIcon(getClass().getResource("../resources/3.png")),
            new ImageIcon(getClass().getResource("../resources/4.png")),
            new ImageIcon(getClass().getResource("../resources/5.png")),
            new ImageIcon(getClass().getResource("../resources/6.png")),
            new ImageIcon(getClass().getResource("../resources/7.png")),
            new ImageIcon(getClass().getResource("../resources/8.png")),
    };

    private final Icon unclickedIcon = new ImageIcon(getClass().getResource("../resources/unclicked_cell.png"));
    private final Icon flaggedIcon = new ImageIcon(getClass().getResource("../resources/flag.png"));
    private final Icon bombIcon = new ImageIcon(getClass().getResource("../resources/bomb.png"));
    private final Icon clickedBomb = new ImageIcon(getClass().getResource("../resources/clicked_bomb.png"));


    /**
     * Creates a new cell
     * @param i the row of the cell
     * @param j the column of the cell
     */
    public Cell(int i, int j, boolean isMine) {

        row = i;
        column = j;
        mine = isMine;

        this.setPreferredSize(new Dimension(20, 20));
        this.setIcon(unclickedIcon);

    }

    /**
     * Checks if the cell has a mine
     * @return boolean
     */
    public boolean isMine() {
        return mine;
    }

    /**
     * Sets the value for adjacent mines
     * @param count adjacent mines count
     * @return int - count
     */
    public int setAdjacentMines(int count) {
        return adjacentMines = count;
    }

    public int getAdjacentMines() {
        return adjacentMines;
    }

    public boolean isUnclicked() {
        return state == 0 ? true : false;
    }

    public void setMine(boolean bool) {
        mine = bool;
    }

    /**
     * Clicks the cell
     * @return boolean - True if clicked a mine
     */
    public boolean activate() {

        state = 1;

        if (isMine()) {
            return true;
        } else {
            this.setIcon(numberIconArray[adjacentMines]);
            return false;
        }

    }

    public void fatalMine() {
        this.setIcon(clickedBomb);
    }

    /**
     * Toggles the flag icon
     */
    public void toggleFlag() {
        if (state == 2) {
            state = 0;
            this.setIcon(unclickedIcon);
        } else {
            state = 2;
            this.setIcon(flaggedIcon);
        }
    }

    /**
     * Checks if the cell has been clicked already
     * @return boolean
     */
    public boolean clicked() {
        return state == 1 ? true : false;
    }

    /**
     * Reveals the bomb <br>
     * Changes the icon to a bomb
     */
    public void reveal() {

        try {

            if (mine) {
                this.setIcon(bombIcon);
            } else {
                throw new Exception("Cannot reveal non-mines.");
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}
