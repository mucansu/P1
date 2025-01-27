import entities.*;
import se.mau.DA343A.VT25.assignment1.AirQualityApp;
import se.mau.DA343A.VT25.assignment1.ImageResources;
import se.mau.DA343A.VT25.assignment1.MovedOutOfGridException;


import java.awt.image.BufferedImage;
import java.util.List;

public class P1 extends AirQualityApp{
    Element element;
    Simulator simulator;
    public P1(String[] elementSelectorTypeNames, BufferedImage mapImage) {
        super(elementSelectorTypeNames, mapImage);
    }



    public static void main(String[] args) {

        String[] elementTypes = {"Car", "Bike", "Bus", "Airplane"};
        ImageResources imageResources = new ImageResources();
        BufferedImage mapImage = imageResources.getMapImage();


        new P1(elementTypes,mapImage).startGUIOnNewThread();


    }

    @Override
    protected void mouseClicked(int i, int i1) {
       String test = getSelectedElementType();


        if (test.equals("car")){
            System.out.println("This is car,do something");
            setPollution(i,i1,5);
        }
        else if (test.equals("bike")){
            System.out.println("This is bike,do something");
        }
        if (test.equals("bus")){
            System.out.println("This is bus, do something");
        }
    }


    @Override
    protected void buttonNextTimeStepClicked() {
        try {
            simulator.simulate();
        } catch (MovedOutOfGridException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected List elementIconsToPaint() {
        return List.of();
    }

    protected Element createElement(int x,int y){
        String type = getSelectedElementType();
        return switch (getSelectedElementType()){
            case "car" : new Car(x,y);
            case "bus" : new Bus(x,y);
            case "airplane" : new Airplane(x,y);
            default:null;
        };
    }
}
