package gitlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import gitlet.ListOfCommits.CommitNode;

import static gitlet.Utils.*;

/**
 * Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 * @author TODO
 */
public class Repository {
    /**
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    public static final File CWD = new File(System.getProperty("user.dir"));
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    public static final File index = join(GITLET_DIR, "index");  // store fileForAddition
    public static File objects = join(GITLET_DIR, "objects"); // store commit lists and file blobs
    public static final File commits = join(objects, "commits"); // store commits
    public static File head = join(GITLET_DIR, "head"); // store a path referring to the current branch and commit
    public static ListOfCommits listOfCommits = new ListOfCommits();
    public static StageIndex stage = new StageIndex(new ArrayList<FileBlob>(), new ArrayList<FileBlob>());

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
        writeObject(commits, listOfCommits);
    }

    /* Implement gitlet add (only one file at time) */
    public static void add(File file) {
        // check if it is the first time to use gitlet add
        if (!index.exists()) {
            createFile(index);
            writeObject(index, stage);
        }
        /** Check if the file content is the same as the one stored in the commit on the HEAD branch
         * To simplify, the latest commit file is obtained for comparison
         */
         // Step 1: extract data from commits file by reading the listOfCommits from commits file first
        ListOfCommits currListOfCommits = readObject(commits, ListOfCommits.class);
        StageIndex currStage = readObject(index, StageIndex.class);
        ListOfCommits.CommitNode<Commit> latestCommitNode = currListOfCommits.getLast();
        // Step 2: read the content of the file to byte[] for later comparison with the committed file
        byte[] fileContent = readContents(file);
        List<FileBlob> committedFileList = latestCommitNode.getItem().getFileBlobList();
        if (committedFileList != null) {
            // check whether the file to be staged has been committed
            for (FileBlob fileBlob : committedFileList) {
                String fileName = file.getName();
                String fileBlobName =fileBlob.getFileName();
                if (Arrays.equals(fileContent, fileBlob.getFileContent()) && fileName.equals(fileBlobName)) {
                    // once there is a file that is exactly the same as the file to be added in terms of both file content and file name
                    System.out.println("nothing to add");
                    System.exit(0);
                }
            }
        }

        // if the file has been updated in the current working directory but not been committed yet, throw a message.
        ArrayList<String> stagedFileNames =
                currStage.getToAdd().stream()
                        .map(n -> n.getFileName())
                        .collect(Collectors.toCollection(ArrayList::new));

        if (stagedFileNames.contains(file.getName())) {
            System.out.println("This file has not been committed");
            System.exit(0);
        }

        FileBlob fileForAddition = new FileBlob(file);
        // first read the current stage instance from index file
        currStage.addFileBlob(fileForAddition);
        System.out.println("The file has been staged!");
        // update the index file with the updated StageIndex instance
        writeObject(index, currStage);
    }

    /* to implement commit operation */
    public static void commit(String message) {
        // make a new commit based on the staged files (fileblobs to be specific)
        // step 1: read stage instance from index file
        StageIndex currStage = readObject(index, StageIndex.class);
        List<FileBlob> filesForAddition = currStage.getToAdd();
        Commit newCommit = new Commit(message, filesForAddition);
        ListOfCommits currListOfCommits = readObject(commits, ListOfCommits.class);
        currListOfCommits.addLast(newCommit);
        writeObject(commits, currListOfCommits);
        System.out.println(currListOfCommits.size);
        // after commit, the file in the index will be removed
        currStage.emptyToAdd();
        // update index file
        writeObject(index, currStage);
    }

    public static void createFile(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* print commit log */
    public static void printLog() {
        ListOfCommits currListOfCommit = readObject(commits, ListOfCommits.class);
        CommitNode<Commit> currCommitNode = currListOfCommit.getLast();
        Commit commit;
        while (currCommitNode != null && currCommitNode.getItem() != null) {
            commit = (Commit) currCommitNode.getItem();
            System.out.println(commit.toString());
            currCommitNode = currCommitNode.getMainNode();
        }
    }
}
