/**
 * Implementation for Project 2 for SWEN20003 Object Oriented Software Development (Semester 2)
 * @author by Justin Aaron Kelley (997351).
 * This is the main driver class to run the ShadowLife simulation.*/

import bagel.Font;

public abstract class Storage extends Actor{

    // Constant to represent infinity.
    protected final static int INFINITE = -1;

    // Fruit held by storage place.
    private int fruit;

    // Constructor for Storage.
    public Storage(int x, int y, int fruit) {
        super(x, y);
        this.fruit = fruit;
    }

    // Getters and setters for fruit.
    public int getFruit() {
        return fruit;
    }

    public void setFruit(int fruit) {
        this.fruit = fruit;
    }

    // Changes add() method to add image of the Tree, Stockpile or Hoard to the graphical display with
    // an included number showing how much fruit it has.
    @Override
    public final void add() {
        super.add();
        Font font = new Font("res/VeraMono.ttf", 18);
        if (fruit != INFINITE) {
            font.drawString(String.valueOf(fruit),
                    (int) (this.getCoordinate().getX() / (TILE_LENGTH * 1.0)) * TILE_LENGTH,
                    (int) (this.getCoordinate().getY() / (TILE_LENGTH * 1.0)) * TILE_LENGTH);
        }

    }
}
