package capers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static capers.Dog.DOG_FOLDER;
import static capers.Utils.*;

/** A repository for Capers 
 * @author TODO
 * The structure of a Capers Repository is as follows:
 *
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 *    - dogs/ -- folder containing all of the persistent data for dogs
 *    - story -- file containing the current story
 *
 * TODO: change the above structure if you do something different.
 */
public class CapersRepository {
    /** Current Working Directory. */
    static final File CWD = new File(System.getProperty("user.dir"));

    /** Main metadata folder. */
    static final File CAPERS_FOLDER = Utils.join(CWD,".capers");
    // TODO Hint: look at the `join` function in Utils

    /**
     * Does required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure (you do not have to follow):
     *
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    - story -- file containing the current story
     */
    public static void setupPersistence() {
        // TODO
        /* check if CAPERS_FOLDER exists
        * If not, create a new actual folder for it */
        if (!CAPERS_FOLDER.exists()) {
            try {
                CAPERS_FOLDER.mkdir();
            } catch (SecurityException  e) {
                e.printStackTrace();
            }
        }
        /* check if DOG_FOLDER exists
         * If not, create a new actual folder for it */
        if (!DOG_FOLDER.exists()) {
            try {
                DOG_FOLDER.mkdir();
            } catch (SecurityException  e) {
                e.printStackTrace();
            }
        }

        /* Below is for exercise */
        /*
        File outFile = Utils.join(DOG_FOLDER, "dog1.txt");
        try {
            ObjectOutputStream out =
                    new ObjectOutputStream(new FileOutputStream(outFile));
            out.writeObject(new Dog("Hugo", "333", 2));
            out.close();
        } catch (IOException excp) {
            excp.printStackTrace();
        }

        File outFile1 = Utils.join(DOG_FOLDER, "story.txt");
        String currContent = Utils.readContentsAsString(outFile1);
        Utils.writeContents(outFile1, currContent, "\n", "Hello");
        */

    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) {
        // TODO
        File storyFile = Utils.join(CAPERS_FOLDER, "story");
        if(!storyFile.exists()) {
            try {
                storyFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String currText = Utils.readContentsAsString(storyFile);
        Utils.writeContents(storyFile,currText, text, "\n");
        String newText = Utils.readContentsAsString(storyFile);
        System.out.println(newText);
    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {
        // TODO
        Dog dog = new Dog(name, breed, age);
        dog.saveDog();
        System.out.println(dog.toString());
    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {
        // TODO
        Dog dog = Dog.fromFile(name);
        dog.haveBirthday();
        dog.saveDog();

    }
}
