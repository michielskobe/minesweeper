package gameBoardClasses;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import boardElementClasses.BoardElement;
import boardElementClasses.Mine;
import boardElementClasses.Number;

public class GameBoardTest {

    /** Test GameBoard object creation on BEGINNER difficulty */
    @Test
    public void testBeginnerInitialization() {
        GameBoard gb = new GameBoard(GameDifficulty.BEGINNER);
        assertEquals(GameDifficulty.BEGINNER, gb.getDifficulty());
        assertEquals(8, gb.getRows());
        assertEquals(8, gb.getColumns());
        assertEquals(10, gb.getNumberOfMines());
        assertEquals(10, gb.getNumberOfFlags());
        assertEquals(1, gb.getNumberOfHints());
    }

    /** Test GameBoard object creation on INTERMEDIATE difficulty */
    @Test
    public void testIntermediateInitialization() {
        GameBoard gb = new GameBoard(GameDifficulty.INTERMEDIATE);
        assertEquals(GameDifficulty.INTERMEDIATE, gb.getDifficulty());
        assertEquals(16, gb.getRows());
        assertEquals(16, gb.getColumns());
        assertEquals(40, gb.getNumberOfMines());
        assertEquals(40, gb.getNumberOfFlags());
        assertEquals(4, gb.getNumberOfHints());
    }

    /** Test GameBoard object creation on EXPERT difficulty */
    @Test
    public void testExpertInitialization() {
        GameBoard gb = new GameBoard(GameDifficulty.EXPERT);
        assertEquals(GameDifficulty.EXPERT, gb.getDifficulty());
        assertEquals(16, gb.getRows());
        assertEquals(30, gb.getColumns());
        assertEquals(99, gb.getNumberOfMines());
        assertEquals(99, gb.getNumberOfFlags());
        assertEquals(9, gb.getNumberOfHints());
    }

    /** Test the board initialization for BEGINNER difficulty and content */
    @Test
    public void testBeginnerBoardInitialization() {
        GameBoard gb = new GameBoard(GameDifficulty.BEGINNER);
        BoardElement[][] board = gb.getBoard();
        int mineCount = 0;
        int numberCount = 0;

        for (int i = 0; i < gb.getRows(); i++) {
            for (int j = 0; j < gb.getColumns(); j++) {
                BoardElement element = board[i][j];
                if (element instanceof Mine) {
                    mineCount++;
                } else if (element instanceof Number) {
                    numberCount++;
                    // Check if the number of adjacent mines is correctly calculated
                    int expectedNumberValue = gb.generateNumberValue(i, j);
                    assertEquals(expectedNumberValue, element.getValue());
                }
            }
        }
        assertEquals(10, mineCount);
        assertEquals((8 * 8) - 10, numberCount);
    }

    /** Test the board initialization for INTERMEDIATE difficulty and content */
    @Test
    public void testIntermediateBoardInitialization() {
        GameBoard gb = new GameBoard(GameDifficulty.INTERMEDIATE);
        BoardElement[][] board = gb.getBoard();
        int mineCount = 0;
        int numberCount = 0;

        for (int i = 0; i < gb.getRows(); i++) {
            for (int j = 0; j < gb.getColumns(); j++) {
                BoardElement element = board[i][j];
                if (element instanceof Mine) {
                    mineCount++;
                } else if (element instanceof Number) {
                    numberCount++;
                    // Check if the number of adjacent mines is correctly calculated
                    int expectedNumberValue = gb.generateNumberValue(i, j);
                    assertEquals(expectedNumberValue, element.getValue());
                }
            }
        }
        assertEquals(40, mineCount);
        assertEquals((16 * 16) - 40, numberCount);
    }

    /** Test the board initialization for EXPERT difficulty and content */
    @Test
    public void testExpertBoardInitialization() {
        GameBoard gb = new GameBoard(GameDifficulty.EXPERT);
        BoardElement[][] board = gb.getBoard();
        int mineCount = 0;
        int numberCount = 0;

        for (int i = 0; i < gb.getRows(); i++) {
            for (int j = 0; j < gb.getColumns(); j++) {
                BoardElement element = board[i][j];
                if (element instanceof Mine) {
                    mineCount++;
                } else if (element instanceof Number) {
                    numberCount++;
                    // Check if the number of adjacent mines is correctly calculated
                    int expectedNumberValue = gb.generateNumberValue(i, j);
                    assertEquals(expectedNumberValue, element.getValue());
                }
            }
        }
        assertEquals(99, mineCount);
        assertEquals((16 * 30) - 99, numberCount);
    }

    /** Test incrementing and decrementing the number of flags */
    @Test
    public void testFlagging() {
        GameBoard gb = new GameBoard(GameDifficulty.BEGINNER);
        assertEquals(10, gb.getNumberOfFlags());
        
        gb.decrementNumberOfFlags();
        assertEquals(9, gb.getNumberOfFlags());

        gb.incrementNumberOfFlags();
        assertEquals(10, gb.getNumberOfFlags());
    }

    /** Test generating number values on board corners and edges */
    @Test
    public void testGenerateNumberValueEdges() {
        GameBoard gb = new GameBoard(GameDifficulty.BEGINNER);
        BoardElement[][] board = gb.getBoard();
        // (0,0) is a corner case
        int cornerX = 0, cornerY = 0;
        if (board[cornerX][cornerY] instanceof Number) {
            int expectedValue = gb.generateNumberValue(cornerX, cornerY);
            assertEquals(expectedValue, board[cornerX][cornerY].getValue());
        }
    }
}

