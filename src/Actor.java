/**
 * Implementation for Project 2 for SWEN20003 Object Oriented Software Development (Semester 2)
 * @author by Justin Aaron Kelley (997351).
 * This is the abstract class that represents a general actor that may be static or non-static in behaviour.*/

import bagel.*;

public abstract class Actor {

    // Constant for tile length.
    protected static final int TILE_LENGTH = 64;

    // Marks if Actor is to be deleted.
    private boolean markedForDelete;

    // Coordinate for actor and the image representation for them.
    private final Coordinate coordinate;
    private Image image;



    /**
     * Constructor for creating a general Actor.
     * @param x This is the x-coordinate of the Actor's position on the display (int).
     * @param y This is the y-coordinate of the Actor's position on the display (int).
     * @param image This is the image that represents the Actor on the display (Image).
     */
    public Actor(int x, int y, String image) {
        this.coordinate = new Coordinate(x, y);
        this.image = new Image(image);
        this.markedForDelete = false;
    }


    /**
     * Another constructor for creating an Actor.
     * @param x This is the x-coordinate of the Actor's position on the display (int).
     * @param y This is the y-coordinate of the Actor's position on the display (int).
     */
    public Actor(int x, int y) {
        this.coordinate = new Coordinate(x, y);
    }



    /**
     * Getter for coordinates.
     * @return Returns the Coordinate for the Actor (Coordinate).
     */
    public final Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Sets the x-coordinate of the Actor.
     * @param x The x-coordinate to be set (int).
     */
    public final void setCoordinateX(int x) {
        this.coordinate.setX(x);
    }

    public final void setCoordinateY(int y) {
        this.coordinate.setY(y);
    }


    // Setter for image.
    public void setImage(Image image) {
        this.image = image;
    }


    // Getter and setter for markForDelete.
    public boolean isMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(boolean markedForDelete) {
        this.markedForDelete = markedForDelete;
    }


    // Adds an actor's image representation to the 2D graphical image.
    public void add() {
        this.image.drawFromTopLeft((int) (this.coordinate.getX() / (TILE_LENGTH * 1.0)) * TILE_LENGTH,
                (int) (this.coordinate.getY() / (TILE_LENGTH * 1.0)) * TILE_LENGTH);
    }
}
