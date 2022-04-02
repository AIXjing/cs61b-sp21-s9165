package gitlet;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

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
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    public static final File CWD = new File(System.getProperty("user.dir"));
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    public static final File index = join(GITLET_DIR, "index");  // store fileForAddition
    public static File objects = join(GITLET_DIR, "objects"); // store commits

    /* implement gitlet init */
    public static void init() {
        if (GITLET_DIR.exists()) {
            throw new IllegalArgumentException("has been initialized.");
        }
        GITLET_DIR.mkdir();
        // create an initial commit
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        objects.mkdir();
        Commit initCommit = new Commit("main");  // main branch is created by default in gitlet init
        /** .gitlet -- refs -- heads -- main (store sha1)
         *                  -- remotes -- origin -- main (store sha1, the same as heads/main)
         *                             -- skeleton
         *          -- HEAD -- "ref: refs/heads/main"
         * */
    }

    /* Implement gitlet add (only one file at time) */
    public static void add (File file) {
        // check if it is the first time to use gitlet add
        if(!index.exists()) {
            createFile(index);
            FileForAddition fileForAddition = new FileForAddition(file);
            fileForAddition.initAdd();
//            FileForAddition readedFile = readObject(index, FileForAddition.class);
//            readedFile.initAdd();
        } else {
            // Compare the added file with the staged file
            /* Read the file content from index and compare with new added file */
            FileForAddition readedFile = readObject(index, FileForAddition.class);
//            String content1 = null;
//            try {
//                content1 = Files.toString(readedFile.getFile(), Charsets.UTF_8);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            System.out.println("staged file content: " + content1);
//
//            String content2 = null;
//            try {
//                content2 = Files.toString(file, Charsets.UTF_8);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            System.out.println("added file content: " + content2);

            readedFile.add(file);
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



    public static void createFile(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
