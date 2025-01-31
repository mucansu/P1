import entities.*;
import se.mau.DA343A.VT25.assignment1.*;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class P1 extends AirQualityApp{
    private List<Element> elements;
    private Random random = new Random();
    private double[][] pollutionGrid;
    private IsLand island;

    public P1(String[] elementSelectorTypeNames, BufferedImage mapImage) {
        super(elementSelectorTypeNames, mapImage);
        this.elements = new ArrayList<>();
        this.pollutionGrid = new double[GRID_SIZE][GRID_SIZE];
        this.island = new IsLand();
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

    public boolean isOnLand(int x, int y) {
        return island.isLand(y, x); // isLand() metodu row, col bekliyor
    }
    protected void createElement(String type,int x,int y){
        Element newElement = null;
       newElement =  switch (type.toLowerCase()) {
            case "car" -> new Car(x, y);
            case "bike" -> new Bike(x, y);
            case "bus" -> new Bus(x, y);
            case "tree" -> new Tree(x, y);
            case "airplane" -> new Airplane(x, y);
           default -> null;
        };
        if (newElement.isLandOnly() && !isOnLand(x, y)) {
            System.out.println("⚠️ Bu element sadece kara üzerinde oluşturulabilir: " + type);
            return;
        }

        if (!newElement.isMovable() && containsNonMovableElement(x, y)) {
            System.out.println("⚠️ Bu koordinatta zaten bir hareket etmeyen element var! Tekrar eklenemez.");
            return;
        }
        System.out.println("mouse clicked : " +  x + " , " + y);
        elements.add(newElement);
        if (!elements.isEmpty()) {
            System.out.println("Element added: " + elements.getLast().getName());
        }
        updatePollutionGrid();
        repaint();
    }
    public boolean containsNonMovableElement(int x, int y) {
        for (Element element : elements) {
            if (!element.isMovable() && element.getX() == x && element.getY() == y) {
                return true;
            }
        }
        return false;
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
        updatePollutionGrid();
        System.out.println("List size: " + elements.size());
        repaint();

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
    private void updatePollutionGrid() {
        double[][] newPollutionGrid = new double[GRID_SIZE][GRID_SIZE];
        for (int y = 0; y < GRID_SIZE; y++) {
            for (int x = 0; x < GRID_SIZE; x++) {
                pollutionGrid[y][x] = 0.0;
            }
        }


        for (Element element : elements) {
            int x = element.getX();
            int y = element.getY();
            if (x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE) {
                pollutionGrid[y][x] += element.getPollutionValue();
            }
        }

        for (int y = 0; y < GRID_SIZE; y++) {
            for (int x = 0; x < GRID_SIZE; x++) {
                double sum = pollutionGrid[y][x];


                if (x > 0) sum += pollutionGrid[y][x - 1];
                if (x < GRID_SIZE - 1) sum += pollutionGrid[y][x + 1];
                if (y > 0) sum += pollutionGrid[y - 1][x];
                if (y < GRID_SIZE - 1) sum += pollutionGrid[y + 1][x];

                newPollutionGrid[y][x] = Math.max(0, sum / 5);
            }
        }
        pollutionGrid = newPollutionGrid;

        for (int y = 0; y < GRID_SIZE; y++) {
            for (int x = 0; x < GRID_SIZE; x++) {
                setPollution(y, x, pollutionGrid[y][x]);
            }
        }
    }
}
