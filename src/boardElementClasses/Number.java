package boardElementClasses;

public class Number extends BoardElement {

    protected final int value;

    public Number(ElementType elementType, int value) {
        super(elementType);
        this.value = value;
    }

    /** Returns the value of the Number */
    @Override
    public int getValue(){
        return value;}
}
