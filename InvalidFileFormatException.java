import java.io.IOException;

/**
 * InvalidFileFormatException is a new exception made in case a file is in the
 * wrong format and it is implemented in FormatChecker.java.
 * 
 * @author Zoe Lawler
 * 
 */

public class InvalidFileFormatException extends IOException {
    public InvalidFileFormatException() {
    }

    /**
     * @param message Takes in a string message and passes it through super
     */
    public InvalidFileFormatException(String message) {
        super(message);
    }
}
