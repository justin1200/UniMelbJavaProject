import java.util.ArrayList;

/**
 * Implementation for Project 2 for SWEN20003 Object Oriented Software Development (Semester 2).
 * This class handles the functionality for a Gatherer including the logic for updating it every tick. A Gatherer will
 * collect fruit from trees and stores it at hoards or stockpiles.
 * @author by Justin Aaron Kelley (997351).
 * */
public class Gatherer extends Mover {

    /**
     * Constructor that allows for position setting.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public Gatherer(int x, int y) {
        super(x, y, "res/images/gatherer.png", LEFT, "Gatherer");
    }


    /**
     * Constructor that allows for position and direction setting.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @param direction The direction the Gatherer moves in.
     */
    public Gatherer(int x, int y, int direction) {
        super(x, y, "res/images/gatherer.png", direction, "Gatherer");
        this.setDirection(direction);
    }



    /**
     * Runs the algorithm for updating the Gatherer each tick depending on where it lands in the simulation.
     * @param world Contains all the actors in the simulation and operations related to updating them.
     */
    // Method for running Algorithm 2 to update the state of a Gatherer every tick.
    @Override
    public void updateStatus(World world) {

        // Check if Gatherer is to move forward, is on a Mitosis Pool, Fence or Sign. Update accordingly.
        this.checkCommonMoverBehaviour(world);

        // Get actors that are on the same tile.
        ArrayList<Actor> actorsInTile = getActorsInTile(world);


        // Check if the Gatherer is on a Stockpile or Hoard.
        Actor actor;
        actor = instanceInList(actorsInTile, "Stockpile");
        if (actor == null) {
            actor = instanceInList(actorsInTile, "Hoard");
        }

        // Place any held fruit on the Stockpile or Hoard.
        if (actor != null) {
            if (this.isCarrying()) {
                this.setCarrying(false);
                Storage storage = (Storage) actor;
                storage.setFruit(storage.getFruit() + 1);
            }
            this.setDirection((this.getDirection() + 2) % 4);
        }
    }


    // Method for running Gatherer specific logic for landing on a Mitosis Pool. Create two new gatherers.
    @Override
    protected void onPool(World world) {

        // Create two new gatherers.
        Gatherer gatherer1, gatherer2;
        gatherer1 = new Gatherer(this.getCoordinate().getX(), this.getCoordinate().getY(),
                (4 + this.getDirection() - 1) % 4);
        gatherer2 = new Gatherer(this.getCoordinate().getX(), this.getCoordinate().getY(),
                (4 + this.getDirection() + 1) % 4);

        // Move the gatherers.
        gatherer1.moveForward();
        gatherer2.moveForward();

        // Add the new gatherers to the simulation.
        world.getNewMovers().add(gatherer1);
        world.getNewMovers().add(gatherer2);
    }
}
