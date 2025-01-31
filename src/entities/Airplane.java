package entities;

import se.mau.DA343A.VT25.assignment1.ImageResources;

import java.awt.image.BufferedImage;

public class Airplane extends Element {


    public Airplane(int x, int y) {
        super("Airplane",x, y, 100, 5);
    }

    @Override
    public void calculatePollution() {

    }

    @Override
    public BufferedImage getIcon() {
        return new ImageResources().getElScooterImage();
    }

    @Override
    public boolean isMovable() {
        return true;
    }

    @Override
    public boolean isLandOnly() {
        return false;
    }
}
