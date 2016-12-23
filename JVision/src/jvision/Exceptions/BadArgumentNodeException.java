package jvision.Exceptions;

/**
 *
 * @author Michael
 * @see Exception
 * 
 * This exception is generally used whenever a Node used as an argument of some method is of the wrong subclass of Node for that method.
 */
public class BadArgumentNodeException extends Exception {

    public BadArgumentNodeException() {
    }

    public BadArgumentNodeException(String message) {
        super(message);
    }
    
}
