package gitlet;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Charsets;
import org.apache.commons.io.FileUtils;

import static gitlet.Repository.*;
import static gitlet.Utils.*;

public class FileForAddition implements Serializable {
     private final File file;
     private final String blob;
    private final Map<File, String> fileMap = new HashMap<>();

    public FileForAddition(File file) {
        // create a sha1 for the staged file
        this.file = fil
        String blob = sha1((Object) serialize(file));
        fileMap.put(file, blob);
        storeBlob();  // store blob in objects
    }

    public void initAdd() {
        writeObject(index, this);
        System.out.println(getFile().getName());
        /* print out file content */
        String content = null;
        try {
            content = com.google.common.io.Files.toString(getFile(), Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("The initially staged file content: " + content);
        System.out.println("Blob: " + getBlob());
        // if the current working version of the file is identical to the version in the current commit,
        // do not stage it to be added, and remove it from the staging area
        // Step 1: compare whether the added File

        // if it is already there (as can happen when a file is changed, added, and then changed back to it’s original version).
//        if(isCommitted()) {
//            return;
//        }

        // Staging an already-staged file
        // overwrites the previous entry in the staging area with the new contents
    }

    public void add(File file) {
        /* check the added file whether it has the same content as previous staged file by searching the blob */
        FileForAddition newAddedFileForStage = new FileForAddition(file);
        //TODO: why readStagedFile does not return a string.
        File stagedFile = new File(objects + "/" + getBlob().substring(0,2) + "/" + getBlob().substring(2));
        String stagedFileContent = readContentsAsString(stagedFile);
        String addedFileContent = readContentsAsString(file);
        System.out.println("staged file content: " + stagedFileContent);
        System.out.println("added file content: " + addedFileContent);
        if (!stagedFileContent.equals(addedFileContent)) {
            System.out.println("The file has been updated! Let's stage new file.");
        }
        else {
            System.out.println("No change is made.");
        }
//        if (getBlob() != newAddedFileForStage.getBlob()) {
//            restrictedDelete()
//        }
//            writeObject(index, new FileForAddition(file));
//            FileForAddition file1 = readObject(index, FileForAddition.class);
//            System.out.println("overwrite " + file1.getFile().toString());
//        }
//        System.out.println("The file has already been added.");
        // if the name is not the same , add the new file after the staged file？ REQUIRED？
        // TODO: else
    }

    /* get file in the Map */
    public File getFile() {
        for (File file : fileMap.keySet()) {
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
        String fileBlogInCWD_firstTwoChars = fileBlogInCWD.substring(0, 2);
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

    private void storeBlob(){
        String blob_folderName = getBlob().substring(0,2);
        String blob_fileName = getBlob().substring(2);
        File blob_dir = new File(objects, blob_folderName);
        if(!blob_dir.exists()) {
            blob_dir.mkdir();
        }
        File blobFile = new File(blob_dir, blob_fileName);
        if (!blobFile.exists()) {
            createFile(blobFile);
        }
        writeObject(blobFile, this.getFile());
        String content = readContentsAsString(blobFile);
        System.out.println("The saved file content in Blob: " + content);
    }

}
