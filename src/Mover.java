/* Implementation for Project 2 for SWEN20003 Object Oriented Software Development
 * (Semester 2) by Justin Aaron Kelley (997351).
 * Super class to represent actors that can move around.
 */

import java.util.ArrayList;

public abstract class Mover extends Actor{

    protected static final int UP = 0, DOWN = 2, RIGHT = 1, LEFT = 3;
    private int direction;
    private boolean carrying, active;
    private Coordinate prevCoordinate;

    public Mover(int x, int y, String image, int direction) {
        super(x, y, image);
        this.direction = direction;
        this.active = true;
        this.carrying = false;
        this.prevCoordinate = new Coordinate(x, y);
    }

    public boolean isActive() {
        return active;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isCarrying() {
        return carrying;
    }

    public void setCarrying(boolean carrying) {
        this.carrying = carrying;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Coordinate getPrevCoordinate() {
        return prevCoordinate;
    }

    public void setPrevCoordinate(Coordinate prevCoordinate) {
        this.prevCoordinate = prevCoordinate;
    }

    // Method to move the gatherer.
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

    private boolean withinTile(Actor actor) {
        int x1, y1, x2, y2;
        x1 = this.getCoordinate().getX();
        y1 = this.getCoordinate().getY();
        x2 = actor.getCoordinate().getX();
        y2 = actor.getCoordinate().getY();

        if (x2 - x1 < TILE_LENGTH - 1 && x2 - x1 >= -0.1 && y2 - y1 < TILE_LENGTH - 1 &&
                                                                y2 - y1 >= -0.1) {
            return true;
        }
        return false;
    }

    public ArrayList<Actor> getActorsInTile(World world) {
        ArrayList<Actor> actorsInSameTile = new ArrayList<>();
        for (Actor actor: world.getActors()) {
            if (withinTile(actor)) {
                actorsInSameTile.add(actor);
            }
        }
        return actorsInSameTile;
    }

    public abstract void updateStatus(World world);

    protected void onSign(Sign sign) {
        this.direction = sign.getDirection();
    }

    protected void onFence() {
        this.active = false;
        this.setCoordinate(this.prevCoordinate);
    }

    protected void moveForward() {
        this.move();
    }

    protected abstract ArrayList<Mover> onPool();

    protected boolean instanceInList(ArrayList<Actor> actorsInTile, Object o) {
        for (Actor actor: actorsInTile) {
            if (actor.getClass() == o.getClass()) {
                return true;
            }
        }
        return false;
    }
}



