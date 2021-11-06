package src.main.java;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.EventListener;

public class Minesweeper implements MouseListener, ActionListener {

    private Game game;

    // Frame calss
    private JFrame frame;
    // Labels
    private JLabel titleLable, titleImage;
    // Panel
    private JPanel mainMenuPanel;
    // Buttons
    private JButton startButton, exitButton, resetButton, quitButton;
    // ComboBox
    private JComboBox difficultyList;

    // Difficulty Vars
    private String[] difficultyNames = {"Easy", "Medium", "Hard", "Expert"};
    private int difficulty = 1;

    public static void main(String[] args) {

        Minesweeper app = new Minesweeper();

    }

    public Minesweeper() {

        frame = new JFrame("JcbSm's Minesweeper");

        // Exit process when window is closed.
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

        // Prepare menu GUI
        prepareGUI();
        showMenuGUI();

        // Center the frame
        frame.pack();
        frame.setVisible(true);

    }

    private void prepareGUI() {

        titleLable = new JLabel("JcbSm's Minesweeper!", JLabel.CENTER);
        titleLable.setPreferredSize(new Dimension(500, 100));
        titleLable.setMaximumSize(new Dimension(500, 100));
        titleLable.setMinimumSize(new Dimension(500, 100));

        titleImage = new JLabel("", JLabel.CENTER);
        // Get image
        titleImage.setIcon(new ImageIcon(getClass().getResource("../resources/title.png")));
        titleImage.setPreferredSize(new Dimension(500, 171));
        titleImage.setMaximumSize(new Dimension(500, 171));
        titleImage.setMinimumSize(new Dimension(500, 171));

        mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new FlowLayout());
        addMenuButtons();
        mainMenuPanel.setPreferredSize(new Dimension(500, 100));

    }

    private void addMenuButtons() {

        // Add start button
        startButton = new JButton("START");
        // Set colour to light green
        startButton.setBackground(Color.getHSBColor((float)0.3, (float)0.5, (float)0.73));
        // Add mouse listener
        startButton.addMouseListener(this);

        // Add Exit button
        exitButton = new JButton("EXIT");
        // Set colour to red
        exitButton.setBackground(Color.getHSBColor(0, (float)0.5, (float)0.73));
        // Add mouse listener
        exitButton.addMouseListener(this);

        // Add difficulty select
        difficultyList = new JComboBox(difficultyNames);
        // Set default choice to current difficulty
        difficultyList.setSelectedIndex(difficulty);
        // Add event listener
        difficultyList.addActionListener(this);
        // Set Size
        difficultyList.setPreferredSize(new Dimension(100, 25));

        // Add start and exit buttons to the main menu
        mainMenuPanel.add(difficultyList);
        mainMenuPanel.add(startButton);
        mainMenuPanel.add(exitButton);

    }

    private void showMenuGUI() {

        Container contentPane = frame.getContentPane();

        frame.setPreferredSize(new Dimension(500,500));
        contentPane.setLayout(new BorderLayout());

        contentPane.removeAll();
        contentPane.add(titleLable, BorderLayout.PAGE_START);
        contentPane.add(titleImage, BorderLayout.CENTER);
        contentPane.add(mainMenuPanel, BorderLayout.PAGE_END);

        frame.pack();
        frame.validate();
        frame.repaint();

    }

    private void startNewGame(int difficulty) {

        Container contentPane = frame.getContentPane();

        switch (difficulty) {
            case 0:
                game = new Game(10, 8, 10);
                break;
            case 1:
                game = new Game(40, 16, 16);
                break;
            case 2:
                game = new Game(99, 20, 20);
            case 3:
                game = new Game(180, 24, 24);
        }

        game.quitButton.addMouseListener(this);
        game.resetButton.addMouseListener(this);

        contentPane.removeAll();
        contentPane.add(game.mainPanel);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

        frame.setPreferredSize(new Dimension(game.board.columns * 26, game.board.rows * 26 + 64));
        frame.pack();

        frame.validate();

    }

    @Override
    public void actionPerformed(ActionEvent event) {

        JComboBox cb = (JComboBox) event.getSource();
        difficulty = cb.getSelectedIndex();

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

        JButton button = (JButton)event.getSource();

        // Menu Buttons
        if (button.equals(exitButton)) {
            // Quit Program
            frame.dispose();
        } else if (button.equals(startButton)) {
            // Start a new game
            startNewGame(difficulty);
        } else if (button.equals(game.quitButton)) {
            // Back to menu
            showMenuGUI();
        } else if (button.equals(game.resetButton)) {
            // Restart game
            startNewGame(difficulty);
        }

    }

    @Override
    public void mouseReleased(MouseEvent event) {

    }

}
