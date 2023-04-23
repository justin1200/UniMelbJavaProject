import bagel.Font;

/**
 * Implementation for Project 2 for SWEN20003 Object Oriented Software Development (Semester 2).
 * This abstract class represents any static/stationary actor that can store fruit and has operations
 * related to displaying fruit count, adding or removing fruit.
 * @author by Justin Kelley.
 * */
public abstract class Storage extends Actor{

    // Constant to represent infinity.
    protected final static int INFINITE = -1;

    // Constant for font size.
    private final static int FONT_SIZE = 18;

    // Fruit held by storage place.
    private int fruit;



    /**
     * Constructor for setting coordinates and the number of fruit it stores.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @param fruit The amount of fruit to be stored.
     */
    public Storage(int x, int y, int fruit) {
        super(x, y);
        this.fruit = fruit;
    }


    /**
     * Gets the amount of fruit held by this Actor.
     * @return The amount of fruit held.
     */
    public int getFruit() {
        return fruit;
    }


    /**
     * Updates the amount of fruit held by this Actor.
     * @param fruit The amount of fruit that it will now hold.
     */
    public void setFruit(int fruit) {
        this.fruit = fruit;
    }



    /**
     * Adds the image of the Actor that can store fruit to the display at its position. A number indicating the
     * number of fruit held is shown next to its image unless it is storing an infinite amount of fruit.
     */
    @Override
    public void add() {
        super.add();
        Font font = new Font("res/VeraMono.ttf", FONT_SIZE);
        if (fruit != INFINITE) {
            font.drawString(String.valueOf(fruit), this.getCoordinate().getX(), this.getCoordinate().getY());
        }
    }
}
