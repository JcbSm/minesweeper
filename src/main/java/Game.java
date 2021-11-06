package src.main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Game implements MouseListener {

    public Board board;

    // Content Panel
    public JPanel mainPanel = new JPanel();
    public JPanel optionsPanel = new JPanel();

    // Buttons
    public JButton resetButton, quitButton;


    Game(int bombs, int rows, int columns) {

        board = new Board(columns, rows, bombs);
        mainPanel.add(board.panel);
        prepareOptionsPanel();
        mainPanel.add(optionsPanel);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setPreferredSize(new Dimension(board.columns * 25, (board.rows * 25) + 50));
        mainPanel.validate();

    }

    private void prepareOptionsPanel() {

        // Add start button
        resetButton = new JButton("RESET");
        // Set colour to light green
        resetButton.setBackground(Color.getHSBColor((float)0.3, (float)0.5, (float)0.73));
        // Add mouse listener
        resetButton.addMouseListener(this);

        // Add Exit button
        quitButton = new JButton("QUIT");
        // Set colour to red
        quitButton.setBackground(Color.getHSBColor(0, (float)0.5, (float)0.73));
        // Add mouse listener
        quitButton.addMouseListener(this);

        // Add to panel
        optionsPanel.add(resetButton);
        optionsPanel.add(quitButton);

        // Set Size
        Dimension dim = new Dimension(400, 32);

        optionsPanel.setPreferredSize(dim);
        optionsPanel.setMaximumSize(dim);
        optionsPanel.setMinimumSize(dim);
        optionsPanel.validate();

    }

    @Override
    public void mouseClicked(MouseEvent event) {

    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }

    @Override
    public void mouseExited(MouseEvent event) {

    }

    @Override
    public void mousePressed(MouseEvent event) {

    }

    @Override
    public void mouseReleased(MouseEvent event) {

    }

}
