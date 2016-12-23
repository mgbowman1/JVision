package jvision.Nodes;

import jvision.Exceptions.BadArgumentNodeException;
import jvision.Exceptions.NodeDoesNotExistException;
import jvision.Exceptions.NullNodeException;
import jvision.NodeList;

/**
 *
 * @author Michael
 * 
 * This is the abstract Node class that is extended by all other Node classes.
 * The basic function of the Node is to be the device by which all Java instructions are created.
 * The setVars method, and associated methods, are generally used in the case of changes to a MethodNodes arguments.
 * Since most of this program is based off of references to other Nodes, the instances of the old VariableNodes need to be changed to instances of the new VariableNodes for all Nodes inside of the method.
 */
public abstract class Node implements Cloneable {

    public Node(String name, Node previousNode, Node nextNode, Node childNode, Node parentNode, int xPos, int yPos, int xSize, int ySize, int xAreaSize, int yAreaSize, String comment) {
        this.name = name;
        this.previousNode = previousNode;
        this.nextNode = nextNode;
        this.childNode = childNode;
        this.parentNode = parentNode;
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSize = xSize;
        this.ySize = ySize;
        this.xAreaSize = xAreaSize;
        this.yAreaSize = yAreaSize;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public Node getChildNode() {
        return childNode;
    }

    public void setChildNode(Node childNode) {
        this.childNode = childNode;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getxSize() {
        return xSize;
    }

    public void setxSize(int xSize) {
        this.xSize = xSize;
    }

    public int getySize() {
        return ySize;
    }

    public void setySize(int ySize) {
        this.ySize = ySize;
    }

    public int getxAreaSize() {
        return xAreaSize;
    }

    public void setxAreaSize(int xAreaSize) {
        this.xAreaSize = xAreaSize;
    }

    public int getyAreaSize() {
        return yAreaSize;
    }

    public void setyAreaSize(int yAreaSize) {
        this.yAreaSize = yAreaSize;
    }
    
    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public boolean isExecuted() {
        return executed;
    }
    
    public void setExecuted(boolean executed) {
        this.executed = executed;
    }
    
    @Override
    public Node clone() throws CloneNotSupportedException {
        return (Node)super.clone(); //returns a clone of the current instance so that the receiver obtains a copy of this instance, but not this actual instance.  Note: the copy still references the same instances as this one
    }
    
    public VariableNode getVariable(Node current) throws BadArgumentNodeException, CloneNotSupportedException { //returns a VariableNode containing the value of the given Node.  For use when the value is needed for some other operation
        VariableNode temp = copyToVariableNode(current);
        if (current instanceof ClassNode) {
            temp.setType("class");
            temp.setValue(current.clone());
        } else if (current instanceof MethodNode) {
            temp.setType(((MethodNode)current).getReturnType());
            temp.setValue(((MethodNode)current).getReturned());
        } else if (current instanceof BooleanNode) {
            temp.setType("boolean");
            temp.setValue(((BooleanNode)current).isReturned());
        } else if (current instanceof LogicNode) {
            temp.setType("boolean");
            temp.setValue(((LogicNode)current).isReturned());
        } else if (current instanceof ArithmeticNode) {
            temp.setType(((ArithmeticNode)current).getReturnType());
            temp.setValue(((ArithmeticNode)current).getReturned());
        } else throw new BadArgumentNodeException();
        return temp;
    }
    
    private VariableNode copyToVariableNode(Node n) { //returns a VariableNode containing all the same super properties as the given Node
        return new VariableNode("", null, n.getName(), n.getPreviousNode(), n.getNextNode(), n.getChildNode(), n.getParentNode(), n.getxPos(), n.getyPos(), n.getxSize(), n.getySize(), n.getxAreaSize(), n.getyAreaSize(), n.getComment());
    }
    
    public Node execute() { //used for the interpreter.  Executes the necessary instruction for the Node and sets its execution status to true
        executed = true;
        return null;
    }
    
    public void unExecute() { //sets the execution status of the current Node and all child Nodes to false to allow for further execution
        executed = false;
        Node current = childNode;
        while (current != null) {
            current.unExecute();
            current = current.getNextNode();
        }
    }
    
    public void setVars(NodeList<VariableNode> old, NodeList<VariableNode> newer) throws NullNodeException, NodeDoesNotExistException { //sets the VariableNode instances of all child Nodes to newer instances if they need to be changed
        Node current = childNode;
        while (current != null) {
            setVar(old, newer, current);
            current = current.getNextNode();
        }
    }
    
    public void setVarList(NodeList<VariableNode> old, NodeList<VariableNode> newer, NodeList n) throws NullNodeException, NodeDoesNotExistException { //changes all VariableNodes in the given list to new instances if they are found in the old list
        if (n == null || n.getSize() == 0) return;
        Node current = n.get(0);
        while (current != null) {
            if (current instanceof VariableNode) {
                VariableNode second = old.get(0);
                int index = 0;
                while (second != null) {
                    if (second == current) {
                        n.addAt(current, newer.get(index));
                        n.remove(current);
                        current = current.getNextNode();
                        break;
                    }
                    second = (VariableNode)second.getNextNode();
                    index++;
                }
            } else current.setVars(old, newer);
            current = current.getNextNode();
        }
    }
    
    public Node setVar(NodeList<VariableNode> old, NodeList<VariableNode> newer, Node n) throws NullNodeException, NodeDoesNotExistException { //replaces a singular VariableNode if needed or just calls setVars on another type of Node
        NodeList newList = new NodeList();
        newList.add(n);
        setVarList(old, newer, newList);
        return newList.get(0);
    }
    
    private String name; //name of command
    private Node previousNode; //Node before this Node
    private Node nextNode; //Node after this Node
    private Node childNode; //Node if this Node has children
    private Node parentNode; //Node if this Node has a parent
    private int xPos; //graphical x-position
    private int yPos; //graphical y-position
    private int xSize; //graphical x-size
    private int ySize; //graphical y-size
    private int xAreaSize; //graphical x-size of background panel
    private int yAreaSize; //graphical y-size of background panel
    private String comment; //any comment placed on this node
    private boolean executed; //if the node has been executed yet
    
}
