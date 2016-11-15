package jvision;

public class Instruction {
    private final String returnValue; //the actual returned value of the instruction
    private final Node currentNode; //the current node that is being executed
    
    public Instruction(String r, Node c) {
        returnValue = r;
        currentNode = c;
    }
    
    public String getReturnValue() {
        return returnValue;
    }
    
    public Node getCurrentNode() {
        return currentNode;
    }
}
