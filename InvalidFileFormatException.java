import java.io.IOException;

/**
 * InvalidFileFormatException is a new exception made in case a file is in the wrong format
 * 
 * @author Zoe Lawler
 * 
 * @param InvalidFileFormatException takes in a String message and then passes the message through super
 */

public class InvalidFileFormatException extends IOException {
    public InvalidFileFormatException() {
    }

    public InvalidFileFormatException(String message) {
        super(message);
    }
}
