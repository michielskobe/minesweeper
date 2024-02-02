package GameBoardClasses;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameBoardTest {

    /** Test GameBoard object creation on EASY difficulty */
    @Test
    public void test01() {
        GameBoard gb = new GameBoard(GameDifficulty.EASY);
        assertEquals(GameDifficulty.EASY, gb.getDifficulty());
        assertEquals(8, gb.getRows());
        assertEquals(8, gb.getColumns());
        assertEquals(10, gb.getNumberOfMines());
        assertEquals(10, gb.getNumberOfFlags());
        assertEquals(1, gb.getNumberOfHints());
    }

    /** Test GameBoard object creation on MEDIUM difficulty */
    @Test
    public void test02() {
        GameBoard gb = new GameBoard(GameDifficulty.MEDIUM);
        assertEquals(GameDifficulty.MEDIUM, gb.getDifficulty());
        assertEquals(16, gb.getRows());
        assertEquals(16, gb.getColumns());
        assertEquals(40, gb.getNumberOfMines());
        assertEquals(40, gb.getNumberOfFlags());
        assertEquals(4, gb.getNumberOfHints());
    }

    /** Test GameBoard object creation on HARD difficulty */
    @Test
    public void test03() {
        GameBoard gb = new GameBoard(GameDifficulty.HARD);
        assertEquals(GameDifficulty.HARD, gb.getDifficulty());
        assertEquals(16, gb.getRows());
        assertEquals(30, gb.getColumns());
        assertEquals(99, gb.getNumberOfMines());
        assertEquals(99, gb.getNumberOfFlags());
        assertEquals(9, gb.getNumberOfHints());
    }
}

