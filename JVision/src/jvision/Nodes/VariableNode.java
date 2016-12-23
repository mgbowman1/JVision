package jvision.Nodes;

import jvision.NodeList;

public class VariableNode extends Node {

    public VariableNode(String type, Object value, String name, Node previousNode, Node nextNode, Node childNode, Node parentNode, int xPos, int yPos, int xSize, int ySize, int xAreaSize, int yAreaSize, String comment) {
        super(name, previousNode, nextNode, childNode, parentNode, xPos, yPos, xSize, ySize, xAreaSize, yAreaSize, comment);
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    
    private String type; //type of variable
    private Object value; //value of variable
    
}
