package entities;

import se.mau.DA343A.VT25.assignment1.Direction;
import se.mau.DA343A.VT25.assignment1.ImageResources;

import java.awt.image.BufferedImage;

public class Tree extends Element{


    public Tree(int x, int y) {
        super("Tree",x, y, -5, 0);
        setMovable(false);
    }

    @Override
    public void calculatePollution() {

    }

    @Override
    public BufferedImage getIcon() {
        return new ImageResources().getTreesImage();
    }
    @Override
    public void move(Direction direction) {

    }

}
