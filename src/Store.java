import bagel.Image;

/**
 * Implementation for Project 2 for SWEN20003 Object Oriented Software Development (Semester 2).
 * This represents a place where fruit is stored by actors like gatherers or thiefs in the simulation. These locations
 * are intially empty and act as points of storing gathered fruit.
 * @author by Justin Kelley.
 * */
public class Store extends Storage {

    /**
     * Constructor for setting coordinates.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @param type The type of Actor this Store is.
     */
    public Store(int x, int y, String type) {
        super(x, y, 0);

        if (type.equals("Stockpile")) {
            this.setImage(new Image("res/images/cherries.png"));
            this.setType("Stockpile");
        } else if (type.equals("Hoard")) {
            this.setImage(new Image("res/images/hoard.png"));
            this.setType("Hoard");
        }
    }
}
