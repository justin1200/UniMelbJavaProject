/* Implementation for Project 2 for SWEN20003 Object Oriented Software Development
 * (Semester 2) by Justin Aaron Kelley (997351).
 *
 * This is superclass representing actors, which are any objects like a tree,
 * background or thief. */

import bagel.*;

public abstract class Actor {

    // Constant for tile length.
    protected static final int TILE_LENGTH = 64;

    // Marks if Actor is to be deleted.
    private boolean markForDelete;

    // Coordinate for actor and the image representation for them.
    private final Coordinate coordinate;
    private Image image;


    // Constructors for Actors.
    public Actor(int x, int y, String image) {
        this.coordinate = new Coordinate(x, y);
        this.image = new Image(image);
        this.markForDelete = false;
    }

    public Actor(int x, int y) {
        this.coordinate = new Coordinate(x, y);
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


    // Setter for image.
    public void setImage(Image image) {
        this.image = image;
    }


    // Getter and setter for markForDelete.
    public boolean isMarkForDelete() {
        return markForDelete;
    }

    public void setMarkForDelete(boolean markForDelete) {
        this.markForDelete = markForDelete;
    }


    // Adds an actor's image representation to the 2D graphical image.
    public void add() {
        this.image.drawFromTopLeft((int) (this.coordinate.getX() / (TILE_LENGTH * 1.0)) * TILE_LENGTH,
                (int) (this.coordinate.getY() / (TILE_LENGTH * 1.0)) * TILE_LENGTH);
    }
}
