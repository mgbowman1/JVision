package jvision;

import java.util.ArrayList;

public class Node {
    
    private String name; //name of command
    private String type; //type of command: conditional, method call, assignment, class/method/interface creation, import
    private ArrayList<Node> arguments; //arguments
    private ArrayList<Node> parents; //parent methods and classes to this node
    private ArrayList<Node> children; //children nodes to this node
    private int xPos; //graphical x-position
    private int yPos; //graphical y-position
    private int xSize; //graphical x-size
    private int ySize; //graphical y-size
    private int xAreaSize; //graphical x-size of background panel
    private int yAreaSize; //graphical y-size of background panel
    private String privacy; //public, private or protected
    private String returnValue; //return type for a method
    private Node next;
    
    /**
     * Creates an empty node where all instance variables contain an empty version of themselves
     */
    public Node() {
        name = "";
        type = "";
        arguments = new ArrayList<>();
        parents = new ArrayList<>();
        children = new ArrayList<>();
        xPos = 0;
        yPos = 0;
        xSize = 0;
        ySize = 0;
        xAreaSize = 0;
        yAreaSize = 0;
        privacy = "";
        returnValue = "";
        next = null;
    }
    
    /**
     * Creates a complete {@code Node} with all instance variables entered
     * 
     * @param name the {@code String} name of the command
     * @param type the {@code String} type of command {conditional, method call, assignment, class/method/interface creation, import}
     * @param arguments an {@code ArrayList} of arguments of type {@code Node} that are used in this command
     * @param parents an {@code ArrayList} of all parent elements of type {@code Node} of this {@code Node}
     * @param children an {@code ArrayList} of all children elements of type {@code Node} of this {@code Node}
     * @param xPos an {@code int} that gives the graphical x-position of this {@code Node}
     * @param yPos an {@code int} that gives the graphical y-position of this {@code Node}
     * @param xSize an {@code int} that gives the graphical horizontal size of this {@code Node}
     * @param ySize an {@code int} that gives the graphical vertical size of this {@code Node}
     * @param xAreaSize an {@code int} that gives the graphical horizontal size of the background layer attached to this {@code Node}
     * @param yAreaSize an {@code int} that gives the graphical vertical size of the background layer attached to this {@code Node}
     * @param privacy a {@code String} which gives the privacy setting {private, public or protected}
     * @param returnValue a {@code String} which tells the return type of this {@code Node}
     * @param next a {@code Node} which is the next instruction to be computed
     */
    public Node(String name, String type, ArrayList<Node> arguments, ArrayList<Node> parents, ArrayList<Node> children, int xPos, int yPos, int xSize, int ySize, int xAreaSize, int yAreaSize, String privacy, String returnValue, Node next) {
        this.name = name;
        this.type = type;
        this.arguments = arguments;
        this.parents = parents;
        this.children = children;
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSize = xSize;
        this.ySize = ySize;
        this.xAreaSize = xAreaSize;
        this.yAreaSize = yAreaSize;
        this.privacy = privacy;
        this.returnValue = returnValue;
        this.next = next;
    }
    
    public void setName(String n) {
        name = n;
    }
    
    public void setType(String t) {
        type = t;
    }
    
    public void setArguments(ArrayList<Node> a) {
        arguments = a;
    }
    
    public void setParents(ArrayList<Node> p) {
        parents = p;
    }
    
    public void setChildren(ArrayList<Node> c) {
        children = c;
    }
    
    public void setXPos(int x) {
        xPos = x;
    }
    
    public void setYPos(int y) {
        yPos = y;
    }
    
    public void setXSize(int x) {
        xSize = x;
    }
    
    public void setYSize(int y) {
        ySize = y;
    }
    
    public void setXAreaSize(int x) {
        xAreaSize = x;
    }
    
    public void setYAreaSize(int y) {
        yAreaSize = y;
    }
    
    public void setPrivacy(String p) {
        privacy = p;
    }
    
    public void setReturnValue(String r) {
        returnValue = r;
    }
    
    public void setNext(Node n) {
        next = n;
    }
    
    public String getName() {
        return name;
    }
    
    public String getType() {
        return type;
    }
    
    public ArrayList<Node> getArguments() {
        return arguments;
    }
    
    public ArrayList<Node> getParents() {
        return parents;
    }
    
    public ArrayList<Node> getChildren() {
        return children;
    }
    
    public int getXPos() {
        return xPos;
    }
    
    public int getYPos() {
        return yPos;
    }
    
    public int getXSize() {
        return xSize;
    }
    
    public int getYSize() {
        return ySize;
    }
    
    public int getXAreaSize() {
        return xAreaSize;
    }
    
    public int getYAreaSize() {
        return yAreaSize;
    }
    
    public String getPrivacy() {
        return privacy;
    }
    
    public String getReturnValue() {
        return returnValue;
    }
    
    public Node getNext() {
        return next;
    }
    
}
