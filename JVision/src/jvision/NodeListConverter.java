package jvision;

import jvision.Exceptions.BadArgumentNodeException;
import jvision.Exceptions.NullNodeException;
import jvision.Nodes.Node;

/**
 *
 * @author Michael
 * @param <E>
 * @see Node
 * 
 * This class is used to convert a NodeList from one subclass of Node to another.
 */
public class NodeListConverter<E extends Node> {

    public NodeListConverter() {
    }
    
    public NodeList<E> convert(NodeList<Node> n, Class<E> c) throws NullNodeException, BadArgumentNodeException {
        if (n == null || n.getSize() == 0) throw new NullNodeException();
        NodeList<E> temp = new NodeList<>();
        Node current = n.get(0);
        if (current.getClass() != c) throw new BadArgumentNodeException();
        else {
            while (current != null) {
                temp.add((E)current);
                current = current.getNextNode();
            }
        }
        return temp;
    }
    
}
