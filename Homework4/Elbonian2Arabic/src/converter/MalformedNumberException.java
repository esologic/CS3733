package src.converter;

/**
 * Exception that is thrown when a string that should represent an Elbonian
 * number is malformed.
 * 
 * @version 2/8/2017
 */
@SuppressWarnings("serial")
public class MalformedNumberException extends Exception {

    /**
     * Constructor with a descriptive message for the Exception.
     * 
     * @param message The descriptive message
     */
    public MalformedNumberException(String message) {
        super(message);
    }
}
