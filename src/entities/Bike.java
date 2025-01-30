package entities;

import se.mau.DA343A.VT25.assignment1.ImageResources;

import java.awt.image.BufferedImage;

public class Bike extends Element {

    public Bike(int x, int y) {
        super("Bike",x, y, 0, 1);
    }

    @Override
    public BufferedImage getIcon() {
        return new ImageResources().getBikeImage();
    }

    @Override
    public void calculatePollution() {

    }
}
