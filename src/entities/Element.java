package entities;

import java.util.Random;

public abstract class Element implements Move,IPollution{
    Random random = new Random();
    private String name;
    private double pollutionValue;
     int x;
     int y;
     private int howManyGridToMove;
    public Element(int x, int y, double pollutionValue, int howManyGridToMove) {
        this.x = x;
        this.y = y;
        this.pollutionValue = pollutionValue;
        this.howManyGridToMove = howManyGridToMove;
    }

    public void move(){
        int newX = x;
        int newY = y;
        int direction = random.nextInt(4);
        switch (direction){
            case 0 -> newY -= howManyGridToMove;
            case 1 -> newY += howManyGridToMove;
            case 2 -> newX += howManyGridToMove;
            case 3 -> newX -= howManyGridToMove;
        }
        checkBoundries(newX,newY,x,y);

    }
    public boolean checkIfWithinBoundries(int newX, int newY, int x, int y) {
        if (newX >= 0 && newX <  && newY >= 0 && newY < y) {

        }
    }
    public void checkBoundries(int newX, int newY, int x, int y) {
        if (newX >= 0 && newX <  && newY >= 0 && newY < y) {
            this.x = newX;
            this.y = newY;
        }
    }


    public int getX() { return x; }
    public int getY() { return y; }
    public double getPollutionValue() { return pollutionValue; }

}
