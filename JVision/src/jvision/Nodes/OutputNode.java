package jvision.Nodes;

/**
 *
 * @author Michael
 * @see Node
 * @see InputNode
 * 
 * This Node is used for all output operations.
 * All output will be converted into a string and then sent to the appropriate output stream.
 */
public class OutputNode extends Node {

    public OutputNode(String filePath, String buffer, boolean console, String name, Node previousNode, Node nextNode, Node childNode, Node parentNode, int xPos, int yPos, int xSize, int ySize, int xAreaSize, int yAreaSize, String comment) {
        super(name, previousNode, nextNode, childNode, parentNode, xPos, yPos, xSize, ySize, xAreaSize, yAreaSize, comment);
        this.filePath = filePath;
        this.buffer = buffer;
        this.console = console;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getBuffer() {
        return buffer;
    }

    public void setBuffer(String buffer) {
        this.buffer = buffer;
    }

    public boolean isConsole() {
        return console;
    }

    public void setConsole(boolean console) {
        this.console = console;
    }
    
    @Override
    public Node execute() {
        super.execute();
        if (console) System.out.print(buffer); //if it needs to be put into the console then just output it there
        else {
            not finished //making sure there is an error here so I don't forget.  Also planning on seeing if I can create a output stream so that outputting to the console and to a file or some other type don't require different streams
        }
        return super.getNextNode();
    }
    
    private String filePath; //path to the output file if exists
    private String buffer; //the output string
    private boolean console; //if the output is given to the console
    
}
