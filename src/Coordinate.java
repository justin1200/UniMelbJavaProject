/**
 * Implementation for Project 2 for SWEN20003 Object Oriented Software Development (Semester 2)
 * @author by Justin Aaron Kelley (997351).
 * This class stores the coordinates of the Actors in the simulation and this is the location they appear at on the
 * display.
 * */

public class Coordinate {

    // X and y coordinates.
    private int x, y;



    /**
     * Constructor for creating a pair of coordinates.
     * @param x Sets the x-coordinate.
     * @param y Sets the y-coordinate.
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }



    /**
     * Sets the x-coordinate.
     * @param x The x-coordinate to be set to.
     */
    public void setX(int x) {
        this.x = x;
    }


    /**
     * Sets the y-coordinate.
     * @param y The y-coordinate to be set to.
     */
    public void setY(int y) {
        this.y = y;
    }


    /**
     * Returns the x-coordinate.
     * @return The x-coordinate.
     */
    public int getX() {
        return x;
    }


    /**
     * returns the y-coordinate.
     * @return The y-coordinate.
     */
    public int getY() {
        return y;
    }
}
