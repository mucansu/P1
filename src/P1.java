import entities.*;
import se.mau.DA343A.VT25.assignment1.AirQualityApp;
import se.mau.DA343A.VT25.assignment1.IElementIcon;
import se.mau.DA343A.VT25.assignment1.ImageResources;
import se.mau.DA343A.VT25.assignment1.MovedOutOfGridException;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class P1 extends AirQualityApp{
    Element element;
    Simulator simulator;
    public P1(String[] elementSelectorTypeNames, BufferedImage mapImage, Simulator simulator) {
        super(elementSelectorTypeNames, mapImage);
        this.simulator = simulator;
    }



    public static void main(String[] args) {

        String[] elementTypes = {"Car", "Bike", "Bus", "Airplane","Tree"};
        ImageResources imageResources = new ImageResources();
        BufferedImage mapImage = imageResources.getMapImage();
        Simulator simulator1 = new Simulator();

        new P1(elementTypes,mapImage,simulator1).startGUIOnNewThread();


    }

    @Override
    protected void mouseClicked(int i, int i1) {
       String type = getSelectedElementType();
       createElement(type,i,i1);
        System.out.println("mouse clicked : " +  i + " , " + i1);
        System.out.println(type);

    }


    @Override
    protected void buttonNextTimeStepClicked() {
        try {
            simulator.simulate();
            System.out.println("simulation started");
        } catch (MovedOutOfGridException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected List<IElementIcon> elementIconsToPaint() {
        List<IElementIcon> icons = new ArrayList<>();
        for (Element element : simulator.getElements()) {
            icons.add(new ElementIcon(element.getX(), element.getY(), element.getIcon()));
        }
        return icons;
    }

    protected void createElement(String type,int x,int y){


        switch (type.toLowerCase()) {
            case "car" -> simulator.addElement(new Car(x, y));
            case "bike" -> simulator.addElement(new Bike(x, y));
            case "bus" -> simulator.addElement(new Bus(x, y));
            case "tree" -> simulator.addElement(new Tree(x, y));

        }
    }

}
