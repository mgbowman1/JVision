package jvision.Nodes;

import jvision.Exceptions.NodeDoesNotExistException;
import jvision.Exceptions.NullNodeException;
import jvision.NodeList;


public class ImportNode extends Node {

    public ImportNode(ClassNode imported, String path, String name, Node previousNode, Node nextNode, Node childNode, Node parentNode, int xPos, int yPos, int xSize, int ySize, int xAreaSize, int yAreaSize, String comment) {
        super(name, previousNode, nextNode, childNode, parentNode, xPos, yPos, xSize, ySize, xAreaSize, yAreaSize, comment);
        this.imported = imported;
        this.path = path;
    }

    public ClassNode getImported() {
        return imported;
    }

    public void setImported(ClassNode imported) {
        this.imported = imported;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    @Override
    public Node execute() {
        
    }
    
    @Override
    public void setVars(NodeList<VariableNode> old, NodeList<VariableNode> newer) throws NodeDoesNotExistException, NullNodeException {
        super.setVars(old, newer);
        imported.setVars(old, newer);
    }
    
    private ClassNode imported; //the class that is imported
    private String path; //the file path to the class
    
}
