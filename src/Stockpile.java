/* Implementation for Project 2 for SWEN20003 Object Oriented Software Development
 * (Semester 2) by Justin Aaron Kelley (997351).
 * Class representing gatherer's stockpile for fruit.
 */

import bagel.Image;

public class Stockpile extends Storage {

    public Stockpile(int x, int y) {
        super(x, y, 0);
        this.setImage(new Image("res/images/cherries.png"));
    }
}
