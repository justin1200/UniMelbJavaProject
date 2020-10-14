/**
 * Implementation for Project 2 for SWEN20003 Object Oriented Software Development (Semester 2)
 * @author by Justin Aaron Kelley (997351).
 * This class stores the actors in the simulation and handles the operations related to loading them from a file and
 * updating or checking their states each tick.
 * */

import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;

public class World {

    // Stores actors loaded from world (test.csv) file.
    private final ArrayList<Actor> actors;

    // Stores any newly created movers,
    private ArrayList<Mover> newMovers;



    /**
     * Constructor for creating a World object to store actors in for a simulation.
     */
    public World() {
        this.actors = new ArrayList<Actor>();
    }



    /**
     * Gets the list of actors in the simulation.
     * @return The list of actors in the simulation.
     */
    public ArrayList<Actor> getActors() {
        return actors;
    }


    /**
     * Gets the list of movers to be added to the simulation.
     * @return The list of movers to be added.
     */
    public ArrayList<Mover> getNewMovers() {
        return newMovers;
    }


    /**
     * Updates the list that stores any movers to be created to a new one.
     * @param newMovers The new list of movers to be made.
     */
    public void setNewMovers(ArrayList<Mover> newMovers) {
        this.newMovers = newMovers;
    }



    /**
     * Takes a csv file that has a list of actors to be added to the simulation.
     * @param filename The csv file storing the list of actors to be created.
     */
    public final void readFile(String filename) {

        // Line number.
        int lineNum = 0;

        // Read in file.
        try (BufferedReader br =
                     new BufferedReader(new FileReader(filename))) {

            // Read file line by line.
            String text;
            while ((text = br.readLine()) != null) {
                lineNum++;
                // Split cells in csv file and check for right length.
                String[] cells = text.split(",");
                if (cells.length != 3) {
                    printError(filename, lineNum);
                } else {
                    // Create actor based on file information on each line.
                    createActor(filename, lineNum, cells);
                }
            }

            // Print error message if error in loading file, file not found or a line contains an error.
        } catch (IOException e1) {
            System.out.println("error: file \"" + filename +  "\" not found");
            System.exit(-1);
        } catch (Exception e2) {
            printError(filename, lineNum);
        }
    }


    // Method to print error for file line.
    private void printError(String filename, int lineNum) {
        System.out.println("error: in file \"" + filename + "\" at line " + lineNum);
        System.exit(-1);
    }



    /**
     * Adds the actors read from the file onto the display for the simulation.
     */
    public final void addActors() {
        for (Actor actor: this.actors) {
            if (!actor.isMarkedForDelete()) {
                actor.add();
            }
        }
    }



    // Creates actor for the correct class.
    private void createActor(String filename, int lineNum, String[] cells) {
        // Get coordinates.
        int x = Integer.parseInt(cells[1]);
        int y = Integer.parseInt(cells[2]);

        // Add correct class to list. Otherwise print error message.
        switch (cells[0]) {
            case "Tree":
                actors.add(new Tree(x, y, 3));
                break;
            case "GoldenTree":
                actors.add(new Tree(x, y, Storage.INFINITE));
                break;
            case "Fence":
                actors.add(new StationaryActor(x, y, "res/images/fence.png", cells[0]));
                break;
            case "Pad":
                actors.add(new StationaryActor(x, y, "res/images/pad.png", cells[0]));
                break;
            case "SignDown":
                actors.add(new Sign(x, y, Mover.DOWN));
                break;
            case "SignUp":
                actors.add(new Sign(x, y, Mover.UP));
                break;
            case "SignRight":
                actors.add(new Sign(x, y, Mover.RIGHT));
                break;
            case "SignLeft":
                actors.add(new Sign(x, y, Mover.LEFT));
                break;
            case "Hoard":
            case "Stockpile":
                actors.add(new Store(x, y, cells[0]));
                break;
            case "Thief":
                actors.add(new Thief(x, y));
                break;
            case "Gatherer":
                actors.add(new Gatherer(x, y));
                break;
            case "Pool":
                actors.add(new StationaryActor(x, y, "res/images/pool.png", cells[0]));
                break;
            default:
                printError(filename, lineNum);
                break;
        }
    }



    // Updates the status for a specific subtype of Mover.
    private void updateSpecificMoverType(String type, int length) {

        Actor actor;

        // Update status for the Mover.
        for (int i = 0; i < length; i++) {
            this.newMovers = new ArrayList<Mover>();
            actor = this.actors.get(i);

            // Update status of the Mover if it is of this specific subtype.
            if (actor.getType().equals(type) && !actor.isMarkedForDelete()) {
                ((Mover) actor).updateStatus(this);

                // Add newly created movers to the simulation.
                if (this.newMovers.size() != 0) {
                    this.actors.addAll(this.newMovers);
                }
            }
        }
    }


    /**
     * For each tick in the simulation, this method will update the status for any actors that need to be updated.
     */
    public void updateActors() {

        // Get number of current actors.
        int length = this.actors.size();

        // Update simulation for Gatherers and then Thieves.
        this.updateSpecificMoverType("Gatherer", length);
        this.updateSpecificMoverType("Thief", length);

        // Delete any actors marked for it.
        for (int i = 0; i < actors.size(); i++) {
            if (actors.get(i).isMarkedForDelete()) {
                actors.remove(actors.get(i));
            }
        }
    }


    /**
     * Checks if all actors are inactive.
     * @param numTicks Stores the number of ticks that have passed, which is printed in case all actors
     *                 are inactive.
     */
    public void checkStatus(int numTicks) {

        Mover mover;
        boolean finished = true;

        // Checks if any actors are still active.
        for (Actor actor: actors) {
            if (actor instanceof Mover) {
                mover = (Mover) actor;
                if (mover.isActive()) {
                    finished = false;
                    break;
                }
            }
        }

        // Print summary if all actors are inactive.
        if (finished) {
            printSummary(numTicks);
        }
    }



    // Prints a summary of the simulation once finished.
    private void printSummary(int numTicks) {
        Storage storage;

        // Print out number of ticks that have passed.
        System.out.println(numTicks + " ticks");

        // For each Stockpile or Hoard, print out the number of fruits in each one.
        for (Actor actor: actors) {
            if (actor.getType().equals("Stockpile") || actor.getType().equals("Hoard")) {
                storage = (Storage) actor;
                System.out.println(storage.getFruit());
            }
        }

        // Exit program.
        System.exit(0);
    }
}
