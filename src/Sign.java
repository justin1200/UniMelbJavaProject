/**
 * Implementation for Project 2 for SWEN20003 Object Oriented Software Development (Semester 2)
 * @author by Justin Aaron Kelley (997351).
 * This class represents a sign in the simulation, which points in a direction. A Mover will change its direction
 * in the direction the sign faces in.
 * */

import bagel.Image;

public class Sign extends Actor{

    // Direction sign is facing.
    private final int direction;



    /**
     * Constructor for setting coordinates and direction for sign.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @param direction The direction the sign faces.
     */
    public Sign(int x, int y, int direction) {
        super(x, y);
        this.setType("Sign");

        // Image of sign depends on direction it faces.
        this.direction = direction;
        if (direction == Mover.UP) {
            this.setImage(new Image("res/images/up.png"));
        } else if (direction == Mover.DOWN) {
            this.setImage(new Image("res/images/down.png"));
        } else if (direction == Mover.RIGHT) {
            this.setImage(new Image("res/images/right.png"));
        } else if (direction == Mover.LEFT) {
            this.setImage(new Image("res/images/left.png"));
        }
    }


    /**
     * Default constructor sets position at (0, 0).
     */
    public Sign() {
        super(0, 0);
        this.direction = Mover.UP;
    }



    /**
     * Gets the direction the sign faces.
     * @return The direction the sign faces.
     */
    public int getDirection() {
        return direction;
    }
}