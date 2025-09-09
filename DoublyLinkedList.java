// A generic Doubly Linked List implementation
public class DoublyLinkedList<E>
{
    // Fields for DoublyLinkedList
    private Node<E> header;
    private Node<E> trailer;
    private int size;

    // Constructor
    public DoublyLinkedList()
    {
        header = new Node<>(null, null, null); // sentinel at front
        trailer = new Node<>(null, header, null); // sentinel at back
        header.setNext(trailer);
        size = 0;
    }

    // Nested Node class
    public static class Node<E>
    {
        //Fields for Node class
        private E element;
        private Node<E> next;
        private Node<E> prev;

        //Constructor
        public Node(E element, Node<E> prev, Node<E> next)
        {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        public E getElement() { return element; }
        public Node<E> getNext() { return next; }
        public Node<E> getPrev() { return prev; }
        public void setNext(Node<E> next) { this.next = next; }
        public void setPrev(Node<E> prev) { this.prev = prev; }
    }


    // Utility methods
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public E first()
    {
        if (isEmpty()) return null;
        return header.getNext().getElement();
    }

    public E last()
    {
        if (isEmpty()) return null;
        return trailer.getPrev().getElement();
    }

    // Insert between two nodes
    public void addBetween(E element, Node<E> predecessor, Node<E> successor)
    {
        Node<E> newest = new Node<>(element, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }

    // Remove a node
    private E remove(Node<E> node)
    {
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.getElement();
    }

    // Public update methods
    public void addFirst(E element)
    {
        addBetween(element, header, header.getNext());
    }

    public void addLast(E element)
    {
        addBetween(element, trailer.getPrev(), trailer);
    }

    public E removeFirst()
    {
        if (isEmpty()) return null;
        return remove(header.getNext());
    }

    public E removeLast()
    {
        if (isEmpty()) return null;
        return remove(trailer.getPrev());
    }

    // Debug print method
    public void printList()
    {
        Node<E> current = header.getNext();
        while (current != trailer) {
            System.out.print(current.getElement() + " ");
            current = current.getNext();
        }
        System.out.println();
    }
}

