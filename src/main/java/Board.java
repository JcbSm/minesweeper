package src.main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Board implements MouseListener {

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

    public JPanel panel = new JPanel();

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
                cells[i][j].addMouseListener(this);

                panel.add(cells[i][j]);

            }
        }

        // Set board panel layout
        Dimension dim = new Dimension(columns * 25, rows * 25);

        panel.setLayout(new GridLayout(rows, columns));
        panel.setPreferredSize(dim);
        panel.setMinimumSize(dim);
        panel.setMaximumSize(dim);
        panel.validate();

    }

    /**
     * Place mines and calculate adjacent cells
     * @param initialCell Initially clicked cell, ensures mines are not placed here.
     * @return
     */
    public boolean init(Cell initialCell) {

        placeMines(initialCell);
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

    /**
     * Places mines on the board
     * @param initialCell The cell clicked initially, ensures a mine is not placed here.
     */
    private void placeMines(Cell initialCell) {

        Random rng = new Random();
        int row;
        int column;

        for (int i = 0; i < mines; i++) {

            do {

                row = rng.nextInt(rows);
                column = rng.nextInt(columns);

            } while (cells[row][column].isMine() || isAdjacent(initialCell, cells[row][column]));

            cells[row][column].setMine(true);

        }

    }

    /**
     * Checks if two cells are adjacent
     * @param cellOne
     * @param cellTwo
     * @return boolean
     */
    private boolean isAdjacent(Cell cellOne, Cell cellTwo) {

        if (Math.abs(cellOne.column - cellTwo.column) <= 1 && Math.abs(cellOne.row - cellTwo.row) <= 1 ) return true;

        else return false;

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

                if (cells[i][j].isMine()) {
                    count++;
                }

            }

        }

        return cell.setAdjacentMines(count);

    }

    /**
     *
     * @param cell
     */
    public void click(Cell cell) {

        // Activate the target cell and assign to variaable
        boolean loss = cell.activate();

        if (cell.getAdjacentMines() == 0 && !loss) {

            // Iterate through rows, above current and below
            for (int i = cell.row > 0 ? cell.row - 1 : 0; i <= cell.row + 1 && i < rows; i++) {

                // Iterate through columns, above current and below
                for (int j = cell.column > 0 ? cell.column - 1 : 0; j <= cell.column + 1 && j < columns; j++) {

                    if ((cell.column == j && cell.row == i) || cells[i][j].isMine()) continue;

                    if (cells[i][j].isUnclicked()) click(cells[i][j]);

                }

            }

        }

        // If loss, reveal all mines
        if (loss) {

            // For each row
            for (int i = 0; i < rows; i++) {

                // For each column
                for (int j = 0; j < columns; j++) {

                    // If cell is a mine
                    // AND Column and Row aren't the same
                    if (cells[i][j].isMine()) {

                        // Reveal the mine
                        cells[i][j].reveal();

                    }
                }
            }

            cell.fatalMine();
            state = 3;

        }

    }

    @Override
    public void  mouseClicked(MouseEvent event) {

    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }

    @Override
    public void mouseExited(MouseEvent event) {

    }

    /**
     * When the mouse is pressed.
     * @param event the mouse event emitted
     */
    @Override
    public void mousePressed(MouseEvent event) {

        if (state == 2 || state == 3) return;

        // Get and typecast the clicked cell.
        Cell clickedCell = (Cell)event.getSource();

        // Start game
        if (state == 0) {
            init(clickedCell);
            state = 1; // In Progress
            click(clickedCell);
            return;
        }

        if (clickedCell.clicked()) return;

        switch (event.getButton()) {

            // Left clicked
            case 1:

                click(clickedCell);

                break;

            // Middle Clicked
            case 2:

                break;

            // Right Clicked
            case 3:

                clickedCell.toggleFlag();

                break;

        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {

    }

}
