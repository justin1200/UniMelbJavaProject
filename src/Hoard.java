/**
 * Implementation for Project 2 for SWEN20003 Object Oriented Software Development (Semester 2)
 * @author by Justin Aaron Kelley (997351).
 * This class represents a hoard in the simulation.
 * */

import bagel.Image;

public class Hoard extends Storage {

    /**
     * Constructor allowing for choosing position of the Hoard.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public Hoard(int x, int y) {
        super(x, y, 0);
        this.setImage(new Image("res/images/hoard.png"));
    }


    /**
     * Default constructor sets position at (0, 0).
     */
    public Hoard() {
        super(0, 0, 0);
        this.setImage(new Image("res/images/hoard.png"));
    }
}