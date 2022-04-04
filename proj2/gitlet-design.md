# Gitlet Design Document

**Name**:

## Classes and Data Structures

### Class 1 - ListOfCommit

A linked commit list to store commits

#### Fields

CommitNode

#### Basic element of CommitNode

- Commit 
- CommitNode (mainNode) pointing to the parent commitNode if on the main branch
- CommitNode (stepNode) pointing to the parent commiteNode if on the other branch

#### Functions

1. addLast: add commitNode from the last commitNode

### Class 2 - Commit

A Commit class to store each commit information

#### Fields

1. String commitID: sha1 code
2. Data date
3. String message: commit message
4. FileBlob fileBlob: an instance contains file information (see FileBlob for details)

#### Functions

1. getCommitID: return commitID
2. toString: used for printing
    

### Class 3 - FileBolb

Used to store information of the file and its corresponding blob

#### Fields

1. File file: the file for commit
2. String fileName
3. byte[] fileContent: obtained by readContent
4. String blob: reference to this file
5. File blobFile: a file that store file information in the directory objects/

## Algorithms

### gitlet init

When `gitlet init` is called, 

- objects directory is made, 
- a new commit instance as "initial commit" is made,
- the new commit is added to the listOfCommit object, and the listOfCommit is written to the file in objects/commits


### gitlet add

when `gitlet add <fileName>` is called,




### Algorithm of commit object

Commit object is the item in the ListOfCommit (linked commit list but with a binary tree node).
When new Commit(), the input commit message, the date time and commit ID will be added to this instance. 
In addition, FileBlob will be generated to store the committed file information including file itself (path), file name, file content (byte[]); 
also, a blob, a reference generated via sha1, will also be generated; in the last, a file named by the blob will be created and the file content will be written to it to store data.

Note that the file in the commit instance should have been stored in StageIndex instance first, indicating this file has already been added.




## Persistence

- **The structure of stored data**

```java
/**
.gitlet -- objects -- commits (file to store the linked commit lists)
        |          |
        |          -- blobs_dir01 -- blob234 (file to store committed file)
        |          |              |
        |          -- blobs_dir02 -- blob7de
        |          |              |
        |          -- .....       -- ...
        index (a file to store a list of file blobs)
        |
        heads (a file store the path to the current branch and commit)
 */
```

**Self-reflection**

Instance itself does not store data. If instance is not written to a file, every time to call the instance and run main method, a new instance will be created.
To make instance persistent, we need to write this instance to a file, that is, to ask file to store what in this instance.
Every time when we want to update the instance, first we need to read the current stored instance, then do something, and write updated instance to the file.

Following above algorithm, when add file to staging area (StageIndex), we also need to read the StageIndex instance from the index file first, and then to see whether we need to add or not by reading ListOfCommit instance from commits file.
If the StageIndex is updated, then the updated instance will be written into the index file. Similarly, when we want to get the last commit from the ListOfCommit instance, we need to read from the commits file first. 
And if a new commit is added using `addLast` to the ListOfCommit instance, then write the instance to the commits file.
                    
