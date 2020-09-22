/* Implementation for Project 2 for SWEN20003 Object Oriented Software Development
 * (Semester 2) by Justin Aaron Kelley (997351).
 * This is the main driver class to run ShadowLife. */

import bagel.AbstractGame;
import bagel.Input;

public class ShadowLife extends AbstractGame {

    private static final int HEIGHT = 768, WIDTH = 1024;
    private final int tickRate, maxTicks;
    private int tickerCount;
    private double time;
    private final Background background;
    private World world;

    public ShadowLife(String filename, String args[]) {
        super(WIDTH, HEIGHT);
        this.tickRate = Integer.parseInt(args[0]);
        this.maxTicks = Integer.parseInt(args[1]);
        this.background = new Background();
        world = new World();
        world.readFile(args[2]);
        tickerCount = 0;
        time = 0;
        world.checkStatus(tickerCount);
    }

    @Override
    protected void update(Input input) {
        background.Add();
        world.addActors();
        if (tickerCount == 0) {
            time = System.currentTimeMillis();
            tickerCount++;
        } else if (System.currentTimeMillis() - time > tickRate) {
            tickerCount++;
        }

        world.checkStatus(tickerCount);
        if (tickerCount > maxTicks) {
            System.out.println("Timed out");
            System.exit(-1);
        }
    }

    public static boolean isValidCommandline(String[] args) {
        boolean valid = true;

        if (args.length < 3) {
            valid = false;
        }

        try {
            if (Integer.parseInt(args[0]) < 0) {
                valid = false;
            } else if (Integer.parseInt(args[1]) < 0) {
                valid = false;
            }
        } catch (Exception e) {
            System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
            System.exit(-1);
        }

        if (!valid) {
            System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
            System.exit(-1);
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        if (isValidCommandline(args)) {
            new ShadowLife("res/worlds/harvest.csv", args).run();
        }
    }
}
