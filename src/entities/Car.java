package entities;

import se.mau.DA343A.VT25.assignment1.ImageResources;

import java.awt.image.BufferedImage;
/**
 * The Car class represents a vehicle that moves on land and contributes to air pollution.
 * Cars emit a specific amount of pollution at their current location and move one grid unit per step.
 */
public class Car extends Element{



    /**
     * Constructs a new Car object at the specified coordinates.
     *
     * @param x The initial X-coordinate of the car.
     * @param y The initial Y-coordinate of the car.
     */
    public Car(int x, int y) {
        super("Car",x, y, 50,1);

    }
    /**
     * Returns the image icon representing the car.
     *
     * @return A BufferedImage of the car's icon.
     */
    @Override
    public BufferedImage getIcon() {
        return new ImageResources().getCarImage();
    }
    /**
     * Retrieves the pollution value emitted by the car.
     *
     * @return The pollution value (50).
     */
    @Override
    public double getPollutionValue() {
        return 50;
    }
    /**
     * Determines if the car is capable of movement.
     *
     * @return True, since cars can move.
     */
    @Override
    public boolean isMovable() {
        return true;
    }
    /**
     * Determines whether the car can only be placed on land.
     *
     * @return True, as cars must be created on land.
     */
    @Override
    public boolean isLandOnly() {
        return true;
    }
 
    @Override
    public void calculatePollution() {

    }



}
