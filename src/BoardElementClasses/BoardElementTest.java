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

}
