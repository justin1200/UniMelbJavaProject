/* Implementation for Project 2 for SWEN20003 Object Oriented Software Development
 * (Semester 2) by Justin Aaron Kelley (997351).
 * Class to store x and y coordinates for actors.
 */

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
