package gitlet;

// TODO: any imports you need here

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Commit implements Serializable {
    private static final String DEFAULT_BRANCH = "main";
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */
    private String commitId;
    private Date date; //TODO:
    private String message;
    private String parentCommitId;
    private Map<File, String> file; // Map<file, blob>
    private HashMap<String, String> branchMap;
    private HashMap<String, String> headMap;

    /* for normal commit */
    public Commit(
            String message,
            String parentCommitId,
            Map<File, String> file,
            HashMap<String, String> branchMap,
            HashMap<String, String> headMap) {
        // generate current timestamp
        this.date = new Date(System.currentTimeMillis());
        this.message = message;
        // generate commitId SHA-1 using message and timestamp
        this.commitId = Utils.sha1(this.message, date);
        this.parentCommitId = parentCommitId;
        this.file = file; //TODO:
        this.branchMap = branchMap;
        this.headMap = headMap;
    }

    /* for initial commit */
    public Commit(HashMap<String, String> branchMap,
                  HashMap<String, String> headMap) {
        // generate current timestamp
        this.date = new Date(System.currentTimeMillis());
        this.message = "initial commit";
        // generate commitId SHA-1 using message and timestamp
        this.commitId = Utils.sha1(this.message, date);
        this.parentCommitId = null;
        this.file = null;
        this.branchMap = branchMap;
        this.headMap = headMap;
    }

    public String getCommitId() {
        return this.commitId;
    }

    /* used to print git log */
    public String printTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss, z, dd MMMM yyyy");
        return formatter.format(date);
    }

    // print out commit
    @Override
    public String toString(){
        return "Commit " + commitId + "\n"
                + timestamp + "\n"
                + "     " + message + "\n";
    }
}
