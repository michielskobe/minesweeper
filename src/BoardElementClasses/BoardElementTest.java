package BoardElementClasses;

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

    /** Test makeVisible()-function */
    @Test
    public void test03() {
        Number number = new Number(ElementType.NUMBER, 5);
        assertFalse(number.getVisibility());
        number.makeVisible();
        assertTrue(number.getVisibility());
    }

    /** Test addFlag()-function */
    @Test
    public void test04() {
        Number number = new Number(ElementType.NUMBER, 5);
        assertFalse(number.getFlag());
        number.addFlag();
        assertTrue(number.getFlag());
    }

    /** Test removeFlag()-function */
    @Test
    public void test05() {
        Number number = new Number(ElementType.NUMBER, 5);
        number.addFlag();
        number.removeFlag();
        assertFalse(number.getFlag());
    }

}
