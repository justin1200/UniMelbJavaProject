/**
 * Implementation for Project 2 for SWEN20003 Object Oriented Software Development (Semester 2)
 * @author by Justin Aaron Kelley (997351).
 * This class represents a fence in the simulation.
 * */

public class Fence extends Actor{

    /**
     * Constructor for setting coordinates.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public Fence(int x, int y) {
        super(x, y, "res/images/fence.png");
    }


    /**
     * Default constructor sets position at (0, 0).
     */
    public Fence() {
        super(0, 0, "res/images/fence.png");
    }
}
