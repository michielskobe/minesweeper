public class Number extends BoardElement {
    protected int value;
    public Number(ElementType elementType, int value) {
        super(elementType);
        this.value = value;
    }
    public int getValue(){return value;}
}
