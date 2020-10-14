/**
 * Implementation for Project 2 for SWEN20003 Object Oriented Software Development (Semester 2)
 * @author by Justin Aaron Kelley (997351).
 * This class represents a normal or golden tree in the simulation, which hold fruit. An actor capable of taking fruit
 * can take fruit from a tree. A tree acts as a place where fruit is intially located at and can be taken from.
 * */

import bagel.Image;

public class Tree extends Storage{



    /**
     * Constructor for creating a tree at a location and indicating if it is golden or not.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @param fruit The amount of fruit it holds.
     */
    public Tree(int x, int y, int fruit) {
        super(x, y, fruit);

        if (fruit == INFINITE) {
            this.setImage(new Image("res/images/gold-tree.png"));
            this.setType("GoldenTree");
        } else {
            this.setImage(new Image("res/images/tree.png"));
            this.setType("Tree");
        }
    }
}
