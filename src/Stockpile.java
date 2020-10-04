/**
 * Implementation for Project 2 for SWEN20003 Object Oriented Software Development (Semester 2)
 * @author by Justin Aaron Kelley (997351).
 * This represents a stockpile of fruit in the simulation.
 * */

import bagel.Image;

public class Stockpile extends Storage {

    /**
     * Constructor for setting coordinates.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public Stockpile(int x, int y) {
        super(x, y, 0);
        this.setImage(new Image("res/images/cherries.png"));
    }


    /**
     * Default constructor sets position at (0, 0).
     */
    public Stockpile() {
        super(0, 0, 0);
        this.setImage(new Image("res/images/cherries.png"));
    }
}
