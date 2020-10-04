/**
 * Implementation for Project 2 for SWEN20003 Object Oriented Software Development (Semester 2)
 * @author by Justin Aaron Kelley (997351).
 * This class handles the functionality for a thief including the logic for updating it every tick.
 * */

import java.util.ArrayList;

public class Thief extends Mover {

    // If Thief will consume a fruit or not.
    private boolean consuming;



    /**
     * Constructor that allows for position setting.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public Thief(int x, int y) {
        super(x, y, "res/images/thief.png", UP);
        consuming = false;
    }


    /**
     * Constructor that allows for position and direction setting.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @param direction The direction the Gatherer moves in.
     */
    public Thief(int x, int y, int direction) {
        super(x, y, "res/images/thief.png", UP);
        consuming = false;
        this.setDirection(direction);
    }


    /**
     * Default constructor sets position at (0, 0).
     */
    public Thief() {
        super(0, 0, "res/images/thief.png", UP);
        this.consuming = false;
    }



    /**
     * Runs the algorithm for updating the Thief each tick depending on where it lands in the simulation.
     * @param world Contains all the actors in the simulation and operations related to updating them.
     */
    @Override
    public void updateStatus(World world) {

        // Check if Thief is to move forward, is on a Mitosis Pool, Fence or Sign. Update accordingly.
        this.checkCommonMoverBehaviour(world);

        // Get actors that are on the same tile.
        ArrayList<Actor> actorsInTile = getActorsInTile(world);


        // Set consuming to true if the Thief lands on a Pad.
        Actor actor;
        actor = instanceInList(actorsInTile, new Pad());
        if (actor != null) {
            this.consuming = true;
        }


        // Change direction of Thief by 270 degrees clockwise if it lands on a tile with a Gatherer that has moved.
        actor = instanceInList(actorsInTile, new Gatherer());
        if (actor != null) {
            this.setDirection((this.getDirection() + 4 - 1) % 4);
        }


        // Check if the Thief lands on a Hoard.
        actor = instanceInList(actorsInTile, new Hoard());
        if (actor != null) {
            Hoard hoard = (Hoard) actor;

            // If the Thief is set for consuming, then take a fruit.
            if (consuming) {
                this.consuming = false;
                if (!this.isCarrying()) {
                    if (hoard.getFruit() > 0) {
                        this.setCarrying(true);
                        hoard.setFruit(hoard.getFruit() - 1);
                    } else {
                        this.setDirection((this.getDirection() + 1) % 4);
                    }
                }

            // Put any fruit it holds onto a Hoard.
            } else if (this.isCarrying()) {
                this.setCarrying(false);
                hoard.setFruit(hoard.getFruit() + 1);
                this.setDirection((this.getDirection() + 1) % 4);
            }
        }


        // Check if the Thief is on a Stockpile.
        actor = instanceInList(actorsInTile, new Stockpile());
        if (actor != null) {

            // If possible take a fruit from a Stockpile.
            Stockpile stockpile = (Stockpile) actor;
            if (!this.isCarrying()) {
                if (stockpile.getFruit() > 0) {
                    this.setCarrying(true);
                    this.consuming = false;
                    stockpile.setFruit(stockpile.getFruit() - 1);
                    this.setDirection((this.getDirection() + 1) % 4);
                }
            } else {
                this.setDirection((this.getDirection() + 1) % 4);
            }
        }
    }



    // Method for running Thief specific logic for landing on a Mitosis Pool. Create two new thieves.
    @Override
    protected void onPool(World world) {

        // Create two new thieves.
        Thief thief1, thief2;
        thief1 = new Thief(this.getCoordinate().getX(), this.getCoordinate().getY(),
                (4 + this.getDirection() - 1) % 4);
        thief2 = new Thief(this.getCoordinate().getX(), this.getCoordinate().getY(),
                (4 + this.getDirection() + 1) % 4);

        // Move the thieves.
        thief1.moveForward();
        thief2.moveForward();

        // Add the new thieves to the simulation.
        world.getNewMovers().add(thief1);
        world.getNewMovers().add(thief2);
    }
}