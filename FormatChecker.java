import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FormatChecker {

    public static void main(String[] args) {

        String usageMsg = "Usage message should be in this format: \n $ java FormatChecker file1 [file2 ... fileN] \n where FormatChecker is the name of this class, file1 is the name of the file to be tested and [file2...fileN] are the additional files to be tested";
        double[][] array;

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

                if (row > 0 && col > 0) { // make sure that the matrix is greater than 0 otherwise it's invalid
                    array = new double[row][col];

                    for (int k = 0; k < row; k++) {
                        if (scnr.hasNextLine()) {
                            String[] rowVal = scnr.nextLine().split(" ");

                            if (rowVal.length == col) {
                                for (int j = 0; j < col; j++) {
                                    array[k][j] = Double.parseDouble(rowVal[j]); // parse to double so because our array
                                                                                 // is a double array
                                }
                            }

                        }
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