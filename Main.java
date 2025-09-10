public class Main {
    public static void main (String[] args)
    {
        System.out.println("===Doubly Linked List Demo===\n");
        
        // Create a new doubly linked list of integers
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();

        //Display empty list
        dll.displayForward();
        
        //Add elements
        dll.addBack(10);
        dll.addBack(20);
        dll.addFront(5);
        dll.addBack(30);
        dll.displayForward();
        System.out.println("Size: " + dll.getSize() + "\n");
        
        //Add between nodes
        dll.addBetween(15, 10);  // Add 15 after 10
        dll.addBetween(25, 20);  // Add 25 after 20
        dll.displayForward();
        System.out.println("Size: " + dll.getSize() + "\n");
        
        //Display in both directions
        dll.displayForward();
        dll.displayBackward();
        System.out.println();
        
        //Searching
        dll.search(15);
        dll.search(100);
        System.out.println();
        
        //Removing elements
        dll.removeFront();
        dll.removeBack();
        dll.removeValue(15);
        dll.displayForward();
        System.out.println("Size: " + dll.getSize() + "\n");
        
        //Eedge cases
        DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<>();
        emptyList.displayForward();
        emptyList.removeFront();
        emptyList.addBetween(5, 10);
        System.out.println("Empty list size: " + emptyList.getSize());
        System.out.println("Is empty: " + emptyList.isEmpty());
        
        //Using generics allows for different data types
        DoublyLinkedList<String> stringList = new DoublyLinkedList<>();
        stringList.addBack("Hello");
        stringList.addBack("World");
        stringList.addFront("Hi");
        stringList.displayForward();

        //----------------- Duck Duck Goose Game Demo ----------------

        System.out.println("=== DUCK DUCK GOOSE GAME ===");
        
        // Create a new game
        DuckDuckGooseGame game = new DuckDuckGooseGame();
        
        // Add some players to the game
        game.addPlayer("Alice");
        game.addPlayer("Bob");
        game.addPlayer("Charlie");
        game.addPlayer("Diana");
        
        // Show the initial setup
        System.out.println("Game setup complete!");
        game.displayCircle();
        
        // Play the game
        for (int round = 1; round <= 3; round++) {
            System.out.println("\n========== ROUND " + round + " ==========");
            game.playOneRound();
            game.displayCircle();
            
            // Pause between rounds for readability
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        System.out.println("\nGame Over!");
    }
}