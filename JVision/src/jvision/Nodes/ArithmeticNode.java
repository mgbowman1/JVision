package jvision.Nodes;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jvision.Exceptions.BadArgumentNodeException;
import jvision.Exceptions.NodeDoesNotExistException;
import jvision.Exceptions.NullNodeException;
import jvision.NodeList;

/**
 *
 * @author Michael
 * @see Node
 * 
 * This Node performs all arithmetic operations including concatenating strings.
 */
public class ArithmeticNode extends Node {

    public ArithmeticNode(NodeList<Node> arguments, LinkedList<String> operations, String returnType, String name, Node previousNode, Node nextNode, Node childNode, Node parentNode, int xPos, int yPos, int xSize, int ySize, int xAreaSize, int yAreaSize, String comment) {
        super(name, previousNode, nextNode, childNode, parentNode, xPos, yPos, xSize, ySize, xAreaSize, yAreaSize, comment);
        this.arguments = arguments;
        this.operations = operations;
        this.returnType = returnType;
        returned = "";
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getReturned() {
        return returned;
    }
    
    @Override
    public Node execute() {
        super.execute();
        Node current = arguments.get(0);
        while (current != null) {
            if (!current.isExecuted()) return current;
            current = current.getNextNode();
        }
        try {
            Object value = super.getVariable(arguments.get(0));
            Object newOne = super.getVariable(arguments.get(1));
            for (String s : operations) {
                switch (s) {
                    case "+":
                        if (returnType.equals("String")) value = value.toString() + newOne.toString();
                        else value = Double.parseDouble(value.toString()) + Double.parseDouble(newOne.toString());
                        break;
                    case "-":
                        value = Double.parseDouble(value.toString()) - Double.parseDouble(newOne.toString());
                        break;
                    case "*":
                        value = Double.parseDouble(value.toString()) * Double.parseDouble(newOne.toString());
                        break;
                    case "/":
                        value = Double.parseDouble(value.toString()) / Double.parseDouble(newOne.toString());
                        break;
                    case "%":
                        value = Double.parseDouble(value.toString()) % Double.parseDouble(newOne.toString());
                        break;
                }
            }
            returned = value.toString();
        } catch (BadArgumentNodeException | CloneNotSupportedException ex) {
            Logger.getLogger(ArithmeticNode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.getNextNode();
    }
    
    @Override
    public void unExecute() {
        super.unExecute();
        Node current = arguments.get(0);
        while (current != null) {
            current.unExecute();
            current = current.getNextNode();
        }
    }
    
    @Override
    public void setVars(NodeList<VariableNode> old, NodeList<VariableNode> newer) throws NullNodeException, NodeDoesNotExistException {
        super.setVars(old, newer);
        super.setVarList(old, newer, arguments);
    }
    
    protected final NodeList<Node> arguments; //arguments of the math
    protected final LinkedList<String> operations; //operations of the math
    private String returnType; //type of returned value
    private String returned; //returned value
    
}
