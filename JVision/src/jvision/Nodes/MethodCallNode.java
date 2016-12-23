package jvision.Nodes;

import java.util.logging.Level;
import java.util.logging.Logger;
import jvision.Exceptions.BadArgumentNodeException;
import jvision.Exceptions.NodeDoesNotExistException;
import jvision.Exceptions.NullNodeException;
import jvision.NodeList;
import jvision.NodeListConverter;

public class MethodCallNode extends Node {

    public MethodCallNode(MethodNode method, NodeList<Node> arguments, String name, Node previousNode, Node nextNode, Node childNode, Node parentNode, int xPos, int yPos, int xSize, int ySize, int xAreaSize, int yAreaSize, String comment) {
        super(name, previousNode, nextNode, childNode, parentNode, xPos, yPos, xSize, ySize, xAreaSize, yAreaSize, comment);
        this.method = method;
        this.arguments = arguments;
    }

    public MethodNode getMethod() {
        return method;
    }

    public void setMethod(MethodNode method) {
        this.method = method;
    }
    
    @Override
    public Node execute() {
        super.execute();
        Node current = arguments.get(0);
        while (current != null) {
            if (!(current instanceof VariableNode)) {
                if (!current.isExecuted() && !(current instanceof ClassNode)) return current;
                else {
                    try {
                        current = super.getVariable(current);
                    } catch (BadArgumentNodeException | CloneNotSupportedException ex) {
                        Logger.getLogger(MethodCallNode.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        try {
            method.setVars(method.arguments, new NodeListConverter<VariableNode>().convert(arguments, VariableNode.class));
        } catch (NullNodeException | BadArgumentNodeException ex) {
            Logger.getLogger(MethodCallNode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return method;
    }
    
    @Override
    public void unExecute() {
        super.unExecute();
        method.unExecute();
        Node current = arguments.get(0);
        while (current != null) {
            current.unExecute();
            current = current.getNextNode();
        }
    }
    
    @Override
    public void setVars(NodeList<VariableNode> old, NodeList<VariableNode> newer) throws NullNodeException, NodeDoesNotExistException {
        super.setVars(old, newer);
        method.setVars(old, newer);
        super.setVarList(old, newer, arguments);
    }
    
    private MethodNode method; //method being called
    protected final NodeList<Node> arguments; //arguments for method call
    
}
