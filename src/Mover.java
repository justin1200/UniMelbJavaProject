/**
 * Implementation for Project 2 for SWEN20003 Object Oriented Software Development (Semester 2)
 * @author by Justin Aaron Kelley (997351).
 * This class represents a general Mover in the simulation, which is able to move every tick and performs actions
 * depending on what it moves onto.
 * */

import java.util.ArrayList;

public abstract class Mover extends Actor{

    // Directions as constants set up as a modulus 4 number system.
    protected static final int UP = 0, DOWN = 2, RIGHT = 1, LEFT = 3;

    // Direction gatherer moves in.
    private int direction;

    // If gatherer is carrying a fruit or if it is active still.
    private boolean carrying, active;

    // Previous position of the Mover.
    private final Coordinate prevCoordinate;



    /**
     * Constructor for creating a Mover.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @param image The image that represents the Mover on the display.
     * @param direction the direction the Mover moves in.
     */
    public Mover(int x, int y, String image, int direction) {
        super(x, y, image);
        this.direction = direction;
        this.active = true;
        this.carrying = false;
        this.prevCoordinate = new Coordinate(x, y);
    }



    /**
     * Getter for direction
     * @return The direction the Mover is moving in.
     */
    public int getDirection() {
        return direction;
    }


    /**
     * Sets the direction.
     * @param direction The direction the Mover will face.
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }


    /**
     * Returns the value indicating if the Mover is active.
     * @return Indicates if the Mover is active.
     */
    public boolean isActive() {
        return active;
    }


    /**
     * Returns the value indicating if the Mover is carrying a fruit.
     * @return Indicates if the Mover carries a fruit.
     */
    public boolean isCarrying() {
        return carrying;
    }


    /**
     * Sets the value for if the Mover is carrying.
     * @param carrying The new value indicating if the Mover is carrying.
     */
    public void setCarrying(boolean carrying) {
        this.carrying = carrying;
    }


    /**
     * Gets the previous position of the Mover.
     * @return The previous coordinate for the Mover.
     */
    public Coordinate getPrevCoordinate() {
        return prevCoordinate;
    }


    /**
     * Moves the Mover by a tile length (64 pixels) in the direction it is indicated to move in.
     */
    public void move() {
            // Move up.
        if (direction == UP) {
            this.setCoordinateY(this.getCoordinate().getY() - TILE_LENGTH);
            // Move down.
        } else if (direction == DOWN){
            this.setCoordinateY(this.getCoordinate().getY() + TILE_LENGTH);
            // Move right.
        } else if (direction == RIGHT){
            this.setCoordinateX(this.getCoordinate().getX() + TILE_LENGTH);
            // Move left.
        } else if (direction == LEFT){
            this.setCoordinateX(this.getCoordinate().getX() - TILE_LENGTH);
        }
    }



    // Finds out if another Actor is within the same tile.
    private boolean withinTile(Actor actor) {

        // Coordinates for Mover and Actor.
        int x1, y1, x2, y2;

        // Mover's coordinates.
        x1 = this.getCoordinate().getX();
        y1 = this.getCoordinate().getY();

        // Other Actor's coordinates.
        x2 = actor.getCoordinate().getX();
        y2 = actor.getCoordinate().getY();

        // Checks if the two actors are in same tile.
        return x2 - x1 < TILE_LENGTH - 1 && x2 - x1 >= -0.1 && y2 - y1 < TILE_LENGTH - 1 &&
                y2 - y1 >= -0.1;
    }


    /**
     * Finds and returns an ArrayList of all the actors that are in the same tile as the Mover.
     * @param world Stores all the actors in the simulation.
     * @return The ArrayList containing all the actors in the same tile as the Mover.
     */
    public ArrayList<Actor> getActorsInTile(World world) {

        // All the actors within the same tile.
        ArrayList<Actor> actorsInSameTile = new ArrayList<>();

        // Find what actors are in the same tile.
        for (Actor actor: world.getActors()) {
            if (withinTile(actor) && !actor.isMarkedForDelete()) {
                actorsInSameTile.add(actor);
            }
        }
        return actorsInSameTile;
    }


    // Checks if an actor of a specific class is in the list.
    protected Actor instanceInList(ArrayList<Actor> actorsInTile, Object o) {
        for (Actor actor: actorsInTile) {
            if (actor.getClass() == o.getClass()) {
                return actor;
            }
        }
        return null;
    }



    /**
     * Updates the status of the Mover based on its subtype.
     * @param world Stores all the actors in the simulation.
     */
    public abstract void updateStatus(World world);



    // Creates two more of the same class if the Mover lands on a Mitosis Pool.
    protected abstract void onPool(World world);


    // To move a Mover forward.
    protected void moveForward() {
        this.getPrevCoordinate().setX(this.getCoordinate().getX());
        this.getPrevCoordinate().setY(this.getCoordinate().getY());
        this.move();
    }


    // To update state of Mover if they are on a pool, fence, tree, sign or on nothing.
    protected void checkCommonMoverBehaviour(World world) {

        // Move the Mover forward if active.
        if (this.isActive()) {
            moveForward();
        }

        // Get actors that are on the same tile.
        ArrayList<Actor> actorsInTile = getActorsInTile(world);


        // If Gatherer is on a fence. Set it to inactive and move to previous position.
        Actor actor = instanceInList(actorsInTile, new Fence());
        if (actor != null) {
            this.active = false;
            this.getCoordinate().setX(this.getPrevCoordinate().getX());
            this.getCoordinate().setY(this.getPrevCoordinate().getY());
        }


        // Check if the Mover is on a Mitosis Pool. If so, add two new Movers of the same class of the Mover and delete
        // the Mover.
        ArrayList<Mover> newMovers;
        actor = instanceInList(actorsInTile, new MitosisPool());

        if (actor != null) {
            this.onPool(world);
            this.active = false;
            this.setMarkedForDelete(true);
        }


        // Check if Mover lands on a sign. Update direction to Sign's direction if so.
        actor = instanceInList(actorsInTile, new Sign());
        if (actor != null) {
            this.direction = ((Sign) actor).getDirection();
        }


        // Check if the Mover is on a tree and can pick up a fruit.
        actor = instanceInList(actorsInTile, new Tree());
        if (actor != null && !this.isCarrying()) {
            Tree tree = (Tree) actor;
            // Take a fruit from a tree if it has any.
            if (tree.getFruit() > 0 || tree.isGolden()) {
                if (!tree.isGolden()) {
                    tree.setFruit(tree.getFruit() - 1);
                }
                this.setCarrying(true);
                if (this instanceof Gatherer) {
                    this.setDirection((this.getDirection() + 2) % 4);
                }
            }
        }
    }
}



