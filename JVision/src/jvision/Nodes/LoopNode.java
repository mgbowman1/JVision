package jvision.Nodes;

import jvision.Exceptions.NodeDoesNotExistException;
import jvision.Exceptions.NullNodeException;
import jvision.NodeList;

/**
 *
 * @author Michael
 * @see Node
 * 
 * This Node is used for all loops.
 */
public class LoopNode extends Node {

    public LoopNode(AssignmentNode initializer, LogicNode condition, Node finisher, boolean conditionBefore, String name, Node previousNode, Node nextNode, Node childNode, Node parentNode, int xPos, int yPos, int xSize, int ySize, int xAreaSize, int yAreaSize, String comment) {
        super(name, previousNode, nextNode, childNode, parentNode, xPos, yPos, xSize, ySize, xAreaSize, yAreaSize, comment);
        this.initializer = initializer;
        this.condition = condition;
        this.finisher = finisher;
        this.conditionBefore = conditionBefore;
    }

    public AssignmentNode getInitializer() {
        return initializer;
    }

    public void setInitializer(AssignmentNode initializer) {
        this.initializer = initializer;
    }

    public LogicNode getCondition() {
        return condition;
    }

    public void setCondition(LogicNode condition) {
        this.condition = condition;
    }

    public Node getFinisher() {
        return finisher;
    }

    public void setFinisher(Node finisher) {
        this.finisher = finisher;
    }

    public boolean isConditionBefore() {
        return conditionBefore;
    }

    public void setConditionBefore(boolean conditionBefore) {
        this.conditionBefore = conditionBefore;
    }
    
    @Override
    public Node execute() {
        super.execute();
        if (initializer != null && !initializer.isExecuted()) return initializer; //ensure that the initializer and condition are executed.  If so then execute the loop.  If that's done then execute the finishes and the condition if it should be done after the loop.
        else if (!condition.isExecuted() && conditionBefore) return condition;
        else if (super.getChildNode() != null && !super.getChildNode().isExecuted()) return super.getChildNode();
        else if (finisher != null && !finisher.isExecuted()) return finisher;
        else if (!condition.isExecuted() && !conditionBefore) return condition;
        else return super.getNextNode();
    }
    
    @Override
    public void unExecute() {
        super.unExecute();
        if (initializer != null) initializer.unExecute();
        condition.unExecute();
        if (finisher != null) finisher.unExecute();
    }
    
    @Override
    public void setVars(NodeList<VariableNode> old, NodeList<VariableNode> newer) throws NullNodeException, NodeDoesNotExistException {
        super.setVars(old, newer);
        initializer.setVars(old, newer);
        condition.setVars(old, newer);
        super.setVar(old, newer, finisher);
    }
    
    private AssignmentNode initializer; //initialized variable for loop if exists
    private LogicNode condition; //condition for loop execution
    private Node finisher; //command to execute at end of each loop if exists
    private boolean conditionBefore; //if the condition is checked before or after loop
    
}
