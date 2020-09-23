/* Implementation for Project 2 for SWEN20003 Object Oriented Software Development
 * (Semester 2) by Justin Aaron Kelley (997351).
 * Class to represent a gatherer.
 */

import java.util.ArrayList;

public class Gatherer extends Mover {
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

    @Override
    public void updateStatus(World world) {

        // Get actors that are on the same tile.
        ArrayList<Actor> actorsInTile = getActorsInTile(world);

        // Check if Gatherer on a Mitosis Pool. Delete it and create two new ones if so.
        if (this.onPoolOrSign(world, actorsInTile)) {
            return;
        }

        // Check if the Gatherer is on a tree.
        Actor actor;
        actor = instanceInList(actorsInTile, new Tree());
        if (actor != null && !this.isCarrying()) {
            Tree tree = (Tree) actor;
            if (tree.getFruit() > 0 || tree.isGolden()) {
                if (!tree.isGolden()) {
                    tree.setFruit(tree.getFruit() - 1);
                }
                this.setCarrying(true);
                this.setDirection((this.getDirection() + 2) % 4);
            }
        }

        actor = instanceInList(actorsInTile, new Stockpile());
        if (actor == null) {
            actor = instanceInList(actorsInTile, new Hoard());
        }
        if (actor != null && this.isCarrying()) {
            this.setCarrying(false);
            Storage storage = (Storage) actor;
            storage.setFruit(storage.getFruit() + 1);
            this.setDirection((this.getDirection() + 2) % 4);
        }

        // If Gatherer is on a fence. Set it to inactive and move to previous position.
        actor = instanceInList(actorsInTile, new Fence());
        if (actor != null) {
            this.onFence();
        }

        // Move Gatherer forward if active.
        if (this.isActive()) {
            moveForward();
        }
    }

    @Override
    protected ArrayList<Mover> onPool() {
        ArrayList<Mover> movers = new ArrayList<Mover>();

        Gatherer gatherer1, gatherer2;
        gatherer1 = new Gatherer(this.getCoordinate().getX(), this.getCoordinate().getY(),
                (4 + this.getDirection() - 1) % 4);
        gatherer2 = new Gatherer(this.getCoordinate().getX(), this.getCoordinate().getY(),
                (4 + this.getDirection() + 1) % 4);
        gatherer1.move();
        gatherer2.move();

        movers.add(gatherer1);
        movers.add(gatherer2);
        return movers;
    }
}
