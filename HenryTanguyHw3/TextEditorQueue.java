// Node class for doubly linked list
class Node<E> {
    E element;       
    Node<E> prev;    
    Node<E> next;     

    Node(E e, Node<E> p, Node<E> n) {
        element = e;
        prev = p;
        next = n;
    }
}

// Position interface represents a "location" inside the list
interface Position<E> {
    E getElement();
}

// Position implementation, wrapping around a Node
class PositionImpl<E> implements Position<E> {
    Node<E> node;

    //constructor that inherits from Node
    PositionImpl(Node<E> n) { node = n; }
    public E getElement() { return node.element; }
}

// A PositionalList implemented with a doubly linked list
class PositionalList<E> {
    private Node<E> header;   
    private Node<E> trailer;  
    private int size = 0;     

    public PositionalList() {
        // Create header and trailer sentinels
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.next = trailer;
    }

    // Utility: Wrap a node as a Position
    private Position<E> position(Node<E> node) {
        if (node == header || node == trailer) return null;
        return new PositionImpl<>(node);
    }

    // Basic getters
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public Position<E> first() { return position(header.next); }
    public Position<E> last() { return position(trailer.prev); }

    // Get position before a given position
    public Position<E> before(Position<E> p) {
        Node<E> node = ((PositionImpl<E>) p).node;
        return position(node.prev);
    }

    // Get position after a given position
    public Position<E> after(Position<E> p) {
        Node<E> node = ((PositionImpl<E>) p).node;
        return position(node.next);
    }

    // Internal insert between two existing nodes
    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
        Node<E> newest = new Node<>(e, pred, succ);
        pred.next = newest;
        succ.prev = newest;
        size++;
        return new PositionImpl<>(newest);
    }

    // Public insertion methods
    public Position<E> addFirst(E e) { return addBetween(e, header, header.next); }
    public Position<E> addLast(E e) { return addBetween(e, trailer.prev, trailer); }
    public Position<E> addBefore(Position<E> p, E e) {
        Node<E> node = ((PositionImpl<E>) p).node;
        return addBetween(e, node.prev, node);
    }
    public Position<E> addAfter(Position<E> p, E e) {
        Node<E> node = ((PositionImpl<E>) p).node;
        return addBetween(e, node, node.next);
    }

    // Remove a position and return its element
    public E remove(Position<E> p) {
        Node<E> node = ((PositionImpl<E>) p).node;
        Node<E> pred = node.prev;
        Node<E> succ = node.next;
        pred.next = succ;
        succ.prev = pred;
        size--;

        E element = node.element;
        // Clear node
        node.element = null;
        node.prev = node.next = null;
        return element;
    }

    // For displaying the list with the cursor marker
    public String toString(Position<E> cursor) {
        StringBuilder sb = new StringBuilder();
        Node<E> walk = header.next;
        while (walk != trailer) {
            // If cursor is at this node, draw a "|"
            if (cursor != null && ((PositionImpl<E>) cursor).node == walk) {
                sb.append("|");
            }
            sb.append(walk.element);
            walk = walk.next;
        }
        // If cursor is null, treat it as "before the first element"
        if (cursor == null) sb.append("|");
        return sb.toString();
    }
}

class TextEditorQueue {
    private PositionalList<Character> text;   // The list of characters
    private Position<Character> cursor;       // The cursor's current position

    public TextEditorQueue() {
        text = new PositionalList<>();
        cursor = null; // Cursor starts before the first character
    }

    // paste c: Insert a new character immediately after the cursor
    public void paste(char c) {
        if (cursor == null) { // If cursor is at "start"
            cursor = text.addFirst(c);
        } else {
            cursor = text.addAfter(cursor, c); // Insert after cursor
        }
    }

    // left: Move cursor one character left
    public void left() {
        if (cursor != null) {
            Position<Character> before = text.before(cursor);
            if (before != null) {
                cursor = before;
            }
        }
    }

    // right: Move cursor one character right
    public void right() {
        if (cursor != null) {
            Position<Character> after = text.after(cursor);
            if (after != null) {
                cursor = after;
            }
        }
    }

    // cut: Delete the character immediately after the cursor
    public void cut() {
        if (cursor != null) {
            Position<Character> after = text.after(cursor);
            if (after != null) {
                text.remove(after);
            }
        }
    }

    // Display the current text with the cursor marker
    @Override
    public String toString() {
        return text.toString(cursor);
    }
    public static void main(String[] args) {
        TextEditorQueue editor = new TextEditorQueue();

        // Insert some text
        editor.paste('H');
        editor.paste('e');
        editor.paste('l');
        editor.paste('l');
        editor.paste('o');
        System.out.println(editor);

        // Move cursor left and cut
        editor.left();
        editor.left();
        editor.cut(); // delete 'l'
        System.out.println(editor);

        // Move right and insert another character
        editor.right();
        editor.paste('!');
        System.out.println(editor);
    }
}





