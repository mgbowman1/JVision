package jvision.Nodes;

import java.util.logging.Level;
import java.util.logging.Logger;
import jvision.Exceptions.BadArgumentNodeException;
import jvision.Exceptions.NodeDoesNotExistException;
import jvision.Exceptions.NullNodeException;
import jvision.NodeList;

public class AssignmentNode extends Node {

    public AssignmentNode(VariableNode leftSide, NodeList<VariableNode> middle, Node rightSide, String name, Node previousNode, Node nextNode, Node childNode, Node parentNode, int xPos, int yPos, int xSize, int ySize, int xAreaSize, int yAreaSize, String comment) {
        super(name, previousNode, nextNode, childNode, parentNode, xPos, yPos, xSize, ySize, xAreaSize, yAreaSize, comment);
        this.leftSide = leftSide;
        this.middle = middle;
        this.rightSide = rightSide;
    }

    public VariableNode getLeftSide() {
        return leftSide;
    }

    public void setLeftSide(VariableNode leftSide) {
        this.leftSide = leftSide;
    }

    public Node getRightSide() {
        return rightSide;
    }

    public void setRightSide(Node rightSide) {
        this.rightSide = rightSide;
    }
    
    @Override
    public Node execute() {
        super.execute();
        if (!rightSide.isExecuted()) return rightSide;
        else {
            String typeString = "";
            Object valueObject = new Object();
            try {
                VariableNode temp = super.getVariable(rightSide);
                typeString = temp.getType();
                valueObject = temp.getValue();
            } catch (BadArgumentNodeException | CloneNotSupportedException ex) {
                Logger.getLogger(AssignmentNode.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (middle.getSize() > 0) {
                VariableNode current = middle.get(0);
                while (current != null) {
                    current.setType(typeString);
                    current.setValue(valueObject);
                }
            }
            leftSide.setType(typeString);
            leftSide.setValue(valueObject);
            return super.getNextNode();
        }
    }
    
    @Override
    public void unExecute() {
        super.unExecute();
        leftSide.unExecute();
        VariableNode current = middle.get(0);
        while (current != null) {
            current.unExecute();
            current = (VariableNode)current.getNextNode();
        }
        rightSide.unExecute();
    }
    
    @Override
    public void setVars(NodeList<VariableNode> old, NodeList<VariableNode> newer) throws NullNodeException, NodeDoesNotExistException {
        super.setVars(old, newer);
        leftSide.setVars(old, newer);
        super.setVarList(old, newer, middle);
        rightSide = super.setVar(old, newer, rightSide);
    }
    
    private VariableNode leftSide; //left side of the assignment
    protected final NodeList<VariableNode> middle; //middle variables if any
    private Node rightSide; //right side of the assignment
    
}
