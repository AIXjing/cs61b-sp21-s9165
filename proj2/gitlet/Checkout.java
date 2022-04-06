package gitlet;

import java.io.File;
import java.util.List;

import static gitlet.Repository.commits;
import static gitlet.Repository.createFile;
import static gitlet.Utils.readObject;
import static gitlet.Utils.writeContents;
import static gitlet.ListOfCommits.CommitNode;

public class Checkout {

    /** Takes the version of the file as it exists in the head commit and puts it in the working directory,
     * overwriting the version of the file that’s already there if there is one.
     * The new version of the file is not staged. */
    public static void checkoutFile(String fileName) {
        // check where is the head commit
        ListOfCommits currListOfCommits = readObject(commits, ListOfCommits.class);
        ListOfCommits.CommitNode<Commit> lastCommitNode = currListOfCommits.getLast();
        while (lastCommitNode != null && !lastCommitNode.isHEAD()) {
            lastCommitNode = currListOfCommits.getLast().getMainNode();  // supposed there is only one branch, i.e., main
        }
        if (lastCommitNode != null) {
            Commit commitForCheckout = lastCommitNode.getItem();
            // get the file to be checked out
            List<FileBlob> listOfFileBlob = commitForCheckout.getFileBlobList();
            for (FileBlob fb : listOfFileBlob) {
                if (fb.getFileName().equals(fileName)) {
                    // write this file to the current working directory
                    File fileToBeCheckout = new File(fileName);
                    if(!fileToBeCheckout.exists()) {
                        createFile(fileToBeCheckout);
                    }
                    writeContents(fileToBeCheckout, (Object) fb.getFileContent());
                    System.out.println("checkout success!");
                    System.exit(0);
                }
            }
        }
    }

    public static void checkoutCommitId(String commitId) {
        CommitNode currCommitNode = readObject(commits, ListOfCommits.class).getLast();
        while (currCommitNode.getMainNode() != null) {
            Commit c = (Commit) currCommitNode.getItem();
            String cId = c.getCommitId();
            if (cId.equals(commitId)) {
                List<FileBlob> listOfFileBlob = c.getFileBlobList();
                for (FileBlob fb : listOfFileBlob) {
                    checkoutFile(fb.getFileName());
                }
                System.out.println("checkout commit succeeds!");
                System.exit(0);
            }
            currCommitNode = currCommitNode.getMainNode();
        }
        if (currCommitNode == null) {
            System.out.println("no such commitId was found");
        }
    }

    /** Takes all files in the commit at the head of the given branch, and puts them in the working directory,
     * overwriting the versions of the files that are already there if they exist.
     * Also, at the end of this command, the given branch will now be considered the current branch (HEAD).
     * Any files that are tracked in the current branch but are not present in the checked-out branch are deleted.
     * The staging area is cleared, unless the checked-out branch is the current branch (see Failure cases below).
     *  */
    public static void checkoutBranch(String branchName) {
        //TODO:
    }

}
