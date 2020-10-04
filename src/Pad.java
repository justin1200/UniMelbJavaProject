/**
 * Implementation for Project 2 for SWEN20003 Object Oriented Software Development (Semester 2)
 * @author by Justin Aaron Kelley (997351).
 * This is the main driver class to run the ShadowLife simulation.*/

public class Pad extends Actor{

    // Constructors for Pad. One for detailed input, another default version.
    public Pad(int x, int y) {
        super(x, y, "res/images/pad.png");
    }

    public Pad() {
        super(0, 0 , "res/images/pad.png");
    }
}
