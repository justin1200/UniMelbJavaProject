/* Implementation for Project 2 for SWEN20003 Object Oriented Software Development
 * (Semester 2) by Justin Aaron Kelley (997351).
 *Class representing a normal or golden tree.
 */

import bagel.Image;

public class Tree extends Storage{

    private boolean isGolden;

    public Tree(int x, int y, int fruit, boolean isGolden) {
        super(x, y, fruit);
        this.isGolden = isGolden;

        if (isGolden) {
            this.setImage(new Image("res/images/gold-tree.png"));
        } else {
            this.setImage(new Image("res/images/tree.png"));
        }
    }
}
