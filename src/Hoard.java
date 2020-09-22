/* Implementation for Project 2 for SWEN20003 Object Oriented Software Development
 * (Semester 2) by Justin Aaron Kelley (997351).
 * Class representing thief's hoard for fruit.
 */

import bagel.Image;

public class Hoard extends Storage {

    public Hoard(int x, int y) {
        super(x, y, 0);
        this.setImage(new Image("res/images/hoard.png"));
    }
}