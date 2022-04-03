package gitlet;

import java.io.File;
import java.util.TimeZone;

/**
 * Driver class for Gitlet, a subset of the Git version-control system.
 *
 * @author TODO
 */
public class Main {

    /**
     * Usage: java gitlet.Main ARGS, where ARGS contains
     * <COMMAND> <OPERAND1> <OPERAND2> ...
     * Runs one of the following commands:
     * init --
     *          create .gitlet directory in the current working directory. Additionally, create an initial commit
     * add [filename] --
     *          write the file into the index file which is a staging area.
     *          If the file has already been staged, do nothing.
     *          If the file has been commit, do not need to commit
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Please enter a command.");
        }
        String firstArg = args[0];
        switch (firstArg) {
            case "init":
                // TODO: handle the `init` command
                validateNumArgs("init", args, 1);
                Repository.init();
                break;
            case "add":
                // TODO: handle the `add [filename]` command
                validateNumArgs("add", args, 2);
                Repository.add(new File(args[1]));
                break;
            case "commit":
                // TODO: handle the `commit "message"` command
                validateNumArgs("commit", args, 3);
//                Repository.commit(args[1], args[2]);
                break;
            // TODO: FILL THE REST IN
        }
    }

    /**
     * Checks the number of arguments versus the expected number,
     * throws a RuntimeException if they do not match.
     *
     * @param cmd  Name of command you are validating
     * @param args Argument array from command line
     * @param n    Number of expected arguments
     * @source cs61b-21sp-lab6
     */
    public static void validateNumArgs(String cmd, String[] args, int n) {
        if (args.length != n) {
            throw new RuntimeException(
                    String.format("Invalid number of arguments for: %s.", cmd));
        }
    }
}
