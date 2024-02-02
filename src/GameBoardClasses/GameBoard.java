package GameBoardClasses;

import BoardElementClasses.BoardElement;
import BoardElementClasses.ElementType;
import BoardElementClasses.Mine;
import BoardElementClasses.Number;
import java.util.Random;

public class GameBoard {
    private final BoardElement[][] board;
    private int rows;
    private int columns;
    private GameDifficulty difficulty;
    private int numberOfMines;
    private int numberOfHints;
    private int numberOfFlags;
    public GameBoard(GameDifficulty difficulty) {
        this.difficulty = difficulty;
        switch (difficulty) {
            case EASY -> {
                numberOfMines = 10;
                rows = 8;
                columns = 8;
            }
            case MEDIUM -> {
                numberOfMines = 40;
                rows = 16;
                columns = 16;
            }
            case HARD -> {
                numberOfMines = 99;
                rows = 16;
                columns = 30;
            }
        }
        numberOfFlags = numberOfMines;
        numberOfHints = numberOfMines/10;
        board = new BoardElement[rows][columns];
        this.buildGameBoard();
    }

    /** Builds a 2D-list that will be used to create the GUI. */
    private void buildGameBoard() {
        Random random = new Random();
        int numberOfMinesPlaced = 0;
        while(numberOfMinesPlaced<numberOfMines) {
            int randomRow = random.nextInt(rows);
            int randomCol = random.nextInt(columns);
            if(board[randomRow][randomCol] == null) {
                board[randomRow][randomCol] = new Mine(ElementType.MINE);
                numberOfMinesPlaced++;
            }
        }
        for(int row=0; row<rows; row++) {
            for(int col=0; col<columns; col++) {
                if(board[row][col] == null) {
                    board[row][col] = new Number(ElementType.NUMBER, this.generateNumberValue(row, col));
                }
            }
        }
    }

    /** Counts how many mines there are in the surrounding tiles and returns this value. */
    private int generateNumberValue(int x, int y) {
        int mineCount = 0;
        for(int m = -1; m <= 1; m++) {      //rows around tile counter
            for(int n = -1; n <= 1; n++) {      //columns around tile counter
                try {
                    if(board[x+m][y+n].getElementType().equals(ElementType.MINE)) {
                        mineCount++;
                    }
                } catch (Exception ignored) {}
            }
        }
        return mineCount;
    }

    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }
    public int getNumberOfMines() {
        return numberOfMines;
    }
    public int getNumberOfFlags(){return numberOfFlags;}
    public int getNumberOfHints(){return numberOfHints;}
    public GameDifficulty getDifficulty() {
        return difficulty;
    }
    public BoardElement[][] getBoard(){return board;}
}
