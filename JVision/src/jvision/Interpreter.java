package jvision;

import jvision.Nodes.Node;

/**
 *
 * @author Michael
 * 
 * This class is simply used for running an interpreter on a given list of Nodes.
 */
public class Interpreter {

    public Interpreter(NodeList<Node> masterList) {
        this.masterList = masterList;
        current = this.masterList.get(0);
    }

    public Node getCurrent() {
        return current;
    }

    public void setCurrent(Node current) {
        this.current = current;
    }

    public boolean isFinished() {
        return finished;
    }
    
    public void nextInstruction() { //execute the next Node/instruction in the code
        Node temp = current.execute();
        while (current.isExecuted()) {
            while (temp == null) {
                current = current.getParentNode();
                if (current == null) {
                    finished = true;
                    return;
                } else temp = current.getNextNode();
            }
            current = temp;
        }
    }
    
    protected NodeList<Node> masterList; //main list of instructions
    private Node current; //the currently selected instruction
    private boolean finished; //if the interpreter has finished executing
    
}
