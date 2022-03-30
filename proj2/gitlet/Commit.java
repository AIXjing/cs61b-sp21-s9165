package gitlet;

// TODO: any imports you need here

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private String commitId;
    private String timestamp; //TODO:
    private String message;
    private String parentCommitId;
    private String fileName;
    private String blob;
    private String branch;
    private boolean isHead;

    public Commit(
            String message,
            String parentCommitId,
            String fileName,
            String blob,
            String branch,
            boolean isHead) {
        // generate current timestamp
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss, z, dd MMMM yyyy");
        this.timestamp = formatter.format(new Date());
        this.message = message;
        // generate commitId SHA-1 using message and timestamp
        this.commitId = Utils.sha1(this.message,timestamp);
        this.parentCommitId = parentCommitId;
        this.fileName = fileName;
        this.blob = blob;
        this.branch = branch;
        this.isHead = isHead;
    }

    public String getCommitId() {
        return this.commitId;
    }

    // print out commit
    @Override
    public String toString(){
        return "Commit " + commitId + "\n"
                + timestamp + "\n"
                + "     " + message + "\n";
    }
}
