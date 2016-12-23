package jvision.Nodes;

import jvision.Exceptions.NodeDoesNotExistException;
import jvision.Exceptions.NullNodeException;
import jvision.NodeList;

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
        if (!argument.isExecuted()) return argument;
        Node current = super.getParentNode();
        while (!(current instanceof MethodNode)) current = current.getParentNode();
        ((MethodNode)current).setReturned(argument);
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
