# cs61b-sp21-s9165

This Repo contains all the lab exercises and projects if the course **CS61, 2021 Spring**. 
The skeleton code can be found [here](https://github.com/Berkeley-CS61B/skeleton-sp21)

### Lab exercises

- Lab 1: IntelliJ, Java, git

- Lab 2: JUnit Tests and Debugging
  
    - Practice with *List* data structure and nested helper method

- Lab 3: Timing Tests and Randomized Comparison Tests

    - Practice with *ArrayList*, *LinkedList*, *Doubly-linked List*
    - Get insight to their time complexity by building timing testing method

- Lab 4: Git and Debugging

- Lab 5: Project 1 Peer Code Review

- Lab 6: Canine Capers

    - Serialize Java objects to a file and read them back later (also called Persistence).
    - Work with files and directories in Java.

- Lab 7: BSTMap

- Lab 8: HashMap

### Projects

- Project 0: [2048](https://github.com/Berkeley-CS61B/skeleton-sp21/tree/master/proj0/game2048)
    
  - Complete the given skeleton file with code, and make the program (the 2048 Game) to work

- Project 1: [Data Structures](https://github.com/Berkeley-CS61B/skeleton-sp21/tree/master/proj1/deque)

    - Build my own `LinkedListDeque` Class following *Deque* API 

- Project 2: [Gitlet](https://github.com/Berkeley-CS61B/skeleton-sp21/tree/master/proj2)
  
  The project specification can be found [here](https://sp21.datastructur.es/materials/proj/proj2/proj2#example-test)

  The project aims to build an implementation very similar to github, but much simplier (that's why it is called gitlet!).
The goal is to achieve the basic function like `git init`, `git add`, `git commit`, `git checkout`, `git log`, `git merge`, etc.
Due to time limitation and lack of TA while self-study, I only implemented the following functions for gitlet.

  - `gitlet init`: creates a hidden folder named .gitlet which tracks all the work in the current working directory.

  - `gitlet add <fileName>`: stages a file to the staging area

  - `gitlet commit <message>`: adds the staged file to a commit

  - `gitlet checkout -- <fileName>`: takes the version of the file as it exists in the head commit and puts it in the working directory

  - `gitlet log`: print out commit history

