package entities;

import se.mau.DA343A.VT25.assignment1.ImageResources;

import java.awt.image.BufferedImage;

public class Car extends Element{


    ImageResources imageResources;
    public Car(int x, int y) {
        super("Car",x, y, 50,1);

    }
    @Override
    public BufferedImage getIcon() {
        return new ImageResources().getCarImage();
    }
    @Override
    public void calculatePollution() {

    }



}
