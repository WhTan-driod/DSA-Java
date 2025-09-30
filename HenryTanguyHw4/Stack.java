import java.util.*;

public class Stack {
    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();

        // Push elements onto the stack
        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Stack after pushes: " + stack);

        // Pop elements from the stack
        int poppedElement = stack.pop();
        System.out.println("Popped element: " + poppedElement);
        System.out.println("Stack after pop: " + stack);

        // Peek at the top element
        int topElement = stack.peek();
        System.out.println("Top element: " + topElement);
        System.out.println("Stack after peek: " + stack);

        // Check if the stack is empty
        boolean isEmpty = stack.isEmpty();
        System.out.println("Is the stack empty? " + isEmpty);

        // Get the size of the stack
        int size = stack.size();
        System.out.println("Size of the stack: " + size);
    }
}
