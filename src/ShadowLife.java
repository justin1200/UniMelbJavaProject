/**
 * Implementation for Project 2 for SWEN20003 Object Oriented Software Development (Semester 2)
 * @author by Justin Aaron Kelley (997351).
 * This is the main driver class to run the ShadowLife simulation.*/

import bagel.AbstractGame;
import bagel.Input;

public class ShadowLife extends AbstractGame {

    // Dimensions of simulation window.
    private static final int HEIGHT = 768, WIDTH = 1024;

    // tickRate is time between ticks, maxTicks is number of ticks before timeout, tickerCount is running
    // number of ticks and time tracks current time.
    private final int tickRate, maxTicks;
    private int tickerCount;
    private double time;

    // Background of simulation.
    private final Background background;

    // Reads, stores and processes the Actors for simulation.
    private final World world;



    /**
     * Constructor to generate a simulation for ShadowLife.
     * @param args This stores the commandline input for tick rate (int),
     *             maximum number of ticks (int) and file to read (String).
     */
    public ShadowLife(String[] args) {
        // Set window.
        super(WIDTH, HEIGHT);

        // Get information from command line and world file.
        this.tickRate = Integer.parseInt(args[0]);
        this.maxTicks = Integer.parseInt(args[1]);
        this.background = new Background();
        world = new World();
        world.readFile(args[2]);

        // Set time and ticker trackers.
        tickerCount = 0;
        time = 0;
    }



    // Updates the simulation over time.
    @Override
    protected final void update(Input input) {
        // Add background and actors to graphical display.
        background.add();
        world.addActors();

        // Update actors on first tick.
        if (tickerCount == 0) {
            world.updateActors();
            time = System.currentTimeMillis();
            tickerCount++;
        // Update actors every tick.
        } else if (System.currentTimeMillis() - time > tickRate) {
            world.updateActors();
            tickerCount++;
            time = System.currentTimeMillis();
        }

        // Check for timeout.
        if (tickerCount > maxTicks) {
            System.out.println("Timed out");
            System.exit(-1);
        }

        // Check if simulation is finished.
        world.checkStatus(tickerCount);
    }



    // Check for valid commandline input.
    private static boolean isValidCommandline(String[] args) {

        // Checker for validity.
        boolean valid = true;

        // Check for enough inputs.
        if (args.length < 3) {
            valid = false;
        }

        // Check that input for ticker rate and maximum number of ticks are non-negative integers.
        try {
            if (Integer.parseInt(args[0]) < 0) {
                valid = false;
            } else if (Integer.parseInt(args[1]) < 0) {
                valid = false;
            }
            // Print out error for invalid input.
        } catch (NumberFormatException e) {
            exitWithCommandlineError();
        }

        // Print out error for invalid input.
        if (!valid) {
            exitWithCommandlineError();
            return false;
        } else {
            return true;
        }
    }


    // Exits program and reports an error with commandline input by printing what input is excepted.
    private static void exitWithCommandlineError() {
        System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
        System.exit(-1);
    }



    /**
     * This is the main method that is called to run the simulation.
     * @param args This stores the commandline input for tick rate (int),
     *             maximum number of ticks (int) and file to read (String).
     */
    public static void main(String[] args) {
        // Run simulation only if commandline input is valid.
        if (isValidCommandline(args)) {
            new ShadowLife(args).run();
        }
    }
}
