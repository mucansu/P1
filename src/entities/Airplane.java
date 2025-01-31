package entities;

import se.mau.DA343A.VT25.assignment1.ImageResources;

import java.awt.image.BufferedImage;

public class Airplane extends Element {


    public Airplane(int x, int y) {
        super("Bike",x, y, 10, 5);
    }

    @Override
    public void calculatePollution() {

    }

    @Override
    public BufferedImage getIcon() {
        return new ImageResources().getTrainImage();
    }

    @Override
    public boolean isMovable() {
        return true;
    }
}
