package jvision;

import jvision.Exceptions.NodeDoesNotExistException;
import jvision.Exceptions.NullNodeException;
import jvision.Nodes.Node;

/**
 *
 * @author Michael
 * @param <E>
 * @see Node
 * 
 * This is a simple recreation of some of the parts of a LinkedList for use with the Node class and its subclasses.
 */
public class NodeList<E extends Node> {

    public int getSize() {
        return size;
    }

    public NodeList() {
        head = null;
        tail = null;
        size = 0;
    }
    
    public void add(E n) throws NullNodeException { //adds the given Node to the end of the list
        if (n == null) throw new NullNodeException();
        if (head == null) {
            head = n;
            head.setPreviousNode(null);
            head.setNextNode(null);
        }
        else {
            tail.setNextNode(n);
            n.setNextNode(null);
            n.setPreviousNode(tail);
        }
        tail = n;
        size++;
    }
    
    public void addAt(E n, E add) throws NullNodeException, NodeDoesNotExistException { //adds the given Node right after the first given Node in the list
        if (n == null || add == null) throw new NullNodeException();
        if (head == null) throw new NodeDoesNotExistException();
        E temp = (E)n.getNextNode();
        if (temp == null) add(add);
        else {
            n.setNextNode(add);
            temp.setPreviousNode(add);
            add.setPreviousNode(n);
            add.setNextNode(temp);
        }
        size++;
    }
    
    public void addAt(int index, E n) throws NullNodeException, NodeDoesNotExistException { //adds the given Node right after the given position in the list
        if (n == null) throw new NullNodeException();
        if (index < 0 || index >= size) throw new NodeDoesNotExistException();
        if (index == size - 1) add(n);
        else {
            E current = get(index);
            E temp = (E)current.getNextNode();
            current.setNextNode(n);
            n.setPreviousNode(current);
            n.setNextNode(temp);
            temp.setPreviousNode(n);
        }
    }
    
    public void addAll(NodeList<E> n) throws NullNodeException, CloneNotSupportedException { //adds all Nodes from the given list to the end of this list
        if (n == null || n.head == null) throw new NullNodeException();
        E current = n.head;
        while (current != null) {
            add((E)current.clone());
            current = (E)current.getNextNode();
        }
    }
    
    public void addHead(E n) throws NullNodeException { //places the given Node as the new head of this list and pushes all other Nodes down one
        if (n == null) throw new NullNodeException();
        if (head == null) add(n);
        n.setNextNode(head);
        n.setPreviousNode(null);
        head = n;
    }
    
    public void remove() throws NodeDoesNotExistException { //removes the last Node from this list
        if (head == null) throw new NodeDoesNotExistException();
        tail = (E)tail.getPreviousNode();
        tail.setNextNode(null);
        size--;
    }
    
    public void remove(E n) throws NodeDoesNotExistException { //removes the first instance of the given Node from this list
        if (head == null) throw new NodeDoesNotExistException();
        if (n.equals(head)) {
            head = (E)head.getNextNode();
            head.setPreviousNode(null);
        } else {
            if (n == null) throw new NodeDoesNotExistException();
            if (n.getNextNode() == null) remove();
            else {
                n.getPreviousNode().setNextNode(n.getNextNode());
                n.getNextNode().setPreviousNode(n.getPreviousNode());
            }
        }
    }
    
    public void remove(int index) throws NodeDoesNotExistException { //removes the Node at the given index in this list
        if (index < 0 || index >= size) throw new NodeDoesNotExistException();
        if (index == size - 1) remove();
        if (index == 0) {
            head = (E)head.getNextNode();
            head.setPreviousNode(null);
        } else {
            E temp = get(index);
            temp.getPreviousNode().setNextNode(temp.getNextNode());
            temp.getNextNode().setPreviousNode(temp.getPreviousNode());
        }
    }
    
    public void removeAll() { //removes all Nodes from this list
        head = null;
        tail = null;
    }
    
    public E get(int index) { //returns the Node at the given index in this list
        if (head == null) return null;
        E current = head;
        while (index > 0) {
            current = (E)current.getNextNode();
            if (current == null) return null;
            index--;
        }
        return current;
    }
    
    private E head; //head node
    private E tail; //tail node
    private int size; // size of list
    
}
