package userInteractionClasses;

import gameBoardClasses.GameBoard;
import gameBoardClasses.GameDifficulty;

import javax.swing.*;
import java.awt.*;

public class MinesweeperGUI extends JFrame {

    public MinesweeperGUI(){
        this.setTitle("Minesweeper");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        JPanel homePanel = new JPanel();
        homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));
        this.getContentPane().add(homePanel);

        JLabel titleLabel = new JLabel("Minesweeper");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 50));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        homePanel.add(titleLabel);

        JLabel creditLabel = new JLabel("Implementation by Kobe Michiels");
        creditLabel.setFont(new Font("SansSerif", Font.BOLD, 10));
        creditLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        homePanel.add(creditLabel);
        homePanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel instructionLabel = new JLabel("Please select your difficulty level to start the game.");
        instructionLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        homePanel.add(instructionLabel);
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        homePanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton beginnerButton = new JButton("Beginner");
        homePanel.add(beginnerButton);
        beginnerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        homePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        beginnerButton.addActionListener(e -> {
            startGame(GameDifficulty.BEGINNER);
            remove(homePanel);
        });

        JButton intermediateButton = new JButton("Intermediate");
        homePanel.add(intermediateButton);
        intermediateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        homePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        intermediateButton.addActionListener(e -> {
            startGame(GameDifficulty.INTERMEDIATE);
            remove(homePanel);
        });

        JButton expertButton = new JButton("Expert");
        homePanel.add(expertButton);
        expertButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        homePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        expertButton.addActionListener(e -> {
            startGame(GameDifficulty.EXPERT);
            remove(homePanel);
        });

        setVisible(true);
    }

    public void startGame(GameDifficulty difficulty){
        switch (difficulty) {
            case BEGINNER -> setSize(500, 675);
            case INTERMEDIATE -> setSize(900, 675);
            case EXPERT -> setSize(1600, 675);
        }
        setTitle("Minesweeper");
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
