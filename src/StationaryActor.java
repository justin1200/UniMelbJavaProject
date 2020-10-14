/**
 * Implementation for Project 2 for SWEN20003 Object Oriented Software Development (Semester 2)
 * @author by Justin Aaron Kelley (997351).
 * This class represents an actor that is stationary and has no specifc behaviour such as movement or being able
 * to store fruit. Any actions related to these actors depends on that Actor landing on this Actor.
 * */

public class StationaryActor extends Actor{

    /**
     * Constructor for setting coordinates.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @param image The image used to represent this Actor on the display.
     * @param type The type of StationaryActor this Actor is.
     */
    public StationaryActor(int x, int y, String image, String type) {
        super(x, y, image, type);
    }
}

