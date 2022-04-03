package gitlet;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
    public static File objects = join(GITLET_DIR, "objects"); // store commit lists and file blobs
    public static final File commits = join(objects, "commits"); // store commits
    public static ListOfCommits<Commit> listOfCommits = new ListOfCommits<>();
    public static StageIndex stage = new StageIndex(new ArrayList<FileBlob>(),new ArrayList<FileBlob>());
    public static File head = join(GITLET_DIR, "head"); // store a path referring to the current branch and commit

    /* implement gitlet init */
    public static void init() {
        if (GITLET_DIR.exists()) {
            throw new IllegalArgumentException("has been initialized.");
        }
        GITLET_DIR.mkdir();
        // create an initial commit
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        objects.mkdir();
        try {
            commits.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Commit initCommit = new Commit("init commit", null);
        listOfCommits.addLast(initCommit);
    }

    /* implement gitlet add */
    public static void commit(File file, String message){
        // check whether the file has already in the StageIndex instance


//        listOfCommits.addLast(commit);
    }

    /* Implement gitlet add (only one file at time) */
    public static void add(File file) {
        // check if it is the first time to use gitlet add
        if(!index.exists()) {
            createFile(index);
        }
        writeObject(index, stage);
        // TODO: check if the file content is the same as the one stored in the commit on the HEAD branch
        // To simplify, I extract the latest commit file to compare
        Commit latestCommit = listOfCommits.getLastCommit();
        byte[] fileContent = null;
        FileBlob committedFile = null;
        if (latestCommit != null) {
            committedFile = latestCommit.getFileBlob();
            // read the content of the file to byte[]
            fileContent = Utils.readContents(file);
            if (Arrays.equals(fileContent, committedFile.getFileContent())) {
                System.out.println("Nothing to commit");
            }
        }
          // add fileBlob to StageIndex instance and write fileBlob to index file
            FileBlob fileBlobForAddition = new FileBlob(file);
            stage.addFileBlob(fileBlobForAddition);
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
