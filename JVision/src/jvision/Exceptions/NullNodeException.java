package jvision.Exceptions;

/**
 *
 * @author Michael
 * @see Exception
 * 
 * This exception is generally used whenever a Node that should have an instance is instead Null.
 */
public class NullNodeException extends Exception {

    public NullNodeException() {
    }

    public NullNodeException(String message) {
        super(message);
    }
    
}
