package gitlet;



import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

import static gitlet.Repository.*;
import static gitlet.Utils.*;

/** Represents a gitlet commit object.
 *
 *  @Jing
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
    private final Date date;
    private String message;
    private List<FileBlob> listOfFileBlobs;

    /* for normal commit */
    public Commit(String message, List<FileBlob> listOfFileBlobs) {
        // generate current timestamp
        this.date = new Date(System.currentTimeMillis());
        this.message = message;
        // generate commitId SHA-1 using message and timestamp
        this.listOfFileBlobs = listOfFileBlobs;
        this.commitId = sha1(this.message);
    }

    public List<FileBlob> getFileBlobList() {
        return listOfFileBlobs;
    }

    public String getCommitId() {
        return this.commitId;
    }

    /* used to print git log */
    private String printTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss, z, dd MMMM yyyy");
        return formatter.format(date);
    }

    // print out commit
    @Override
    public String toString(){
        return "Commit " + commitId + "\n"
                + "Date: " + date + "\n"
                + message + "\n"
                + "\n"
                + "===";
    }
}
