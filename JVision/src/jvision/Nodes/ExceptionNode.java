package jvision.Nodes;

/**
 *
 * @author Michael
 * @see Node
 * 
 * This Node is used whenever an exception is needed.
 */
public class ExceptionNode extends Node {

    public ExceptionNode(String exceptionName, String message, String name, Node previousNode, Node nextNode, Node childNode, Node parentNode, int xPos, int yPos, int xSize, int ySize, int xAreaSize, int yAreaSize, String comment) {
        super(name, previousNode, nextNode, childNode, parentNode, xPos, yPos, xSize, ySize, xAreaSize, yAreaSize, comment);
        this.exceptionName = exceptionName;
        this.message = message;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    @Override
    public Node execute() {
        super.execute();
        System.out.println(exceptionName + ": " + message);
        return null;
    }
    
    private String exceptionName; //name of the exception
    private String message; //message to be displayed when thrown if any
    
}
