package entities;

import se.mau.DA343A.VT25.assignment1.AirQualityApp;
import se.mau.DA343A.VT25.assignment1.Direction;
import se.mau.DA343A.VT25.assignment1.ImageResources;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class Element implements Move,IPollution{
    Random random = new Random();
    private String name;
    private double pollutionValue;
     private int x;
     private int y;
     private int howManyGridToMove;
     BufferedImage icon;

     private boolean isMovable;

    public Element(String name,int x, int y, double pollutionValue, int howManyGridToMove ) {
        this.x = x;
        this.y = y;
        this.pollutionValue = pollutionValue;
        this.howManyGridToMove = howManyGridToMove;
        this.name = name;
        this.isMovable = true;

    }



    public String getName() {
        return name;
    }

    public int getHowManyGridToMove() {
        return howManyGridToMove;
    }

    public BufferedImage getIcon() {
        return icon;
    }

    public boolean isMovable() {
        return isMovable;
    }

    public void setMovable(boolean movable) {
        isMovable = movable;
    }

    public void move(Direction direction){
        switch (direction){
            case Direction.EAST : setX(x+howManyGridToMove); break;
            case Direction.NORTH: setY(y+howManyGridToMove); break;
            case Direction.WEST: setX(x-howManyGridToMove); break;
            case Direction.SOUTH: setY(y-howManyGridToMove); break;
        }


    }



    public int getX() { return x; }
    public int getY() { return y; }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getPollutionValue() { return pollutionValue; }

}
