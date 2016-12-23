package jvision.Nodes;

import java.util.LinkedList;
import jvision.Exceptions.NodeDoesNotExistException;
import jvision.Exceptions.NullNodeException;
import jvision.NodeList;

/**
 *
 * @author Michael
 * @see Node
 * @see BooleanNode
 * 
 * This Node is used as the parent class for all boolean operations.
 * It can contain BooleanNodes within it to do smaller boolean operations
 */
public class LogicNode extends Node {

    public LogicNode(NodeList<BooleanNode> checks, LinkedList<String> operations, boolean not, String name, Node previousNode, Node nextNode, Node childNode, Node parentNode, int xPos, int yPos, int xSize, int ySize, int xAreaSize, int yAreaSize, String comment) {
        super(name, previousNode, nextNode, childNode, parentNode, xPos, yPos, xSize, ySize, xAreaSize, yAreaSize, comment);
        this.checks = checks;
        this.operations = operations;
        this.not = not;
        returned = false;
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
        super.execute();
        BooleanNode current = checks.get(0);
        while (current != null) { //ensure that all boolean operations involving comparisons are completed
            if (!current.isExecuted()) return current;
            current = (BooleanNode)current.getNextNode();
        }
        current = checks.get(1);
        boolean bool = checks.get(0).isReturned();
        for (String s : operations) { //do boolean operations involving logical operations
            switch (s) {
                case "&&":
                    bool = bool && current.isReturned();
                    break;
                case "||":
                    bool = bool || current.isReturned();
                    break;
            }
            current = (BooleanNode)current.getNextNode();
        }
        if (not) bool = !bool; //invert the result if the not variable is set
        returned = bool;
        return super.getNextNode();
    }
    
    @Override
    public void unExecute() {
        super.unExecute();
        BooleanNode current = checks.get(0);
        while (current != null) {
            current.unExecute();
            current = (BooleanNode)current.getNextNode();
        }
    }
    
    @Override
    public void setVars(NodeList<VariableNode> old, NodeList<VariableNode> newer) throws NullNodeException, NodeDoesNotExistException {
        super.setVars(old, newer);
        super.setVarList(old, newer, checks);
    }
    
    protected final NodeList<BooleanNode> checks; //all boolean checks
    protected final LinkedList<String> operations; //all logic operations
    private boolean not; //whether the entire logical check is negated
    private boolean returned; //result of the logical check
    
}
