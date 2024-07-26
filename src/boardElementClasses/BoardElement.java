package boardElementClasses;

public abstract class BoardElement {

    protected final ElementType elementType;
    protected boolean flag;
    protected boolean visibility;

    public BoardElement(ElementType elementType){
        this.elementType = elementType;
        this.flag = false;
        this.visibility = false;
    }

    /** Returns the elementType of the BoardElement */
    public ElementType getElementType() {
        return elementType;
    }

    /** Returns the visibility of the BoardElement */
    public boolean getVisibility() {
        return visibility;
    }

    /** Sets the visibility of the BoardElement to true */
    public void makeVisible(){
        visibility = true;
    }

    /** Returns the flag of the BoardElement */
    public boolean getFlag() {
        return flag;
    }

    /** Sets the flag of the BoardElement to true */
    public void addFlag(){
        flag = true;
    }

    /** Sets the flag of the BoardElement to false */
    public void removeFlag(){
        flag = false;
    }

    /** Returns the value of the BoardElement */
    public int getValue(){
        return -1;
    }
}
