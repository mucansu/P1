package entities;

import se.mau.DA343A.VT25.assignment1.ImageResources;

import java.awt.image.BufferedImage;

public class Bus extends Element{


    public Bus(int x, int y) {
        super("Bus",x, y, 0.1, 2);
    }

    @Override
    public void calculatePollution() {

    }
    @Override
    public BufferedImage getIcon() {
        return new ImageResources().getBusImage();
    }
}
