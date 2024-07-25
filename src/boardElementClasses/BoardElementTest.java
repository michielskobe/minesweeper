package boardElementClasses;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardElementTest {

    /** Test Mine object creation */
    @Test
    public void testMineInitialization() {
        Mine mine = new Mine(ElementType.MINE);
        assertEquals(ElementType.MINE, mine.getElementType());
        assertFalse(mine.getVisibility());
        assertFalse(mine.getFlag());
    }

    /** Test Number object creation */
    @Test
    public void testNumberInitialization() {
        Number number = new Number(ElementType.NUMBER, 5);
        assertEquals(ElementType.NUMBER, number.getElementType());
        assertEquals(5, number.getValue());
        assertFalse(number.getVisibility());
        assertFalse(number.getFlag());
    }

    /** Test getElementType()-method for Number object */
    @Test
    public void testNumberGetType() {
        Number number = new Number(ElementType.NUMBER, 1);
        assertEquals(ElementType.NUMBER, number.getElementType());
    }

    /** Test getElementType()-method for Mine object*/
    @Test
    public void testMineGetType() {
        Mine mine = new Mine(ElementType.MINE);
        assertEquals(ElementType.MINE, mine.getElementType());
    }

    /** Test getVisibility()-method */
    @Test
    public void testGetVisibility() {
        Number number = new Number(ElementType.NUMBER, 5);
        assertTrue(number.getVisibility() == true || number.getVisibility() == false);
    }

    /** Test makeVisible()-method */
    @Test
    public void testMakeVisible() {
        Number number = new Number(ElementType.NUMBER, 5);
        assertFalse(number.getVisibility());
        number.makeVisible();
        assertTrue(number.getVisibility());
    }

    /** Test getFlag()-method */
    @Test
    public void testGetFlag() {
        Number number = new Number(ElementType.NUMBER, 5);
        assertTrue(number.getFlag() == true || number.getFlag() == false);
    }

    /** Test addFlag()-method */
    @Test
    public void testAddFlag() {
        Number number = new Number(ElementType.NUMBER, 5);
        assertFalse(number.getFlag());
        number.addFlag();
        assertTrue(number.getFlag());
    }

    /** Test removeFlag()-method */
    @Test
    public void testRemoveFlag() {
        Number number = new Number(ElementType.NUMBER, 5);
        number.addFlag();
        number.removeFlag();
        assertFalse(number.getFlag());
    }

    /** Test getValue()-method for Mine Object*/
    @Test
    public void testMineGetValue() {
        Mine mine = new Mine(ElementType.MINE);
        assertEquals(-1, mine.getValue());
    }

    /** Test getValue()-method for Number Object*/
    @Test
    public void testNumberGetValue() {
        Number number = new Number(ElementType.NUMBER, 5);
        assertEquals(5, number.getValue());
    }
}
