/* Implementation for Project 2 for SWEN20003 Object Oriented Software Development
 * (Semester 2) by Justin Aaron Kelley (997351).
 * Class to represent a sign.
 */

import bagel.Image;

public class Sign extends Actor{

    // Direction sign is facing.
    private final int direction;

    // Constructor for Sign.
    public Sign(int x, int y, int direction) {
        super(x, y);

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

    // Default constructor for sign.
    public Sign() {
        super(0, 0);
        this.direction = Mover.UP;
    }

    // Getter for direction.
    public int getDirection() {
        return direction;
    }
}