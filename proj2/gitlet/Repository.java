package gitlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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

    public static final File CWD = new File(System.getProperty("user.dir"));
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    public static File index = join(GITLET_DIR, "index");  // store fileForAddition
    public static File objects = join(GITLET_DIR, "objects"); // store commits

    /* implement gitlet init */
    public static void init() {
        if (GITLET_DIR.exists()) {
            throw new IllegalArgumentException("has been initialized.");
        }
        GITLET_DIR.mkdir();
        // create an initial commit
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        HashMap<String, String> branchMap = new HashMap<>();
        branchMap.put("main", sha1("main"));
        HashMap<String, String> headMap = new HashMap<>();
        headMap.put("HEAD", sha1("HEAD"));
        Commit initCommit = new Commit(branchMap, headMap);
        //TODO: need to create file and folder to store branch and HEAD
        /** .gitlet -- refs -- heads -- main (store sha1)
         *                  -- remotes -- origin -- main (store sha1, the same as heads/main)
         *                             -- skeleton
         *          -- HEAD -- "ref: refs/heads/main"
         * */
        storeCommit(initCommit);
    }

    /* Implement gitlet add (only one file at time) */
    public static void add (File file) {
        if(!index.exists()) {
            createFile(index);
            // Adds a copy of the file as it currently exists to the staging area
            FileForAddition fileForAddition = new FileForAddition(file);
            writeObject(index, fileForAddition);
            // To test whether the fileForAddition object has been written into index
            FileForAddition readedFile = readObject(index, FileForAddition.class);
            System.out.println(readedFile.getFile().getName());
        } else {
            /* Compare the added file with the staged file */
            FileForAddition readedFile = readObject(index, FileForAddition.class);
            if (readedFile.getFile().equals(file)) {
                try {
                    throw new IOException("The file has already been staged.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* to implement commit operation */
//    public static void commit(String message) {
//        Commit commit = new Commit(
//                message,
//                parentCommitId,
//                String fileName,
//                String blob,
//                String branch,
//        boolean isHead
//                )
//    }



    /* store the object into a file */
    private static void storeCommit(Commit commit) {
        // step 1: extract the first two chars as the folder under .gitlet/objects
        String commitId = commit.getCommitId();
        String commit_folderName = commitId.substring(0,2);
        // step 2: use the rest of chars as the file name to store serialized initCommit
        String commit_fileName = commitId.substring(2);
        // step 3: create corresponding folders and files
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
