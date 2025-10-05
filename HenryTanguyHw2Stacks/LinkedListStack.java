//class to demonstrate a stack through a singly linked list
public class LinkedListStack<T> {
    private Node<T> top;
    private int size;

    //Node class to store data and point to next node
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    //constructor
    public LinkedListStack() {
        this.top = null;
        this.size = 0;
    }

    //push node onto stack
    public void push(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.next = top;
        top = newNode;
        size++;
    }

    //remove node from stack
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T item = top.data;
        top = top.next;
        size--;
        return item;
    }

    //display top bode
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return top.data;
    }

    //check if stack is empty
    public boolean isEmpty() {
        return size == 0;
    }

    //check size of stack
    public int size() {
        return size;
    }

    public void listElements(){
        Node<T> current = top;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public static void main(String[] args) {
        //create new stack object
        LinkedListStack<Integer> stack = new LinkedListStack<>();
        
        //add elements to stack
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(69420);
        
        //call stack methods
        System.out.println("Top element: " + stack.peek()); // 30
        System.out.println("Stack size: " + stack.size()); // 3
        
        System.out.println("Popped element: " + stack.pop()); // 30
        System.out.println("New top element: " + stack.peek()); // 20
        System.out.println("Stack size after pop: " + stack.size()); // 2
        stack.listElements();
    }
}