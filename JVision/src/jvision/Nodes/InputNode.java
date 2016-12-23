package jvision.Nodes;

import java.util.Scanner;

public class InputNode extends Node {

    public InputNode(Scanner in, String filePath, boolean console, String name, Node previousNode, Node nextNode, Node childNode, Node parentNode, int xPos, int yPos, int xSize, int ySize, int xAreaSize, int yAreaSize, String comment) {
        super(name, previousNode, nextNode, childNode, parentNode, xPos, yPos, xSize, ySize, xAreaSize, yAreaSize, comment);
        this.in = in;
        this.filePath = filePath;
        this.console = console;
    }

    public Scanner getIn() {
        return in;
    }

    public void setIn(Scanner in) {
        this.in = in;
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

    public boolean isConsole() {
        return console;
    }

    public void setConsole(boolean console) {
        this.console = console;
    }
    
    @Override
    public Node execute() {
        super.execute();
        buffer = in.nextLine();
        return super.getNextNode();
    }
    
    private Scanner in; //the input scanner if already set
    private String filePath; //path to the input file if exists
    private String buffer; //the current input string
    private boolean console; //if the input is given by the console
    
}
