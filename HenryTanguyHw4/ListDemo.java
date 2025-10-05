import java.util.List;

public class ListDemo {
    public static void main(String[] args) {
        List<Integer> list = new java.util.ArrayList<>();

        // Add elements to the list
        list.add(10);
        list.add(20);
        list.add(30);

        System.out.println("List after additions: " + list);

        // Remove an element from the list
        int removedElement = list.remove(1); // removes the element at index 1
        System.out.println("Removed element: " + removedElement);
        System.out.println("List after removal: " + list);

        // Get an element from the list
        int elementAtIndex0 = list.get(0);
        System.out.println("Element at index 0: " + elementAtIndex0);
        System.out.println("List after get: " + list);

        // Check if the list is empty
        boolean isEmpty = list.isEmpty();
        System.out.println("Is the list empty? " + isEmpty);

        // Get the size of the list
        int size = list.size();
        System.out.println("Size of the list: " + size);
    }
}
