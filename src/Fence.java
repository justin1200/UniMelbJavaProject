/* Implementation for Project 2 for SWEN20003 Object Oriented Software Development
 * (Semester 2) by Justin Aaron Kelley (997351).
 * Class to represent a fence.
 */

public class Fence extends Actor{

    // Detailed and default constructor for Fence.
    public Fence(int x, int y) {
        super(x, y, "res/images/fence.png");
    }

    public Fence() {
        super(0, 0, "res/images/fence.png");
    }
}
