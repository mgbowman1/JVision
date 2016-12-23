package jvision.Nodes;

import jvision.Exceptions.BadArgumentNodeException;
import jvision.Exceptions.NodeDoesNotExistException;
import jvision.Exceptions.NullNodeException;
import jvision.NodeList;

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
        return (Node)super.clone();
    }
    
    public VariableNode getVariable(Node current) throws BadArgumentNodeException, CloneNotSupportedException {
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
        } else if (current instanceof ArithemeticNode) {
            temp.setType(((ArithemeticNode)current).getReturnType());
            temp.setValue(((ArithemeticNode)current).getReturned());
        } else throw new BadArgumentNodeException();
        return temp;
    }
    
    private VariableNode copyToVariableNode(Node n) {
        return new VariableNode("", null, n.getName(), n.getPreviousNode(), n.getNextNode(), n.getChildNode(), n.getParentNode(), n.getxPos(), n.getyPos(), n.getxSize(), n.getySize(), n.getxAreaSize(), n.getyAreaSize(), n.getComment());
    }
    
    public Node execute() {
        executed = true;
        return null;
    }
    
    public void unExecute() {
        executed = false;
        Node current = childNode;
        while (current != null) {
            current.unExecute();
            current = current.getNextNode();
        }
    }
    
    public void setVars(NodeList<VariableNode> old, NodeList<VariableNode> newer) throws NullNodeException, NodeDoesNotExistException {
        Node current = childNode;
        while (current != null) {
            setVar(old, newer, current);
            current = current.getNextNode();
        }
    }
    
    public void setVarList(NodeList<VariableNode> old, NodeList<VariableNode> newer, NodeList n) throws NullNodeException, NodeDoesNotExistException {
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
    
    public Node setVar(NodeList<VariableNode> old, NodeList<VariableNode> newer, Node n) throws NullNodeException, NodeDoesNotExistException {
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
