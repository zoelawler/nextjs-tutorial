import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Takes in one or many files and checks to see if they are VALID or INVALId
 * based on certain conditions
 * 
 * @author Zoe Lawler
 * 
 * @param main takes String[] args as a parameter which passes in command-line
 *             arguments
 */

public class FormatChecker {

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
                        String[] rowVal = scnr.nextLine().split(" ");

                        if (rowVal.length != col) { //invalid1.dat fails here but i want it to go to numberformatexception
                            throw new InvalidFileFormatException(
                                    "boom Number of rows and columns of the matrix do not match the specified size of matrix found on the first line");
                        }

                        linecount++;
                    }

                    if (linecount != row) {
                        throw new InvalidFileFormatException(
                                "Number of rows and columns of the matrix do not match the specified size of matrix found on the first line");
                    }
                }

                System.out.println(filename + "\n" + "VALID");

            } catch (FileNotFoundException e) {
                System.out.println(filename + "\n" + e.toString() + "\n" + "INVALID");
            } catch (NumberFormatException e) {
                System.out.println(filename + "\n" + e.toString() + "\n" + "INVALID");
            } catch (Exception e) {
                System.out.println(filename + "\n" + e.toString() + "\n" + "INVALID");
            }

            System.out.println();
        }
    }
}