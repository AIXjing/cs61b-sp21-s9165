package gitlet;

import java.io.File;
import java.io.IOException;
import java.util.TimeZone;

import static gitlet.Utils.*;

// TODO: any imports you need here

/**
 * Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 * @author TODO
 */
public class Repository{
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /**
     * The current working directory.
     */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /**
     * The .gitlet directory.
     */
    public static final File GITLET_DIR = join(CWD, ".gitlet");

    private static final String DEFAULT_BRANCH = sha1("main");

    /* implement gitlet init */
    public static void init() {
        if (GITLET_DIR.exists()) {
            throw new IllegalArgumentException("has been initialized.");
        }
        GITLET_DIR.mkdir();

        // create an initial commit
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        Commit initCommit = new Commit(
                "initial commit",
                null,
                null,
                null,
                DEFAULT_BRANCH,
                true
        );
        storeCommit(initCommit);
    }

    /* Implement gitlet add (only one file at time) */
    public void add (File fileForAddition) {
        File index = new File(GITLET_DIR, "index");
        if(!index.exists()) {
            createFile(index);
        }
        
    }


    /* store the object into a file */
    private static void storeCommit(Commit commit) {
        // step 1: extract the first two chars as the folder under .gitlet/objects
        String commitId = commit.getCommitId();
        String commit_folderName = commitId.substring(0,2);
        // step 2: use the rest of chars as the file name to store serialized initCommit
        String commit_fileName = commitId.substring(2);
        // step 3: create corresponding folders and files
        File objects = new File(GITLET_DIR, "objects");
        objects.mkdir();
        File folder_for_initCommit = new File(objects, commit_folderName);
        folder_for_initCommit.mkdir();
        File file_for_initCommit = new File(folder_for_initCommit, commit_fileName);
        createFile(file_for_initCommit);


        Utils.writeObject(file_for_initCommit, commit);
        Commit init = Utils.readObject(file_for_initCommit, Commit.class);
        System.out.println(init.toString());
    }

    private static void createFile(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
