package userInteractionClasses;

import boardElementClasses.*;
import gameBoardClasses.GameBoard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static javax.swing.JOptionPane.*;

public class ButtonClickListener extends MouseAdapter {

    private final int x,y;
    private final GameBoard gameBoard;
    private final JButton[][] buttons;
    private static final Color OPENED_TILE_BACKGROUND_COLOR = new Color(38, 39, 41);
    private final MinesweeperGUI minesweeperGUI;

    public ButtonClickListener(int x, int y, GameBoard gameBoard, JButton[][] buttons, MinesweeperGUI gui){
        this.x = x;
        this.y = y;
        this.gameBoard = gameBoard;
        this.buttons = buttons;
        this.minesweeperGUI = gui;
    }

    /** Executes an action when a tile is clicked */
    @Override
    public void mousePressed(MouseEvent e) {
        // Action for left click:
        if (SwingUtilities.isLeftMouseButton(e)) {
            leftClickButtonAction(x, y);
        }
        // Action for right click:
        else if (SwingUtilities.isRightMouseButton(e)) {
            rightClickButtonAction(x, y);
        }
    }

    /** Executes the action linked to a left mouse click (opening tiles) */
    public void leftClickButtonAction(int x, int y){
        // Get the clicked-on tile:
        BoardElement element = gameBoard.getBoard()[x][y];
        JButton button = buttons[x][y];

        // Check if the tile has no flag and can be opened:
        if (!element.getFlag()){

            // Open the tile:
            element.makeVisible();
            button.setBackground(OPENED_TILE_BACKGROUND_COLOR);
            button.setForeground(Color.WHITE);

            // Check if the tile contains a mine:
            if (element.getElementType().equals(ElementType.MINE)){

                // End the game:
                endGameWithVictory(false);

                // Display option to retry:
                String[] options = {"Retry?", "Exit"};
                int selectedOption = JOptionPane.showOptionDialog(null, "GAME OVER", "Minesweeper", OK_CANCEL_OPTION, QUESTION_MESSAGE, null, options, OK_OPTION);
                if (selectedOption == OK_OPTION){
                    // Start a new game:
                    minesweeperGUI.dispose();
                    new MinesweeperGUI();
                }
                else {
                    // End the application:
                    minesweeperGUI.dispose();
                }
            }
            // When the tile contains no mine:
            else {
                // Check if the tile is empty:
                if (element.getValue() == 0){
                    button.setText("");
                    // Open surrounding empty tiles:
                    openEmptyTiles(x, y);
                }
                else {
                    // Display the value of the tile:
                    button.setText(String.valueOf(element.getValue()));
                }
            }

            // Disable the tile if it's opened:
            button.setEnabled(false);
        }
    }

    /** Executes the action linked to a right mouse click (altering flags) */
    public void rightClickButtonAction(int x, int y){
        // Get the clicked-on tile:
        BoardElement element = gameBoard.getBoard()[x][y];
        JButton button = buttons[x][y];

        // Check if the tile has a flag:
        if (element.getFlag()){
            // Remove the flag:
            element.removeFlag();
            gameBoard.incrementNumberOfFlags();
            button.setText("");
        }
        else {
            // Check if the user has flags available:
            if (gameBoard.getNumberOfFlags()>0) {
                // Check if the tile is not open:
                if (!element.getVisibility()){
                    // Add a flag to the tile:
                    element.addFlag();
                    gameBoard.decrementNumberOfFlags();
                    // Checks if all the mines are flagged:
                    if (checkForVictory()){
                        // End the game if all mines are flagged:
                        endGameWithVictory(true);
                        // Display option to play again:
                        String[] options = {"Play again?", "Exit"};
                        int selectedOption = JOptionPane.showOptionDialog(null, "Congratulations, you won!", "Minesweeper", OK_CANCEL_OPTION, QUESTION_MESSAGE, null, options, OK_OPTION);
                        if (selectedOption == OK_OPTION){
                            // Start a new game:
                            minesweeperGUI.dispose();
                            new MinesweeperGUI();
                        }
                        else {
                            // End the application:
                            minesweeperGUI.dispose();
                        }
                    }
                    else {
                        // Display the flag:
                        button.setForeground(Color.red);
                        button.setText("\uD83D\uDEA9");
                    }
                }
            }
            else {
                // Indicate that are flags are used:
                JOptionPane.showMessageDialog(null, "No flags left");
            }
        }
    }

   /** Opens all the non-value tiles surrounding an opened tile */
   public void openEmptyTiles(int x, int y){
       // Get gameBoard
       BoardElement[][] board = gameBoard.getBoard();

       // Iterate over gameBoard and open empty tiles:
        for(int m = -1; m <= 1; m++) {
            for (int n = -1; n <= 1; n++) {
                try {
                    if (board[x + m][y + n].getValue() == 0 && !board[x + m][y + n].getVisibility()) {
                        // Make tile visible and disable it:
                        board[x + m][y + n].makeVisible();
                        buttons[x + m][y + n].setBackground(OPENED_TILE_BACKGROUND_COLOR);
                        buttons[x + m][y + n].setText("");
                        buttons[x + m][y + n].setEnabled(false);
                        // Remove flag on empty tile:
                        if (board[x + m][y + n].getFlag()) {
                            board[x + m][y + n].removeFlag();
                        }
                        // Check if empty tile has surrounding empty tiles:
                        this.openEmptyTiles((x + m), (y + n));
                    }
                    // Make non-empty tiles next to empty tiles visible:
                    else if (board[x + m][y + n].getElementType().equals(ElementType.NUMBER) && !board[x + m][y + n].getVisibility()) {
                        // Make tile visible and disable it:
                        board[x + m][y + n].makeVisible();
                        buttons[x + m][y + n].setBackground(OPENED_TILE_BACKGROUND_COLOR);
                        buttons[x + m][y + n].setForeground(Color.WHITE);
                        buttons[x + m][y + n].setText(String.valueOf(board[x + m][y + n].getValue()));
                        buttons[x + m][y + n].setEnabled(false);
                        // Remove flag on tile:
                        if (board[x + m][y + n].getFlag()) {
                            board[x + m][y + n].removeFlag();
                        }
                    }
                }
                // Ignore errors due to edge tiles:
                catch (Exception ignored) {}
            }
        }
    }

    /** End the game if all mines are flagged */
    public void endGameWithVictory(boolean isVictory){
        // Iterate over gameBoard and disable all tiles:
        for(int m = 0; m<gameBoard.getRows(); m++) {
            for (int n = 0; n < gameBoard.getColumns(); n++) {
                BoardElement element = gameBoard.getBoard()[m][n];
                JButton button = buttons[m][n];
                button.setEnabled(false);
                // Make all tiles visible in case of victory:
                if (isVictory){
                    button.setBackground(OPENED_TILE_BACKGROUND_COLOR);
                    button.setForeground(Color.WHITE);
                    if (element.getElementType().equals(ElementType.MINE)){
                        button.setText("\uD83D\uDCA3");
                        button.setForeground(new Color(249, 185, 90));
                    } else if (element.getValue() == 0) {
                        button.setText("");
                    }
                    else {
                        button.setText(String.valueOf(element.getValue()));
                    }
                }
                // Make all mines visible in case of loss:
                else {
                    if (element.getElementType().equals(ElementType.MINE)){
                        button.setText("\uD83D\uDCA3");
                        button.setForeground(new Color(249, 185, 90));
                        button.setBackground(OPENED_TILE_BACKGROUND_COLOR);
                    }

                }
            }
        }
    }

    /** Checks if all the mines are flagged */
    public boolean checkForVictory(){
        int flaggedMinesCounter = 0;
        // Iterate over gameBoard:
        for(int m = 0; m<gameBoard.getRows(); m++) {
            for (int n = 0; n < gameBoard.getColumns(); n++) {
                BoardElement element = gameBoard.getBoard()[m][n];
                // Check if element is a flagged mine:
                if (element.getElementType().equals(ElementType.MINE) && element.getFlag()) {
                    // Count the number of flagged mines:
                    flaggedMinesCounter++;
                }
                // Check if element is an un-flagged mine:
                else if (!element.getElementType().equals(ElementType.MINE) && element.getFlag()) {
                    return false;
                }
            }
        }
        // Return if all mines are flagged:
        return flaggedMinesCounter == gameBoard.getNumberOfMines();
    }
}
