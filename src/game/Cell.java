package src.game;

import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {

    /**
     * State: 0 - Unclicked, 1 - Empty, 2 - Adjacent, 3 - Mine
     */
    public int state = 0;

    public Cell() {
        this.setSize(25, 25);
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

    public void setEmpty() {

    }

    public void setMine() {

    }

}
