package UserInteractionClasses;

import GameBoardClasses.GameBoard;
import GameBoardClasses.GameDifficulty;

import javax.swing.*;
import java.awt.*;

public class MinesweeperGUI extends JFrame {

    public MinesweeperGUI(){
        initializeUI();
    }

    private void initializeUI() {
        startGame(GameDifficulty.BEGINNER);
        //width sizes -> easy: 500, medium: 900, hard: 1600
    }

    public void startGame(GameDifficulty difficulty){
        setTitle("Minesweeper");
        setSize(500, 675);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameBoard gameBoard = new GameBoard(difficulty);
        JPanel mainPanel = new JPanel(new BorderLayout());
        int rows = gameBoard.getRows();
        int columns = gameBoard.getColumns();
        JPanel gridPanel = new JPanel(new GridLayout(rows, columns));
        JButton[][] buttons = new JButton[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                buttons[row][column] = new JButton();
                buttons[row][column].setFont(new Font("SansSerif", Font.BOLD, 15));
                buttons[row][column].addMouseListener(new ButtonClickListener(row, column, gameBoard, buttons));
                gridPanel.add(buttons[row][column]);
            }
        }
        gridPanel.setPreferredSize(new Dimension(500, 500));
        mainPanel.add(gridPanel, BorderLayout.SOUTH);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JLabel titleLabel = new JLabel("Minesweeper");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 50));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(titleLabel);

        JLabel difficultyLabel = new JLabel("Difficulty: " + gameBoard.getDifficulty());
        difficultyLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        difficultyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(difficultyLabel);

        JLabel mineLabel = new JLabel("Number of mines: " + gameBoard.getNumberOfMines());
        mineLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        mineLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(mineLabel);

        add(mainPanel);
        setVisible(true);
    }
}
