package jvision.Nodes;

import jvision.Exceptions.NodeDoesNotExistException;
import jvision.Exceptions.NullNodeException;
import jvision.NodeList;


public class MethodNode extends Node {

    public MethodNode(String privacy, String returnType, NodeList<ExceptionNode> exceptions, NodeList<VariableNode> arguments, String name, Node previousNode, Node nextNode, Node childNode, Node parentNode, int xPos, int yPos, int xSize, int ySize, int xAreaSize, int yAreaSize, String comment) {
        super(name, previousNode, nextNode, childNode, parentNode, xPos, yPos, xSize, ySize, xAreaSize, yAreaSize, comment);
        this.privacy = privacy;
        this.returnType = returnType;
        returned = null;
        this.exceptions = exceptions;
        this.arguments = arguments;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        if (privacy.equals("private") || privacy.equals("package") || privacy.equals("protected") || privacy.equals("public")) this.privacy = privacy;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public Object getReturned() {
        return returned;
    }

    public void setReturned(Object returned) {
        this.returned = returned;
    }
    
    @Override
    public Node execute() {
        return super.getChildNode();
    }
    
    @Override
    public void setVars(NodeList<VariableNode> old, NodeList<VariableNode> newer) throws NullNodeException, NodeDoesNotExistException {
        super.setVars(old, newer);
        super.setVarList(old, newer, exceptions);
        super.setVarList(old, newer, arguments);
    }
    
    private String privacy; //privacy of the method
    private String returnType; //return type of the method
    private Object returned; //returned result of the method
    protected final NodeList<ExceptionNode> exceptions; //thrown exceptions
    protected final NodeList<VariableNode> arguments; //arguments for the method
    
}
