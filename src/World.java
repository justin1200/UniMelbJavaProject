/* Implementation for Project 2 for SWEN20003 Object Oriented Software Development
 * (Semester 2) by Justin Aaron Kelley (997351). */

/* Class used to handle functionality for loading in actors from world file,
 * adding them to the image and updating their states.*/

import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class World {

    // Stores actors loaded from world (test.csv) file.
    private final ArrayList<Actor> actors;

    // Constructor for Class.
    public World() {
        this.actors = new ArrayList<Actor>();
    }

    // Getter for actor list.
    public ArrayList<Actor> getActors() {
        return actors;
    }



    // Function to read actor data from file, filename.
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
                try {
                    // Split cells in csv file and check for right length.
                    String[] cells = text.split(",");
                    if (cells.length != 3) {
                        printError(filename, lineNum);
                    } else {
                        // Create actor based on file information on each line.
                        createActor(filename, lineNum, cells);
                    }
                    // Print error message in case of error on a line.
                } catch (Exception e) {
                    e.printStackTrace();
                    printError(filename, lineNum);
                }
            }

            // Print error message if error in loading file or file not found.
        } catch (Exception e) {
            System.out.println("error: file " + filename +  " not found");
            System.exit(-1);
        }
    }


    // Method to print error for file line.
    private void printError(String filename, int lineNum) {
        System.out.println("error: in file " + filename + " at line " + lineNum);
        System.exit(-1);
    }


    // Add objects that were read from file into the 2D image.
    public final void addActors() {
        for (Actor actor: this.actors) {
            actor.add();
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
                actors.add(new Tree(x, y, 3, false));
                break;
            case "GoldenTree":
                actors.add(new Tree(x, y, Storage.INFINITE, true));
                break;
            case "Fence":
                actors.add(new Fence(x, y));
                break;
            case "Pad":
                actors.add(new Pad(x, y));
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
                actors.add(new Hoard(x, y));
                break;
            case "Stockpile":
                actors.add(new Stockpile(x, y));
                break;
            case "Thief":
                actors.add(new Thief(x, y));
                break;
            case "Gatherer":
                actors.add(new Gatherer(x, y));
                break;
            case "MitosisPool":
                actors.add(new MitosisPool(x, y));
                break;
            default:
                printError(filename, lineNum);
                break;
        }
    }



    // Updates the status of the actors for the simulation each tick.
    public final void updateActors() {

        // Tracks any newly created movers.
        ArrayList<Mover> newMovers = new ArrayList<Mover>(), newMoversTemp;

        // Update status for gatherers.
        for (Actor actor: this.actors) {
            if (actor instanceof Gatherer) {
                newMoversTemp = ((Mover) actor).updateStatus(this);

                // Add newly created movers to a list.
                if (newMoversTemp != null) {
                    newMovers.addAll(newMoversTemp);
                }
            }
        }

        // Update status for thieves.
        for (Actor actor: this.actors) {
            if (actor instanceof Thief) {
                newMoversTemp = ((Mover) actor).updateStatus(this);

                // Add newly created movers to a list.
                if (newMoversTemp != null) {
                    newMovers.addAll(newMoversTemp);
                }
            }
        }

        // Delete any actors marked for it.
        for (int i = 0; i < actors.size(); i++) {
            if (actors.get(i).isMarkForDelete()) {
                actors.remove(actors.get(i));
            }
        }

        // Add newly created movers to the simulation.
        if (newMovers.size() > 0) {
            this.actors.addAll(newMovers);
        }
    }


    // To check if all actors are inactive.
    public final void checkStatus(int numTicks) {

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
            if (actor instanceof Stockpile || actor instanceof Hoard) {
                storage = (Storage) actor;
                System.out.println(storage.getFruit());
            }
        }

        // Exit program.
        System.exit(0);
    }
}
