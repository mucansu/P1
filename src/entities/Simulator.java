package entities;

import se.mau.DA343A.VT25.assignment1.AirQualityApp;
import se.mau.DA343A.VT25.assignment1.MovedOutOfGridException;

import java.util.List;

public class Simulator {
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
        element.checkBoundries();
        element.move();
        if (checkBoundries(element)){
        elements.remove(element);
        throw new MovedOutOfGridException();
        }
    }

    private boolean checkBoundries(Element element) {
        return element.getX() < AirQualityApp.GRID_SIZE && element.getY() < AirQualityApp.GRID_SIZE;
    }
}
