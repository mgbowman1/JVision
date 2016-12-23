package jvision.Nodes;

import jvision.Exceptions.NodeDoesNotExistException;
import jvision.Exceptions.NullNodeException;
import jvision.NodeList;

/**
 *
 * @author Michael
 * @see Node
 * 
 * This Node is used to create classes.
 */
public class ClassNode extends Node {

    public ClassNode(String privacy, NodeList<ClassNode> extenders, MethodNode constructor, String name, Node previousNode, Node nextNode, Node childNode, Node parentNode, int xPos, int yPos, int xSize, int ySize, int xAreaSize, int yAreaSize, String comment) {
        super(name, previousNode, nextNode, childNode, parentNode, xPos, yPos, xSize, ySize, xAreaSize, yAreaSize, comment);
        this.privacy = privacy;
        this.extenders = extenders;
        this.constructor = constructor;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        if (privacy.equals("private") || privacy.equals("package") || privacy.equals("protected") || privacy.equals("public")) this.privacy = privacy;
    }
    
    public MethodNode getConstructor() {
        return constructor;
    }
    
    public void setConstructor(MethodNode constructor) {
        this.constructor = constructor;
    }
    
    @Override
    public Node execute() {
        super.execute();
        return constructor;
    }
    
    @Override
    public void unExecute() {
        ClassNode current = extenders.get(0);
        while (current != null) {
            current.unExecute();
            current = (ClassNode)current.getNextNode();
        }
        constructor.unExecute();
    }
    
    @Override
    public void setVars(NodeList<VariableNode> old, NodeList<VariableNode> newer) throws NullNodeException, NodeDoesNotExistException {
        super.setVars(old, newer);
        constructor.setVars(old, newer);
    }
    
    private String privacy; //privacy of the class
    protected final NodeList<ClassNode> extenders; //extended class and implemented interfaces
    private MethodNode constructor; //constructor method for the class
    
}
