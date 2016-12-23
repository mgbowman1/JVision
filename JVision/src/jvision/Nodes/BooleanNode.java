package jvision.Nodes;

import java.util.logging.Level;
import java.util.logging.Logger;
import jvision.Exceptions.BadArgumentNodeException;
import jvision.Exceptions.NodeDoesNotExistException;
import jvision.Exceptions.NullNodeException;
import jvision.NodeList;


public class BooleanNode extends Node {

    public BooleanNode(Node leftSide, Node rightSide, String operator, boolean not, String name, Node previousNode, Node nextNode, Node childNode, Node parentNode, int xPos, int yPos, int xSize, int ySize, int xAreaSize, int yAreaSize, String comment) {
        super(name, previousNode, nextNode, childNode, parentNode, xPos, yPos, xSize, ySize, xAreaSize, yAreaSize, comment);
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.operator = operator;
        this.not = not;
        returned = false;
    }

    public Node getLeftSide() {
        return leftSide;
    }

    public void setLeftSide(Node leftSide) {
        this.leftSide = leftSide;
    }

    public Node getRightSide() {
        return rightSide;
    }

    public void setRightSide(Node rightSide) {
        this.rightSide = rightSide;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        if (operator.equals("==") || operator.equals(">=") || operator.equals("<=") || operator.equals(">") || operator.equals("<")) this.operator = operator;
    }
    
    public boolean isNot() {
        return not;
    }
    
    public void setNot(boolean not) {
        this.not = not;
    }
    
    public boolean isReturned() {
        return returned;
    }
    
    @Override
    public Node execute() {
        if (!leftSide.isExecuted()) return leftSide;
        else if (!rightSide.isExecuted()) return rightSide;
        else {
            try {
                Object left = super.getVariable(leftSide).getValue();
                Object right = super.getVariable(rightSide).getValue();
                switch (operator) {
                    case "==":
                        returned = left.equals(right);
                        break;
                    case ">=":
                        returned = Double.parseDouble(left.toString()) >= Double.parseDouble(right.toString());
                        break;
                    case "<=":
                        returned = Double.parseDouble(left.toString()) <= Double.parseDouble(right.toString());
                        break;
                    case ">":
                        returned = Double.parseDouble(left.toString()) > Double.parseDouble(right.toString());
                        break;
                    case "<":
                        returned = Double.parseDouble(left.toString()) < Double.parseDouble(right.toString());
                        break;
                }
                if (not) returned = !returned;
            } catch (BadArgumentNodeException | CloneNotSupportedException ex) {
                Logger.getLogger(BooleanNode.class.getName()).log(Level.SEVERE, null, ex);
            }
            return super.getNextNode();
        }
    }
    
    @Override
    public void unExecute() {
        super.unExecute();
        leftSide.unExecute();
        rightSide.unExecute();
    }
    
    @Override
    public void setVars(NodeList<VariableNode> old, NodeList<VariableNode> newer) throws NullNodeException, NodeDoesNotExistException {
        super.setVars(old, newer);
        leftSide = super.setVar(old, newer, leftSide);
        rightSide = super.setVar(old, newer, rightSide);
    }
    
    private Node leftSide; //left side of the check
    private Node rightSide; //right side of the check
    private String operator; //the operator for the check
    private boolean not; //whether to return negation of check
    private boolean returned; //result of check
    
}
