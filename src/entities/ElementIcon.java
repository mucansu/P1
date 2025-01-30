package entities;
import se.mau.DA343A.VT25.assignment1.IElementIcon;
import java.awt.image.BufferedImage;

public class ElementIcon implements IElementIcon {
    private int row;
    private int col;
    private BufferedImage icon;

    public ElementIcon(int row, int col, BufferedImage icon) {
        this.row = row;
        this.col = col;
        this.icon = icon;
    }

    @Override
    public BufferedImage getIcon() {
        return icon;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return col;
    }
}

