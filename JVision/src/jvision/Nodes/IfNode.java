package jvision.Nodes;

import jvision.Exceptions.NodeDoesNotExistException;
import jvision.Exceptions.NullNodeException;
import jvision.NodeList;

/**
 *
 * @author Michael
 * @see Node
 * 
 * This is the conditional Node.
 * This is used for all if statements.
 * It evaluates a given LogicalNode and executes the necessary code a single time if necessary.
 * It also contains all else if instructions as well as else instructions and executes the appropriately.
 */
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
        if (!condition.isExecuted()) return condition; //ensure that the condition has been checked
        else {
            if (condition.isReturned()) { //if the condition result is true
                if (super.getChildNode() != null) return super.getChildNode(); //if there is a child node to execute otherwise if this is an else if/else statement otherwise just return the next node
                else if (elseType) {
                    IfNode current = (IfNode)super.getParentNode();
                    while (current.isElseType()) current = (IfNode)super.getParentNode();
                    return current.getNextNode();
                }
                else return super.getNextNode();
            }
            else if (elseIfNodes.getSize() > 0) { //if the condition is false but there are else if commands
                return elseIfNodes.get(0);
            }
            else if (elseNode != null) return elseNode; //if there is an else command
            else if (elseType) { //if this is an else if/else
                if (super.getNextNode() != null) return super.getNextNode();
                else { //find the final parent if statement
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
