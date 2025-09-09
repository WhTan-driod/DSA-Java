
public class Main {
    public static void main (String[] args)
    {
        //create empty Doubly Linkedlist
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        //Display to terminal
        System.out.println(list.size());

        //Create Node with values
        list.addFirst(10);
        System.out.println(list.size());

        //Create more Nodes with values
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);

        //Display size to terminal
        System.out.println(list.size());
        list.printList();

        //list.addBetween(15,list(10),list(20));
    }
}