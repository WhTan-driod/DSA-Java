public class CircularLinkedList <E> {
    private Node<E> trailer;
    private int size;

    public CircularLinkedList()
    {
        trailer = new Node<>()
    }

    public static class Node<E>
    {
        private E element;
        private Node<E> next;
    }

}
