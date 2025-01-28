package entities;

import se.mau.DA343A.VT25.assignment1.AirQualityApp;
import se.mau.DA343A.VT25.assignment1.Direction;
import se.mau.DA343A.VT25.assignment1.MovedOutOfGridException;

import java.util.List;
import java.util.Random;

public class Simulator {
    Random random = new Random();
    private List<Element> elements;

    public List<Element> getElements() {
        return elements;
    }
    public void simulate() throws MovedOutOfGridException {
        for (Element element : elements){

            moveToRandomDirection(element);
        }
    }
    public void moveToRandomDirection(Element element) throws MovedOutOfGridException {
       Direction direction = generateRandomDirection();

        if (checkBoundries(element,direction)){
            element.move(direction);
        }
        else {

        elements.remove(element);
        throw new MovedOutOfGridException();
        }
    }
    public Direction generateRandomDirection(){
        Direction direction = Direction.values()[random.nextInt(Direction.values().length)];

        return direction;
    }

    private boolean checkBoundries(Element element, Direction direction) {
        switch (direction){
            case Direction.EAST : return !(element.getX()+element.getHowManyGridToMove()<=AirQualityApp.GRID_SIZE);
            case Direction.NORTH: return !(element.getY()+element.getHowManyGridToMove()<=AirQualityApp.GRID_SIZE);
            case Direction.WEST: return !(element.getX()-element.getHowManyGridToMove()>=AirQualityApp.GRID_SIZE);
            case Direction.SOUTH: return !(element.getY()-element.getHowManyGridToMove()>=AirQualityApp.GRID_SIZE);
        }

        return true;
    }
}
