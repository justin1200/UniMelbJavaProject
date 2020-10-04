/**
 * Implementation for Project 2 for SWEN20003 Object Oriented Software Development (Semester 2)
 * @author by Justin Aaron Kelley (997351).
 * This class represents a normal or golden tree in the simulation.
 * */

import bagel.Image;

public class Tree extends Storage{

    // Tracks if the tree is a golden tree.
    private boolean isGolden;



    /**
     * Constructor for creating a tree at a location and indicating if it is golden or not.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @param fruit The amount of fruit it holds.
     * @param isGolden If it is a golden tree or not.
     */
    public Tree(int x, int y, int fruit, boolean isGolden) {
        super(x, y, fruit);
        this.isGolden = isGolden;

        if (isGolden) {
            this.setImage(new Image("res/images/gold-tree.png"));
        } else {
            this.setImage(new Image("res/images/tree.png"));
        }
    }


    /**
     * Default constructor sets position at (0, 0) and creates a normal tree.
     */
    public Tree() {
        super(0, 0, 3);
        this.isGolden = false;
        this.setImage(new Image("res/images/tree.png"));
    }



    /**
     * Gets the value for if the tree is golden.
     * @return The value that indicates if the tree is golden or not.
     */
    public boolean isGolden() {
        return isGolden;
    }
}
