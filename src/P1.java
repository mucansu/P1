import entities.*;
import se.mau.DA343A.VT25.assignment1.*;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class P1 extends AirQualityApp{
    private List<Element> elements;
    private Random random = new Random();

    public P1(String[] elementSelectorTypeNames, BufferedImage mapImage) {
        super(elementSelectorTypeNames, mapImage);
        this.elements = new ArrayList<>();
    }



    public static void main(String[] args) {

        String[] elementTypes = {"Car", "Bike", "Bus", "Airplane","Tree"};
        ImageResources imageResources = new ImageResources();
        BufferedImage mapImage = imageResources.getMapImage();


        new P1(elementTypes,mapImage).startGUIOnNewThread();


    }

    @Override
    protected void mouseClicked(int i, int i1) {
       String type = getSelectedElementType();
       createElement(type,i1,i);
        System.out.println("mouse clicked : " +  i + " , " + i1);
        System.out.println(type);
        //elementIconsToPaint();
        repaint();
    }


    @Override
    protected void buttonNextTimeStepClicked() {
        try {
            simulate();
            repaint();
        } catch (MovedOutOfGridException e) {
            throw new RuntimeException(e);
        }
        System.out.println("simulation started");
    }

    @Override
    protected List<ElementIcon> elementIconsToPaint() {
        List<ElementIcon> icons = new ArrayList<>();
        for (Element element : elements) {

            icons.add(new ElementIcon(element));
        }

        return icons;
    }

    protected void createElement(String type,int x,int y){


        switch (type.toLowerCase()) {
            case "car" -> elements.add(new Car(x, y));
            case "bike" -> elements.add(new Bike(x, y));
            case "bus" -> elements.add(new Bus(x, y));
            case "tree" -> elements.add(new Tree(x, y));

        }
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
        elements.removeAll(toRemove);
        System.out.println("List size: " + elements.size());

    }
    public void moveToRandomDirection(Element element) throws MovedOutOfGridException {
        Direction direction = generateRandomDirection();

        if (!checkBoundaries(element,direction)){

            throw new MovedOutOfGridException(element.getName() + " moved out of bounds at: " + element.getX() + ", " + element.getY());

        }
        else {

            element.move(direction);
            System.out.println(element.getName() + " moved to random direction : " + direction);
            System.out.println("coordinates : " + element.getX() + " , " + element.getY());



        }
    }
    public Direction generateRandomDirection() {
        return Direction.values()[random.nextInt(Direction.values().length)];
    }

    public boolean checkBoundaries(Element element, Direction direction) {
        return switch (direction) {
            case EAST -> (element.getX() + element.getHowManyGridToMove()) < AirQualityApp.GRID_SIZE;
            case NORTH -> (element.getY() - element.getHowManyGridToMove()) >= 0;
            case WEST -> (element.getX() - element.getHowManyGridToMove()) >= 0;
            case SOUTH -> (element.getY() + element.getHowManyGridToMove()) < AirQualityApp.GRID_SIZE;
            default -> false;
        };
    }

}
