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
