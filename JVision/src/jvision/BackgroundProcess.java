package jvision;

import java.util.LinkedList;

public class BackgroundProcess {
    private LinkedList<Node> masterList = new LinkedList<>();
    
    public BackgroundProcess() {
        
    }
    
    public void setMasterList(LinkedList<Node> m) {
        masterList = m;
    }
    
    public LinkedList<Node> getMasterList() {
        return masterList;
    }
}
