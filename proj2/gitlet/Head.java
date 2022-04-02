package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;

import static gitlet.Repository.GITLET_DIR;
import static gitlet.Utils.join;
import static gitlet.Utils.writeContents;

public class Head implements Serializable {
    private static File HEAD_FILE = join(GITLET_DIR, "HEAD");

    public Head(String branchName) {
        String contentInHead = "refs: refs/heads/" + branchName;
        writeContents(HEAD_FILE, contentInHead);
    }
}
