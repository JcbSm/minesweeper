package src.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Board implements ActionListener {

    public int columns;     // Columns
    public int rows;        // Rows
    public int mines;       // Number of mines
    public int totalCells;  // This is pretty fuckin self explainatory idk why im commenting on this

    /**
     * 0 - Unstarted <br>
     * 1 - In progress <br>
     * 2 - Win <br>
     * 3 - Loss
     */
    public int state;   // Game state

    public JFrame frame = new JFrame();
    public JPanel boardPanel = new JPanel();

    public Cell[][] cells;

    public Board (int xx, int yy, int mm) {

        // Iniate board values
        columns = xx; rows = yy; mines = mm;
        totalCells = columns * rows;

        // Initiate cells
        cells = new Cell[rows][columns];

        // For each row
        for (int i = 0; i < rows; i++) {

            // For each column
            for (int j = 0; j < columns; j++) {

                // Create new Cell
                cells[i][j] = new Cell(i,j, false);
                // Emit event to this board when cell is clicked
                cells[i][j].addActionListener(this);

                boardPanel.add(cells[i][j]);

            }
        }

        // Set board panel layout
        boardPanel.setLayout(new GridLayout(rows, columns));

        frame.setSize(columns * 50, rows * 50);
        frame.add(boardPanel);

    }

    public boolean init(Cell initalCell) {

        // FIX
        do {
            placeMines(); // Currently causes infinite loop because it doesnt clear the previously placed mines
        } while (cells[initalCell.row][initalCell.column].isMine());

        initCells();

        return true;

    }

    /**
     * Iterates through each cell and checks how many adjacent mines it has
     */
    private void initCells() {

        // For each row
        for (int i = 0; i < rows; i++) {

            // For each column
            for (int j = 0; j < columns; j++) {

                Cell cell = cells[i][j];

                if (cell.isMine()) continue; // Ignore mine cells

                countAdjacentMines(cell);

            }
        }

    }

    private void placeMines() {

        Random rng = new Random();
        int row;
        int column;

        for (int i = 0; i < mines; i++) {

            do {

                row = rng.nextInt(rows);
                column = rng.nextInt(columns);

            } while (cells[row][column].isMine());

            cells[row][column].plantMine();

        }

    }

    /**
     * Counts the number of mines int he adjacent cells
     * @param cell The cell at the centre
     * @return The number of adjacent cells
     */
    public int countAdjacentMines(Cell cell) {

        int count = 0;

        // Iterate through rows, above current and below
        for (int i = cell.row > 0 ? cell.row - 1 : 0; i <= cell.row + 1 && i < rows; i++) {

            // Iterate through columns, above current and below
            for (int j = cell.column > 0 ? cell.column - 1 : 0; j <= cell.column + 1 && j < columns; j++) {

                if (cell.column == j && cell.row == i) continue;

                if (cells[i][j].isMine()) count++;

            }

        }

        return cell.setAdjacentMines(count);

    }

    public void click(Cell cell) {

    }

    @Override
    public void actionPerformed(ActionEvent event) {

        // Get and typecast the clicked cell.
        Cell clicked = (Cell)event.getSource();

        if (state == 0) {
            init(clicked);
            state = 1; // In Progress
        }



    }

}
