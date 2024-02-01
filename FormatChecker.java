import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * FormatChecker is a java program that reads in one or more files through a
 * command-line argument. It checks if the command-line argument is valid and
 * then checks to see if each file is in the correct format, if
 * it's not, it catches or throws an exception depending on what the format
 * error is.
 * 
 * @author Zoe Lawler
 * 
 */

public class FormatChecker {

    /**
     * @param args Takes in command-line arguments and checks various files if they
     *             are in the correct format
     */
    public static void main(String[] args) {

        String usageMsg = "Usage message should be in this format: \n $ java FormatChecker file1 [file2 ... fileN] \n where FormatChecker is the name of this class, file1 is the name of the file to be tested and [file2...fileN] are the additional files to be tested";

        if (args.length < 1) { // Since command-line arguments can be any amount we don't want it to be less
                               // than one
            System.out.println(usageMsg);
            return;
        }

        for (int i = 0; i < args.length; i++) { // iterate through however many files are in the command-line
            String filename = args[i];

            try {
                Scanner scnr = new Scanner(new File(filename));

                String[] split = scnr.nextLine().split("\\s+"); // this splits the first two ints and it doesn't matter
                                                                // how many spaces are in between them
                int row = Integer.parseInt(split[0]);
                int col = Integer.parseInt(split[1]);

                int linecount = 0;

                if (split.length != 2) { // makes sure the first line is two integers
                    throw new InvalidFileFormatException(
                            "First line must be two integer values corresponding to the rows and columns of the matrix");
                }

                if (row > 0 && col > 0) { // make sure that the matrix is greater than 0 otherwise it's invalid

                    while (scnr.hasNextLine()) {

                        String firstLine = scnr.nextLine();

                        String[] rowVal = firstLine.split("\\s+"); // splits this array at each space

                        if (firstLine.isEmpty()) { // checks for empty lines and ignores them if there are any
                            continue;
                        }

                        for (int s = 0; s < rowVal.length; s++) { // parses everything in the matrix to a double
                            Double.parseDouble(rowVal[s]);

                        }

                        if (rowVal.length != col) { // if length of each row value doesn't equal col number then throw
                                                    // exception
                            throw new InvalidFileFormatException(
                                    "Number of rows and columns of the matrix do not match the specified size of matrix found on the first line");
                        }

                        linecount++;
                    }

                    if (linecount != row) { // makes sure linecount equals row number, if not throws exception
                        throw new InvalidFileFormatException(
                                "Number of rows and columns of the matrix do not match the specified size of matrix found on the first line");
                    }
                }

                System.out.println(filename + "\n" + "VALID");

            } catch (FileNotFoundException e) { // if a file doesn't exist or can't be found this exception is caught
                                                // here
                System.out.println(filename + "\n" + e.toString() + "\n" + "INVALID");
            } catch (NumberFormatException e) { // if a number format error is found it catches the exception here
                System.out.println(filename + "\n" + e.toString() + "\n" + "INVALID");
            } catch (InvalidFileFormatException e) { // this is a custom exception made to catch invalid file formats
                System.out.println(filename + "\n" + e.toString() + "\n" + "INVALID");
            }
            System.out.println();
        }
    }
}