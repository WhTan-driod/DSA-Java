public class QueueList<T>
{
    //fields
    public Node<T> front;
    public Node<T> trailer;
    public int size;

    //Nested Node class
    private static class Node<T>
    {
        //Node fields
        private Node<T> next;
        private T data;

        Node(T data)
        {
            this.data = data;
        }
    }

    //size function to display size of queue list
    public void sizeOf()
    {
        System.out.println(size);
    }

    //add to queue
    public void enqueue(T data)
    {
        Node<T> newNode = new Node<>(data);
        //Check if list queue is empty
        if(front == null)
        {
            newNode.next = front;
            front = newNode;
            trailer = newNode;
            System.out.println(data + " is the first in the queue");
            size++;
        }
        //Add to end of queue
        else 
        {
            Node<T> temp = front;
            while (temp.next != null){
                temp = temp.next;
            }

            temp.next = newNode;
            trailer = newNode;
            size ++;
            System.out.println(data + " has been added to the end of the queue");
        }
    }

    //Remove first Node from front of queue
    public T dequeue()
    {
        T removedElement = null;
        if(front == null)
        {
            System.out.println("queue is empty");
        }
        else
        {
            removedElement = front.data;
            front = front.next;
            size --;
        }

        return removedElement;
    }

    //List elements in queue
    public void listElements()
    {
        Node<T> temp = front;
        while (temp != null){
            System.out.print(temp.data + "->");
            temp = temp.next;
        }

        System.out.print("END" + "\n");
    }

    //List first element in queue
    public void first()
    {
        if(size == 0)
        {
            System.out.println("Queue is empty");
        }
        else
        {
            Node<T> temp = front;
            System.out.println(temp.data + " is first element");
        }
    }

    public static void main(String[] args)
    {
        QueueList<Integer> list = new QueueList<Integer>();
        list.first();
        list.enqueue(2);
        list.enqueue(4);
        list.enqueue(6);
        list.enqueue(8);
        list.enqueue(10);

        list.sizeOf();
        list.listElements();
        list.dequeue();
        list.listElements();

        list.first();
    }
}