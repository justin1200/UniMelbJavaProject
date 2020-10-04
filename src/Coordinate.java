/**
 * Implementation for Project 2 for SWEN20003 Object Oriented Software Development (Semester 2)
 * @author by Justin Aaron Kelley (997351).
 * This is the main driver class to run the ShadowLife simulation.*/

public class Coordinate {

    // X and y coordinates.
    private int x, y;

    // Constructor for coordinates.
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Setters for coordinates.
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Getters for coordinates.
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
