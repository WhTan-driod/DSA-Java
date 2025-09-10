import java.util.*;

//Player class represents a single player in the Duck Duck Goose game.
class Player {
    String name;
    Player next;        // Reference to the next player in the circle
    boolean isIt;       // True if this player is currently "it"
    
    //Constructor
    public Player(String name) {
        this.name = name;
        this.next = null;
        this.isIt = false;
    }
}

//Main game class that implements Duck Duck Goose using a circular linked list.
public class DuckDuckGooseGame {
    private Player head;
    private int playerCount;
    
    //Constructor
    public DuckDuckGooseGame() {
        this.head = null;
        this.playerCount = 0;
    }
    
    //adds a player to the game
    public void addPlayer(String name) {
        Player newPlayer = new Player(name);
        
        // Case 1: First player in the game
        if (head == null) {
            head = newPlayer;
            head.next = head;       // Point to itself to create the circle
            newPlayer.isIt = true;  // First player is automatically "it"
        } 
        // Case 2: Adding to existing circle
        else {
            // Find the last player (the one whose 'next' points to head)
            Player lastPlayer = head;
            while (lastPlayer.next != head) {
                lastPlayer = lastPlayer.next;
            }
            
            // Insert new player between last player and head
            lastPlayer.next = newPlayer;
            newPlayer.next = head;
        }
        
        playerCount++;
    }
    
    //finds the player who is currently "it"
    public Player findItPlayer() {
        if (head == null) return null;
        
        // Walk through the circle to find who is "it"
        Player current = head;
        do {
            if (current.isIt) {
                return current;
            }
            current = current.next;
        } while (current != head);  // Stop when we've completed the circle
        
        return null;    // Should never happen if game is properly initialized
    }
    
    /**
     * Changes who is "it" in the game.
     * Sets the specified player as "it" and removes "it" status from all others.
     * 
     * @param newItPlayer The player who should become "it"
     */
    public void changeItPlayer(Player newItPlayer) {
        if (head == null) return;
        
        // Walk through all players and update their "it" status
        Player current = head;
        do {
            current.isIt = (current == newItPlayer);   // Only the new "it" player gets true
            current = current.next;
        } while (current != head);
    }
    
    /**
     * Displays the current state of the game circle.
     * Shows all players in order with their "it" status.
     */
    public void displayCircle() {
        if (head == null) {
            System.out.println("No players in the game.");
            return;
        }
        
        System.out.print("Players in circle: ");
        Player current = head;
        do {
            // Show player name and indicate if they're "it"
            System.out.print(current.name + (current.isIt ? "(IT)" : "") + " -> ");
            current = current.next;
        } while (current != head);
        
        System.out.println("(back to start)");
    }
    
    /**
     * Plays one complete round of Duck Duck Goose.
     * The "it" player walks around the circle saying "duck" or "goose".
     * When "goose" is said, that player chases the "it" player.
     * The outcome determines who becomes "it" next.
     */
    public void playOneRound() {
        // Check if we have enough players to play
        if (playerCount < 3) {
            System.out.println("Need at least 3 players to play Duck Duck Goose!");
            return;
        }
        
        Player itPlayer = findItPlayer();
        if (itPlayer == null) {
            System.out.println("No 'it' player found!");
            return;
        }
        
        System.out.println("\n--- New Round ---");
        System.out.println(itPlayer.name + " is 'it' and starts walking around the circle...");
        
        // The "it" player starts walking from the next player in the circle
        Player currentPlayer = itPlayer.next;
        
        // Walk around the circle saying "duck" until we decide to say "goose"
        // For simplicity, we'll randomly decide when to say "goose" after at least one "duck"
        Random random = new Random();
        boolean saidGoose = false;
        Player goosePlayer = null;
        int duckCount = 0;
        
        // Keep walking around until we say "goose" to someone
        while (!saidGoose) {
            // Randomly decide if we should say "goose" to this player
            // Make sure we say "duck" to at least one person first
            boolean shouldSayGoose = (duckCount > 0) && random.nextBoolean();
            
            if (shouldSayGoose) {
                System.out.println(itPlayer.name + " taps " + currentPlayer.name + " and says: 'GOOSE!'");
                goosePlayer = currentPlayer;
                saidGoose = true;
            } else {
                System.out.println(itPlayer.name + " taps " + currentPlayer.name + " and says: 'duck'");
                currentPlayer = currentPlayer.next;    // Move to next player
                duckCount++;
                
                // Safety check: if we've gone around the whole circle, pick the next person as goose
                if (currentPlayer == itPlayer) {
                    currentPlayer = currentPlayer.next;
                    System.out.println(itPlayer.name + " taps " + currentPlayer.name + " and says: 'GOOSE!'");
                    goosePlayer = currentPlayer;
                    saidGoose = true;
                }
            }
        }
        
        // Now simulate the chase between the "it" player and the "goose" player
        simulateChase(itPlayer, goosePlayer);
    }
    
    /**
     * Simulates the chase that happens after "goose" is called.
     * The goose player tries to catch the "it" player before they sit down.
     * Randomly determines the winner for simplicity.
     * 
     * @param itPlayer The player who was "it" and is being chased
     * @param goosePlayer The player who was chosen as "goose" and does the chasing
     */
    private void simulateChase(Player itPlayer, Player goosePlayer) {
        System.out.println(goosePlayer.name + " jumps up and chases " + itPlayer.name + "!");
        System.out.println("They run around the circle...");
        
        // Simulate the race with a random outcome
        Random random = new Random();
        boolean itPlayerEscapes = random.nextBoolean();
        
        if (itPlayerEscapes) {
            // "It" player makes it back to the goose's spot safely
            System.out.println(itPlayer.name + " makes it back to the empty spot!");
            System.out.println(goosePlayer.name + " is now 'it'!");
            changeItPlayer(goosePlayer);
        } else {
            // Goose player catches the "it" player
            System.out.println(goosePlayer.name + " catches " + itPlayer.name + "!");
            System.out.println(itPlayer.name + " stays 'it'!");
            // itPlayer remains "it" (no change needed)
        }
    }
}

