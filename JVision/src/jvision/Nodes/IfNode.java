package jvision.Nodes;

import java.util.logging.Level;
import java.util.logging.Logger;
import jvision.Exceptions.NodeDoesNotExistException;
import jvision.Exceptions.NullNodeException;
import jvision.NodeList;

public class IfNode extends Node {

    public IfNode(LogicNode condition, NodeList<IfNode> elseIfNodes, Node elseNode, String name, Node previousNode, Node nextNode, Node childNode, Node parentNode, int xPos, int yPos, int xSize, int ySize, int xAreaSize, int yAreaSize, String comment) {
        super(name, previousNode, nextNode, childNode, parentNode, xPos, yPos, xSize, ySize, xAreaSize, yAreaSize, comment);
        this.condition = condition;
        this.elseIfNodes = elseIfNodes;
        this.elseNode = elseNode;
    }

    public LogicNode getCondition() {
        return condition;
    }

    public void setCondition(LogicNode condition) {
        this.condition = condition;
    }

    public Node getElseNode() {
        return elseNode;
    }

    public void setElseNode(IfNode elseNode) {
        this.elseNode = elseNode;
    }
    
    public boolean isElseType() {
        return elseType;
    }
    
    public void setElseType(boolean elseType) {
        this.elseType = elseType;
    }
    
    @Override
    public Node execute() {
        super.execute();
        if (!condition.isExecuted()) return condition;
        else {
            if (condition.isReturned()) {
                if (super.getChildNode() != null) return super.getChildNode();
                else if (elseType) {
                    IfNode current = (IfNode)super.getParentNode();
                    while (current.isElseType()) current = (IfNode)super.getParentNode();
                    return current.getNextNode();
                }
                else return super.getNextNode();
            }
            else if (elseIfNodes.getSize() > 0) {
                return elseIfNodes.get(0);
            }
            else if (elseNode != null) return elseNode;
            else if (elseType) {
                if (super.getNextNode() != null) return super.getNextNode();
                else {
                    IfNode current = (IfNode)super.getParentNode();
                    if (current.getElseNode() != null) return current.getElseNode();
                    else {
                        while (current.isElseType()) current = (IfNode)super.getParentNode();
                        return current.getNextNode();
                    }
                }
            }
            return super.getNextNode();
        }
    }
    
    @Override
    public void unExecute() {
        super.unExecute();
        condition.unExecute();
        IfNode current = elseIfNodes.get(0);
        while (current != null) {
            current.unExecute();
            current = (IfNode)current.getNextNode();
        }
        if (elseNode != null) elseNode.unExecute();
    }
    
    @Override
    public void setVars(NodeList<VariableNode> old, NodeList<VariableNode> newer) throws NullNodeException, NodeDoesNotExistException {
        super.setVars(old, newer);
        condition.setVars(old, newer);
        super.setVarList(old, newer, elseIfNodes);
        elseNode = super.setVar(old, newer, elseNode);
    }
    
    private LogicNode condition; //condition for the if statement
    protected final NodeList<IfNode> elseIfNodes; //Nodes if there are any elseif
    private Node elseNode; //Node if there is an else
    private boolean elseType; //if this Node is an else/elseif to an if node
    
}
