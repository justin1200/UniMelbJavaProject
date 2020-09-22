/* Implementation for Project 2 for SWEN20003 Object Oriented Software Development
 * (Semester 2) by Justin Aaron Kelley (997351). */

/* Class used to handle functionality for loading in actors from world file,
 * adding them to the image and updating their functionality.*/

import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class World {
    // Stores actors loaded from world (test.csv) file.
    private ArrayList<Actor> actors;

    // Constructor for Class.
    public World() {
        this.actors = new ArrayList<Actor>();
    }

    // Getter for actor list.
    public ArrayList<Actor> getActors() {
        return actors;
    }

    // Function to read actor data from file, filename.
    public void readFile(String filename) {

        // Line number.
        int lineNum = 0;

        // Read in file.
        try (BufferedReader br =
                     new BufferedReader(new FileReader(filename))) {
            String text;
            while ((text = br.readLine()) != null) {
                lineNum++;
                try {
                    // Split cells in csv file and check for right length.
                    String[] cells = text.split(",");
                    if (cells.length != 3) {
                        printError(filename, lineNum);
                    } else {
                        createActor(filename, lineNum, cells);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    printError(filename, lineNum);
                }

            }
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
    public void addActors() {
        for (Actor actor: this.actors) {
            actor.Add();
        }
    }

    // Create actor for the correct class.
    private void createActor(String filename, int lineNum, String cells[]) {
        // Get coordinates.
        int x = Integer.parseInt(cells[1]);
        int y = Integer.parseInt(cells[2]);

        // Add correct class to list.
        if (cells[0].equals("Tree")) {
            actors.add(new Tree(x, y, 3, false));
        } else if (cells[0].equals("GoldenTree")) {
            actors.add(new Tree(x, y, Storage.INFINITE, true));
        } else if (cells[0].equals("Fence")) {
            actors.add(new Fence(x, y));
        } else if (cells[0].equals("Pad")) {
            actors.add(new Pad(x, y));
        } else if (cells[0].equals("SignDown")) {
            actors.add(new Sign(x, y, Mover.DOWN));
        } else if (cells[0].equals("SignUp")) {
            actors.add(new Sign(x, y, Mover.UP));
        } else if (cells[0].equals("SignRight")) {
            actors.add(new Sign(x, y, Mover.RIGHT));
        } else if (cells[0].equals("SignLeft")) {
            actors.add(new Sign(x, y, Mover.LEFT));
        } else if (cells[0].equals("Hoard")) {
            actors.add(new Hoard(x, y));
        } else if (cells[0].equals("Stockpile")) {
            actors.add(new Stockpile(x, y));
        } else if (cells[0].equals("Thief")) {
            actors.add(new Thief(x, y));
        } else if (cells[0].equals("Gatherer")) {
            actors.add(new Gatherer(x, y));
        } else if (cells[0].equals("MitosisPool")) {
            actors.add(new MitosisPool(x, y));
        } else {
            printError(filename, lineNum);
        }
    }

    // Update the status for the simulation each tick.
    public void updateActors() {
        for (Actor actor: this.actors) {
            if (actor instanceof Mover) {
                ((Mover) actor).updateStatus(this);
            }
        }
    }

    public void checkStatus(int numTicks) {
        Mover mover;
        boolean finished = true;
        for (Actor actor: actors) {
            if (actor instanceof Mover) {
                mover = (Mover) actor;
                if (mover.isActive()) {
                    finished = false;
                    break;
                }
            }
        }
        if (finished) {
            printSummary(numTicks);
        }
    }

    private void printSummary(int numTicks) {
        Storage storage;
        System.out.println(numTicks + "ticks");
        for (Actor actor: actors) {
            if (actor instanceof Stockpile || actor instanceof Hoard) {
                storage = (Storage) actor;
                System.out.println(storage.getFruit());
            }
        }
        System.exit(0);
    }
}
