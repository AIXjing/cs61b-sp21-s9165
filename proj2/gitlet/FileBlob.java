package gitlet;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import static gitlet.Repository.objects;
import static gitlet.Utils.*;


public class FileBlob implements Serializable {
     private final File file;
     private String fileName;
     private final byte[] fileContent;
     private final String blob;
     private File fileForCommit;

    public String getFileName() {
        return fileName;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public String getBlob() {
        return blob;
    }

    public FileBlob(File file) {
        this.file = file;
        if (file != null) {
            this.fileName = file.getName();
            this.fileContent = readContents(file);
        } else {
            this.fileName = null;
            this.fileContent = null;
        }
        this.blob = generateBlob();
        this.fileForCommit = createFile();
    }

    // generate a blob based on file content
    private String generateBlob() {
        if (file != null) {
            byte[] fileContent = Utils.readContents(file);
            return sha1((Object) fileContent);
        }
        return null;
    }

    // copy file to the blob file
    private File createFile() {
        if (blob == null) return null;
        // generate a blob file
        String blob_folderName = this.blob.substring(0,2);
        String blob_fileName = this.blob.substring(2);
        File blob_dir = new File(objects, blob_folderName);
        if(!blob_dir.exists()) {
            blob_dir.mkdir();
        }
        File blobFile = new File(blob_dir, blob_fileName);
        if (!blobFile.exists()) {
            try {
                blobFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeContents(blobFile, (Object) this.fileContent);
        return blobFile;
    }
    //    private void storeBlob(){

//    }

}
