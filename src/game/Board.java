package src.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board implements ActionListener {

    public int columns;     // Columns
    public int rows;        // Rows
    public int mines;       // Number of mines
    public int totalCells;  // This is pretty fuckin self explainatory idk why im commenting on this

    public Cell[][] cells;

    public Board (int xx, int yy, int mm) {

        // Iniate board values
        columns = xx; rows = yy; mines = mm;

        totalCells = columns * rows;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

    }

}
