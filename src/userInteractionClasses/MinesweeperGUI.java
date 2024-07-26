package userInteractionClasses;

import gameBoardClasses.GameBoard;
import gameBoardClasses.GameDifficulty;

import javax.swing.*;
import java.awt.*;

public class MinesweeperGUI extends JFrame {
    private JPanel homePanel;
    private static final int STARTUP_MENU_WIDTH = 600;
    private static final int STARTUP_MENU_HEIGHT = 300;
    private static final int BEGINNER_GAME_MENU_WIDTH = 500;
    private static final int INTERMEDIATE_GAME_MENU_WIDTH = 900;
    private static final int EXPERT_GAME_MENU_WIDTH = 1700;
    private static final int GAME_MENU_HEIGHT = 675;
    private static final int GAME_FIELD_HEIGHT = 500;
    private static final int COMPONENT_SPACE = 10;
    private static final int BUTTON_HEIGHT = 30;
    private static final int BUTTON_WIDTH = 200;
    private static final Color BACKGROUND_COLOR = new Color(22,24,23);
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color TITLE_COLOR = new Color(249, 185, 90);

    public MinesweeperGUI(){
        this.getContentPane().removeAll();
        createStartupMenu();
        this.revalidate();
        this.repaint();
    }

    /** Creates a window that opens when the program is run and allows the user to start the game. */
    private void createStartupMenu(){
        configureWindow(STARTUP_MENU_WIDTH, STARTUP_MENU_HEIGHT);
        homePanel = new JPanel();
        homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));
        homePanel.setBackground(BACKGROUND_COLOR);
        this.getContentPane().add(homePanel);

        createLabel("Minesweeper", FontStyle.TITLE_FONT.getFont(), TITLE_COLOR, homePanel, 0);
        createLabel("Implementation by Kobe Michiels", FontStyle.CREDIT_FONT.getFont(), TEXT_COLOR, homePanel, COMPONENT_SPACE);
        createLabel("Please select your difficulty level to start the game.", FontStyle.LABEL_FONT_BOLD.getFont(), TEXT_COLOR, homePanel, COMPONENT_SPACE);
        createButton("Beginner", GameDifficulty.BEGINNER, homePanel);
        createButton("Intermediate", GameDifficulty.INTERMEDIATE, homePanel);
        createButton("Expert", GameDifficulty.EXPERT, homePanel);

        setVisible(true);
    }

    private void startGame(GameDifficulty difficulty){
        switch (difficulty) {
            case BEGINNER -> configureWindow(BEGINNER_GAME_MENU_WIDTH, GAME_MENU_HEIGHT);
            case INTERMEDIATE -> configureWindow(INTERMEDIATE_GAME_MENU_WIDTH,GAME_MENU_HEIGHT);
            case EXPERT -> configureWindow(EXPERT_GAME_MENU_WIDTH,GAME_MENU_HEIGHT);
        }
        GameBoard gameBoard = new GameBoard(difficulty);
        createGameWindow(gameBoard);
    }

    /** Creates a window for a given game board. */
    private void createGameWindow(GameBoard gameBoard){
        int rows = gameBoard.getRows();
        int columns = gameBoard.getColumns();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);

        JPanel gridPanel = new JPanel(new GridLayout(rows, columns));
        switch (gameBoard.getDifficulty()){
            case BEGINNER -> gridPanel.setPreferredSize(new Dimension(BEGINNER_GAME_MENU_WIDTH, GAME_FIELD_HEIGHT));
            case INTERMEDIATE ->  gridPanel.setPreferredSize(new Dimension(INTERMEDIATE_GAME_MENU_WIDTH, GAME_FIELD_HEIGHT));
            case EXPERT -> gridPanel.setPreferredSize(new Dimension(EXPERT_GAME_MENU_WIDTH, GAME_FIELD_HEIGHT));
        }
        mainPanel.add(gridPanel, BorderLayout.SOUTH);
        JButton[][] buttons = new JButton[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                buttons[row][column] = new JButton();
                buttons[row][column].setFont(FontStyle.BUTTON_FONT.getFont());
                buttons[row][column].setForeground(TEXT_COLOR);
                buttons[row][column].setBackground(BACKGROUND_COLOR);
                buttons[row][column].addMouseListener(new ButtonClickListener(row, column, gameBoard, buttons, this));
                gridPanel.add(buttons[row][column]);
            }
        }

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        createLabel("Minesweeper", FontStyle.TITLE_FONT.getFont(), TITLE_COLOR, headerPanel, 0);
        createLabel("Difficulty: " + gameBoard.getDifficulty(), FontStyle.LABEL_FONT_PLAIN.getFont(), TEXT_COLOR, headerPanel, 0);
        createLabel("Number of mines: " + gameBoard.getNumberOfMines(), FontStyle.LABEL_FONT_PLAIN.getFont(), TEXT_COLOR, headerPanel, 0);

        add(mainPanel);
        setVisible(true);
    }

    /** Creates a button with the input text to start a game with an input difficulty and adds that button to the input panel, with a space below it. */
    private void createButton(String text, GameDifficulty difficulty, JPanel panel) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.setMinimumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBackground(BACKGROUND_COLOR);
        button.setBorderPainted(false);
        button.setForeground(TEXT_COLOR);
        button.setFont(FontStyle.BUTTON_FONT.getFont());
        button.addActionListener(e -> {
            startGame(difficulty);
            remove(homePanel);
        });
        panel.add(button);
        panel.add(Box.createRigidArea(new Dimension(0, COMPONENT_SPACE)));
    }

    /** Creates a label with the input text and font and adds that label to the input panel, with a space below it. */
    private void createLabel(String text, Font font, Color color, JPanel panel, int space){
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setForeground(color);
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, space)));
    }

    /** Handles the standard configurations for a window with a given width and height. */
    private void configureWindow(int width, int height) {
        this.setSize(width, height);
        this.setTitle("Minesweeper");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }
}
