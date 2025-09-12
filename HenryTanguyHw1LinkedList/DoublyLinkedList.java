//Node class represents a single node in the doubly linked list.
class Node<T> {
    T data;
    Node<T> next;    
    Node<T> prev; 
    
    //Constructor
    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

//DoublyLinkedList class implements a doubly linked list data structure.
public class DoublyLinkedList<T> {
    private Node<T> head;    // Points to the first node
    private Node<T> tail;    // Points to the last node
    private int size;        // Keep track of list size
    
    //Constructor
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    // Add node to the front of the list
    public void addFront(T data) {
        Node<T> newNode = new Node<>(data);
        
        // If list is empty, new node becomes both head and tail
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            // Link new node to current head
            newNode.next = head;
            head.prev = newNode;
            // Update head to point to new node
            head = newNode;
        }
        
        size++;
        System.out.println("Added " + data + " at the front of the list");
    }
    
    //Add node to end of the list
    public void addBack(T data) {
        Node<T> newNode = new Node<>(data);
        
        // If list is empty, new node becomes both head and tail
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            // Link new node to current tail
            newNode.prev = tail;
            tail.next = newNode;
            // Update tail to point to new node
            tail = newNode;
        }
        
        size++;
        System.out.println("Added " + data + " at the back of the list");
    }
    
    //Add node after specified value
    public boolean addBetween(T data, T afterValue) {
        // If list is empty, cannot add between nodes
        if (head == null) {
            System.out.println("List is empty. Cannot add between nodes.");
            return false;
        }
        
        // Search for the node with afterValue
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(afterValue)) {
                Node<T> newNode = new Node<>(data);
                
                // If inserting after the last node, update tail
                if (current == tail) {
                    newNode.prev = current;
                    current.next = newNode;
                    tail = newNode;
                } else {
                    // Insert between current and current.next
                    newNode.next = current.next;
                    newNode.prev = current;
                    current.next.prev = newNode;
                    current.next = newNode;
                }
                
                size++;
                System.out.println("Added " + data + " after " + afterValue);
                return true;
            }
            
            current = current.next;
        }
        
        // Value not found
        System.out.println("Value " + afterValue + " not found in the list");
        return false;
    }
    
    //remove node from front
    public T removeFront() {
        if (head == null) {
            System.out.println("List is empty. Cannot remove from front.");
            return null;
        }
        
        T removedData = head.data;
        
        // If only one node in the list
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            // Move head to next node and update its prev reference
            head = head.next;
            head.prev = null;
        }
        
        size--;
        System.out.println("Removed " + removedData + " from the front");
        return removedData;
    }
    
    //remove node from end of the list
    public T removeBack() {
        if (tail == null) {
            System.out.println("List is empty. Cannot remove from back.");
            return null;
        }
        
        T removedData = tail.data;
        
        // If only one node in the list
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            // Move tail to previous node and update its next reference
            tail = tail.prev;
            tail.next = null;
        }
        
        size--;
        System.out.println("Removed " + removedData + " from the back");
        return removedData;
    }
    
    //remove node by value
    public boolean removeValue(T value) {
        if (head == null) {
            System.out.println("List is empty. Cannot remove value.");
            return false;
        }
        
        Node<T> current = head;
        
        // Search for the node with the specified value
        while (current != null) {
            if (current.data.equals(value)) {
                // If it's the only node
                if (current == head && current == tail) {
                    head = null;
                    tail = null;
                } 
                // If it's the first node
                else if (current == head) {
                    head = current.next;
                    head.prev = null;
                } 
                // If it's the last node
                else if (current == tail) {
                    tail = current.prev;
                    tail.next = null;
                } 
                // If it's a middle node
                else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                
                size--;
                System.out.println("Removed " + value + " from the list");
                return true;
            }
            
            current = current.next;
        }
        
        System.out.println("Value " + value + " not found in the list");
        return false;
    }
    
    //Display all elements in the list from head to tail.
    public void displayForward() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        
        System.out.print("List (forward): ");
        Node<T> current = head;
        StringBuilder result = new StringBuilder();
        
        while (current != null) {
            result.append(current.data);
            if (current.next != null) {
                result.append(" <-> ");
            }
            current = current.next;
        }
        
        System.out.println(result.toString());
    }
    
    //Display all elements in the list from tail to head.
    public void displayBackward() {
        if (tail == null) {
            System.out.println("List is empty");
            return;
        }
        
        System.out.print("List (backward): ");
        Node<T> current = tail;
        StringBuilder result = new StringBuilder();
        
        while (current != null) {
            result.append(current.data);
            if (current.prev != null) {
                result.append(" <-> ");
            }
            current = current.prev;
        }
        
        System.out.println(result.toString());
    }
    
    //seach for a value in the list
    public boolean search(T value) {
        Node<T> current = head;
        int position = 0;
        
        while (current != null) {
            if (current.data.equals(value)) {
                System.out.println("Value " + value + " found at position " + position);
                return true;
            }
            current = current.next;
            position++;
        }
        
        System.out.println("Value " + value + " not found in the list");
        return false;
    }
    
    //get size of the list
    public int getSize() {
        return size;
    }
    
    //check if the list is empty
    public boolean isEmpty() {
        return head == null;
    }
}