package gitlet;

// TODO: any imports you need here

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static gitlet.Repository.*;
import static gitlet.Utils.*;

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Commit implements Serializable {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */
    private final String commitId;
    private final Date date; //TODO:
    private String message;
    private final String parentCommitId;
    private Map<File, String> file; // Map<file, blob>
    private Branch branch;
    private Head head;

    /* for normal commit */
    public Commit(
            String message,
            String parentCommitId,
            Map<File, String> file,
            String branch,
            HashMap<String, String> headMap) {
        // generate current timestamp
        this.date = new Date(System.currentTimeMillis());
        this.message = message;
        // generate commitId SHA-1 using message and timestamp
        this.parentCommitId = parentCommitId;
        this.file = file; //TODO:
        this.branch = new Branch(branch);
        this.head = new Head(branch);
        this.commitId = Utils.sha1(this);
    }

    /* for initial commit */
    public Commit(String branchName) {
        String commitId1;
        // generate current timestamp
        this.date = new Date(System.currentTimeMillis());
        this.message = "initial commit";
        this.parentCommitId = null;
        this.file = null;
        this.branch = new Branch(branchName);
        this.head = new Head(branchName);
        this.commitId = Utils.sha1((Object) serialize(this));
        storeCommit();
    }

    public String getCommitId() {
        return this.commitId;
    }

    /* used to print git log */
    private String printTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss, z, dd MMMM yyyy");
        return formatter.format(date);
    }

    /* store commit into the object file */
    private void storeCommit() {
        // step 1: extract the first two chars as the folder under .gitlet/objects
        String commitId = getCommitId();
        String commit_folderName = commitId.substring(0,2);
        // step 2: use the rest of chars as the file name to store serialized initCommit
        String commit_fileName = commitId.substring(2);
        // step 3: check if folder exists
        File commit_dir = new File(objects, commit_folderName);
        if(!commit_dir.exists()) {
            // if the dir does not exist, then make a dir
            commit_dir.mkdir();
        }
        // create corresponding and files and write this commit to it
        File commitFile = new File(commit_dir, commit_fileName);
        if (!commitFile.exists()) {
            createFile(commitFile);
        }
        writeObject(commitFile, this);
        Commit commit = Utils.readObject(commitFile, Commit.class);
        System.out.println(commit.toString());
    }

    // print out commit
    @Override
    public String toString(){
        return "Commit " + commitId + "\n"
                + date + "\n"
                + "     " + message + "\n";
    }
}
