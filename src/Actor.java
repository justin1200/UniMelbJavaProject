/* Implementation for Project 2 for SWEN20003 Object Oriented Software Development
 * (Semester 2) by Justin Aaron Kelley (997351).
 *
 * This is superclass representing actors, which are any objects like a tree,
 * background or thief. */

import bagel.*;

public abstract class Actor {

    // Constant for tile length.
    protected static final int TILE_LENGTH = 64;

    // Coordinate for actor and the image representation for them.
    private Coordinate coordinate;
    private Image image;
    private boolean status;

    // Constructors for Actors.
    public Actor(int x, int y, String image) {
        this.coordinate = new Coordinate(x, y);
        this.image = new Image(image);
        this.status = true;
    }

    public Actor(int x, int y) {
        this.coordinate = new Coordinate(x, y);
        this.status = true;
    }

    // Getter and setters for coordinates.
    public final Coordinate getCoordinate() {
        return coordinate;
    }

    public final void setCoordinateX(int x) {
        this.coordinate.setX(x);
    }

    public final void setCoordinateY(int y) {
        this.coordinate.setY(y);
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    // Getter and setter for image.
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    // Getter and setter for status.
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // Adds an actor's image representation to the 2D graphical image if status is active.
    public void Add() {
        if (status) {
            this.image.drawFromTopLeft((int) (this.coordinate.getX() / (TILE_LENGTH * 1.0)) * TILE_LENGTH,
                    (int) (this.coordinate.getY() / (TILE_LENGTH * 1.0)) * TILE_LENGTH);
        }
    }

}
