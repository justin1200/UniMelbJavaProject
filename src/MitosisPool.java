/**
 * Implementation for Project 2 for SWEN20003 Object Oriented Software Development (Semester 2)
 * @author by Justin Aaron Kelley (997351).
 * This class represents a Mitosis Pool in the simulation.
 * */

public class MitosisPool extends Actor {

    /**
     * Constructor allowing for choosing position of the Mitosis Pool.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public MitosisPool(int x, int y) {
        super(x, y, "res/images/pool.png");
    }

    /**
     * Default constructor sets position at (0, 0).
     */
    public MitosisPool() {
        super(0, 0, "res/images/pool.png");
    }
}
