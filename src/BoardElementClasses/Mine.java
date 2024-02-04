package BoardElementClasses;
public class Mine extends BoardElement {
    public Mine(ElementType elementType) {
        super(elementType);
    }

    @Override
    public int getValue() {
        return -1;
    }
}
