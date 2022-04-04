package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StageIndex implements Serializable {
    private List<FileBlob> toAdd;
    private List<FileBlob> toRemove;

    public StageIndex(List<FileBlob> toAdd, List<FileBlob> toRemove) {
        this.toAdd = toAdd;
        this.toRemove = toRemove;
    }

    public List<FileBlob> getToAdd() {
        return toAdd;
    }

    public List<FileBlob> getToRemove() {
        return toRemove;
    }

    public void addFileBlob(FileBlob fb) {
        toAdd.add(fb);
    }

    public void removeFileBlob(FileBlob fb) {
        toRemove.add(fb);
    }

    // to remove file blobs
    public void emptyToAdd(){
        this.toAdd = null;
    }
}
