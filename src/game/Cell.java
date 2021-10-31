package src.game;

import javax.swing.*;
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

    /**
     * Creates a new cell
     * @param i the row of the cell
     * @param j the column of the cell
     */
    public Cell(int i, int j, boolean mine) {

        row = i;
        column = j;

        state = mine ? 2 : 0;

        this.setSize(50, 50);
        this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
    }

    /**
     * Sets the state as adjacent and changes the text
     * @param count the number of adjacent mines
     */
    public void setAdjacent(int count) {
        this.setText(String.valueOf(count));
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

    public void plantMine() {
        mine = true;
        this.setBackground(Color.RED);
    }
}
