package jvision.Nodes;

import jvision.Exceptions.NodeDoesNotExistException;
import jvision.Exceptions.NullNodeException;
import jvision.NodeList;

/**
 *
 * @author Michael
 * @see Node
 * @see MethodNode
 * 
 * This Node is used as the return statement of a method.
 * Whenever a method needs to return a value it will contain at least one instance of this Node within it.
 * When used this Node will return the result of its argument to the return value of the parent MethodNode.
 */
public class ReturnNode extends Node {

    public ReturnNode(Node argument, String name, Node previousNode, Node nextNode, Node childNode, Node parentNode, int xPos, int yPos, int xSize, int ySize, int xAreaSize, int yAreaSize, String comment) {
        super(name, previousNode, nextNode, childNode, parentNode, xPos, yPos, xSize, ySize, xAreaSize, yAreaSize, comment);
        this.argument = argument;
    }

    public Node getArgument() {
        return argument;
    }

    public void setArgument(Node argument) {
        this.argument = argument;
    }
    
    @Override
    public Node execute() {
        super.execute();
        if (!argument.isExecuted()) return argument; //ensure that the argument has been executed and is an actual value
        Node current = super.getParentNode();
        while (!(current instanceof MethodNode)) current = current.getParentNode(); //find the method that this Node belongs to
        ((MethodNode)current).setReturned(argument); //set the methods return value
        return current;
    }
    
    @Override
    public void unExecute() {
        super.unExecute();
        argument.unExecute();
    }
    
    @Override
    public void setVars(NodeList<VariableNode> old, NodeList<VariableNode> newer) throws NullNodeException, NodeDoesNotExistException {
        super.setVars(old, newer);
        super.setVar(old, newer, argument);
    }
    
    private Node argument;
    
}
