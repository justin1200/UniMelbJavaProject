/**
 * Implementation for Project 2 for SWEN20003 Object Oriented Software Development (Semester 2)
 * @author by Justin Aaron Kelley (997351).
 * This class represents a pad in the simulation.
 * */

public class Pad extends Actor{

    /**
     * Constructor for setting coordinates.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public Pad(int x, int y) {
        super(x, y, "res/images/pad.png");
    }


    /**
     * Default constructor sets position at (0, 0).
     */
    public Pad() {
        super(0, 0 , "res/images/pad.png");
    }
}
