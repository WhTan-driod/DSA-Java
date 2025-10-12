public class PrintLinkedList <T>{
    int size;
    Node<T> header;
    Node<T> tail;

    public void add(T element)
    {
        Node<T> newNode = new Node<>(element);
        if(header==null){
            newNode.next = header;
            header = newNode;
            tail = newNode;
        }
        else{
            newNode.next = header;
            header = newNode;
            size++;
        }
    }

    public void listElements()
    {
        Node<T> temp = header;
        while(temp!=null){
            System.out.println(temp.element);
            temp=temp.next;
        }
    }

    private class Node<T> 
    {
        Node<T> next;
        T element;

        private Node(Node<T> next, T element)
        {
            this.next = next;
            this.element = element;
        }
        private Node(T element)
        {
            this.element = element;
        }
    }

    public static void main(String[] args)
    {
        PrintLinkedList<Integer> list = new PrintLinkedList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        list.listElements();

    }
}
