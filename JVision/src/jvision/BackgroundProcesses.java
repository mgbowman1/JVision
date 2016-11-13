package jvision;

import java.util.LinkedList;

public class BackgroundProcesses {
    LinkedList<Node> masterList = new LinkedList<>();
    
    public BackgroundProcesses() {
        
    }
    
    public void setMasterList(LinkedList<Node> m) {
        masterList = m;
    }
    
    public LinkedList<Node> getMasterList() {
        return masterList;
    }
}