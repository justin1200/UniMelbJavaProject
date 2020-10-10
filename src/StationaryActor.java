import bagel.Image;

/**
 * Implementation for Project 2 for SWEN20003 Object Oriented Software Development (Semester 2)
 * @author by Justin Aaron Kelley (997351).
 * This class represents an actor that does not move each tick or store any fruit.
 * */
public class StationaryActor extends Actor{

    /**
     * Constructor for setting coordinates.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public StationaryActor(int x, int y, String image, String type) {
        super(x, y, image, type);
    }
}

