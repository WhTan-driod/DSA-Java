public class BankQueue<T> {
    int size;
    Node<T> header;
    Node<T> tail;    
    
    public void add(T element){
        Node newNode = new Node<>(element);
        if(header == null){
            newNode.next=header;
            header=newNode;
            tail=newNode;
            size++;
        }
        else{
            Node temp = header;
            while(temp.next!=null){
                temp = temp.next;
            }
            temp.next = newNode;
            size++;
        }
    }

    public void listElements(){
        Node temp = header;
        int i=1;
        while(temp!=null){
            System.out.print(temp.element +" is in "+ i + " place-> ");
            temp = temp.next;
            i++;
        }
    }

    private class Node<T>{
        T element;
        Node next;

        private Node(T element){
            this.element = element;
        }

        private Node(T element, Node next){
            this.element = element;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        BankQueue<String> queue = new BankQueue<>();
        queue.add("Big dog");
        queue.add("beeg boy");
        queue.add("slim jim");

        queue.listElements();
    }
}
