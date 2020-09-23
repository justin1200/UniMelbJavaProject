/* Implementation for Project 2 for SWEN20003 Object Oriented Software Development
 * (Semester 2) by Justin Aaron Kelley (997351).
 *Class representing a normal or golden tree.
 */

import bagel.Image;

public class Tree extends Storage{

    // Tracks if the tree is a golden tree.
    private boolean isGolden;


    // Constructor for creating a Tree.
    public Tree(int x, int y, int fruit, boolean isGolden) {
        super(x, y, fruit);
        this.isGolden = isGolden;

        if (isGolden) {
            this.setImage(new Image("res/images/gold-tree.png"));
        } else {
            this.setImage(new Image("res/images/tree.png"));
        }
    }

    // Default constructor for Tree.
    public Tree() {
        super(0, 0, 3);
        this.isGolden = false;
        this.setImage(new Image("res/images/tree.png"));
    }


    // Getter for isGolden.
    public boolean isGolden() {
        return isGolden;
    }
}
