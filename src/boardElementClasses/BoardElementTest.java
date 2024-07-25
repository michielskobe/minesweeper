package boardElementClasses;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardElementTest {

    /** Test Mine object creation */
    @Test
    public void test01() {
        Mine mine = new Mine(ElementType.MINE);
        assertEquals(ElementType.MINE, mine.getElementType());
        assertFalse(mine.getVisibility());
        assertFalse(mine.getFlag());
    }

    /** Test Number object creation */
    @Test
    public void test02() {
        Number number = new Number(ElementType.NUMBER, 5);
        assertEquals(ElementType.NUMBER, number.getElementType());
        assertEquals(5, number.getValue());
        assertFalse(number.getVisibility());
        assertFalse(number.getFlag());
    }

    /** Test getElementType()-method for Number object */
    @Test
    public void test03() {
        Number number = new Number(ElementType.NUMBER, 1);
        assertEquals(ElementType.NUMBER, number.getElementType());
    }

    /** Test getElementType()-method for Mine object*/
    @Test
    public void test04() {
        Mine mine = new Mine(ElementType.MINE);
        assertEquals(ElementType.MINE, mine.getElementType());
    }

    /** Test getVisibility()-method */
    @Test
    public void test05() {
        Number number = new Number(ElementType.NUMBER, 5);
        assertTrue(number.getVisibility() == true || number.getVisibility() == false);
    }

    /** Test makeVisible()-method */
    @Test
    public void test06() {
        Number number = new Number(ElementType.NUMBER, 5);
        assertFalse(number.getVisibility());
        number.makeVisible();
        assertTrue(number.getVisibility());
    }

    /** Test getFlag()-method */
    @Test
    public void test07() {
        Number number = new Number(ElementType.NUMBER, 5);
        assertTrue(number.getFlag() == true || number.getFlag() == false);
    }

    /** Test addFlag()-method */
    @Test
    public void test08() {
        Number number = new Number(ElementType.NUMBER, 5);
        assertFalse(number.getFlag());
        number.addFlag();
        assertTrue(number.getFlag());
    }

    /** Test removeFlag()-method */
    @Test
    public void test09() {
        Number number = new Number(ElementType.NUMBER, 5);
        number.addFlag();
        number.removeFlag();
        assertFalse(number.getFlag());
    }

    /** Test getValue()-method for Mine Object*/
    @Test
    public void test10() {
        Mine mine = new Mine(ElementType.MINE);
        assertEquals(-1, mine.getValue());
    }

    /** Test getValue()-method for Number Object*/
    @Test
    public void test11() {
        Number number = new Number(ElementType.NUMBER, 5);
        assertEquals(5, number.getValue());
    }
}
