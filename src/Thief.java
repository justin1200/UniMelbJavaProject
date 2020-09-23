/* Implementation for Project 2 for SWEN20003 Object Oriented Software Development
 * (Semester 2) by Justin Aaron Kelley (997351).
 * Class to represent a thief.
 */

import java.util.ArrayList;

public class Thief extends Mover {

    private boolean consuming;

    public Thief(int x, int y) {
        super(x, y, "res/images/thief.png", UP);
        consuming = false;
    }

    public Thief(int x, int y, int direction) {
        super(x, y, "res/images/thief.png", UP);
        consuming = false;
        this.setDirection(direction);
    }

    @Override
    public void updateStatus(World world) {
        ArrayList<Actor> actorsInTile = getActorsInTile(world);
        ArrayList<Mover> newThieves;
        Actor actor = instanceInList(actorsInTile, new MitosisPool());

        if (actor != null) {
            newThieves = this.onPool();
            world.getActors().add(newThieves.get(0));
            world.getActors().add(newThieves.get(1));
            world.getActors().remove(this);
            return;
        }

        actor = instanceInList(actorsInTile, new Sign());
        if (actor != null) {
            this.onSign((Sign) actor);
        }

        actor = instanceInList(actorsInTile, new Pad());
        if (actor != null) {
            this.consuming = true;
        }
        actor = instanceInList(actorsInTile, new Gatherer());
        if (actor != null) {
            this.setDirection((this.getDirection() + 4 - 1) % 4);
        }

        actor = instanceInList(actorsInTile, new Hoard());
        if (actor != null) {
            Hoard hoard = (Hoard) actor;
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
            } else if (this.isCarrying()) {
                this.setCarrying(false);
                hoard.setFruit(hoard.getFruit() + 1);
            }
        }

        actor = instanceInList(actorsInTile, new Stockpile());
        if (actor != null) {
            Stockpile stockpile = (Stockpile) actor;
            if (!this.isCarrying() && stockpile.getFruit() > 0) {
                this.setCarrying(true);
                this.consuming = false;
                stockpile.setFruit(stockpile.getFruit() - 1);
            }
            this.setDirection((this.getDirection() + 1) % 4);
        }

        actor = instanceInList(actorsInTile, new Fence());
        if (actor != null) {
            this.onFence();
        }

        if (this.isActive()) {
            moveForward();
        }
    }

    @Override
    protected ArrayList<Mover> onPool() {
        ArrayList<Mover> movers = new ArrayList<Mover>();

        Thief thief1, thief2;
        thief1 = new Thief(this.getCoordinate().getX(), this.getCoordinate().getY(),
                (4 + this.getDirection() - 1) % 4);
        thief2 = new Thief(this.getCoordinate().getX(), this.getCoordinate().getY(),
                (4 + this.getDirection() + 1) % 4);
        thief1.move();
        thief2.move();

        movers.add(thief1);
        movers.add(thief2);
        return movers;
    }
}
