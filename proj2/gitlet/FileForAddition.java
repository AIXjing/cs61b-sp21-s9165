package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static gitlet.Repository.objects;
import static gitlet.Utils.serialize;
import static gitlet.Utils.sha1;

public class FileForAddition implements Serializable {
    // File file;
    // String blob;
    Map<File, String> fileMap = new HashMap<>();

    public FileForAddition(File file) {
        String blob = sha1((Object) serialize(file));
        fileMap.put(file, blob);
    }

    public void add (File file) {
        // if the current working version of the file is identical to the version in the current commit,
        // do not stage it to be added, and remove it from the staging area
        // Step 1: compare whether the added File

        // if it is already there (as can happen when a file is changed, added, and then changed back to it’s original version).
        if(isCommitted()) {
            return;
        }

        // Staging an already-staged file
        // overwrites the previous entry in the staging area with the new contents
    }

    /* get file in the Map */
    public File getFile() {
        for(File file : fileMap.keySet()) {
            return file;
        }
        return null;
    }

    /* get blob in the Map */
    public String getBlob() {
        return fileMap.get(getFile());
    }

    public boolean isCommitted() {
        String fileBlogInCWD = sha1(getFile());
        String fileBlogInCWD_firstTwoChars = fileBlogInCWD.substring(0,2);
        /* search whether fileBlog exists in '.gitlet/objects' by folderName */
        //TODO: BinaryTree search?
        String[] folderNames = objects.list();
        for (String folderName : folderNames) {
            if (fileBlogInCWD_firstTwoChars.equals(folderName)) {
                return true;
            }
        }
        return false;
    }

}
