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

    @Override
    public void updateStatus(World world) {
        ArrayList<Actor> actorsInTile = getActorsInTile(world);

        if (instanceInList(actorsInTile, new MitosisPool())) {
            this.onPool();
            return;
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
