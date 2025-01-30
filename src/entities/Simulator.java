package entities;

import se.mau.DA343A.VT25.assignment1.AirQualityApp;
import se.mau.DA343A.VT25.assignment1.Direction;
import se.mau.DA343A.VT25.assignment1.MovedOutOfGridException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Simulator {
    Random random = new Random();
    private List<Element> elements;

    public Simulator() {
        this.elements = new ArrayList<>();
    }

    public List<Element> getElements() {
        return elements;
    }
    public void simulate() throws MovedOutOfGridException {
        List<Element> toRemove = new ArrayList<>();
        for (Element element : elements){
            if (element.isMovable()){
                try {
                    moveToRandomDirection(element);

                } catch (MovedOutOfGridException e) {
                    System.out.println(e.getMessage());
                    toRemove.add(element);
                }
            }

        }
        System.out.println("List size: " + elements.size());
        elements.removeAll(toRemove);
    }
    public void addElement(Element element){

        elements.add(element);

    }
    public void moveToRandomDirection(Element element) throws MovedOutOfGridException {
       Direction direction = generateRandomDirection();

        if (!checkBoundries(element,direction)){

            throw new MovedOutOfGridException();

        }
        else {

            element.move(direction);
            System.out.println(element.getName() + " moved to random direction : " + direction);
            System.out.println("coordinates : " + element.getX() + " , " + element.getY());



        }
    }
    public Direction generateRandomDirection(){
        Direction direction = Direction.values()[random.nextInt(Direction.values().length)];

        return direction;
    }

    private boolean checkBoundries(Element element, Direction direction) {
        return switch (direction) {
            case Direction.EAST -> (element.getX() + element.getHowManyGridToMove()) < AirQualityApp.GRID_SIZE;
            case Direction.NORTH -> (element.getY() - element.getHowManyGridToMove()) >= 0;
            case Direction.WEST -> (element.getX() - element.getHowManyGridToMove()) >= 0;
            case Direction.SOUTH -> (element.getY() + element.getHowManyGridToMove()) < AirQualityApp.GRID_SIZE;
            default -> false;
        };
    }
}
