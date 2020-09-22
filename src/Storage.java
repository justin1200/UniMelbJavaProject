/* Implementation for Project 2 for SWEN20003 Object Oriented Software Development
 * (Semester 2) by Justin Aaron Kelley (997351).
 * Parent class representing abstract storage place for fruit.
 */

import bagel.Font;

public abstract class Storage extends Actor{

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


    @Override
    public void Add() {
        super.Add();
        Font font = new Font("res/VeraMono.ttf", 18);
        if (fruit != INFINITE) {
            font.drawString(String.valueOf(fruit),
                    (int) (this.getCoordinate().getX() / (TILE_LENGTH * 1.0)) * TILE_LENGTH,
                    (int) (this.getCoordinate().getY() / (TILE_LENGTH * 1.0)) * TILE_LENGTH);
        }

    }
}
