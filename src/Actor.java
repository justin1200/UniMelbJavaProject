import bagel.*;

/**
 * Implementation for Project 2 for SWEN20003 Object Oriented Software Development (Semester 2).
 * This is the abstract class that represents a general actor that may be static or non-static in behaviour.
 * This class allows for the creation of an actor and adding an image of it to the display at a given location.
 * @author by Justin Aaron Kelley (997351).
 * */
public abstract class Actor {

    /**
     * Length of a tile in the simulation in number of pixels.
     */
    public static final int TILE_LENGTH = 64;

    // Marks if Actor is to be deleted.
    private boolean markedForDelete;

    // Coordinate for actor and the image representation for them.
    private final Coordinate coordinate;
    private Image image;

    // Marks the type for the Actor.
    private String type;

    /**
     * Constructor for creating a general Actor.
     * @param x This is the x-coordinate of the Actor's position on the display.
     * @param y This is the y-coordinate of the Actor's position on the display.
     * @param image This is the image that represents the Actor on the display.
     * @param type The type of Actor this Actor is.
     */
    public Actor(int x, int y, String image, String type) {
        this.coordinate = new Coordinate(x, y);
        this.adjustCoordinates();
        this.image = new Image(image);
        this.markedForDelete = false;
        this.type = type;
    }


    /**
     * Another constructor for creating an Actor.
     * @param x This is the x-coordinate of the Actor's position on the display.
     * @param y This is the y-coordinate of the Actor's position on the display.
     */
    public Actor(int x, int y) {
        this.coordinate = new Coordinate(x, y);
        this.adjustCoordinates();
        this.markedForDelete = false;
    }



    /**
     * Getter for coordinates.
     * @return Returns the Coordinate for the Actor.
     */
    public final Coordinate getCoordinate() {
        return coordinate;
    }


    /**
     * Sets the x-coordinate of the Actor.
     * @param x The x-coordinate to be set.
     */
    public final void setCoordinateX(int x) {
        this.coordinate.setX(x);
    }


    /**
     * Sets the y-coordinate of the Actor.
     * @param y The y-coordinate to be set.
     */
    public final void setCoordinateY(int y) {
        this.coordinate.setY(y);
    }


    /**
     * Setter for the image to be displayed.
     * @param image The image that will be displayed.
     */
    public void setImage(Image image) {
        this.image = image;
    }


    /**
     * Returns the type of actor this Actor is.
     * @return Returns the type of this actor as a String.
     */
    public String getType() {
        return type;
    }


    /**
     * Sets the type of actor this Actor is.
     * @param type The type of this actor as a String.
     */
    public void setType(String type) {
        this.type = type;
    }


    /**
     * Returns the value that indicates if the Actor is to be deleted.
     * @return The value that indicates if the Actor is to be deleted.
     */
    public boolean isMarkedForDelete() {
        return markedForDelete;
    }


    /**
     * Sets the value that indicates if the Actor is to be deleted.
     * @param markedForDelete Updated value if the Actor is to be deleted.
     */
    public void setMarkedForDelete(boolean markedForDelete) {
        this.markedForDelete = markedForDelete;
    }



    /**
     * Adds an image of the Actor on the display at its location.
     */
    public void add() {
        this.image.drawFromTopLeft(this.coordinate.getX(), this.coordinate.getY());
    }



    // Fixes the coordinate so it is in the top left of a tile if coordinates are not in that location.
    // Coordinates are in a tile if the x or y coordinates is less then 64 pixels more then the top left location.
    private void adjustCoordinates() {
        this.coordinate.setX((int) (this.coordinate.getX() / (TILE_LENGTH * 1.0)) * TILE_LENGTH);
        this.coordinate.setY((int) (this.coordinate.getY() / (TILE_LENGTH * 1.0)) * TILE_LENGTH);
    }
}
