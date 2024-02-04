package UserInteractionClasses;

import BoardElementClasses.BoardElement;
import BoardElementClasses.ElementType;
import GameBoardClasses.GameBoard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonClickListener implements MouseListener {
    private final int x,y;
    private final GameBoard gameBoard;
    private final JButton[][] buttons;
    public ButtonClickListener(int x, int y, GameBoard gameBoard, JButton[][] buttons){
        this.x = x;
        this.y = y;
        this.gameBoard = gameBoard;
        this.buttons = buttons;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int buttonClicked = e.getButton();
        if (buttonClicked == MouseEvent.BUTTON1) {
            leftClickButtonAction(x, y);
        } else if (buttonClicked == MouseEvent.BUTTON3) {
            rightClickButtonAction(x, y);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void leftClickButtonAction(int x, int y){
        BoardElement element = gameBoard.getBoard()[x][y];
        JButton button = buttons[x][y];
        if (!element.getFlag()){
            element.makeVisible();
            button.setBackground(Color.WHITE);
            if (element.getElementType().equals(ElementType.MINE)){
                button.setText("\uD83D\uDCA3");
                openAllTiles();
                JOptionPane.showMessageDialog(null, "GAME OVER");

            }
            else {
                if (element.getValue() == 0){
                    button.setText("");
                    openEmptyTiles(x, y);
                }
                else {
                    button.setText(String.valueOf(element.getValue()));
                }
            }
            button.setEnabled(false);
        }
    }

    public void rightClickButtonAction(int x, int y){
        BoardElement element = gameBoard.getBoard()[x][y];
        JButton button = buttons[x][y];
        if (element.getFlag()){
            element.removeFlag();
            gameBoard.incrementNumberOfFlags();
            button.setText("");
        }
        else {
            if (gameBoard.getNumberOfFlags()>0) {
                if (!element.getVisibility()){
                    element.addFlag();
                    gameBoard.decrementNumberOfFlags();
                    if (checkForVictory()){
                        openAllTiles();
                        JOptionPane.showMessageDialog(null, "Congratulations, you won!");
                    }
                    else {
                        button.setForeground(Color.red);
                        button.setText("\uD83D\uDEA9");
                    }
                    }
                }
            else {
                JOptionPane.showMessageDialog(null, "No flags left");
            }
        }
    }

   public void openEmptyTiles(int x, int y){
       BoardElement[][] board = gameBoard.getBoard();
        for(int m = -1; m <= 1; m++) {
            for (int n = -1; n <= 1; n++) {
                try {
                    if (board[x + m][y + n].getValue() == 0 && !board[x + m][y + n].getVisibility()) {
                        board[x + m][y + n].makeVisible();
                        buttons[x + m][y + n].setBackground(Color.WHITE);
                        buttons[x + m][y + n].setText("");
                        buttons[x + m][y + n].setEnabled(false);
                        if (board[x + m][y + n].getFlag()) {
                            board[x + m][y + n].removeFlag();
                        }
                        this.openEmptyTiles((x + m), (y + n));
                    } else if (board[x + m][y + n].getElementType().equals(ElementType.NUMBER) && !board[x + m][y + n].getVisibility()) {
                        board[x + m][y + n].makeVisible();
                        buttons[x + m][y + n].setBackground(Color.WHITE);
                        buttons[x + m][y + n].setText(String.valueOf(board[x + m][y + n].getValue()));
                        buttons[x + m][y + n].setEnabled(false);
                        if (board[x + m][y + n].getFlag()) {
                            board[x + m][y + n].removeFlag();
                        }
                    }
                } catch (Exception ignored) {
                }
            }
        }
    }

    public void openAllTiles(){
        for(int m = 0; m<gameBoard.getRows(); m++) {
            for (int n = 0; n < gameBoard.getColumns(); n++) {
                BoardElement element = gameBoard.getBoard()[m][n];
                JButton button = buttons[m][n];
                element.makeVisible();
                button.setBackground(Color.WHITE);
                button.setEnabled(false);
                if (element.getValue() == -1){
                    button.setText("\uD83D\uDCA3");
                } else if (element.getValue() == 0) {
                    button.setText("");
                }
                else {
                    button.setText(String.valueOf(element.getValue()));
                }
            }
        }
    }


    public boolean checkForVictory(){
        int flaggedMinesCounter = 0;
        for(int m = 0; m<gameBoard.getRows(); m++) {
            for (int n = 0; n < gameBoard.getColumns(); n++) {
                BoardElement element = gameBoard.getBoard()[m][n];
                if (element.getElementType().equals(ElementType.MINE) && element.getFlag()) {
                    flaggedMinesCounter++;
                } else if (!element.getElementType().equals(ElementType.MINE) && element.getFlag()) {
                    return false;
                }
            }
        }
        return flaggedMinesCounter == gameBoard.getNumberOfMines();
    }
}
