package entities;
import se.mau.DA343A.VT25.assignment1.IElementIcon;
import java.awt.image.BufferedImage;

public class ElementIcon implements IElementIcon {
    private Element element;
    public ElementIcon(Element element) {
        this.element = element;
    }

    @Override
    public BufferedImage getIcon() {
        return element.getIcon();
    }

    @Override
    public int getRow() {
        return element.getY();
    }

    @Override
    public int getColumn() {
        return element.getX();
    }
}