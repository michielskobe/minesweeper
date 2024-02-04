package boardElementClasses;
public abstract class BoardElement {
    protected ElementType elementType;
    protected boolean flag;
    protected boolean visibility;

    public BoardElement(ElementType elementType){
        this.elementType = elementType;
        this.flag = false;
        this.visibility = false;
    }
    public ElementType getElementType() {
        return elementType;
    }
    public boolean getVisibility() {
        return visibility;
    }
    public void makeVisible(){visibility = true;}
    public boolean getFlag() {
        return flag;
    }
    public void addFlag(){flag = true;}
    public void removeFlag(){flag = false;}
    public int getValue(){
        return -1;
    }
}
