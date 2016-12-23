package jvision.Exceptions;

/**
 *
 * @author Michael
 * @see Exception
 * 
 * This exception is generally used whenever a Node that is required cannot be found in a list.
 */
public class NodeDoesNotExistException extends Exception {

    public NodeDoesNotExistException() {
    }

    public NodeDoesNotExistException(String message) {
        super(message);
    }
    
}
