/**
 * Implementation for Project 2 for SWEN20003 Object Oriented Software Development (Semester 2)
 * @author by Justin Aaron Kelley (997351).
 * This is the main driver class to run the ShadowLife simulation.*/
import java.util.ArrayList;

public class Thief extends Mover {

    // If Thief will consume a fruit or not.
    private boolean consuming;


    // Constructors for a Thief.
    public Thief(int x, int y) {
        super(x, y, "res/images/thief.png", UP);
        consuming = false;
    }

    public Thief(int x, int y, int direction) {
        super(x, y, "res/images/thief.png", UP);
        consuming = false;
        this.setDirection(direction);
    }

    public Thief() {
        super(0, 0, "res/images/thief.png", UP);
        this.consuming = false;
    }

    // Method to run Algorithm 4 to update the state of a Thief every tick.
    @Override
    public void updateStatus(World world) {

        // Check if Thief is to move forward, is on a Mitosis Pool, Fence or Sign. Update accordingly.
        this.checkCommonMoverBehaviour(world);

        // Get actors that are on the same tile.
        ArrayList<Actor> actorsInTile = getActorsInTile(world);

        // If Thief on a Mitosis Pool delete it.
        if (world.getNewMovers().size() != 0) {
            this.setMarkedForDelete(true);
        }


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
            if (!this.isCarrying() && stockpile.getFruit() > 0) {
                this.setCarrying(true);
                this.consuming = false;
                stockpile.setFruit(stockpile.getFruit() - 1);
                this.setDirection((this.getDirection() + 1) % 4);
            } else {
                this.setDirection((this.getDirection() + 1) % 4);
            }
        }
    }


    // Method for running logic for landing on a Mitosis Pool. Create two new thieves and delete this one.
    @Override
    protected void onPool(World world) {

        // Stores any newly created thieves.
        ArrayList<Mover> movers = new ArrayList<Mover>();

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