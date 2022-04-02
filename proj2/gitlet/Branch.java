package gitlet;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import static gitlet.Repository.GITLET_DIR;
import static gitlet.Repository.createFile;
import static gitlet.Utils.*;

public class Branch implements Serializable {
    private static final File refs = join(GITLET_DIR, "refs");
    private static final File heads = join(refs, "heads");

    HashMap<String, String> branch = new HashMap<>();

    public Branch(String branchName) {
        branch.put(branchName, sha1(branchName));
        storeBranch();
    }

    public String getBranchName(){
        for (String name : branch.keySet()) {
            return name;
        }
        return null;
    }

    private String getBranchsha1(){
        for (String name : branch.keySet()) {
            return branch.get(name);
        }
        return null;
    }

    // create a refs/heads/main file to store branch
    private void storeBranch(){
        if (!refs.exists()) {
            refs.mkdir();
        }
        if (!heads.exists()) {
            heads.mkdir();
        }
        File branchFile = new File(heads, getBranchName());
        // check if branch file exists
        if (!branchFile.exists()) {
            createFile(branchFile);
        }
        writeContents(branchFile, getBranchsha1());
        String branch_sha = readContentsAsString(branchFile);
        System.out.println("branch sha1: " + branch_sha);
    }


}
