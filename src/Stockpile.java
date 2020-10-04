/**
 * Implementation for Project 2 for SWEN20003 Object Oriented Software Development (Semester 2)
 * @author by Justin Aaron Kelley (997351).
 * This is the main driver class to run the ShadowLife simulation.*/

import bagel.Image;

public class Stockpile extends Storage {

    // Detailed and default constructors for Stockpile.
    public Stockpile(int x, int y) {
        super(x, y, 0);
        this.setImage(new Image("res/images/cherries.png"));
    }

    public Stockpile() {
        super(0, 0, 0);
        this.setImage(new Image("res/images/cherries.png"));
    }
}
