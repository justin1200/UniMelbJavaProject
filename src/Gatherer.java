/* Implementation for Project 2 for SWEN20003 Object Oriented Software Development
 * (Semester 2) by Justin Aaron Kelley (997351).
 * Class to represent a gatherer.
 */

import java.util.ArrayList;

public class Gatherer extends Mover {

    // Constructors for Gatherer.
    public Gatherer(int x, int y) {
        super(x, y, "res/images/gatherer.png", LEFT);
    }

    public Gatherer(int x, int y, int direction) {
        super(x, y, "res/images/gatherer.png", direction);
        this.setDirection(direction);
    }

    public Gatherer() {
        super(0, 0, "res/images/gatherer.png", LEFT);
    }


    // Method for running Algorithm 2 to update the state of a Gatherer every tick.
    @Override
    public ArrayList<Mover> updateStatus(World world) {

        // Check if Gatherer is to move forward, is on a Mitosis Pool, Fence or Sign. Update accordingly.
        ArrayList<Mover> newMovers = this.checkCommonMoverBehaviour(world);

        // Get actors that are on the same tile.
        ArrayList<Actor> actorsInTile = getActorsInTile(world);

        // If Gatherer on a Mitosis Pool delete it.
        if (newMovers != null) {
            this.setMarkForDelete(true);
            return newMovers;
        }


        // Check if the Gatherer is on a Stockpile or Hoard.
        Actor actor;
        actor = instanceInList(actorsInTile, new Stockpile());
        if (actor == null) {
            actor = instanceInList(actorsInTile, new Hoard());
        }

        // Place any held fruit on the Stockpile or Hoard.
        if (actor != null && this.isCarrying()) {
            this.setCarrying(false);
            Storage storage = (Storage) actor;
            storage.setFruit(storage.getFruit() + 1);
            this.setDirection((this.getDirection() + 2) % 4);
        }

        return null;
    }


    // Method for running logic for landing on a Mitosis Pool. Create two new gatherers and delete this one.
    @Override
    protected ArrayList<Mover> onPool() {

        // Stores any newly created gatherers.
        ArrayList<Mover> movers = new ArrayList<Mover>();

        // Create two new gatherers.
        Gatherer gatherer1, gatherer2;
        gatherer1 = new Gatherer(this.getCoordinate().getX(), this.getCoordinate().getY(),
                (4 + this.getDirection() - 1) % 4);
        gatherer2 = new Gatherer(this.getCoordinate().getX(), this.getCoordinate().getY(),
                (4 + this.getDirection() + 1) % 4);

        // Move the gatherers.
        gatherer1.moveForward();
        gatherer2.moveForward();

        // Add the new gatherers to a list to be added to simulation after each other Actor has been updated.
        movers.add(gatherer1);
        movers.add(gatherer2);
        return movers;
    }
}