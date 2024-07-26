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
        // Create Startup Menu when application is created:
        createStartupMenu();
    }

    /** Creates a window that opens when the program is run and allows the user to start the game. */
    private void createStartupMenu(){
        // Create window for Startup Menu:
        configureWindow(STARTUP_MENU_WIDTH, STARTUP_MENU_HEIGHT);
        homePanel = new JPanel();
        homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));
        homePanel.setBackground(BACKGROUND_COLOR);
        this.getContentPane().add(homePanel);

        // Add labels to Startup Menu:
        addLabel("Minesweeper", FontStyle.TITLE_FONT.getFont(), TITLE_COLOR, homePanel, 0);
        addLabel("Implementation by Kobe Michiels", FontStyle.CREDIT_FONT.getFont(), TEXT_COLOR, homePanel, COMPONENT_SPACE);
        addLabel("Please select your difficulty level to start the game.", FontStyle.LABEL_FONT_BOLD.getFont(), TEXT_COLOR, homePanel, COMPONENT_SPACE);

        // Add buttons for difficulty selection to Startup Menu:
        addDifficultyButton("Beginner", GameDifficulty.BEGINNER, homePanel);
        addDifficultyButton("Intermediate", GameDifficulty.INTERMEDIATE, homePanel);
        addDifficultyButton("Expert", GameDifficulty.EXPERT, homePanel);

        // Display Startup Menu:
        setVisible(true);
    }

    private void startGame(GameDifficulty difficulty){
        // Determine Window dimensions based on difficulty:
        switch (difficulty) {
            case BEGINNER -> configureWindow(BEGINNER_GAME_MENU_WIDTH, GAME_MENU_HEIGHT);
            case INTERMEDIATE -> configureWindow(INTERMEDIATE_GAME_MENU_WIDTH,GAME_MENU_HEIGHT);
            case EXPERT -> configureWindow(EXPERT_GAME_MENU_WIDTH,GAME_MENU_HEIGHT);
        }

        // Create Window for new gameBoard based on selected difficulty:
        GameBoard gameBoard = new GameBoard(difficulty);
        createGameWindow(gameBoard);
    }

    /** Creates a window for a given game board. */
    private void createGameWindow(GameBoard gameBoard){
        // Create new Window for application:
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);

        // Create Panel for gameBoard:
        JPanel boardPanel = getBoardPanel(gameBoard);
        mainPanel.add(boardPanel, BorderLayout.SOUTH);

        // Create tiles for board:
        int rows = gameBoard.getRows();
        int columns = gameBoard.getColumns();
        JButton[][] buttons = new JButton[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                // Create button for tile:
                buttons[row][column] = new JButton();

                // Set button layout:
                buttons[row][column].setFont(FontStyle.BUTTON_FONT.getFont());
                buttons[row][column].setForeground(TEXT_COLOR);
                buttons[row][column].setBackground(BACKGROUND_COLOR);

                // Add Mouse Listener to button:
                buttons[row][column].addMouseListener(new ButtonClickListener(row, column, gameBoard, buttons, this));

                // Add button to Panel:
                boardPanel.add(buttons[row][column]);
            }
        }

        // Create Panel for header:
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Add labels to header:
        addLabel("Minesweeper", FontStyle.TITLE_FONT.getFont(), TITLE_COLOR, headerPanel, 0);
        addLabel("Difficulty: " + gameBoard.getDifficulty(), FontStyle.LABEL_FONT_PLAIN.getFont(), TEXT_COLOR, headerPanel, 0);
        addLabel("Number of mines: " + gameBoard.getNumberOfMines(), FontStyle.LABEL_FONT_PLAIN.getFont(), TEXT_COLOR, headerPanel, 0);

        // Display Game Window:
        add(mainPanel);
        setVisible(true);
    }

    /** Created a Panel for the gameBoard */
    private JPanel getBoardPanel(GameBoard gameBoard) {
        // Get gameBoard dimensions:
        int rows = gameBoard.getRows();
        int columns = gameBoard.getColumns();

        // Create Panel for the board:
        JPanel boardPanel = new JPanel(new GridLayout(rows, columns));

        // Set grid size based on difficulty:
        switch (gameBoard.getDifficulty()){
            case BEGINNER -> boardPanel.setPreferredSize(new Dimension(BEGINNER_GAME_MENU_WIDTH, GAME_FIELD_HEIGHT));
            case INTERMEDIATE ->  boardPanel.setPreferredSize(new Dimension(INTERMEDIATE_GAME_MENU_WIDTH, GAME_FIELD_HEIGHT));
            case EXPERT -> boardPanel.setPreferredSize(new Dimension(EXPERT_GAME_MENU_WIDTH, GAME_FIELD_HEIGHT));
        }

        return boardPanel;
    }

    /** Creates a button with the input text to start a game with an input difficulty and adds that button to the input panel, with a space below it. */
    private void addDifficultyButton(String text, GameDifficulty difficulty, JPanel panel) {
        // Create button:
        JButton button = new JButton(text);

        // Set button dimensions:
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.setMinimumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        // Set button text:
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setForeground(TITLE_COLOR);
        button.setFont(FontStyle.BUTTON_FONT.getFont());

        // Set button layout:
        button.setBackground(BACKGROUND_COLOR);
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        // Alter cursor when hovering over button:
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add Action Listener for button:
        button.addActionListener(e -> {
            startGame(difficulty);
            remove(homePanel);
        });

        // Add button to panel with space below it:
        panel.add(button);
        panel.add(Box.createRigidArea(new Dimension(0, COMPONENT_SPACE)));
    }

    /** Creates a label with the input text and font and adds that label to the input panel, with a space below it. */
    private void addLabel(String text, Font font, Color color, JPanel panel, int space){
        // Create label with text:
        JLabel label = new JLabel(text);

        // Set label layout:
        label.setFont(font);
        label.setForeground(color);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add label to panel with space below it:
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, space)));
    }

    /** Handles the standard configurations for a window with a given width and height. */
    private void configureWindow(int width, int height) {
        // Set Window title:
        this.setTitle("Minesweeper");

        // Set Window dimensions:
        this.setSize(width, height);
        this.setResizable(false);

        // Set Window position to center:
        this.setLocationRelativeTo(null);

        // Exit application when Window is closed:
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
