import java.util.Comparator;

/**
 * RBTreeMap<K,V>
 *
 * Implementation of a Red-Black Tree–based Map in Java.
 * -----------------------------------------------------
 * • Generic: works for any key/value types.
 * • Keys can use either natural ordering or a custom Comparator.
 * • Supports put(), get(), remove(), containsKey(), printTree().
 *
 * Internal logic follows the standard CLRS red-black algorithms
 * with a NIL sentinel node to simplify rotation and fix-up code.
 *
 * Red = true, Black = false
 */

public class RBTreeMap<K, V> 
{

    /* ---------------- Sentinel node ---------------- */
    // A single shared NIL node replaces all null children.
    // It is always black and simplifies boundary cases.
    private final Node NIL = new Node(null, null, BLACK);

    /* ---------------- Tree state ---------------- */
    private Node root = NIL;
    private int size = 0;
    private final Comparator<? super K> comp;

    /* ---------------- Color constants ---------------- */
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    /* ---------------- Node definition ---------------- */
    private class Node 
    {
        K key;
        V value;
        Node left = NIL, right = NIL, parent = NIL;
        boolean color; // true = RED, false = BLACK

        Node(K k, V v, boolean c) 
        {
            key = k;
            value = v;
            color = c;
        }
    }

    /* ---------------- Constructors ---------------- */
    public RBTreeMap() { this.comp = null; }

    public RBTreeMap(Comparator<? super K> comparator) { this.comp = comparator; }

    /* ---------------- Key comparison helper ---------------- */
    @SuppressWarnings("unchecked")
    private int compare(K a, K b) 
    {
        if (a == null || b == null)
            throw new IllegalArgumentException("Keys must be non-null");
        if (comp != null) return comp.compare(a, b);
        return ((Comparable<? super K>) a).compareTo(b);
    }

    /**
     * Insert a key/value pair.
     * If key already exists, replace its value.
     * Returns the old value or null if new key.
     */
    public V put(K key, V value) 
    {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");

        Node z = new Node(key, value, RED); // new node starts red
        z.left = z.right = z.parent = NIL;

        Node y = NIL; // will become parent of z
        Node x = root;

        // Standard BST insertion search
        while (x != NIL) 
        {
            y = x;
            int cmp = compare(z.key, x.key);
            if (cmp == 0) 
            {
                // key already exists → replace value only
                V old = x.value;
                x.value = value;
                return old;
            } else if (cmp < 0) x = x.left;
            else x = x.right;
        }

        // Link new node under its parent
        z.parent = y;
        if (y == NIL) root = z;              // tree was empty
        else if (compare(z.key, y.key) < 0) y.left = z;
        else y.right = z;

        size++;

        // Restore Red-Black properties
        insertFixup(z);
        return null;
    }

    /**
     * Fix violations after inserting a red node.
     * Cases 1–3 handle parent/uncle coloring and rotations.
     */
    private void insertFixup(Node z) 
    {
        while (z.parent.color == RED) 
        {
            if (z.parent == z.parent.parent.left) 
            {
                // Parent is left child of grandparent
                Node y = z.parent.parent.right; // uncle
                if (y.color == RED) 
                {
                    // Case 1: uncle red → recolor and move up
                    z.parent.color = BLACK;
                    y.color = BLACK;
                    z.parent.parent.color = RED;
                    z = z.parent.parent;
                } 
                else 
                {
                    // Uncle black
                    if (z == z.parent.right) 
                    {
                        // Case 2: triangle → rotate left
                        z = z.parent;
                        leftRotate(z);
                    }
                    // Case 3: line → recolor + rotate right
                    z.parent.color = BLACK;
                    z.parent.parent.color = RED;
                    rightRotate(z.parent.parent);
                }
            } 
            else 
            {
                // Parent is right child → mirror of above
                Node y = z.parent.parent.left; // uncle
                if (y.color == RED) 
                {
                    z.parent.color = BLACK;
                    y.color = BLACK;
                    z.parent.parent.color = RED;
                    z = z.parent.parent;
                } 
                else 
                {
                    if (z == z.parent.left) 
                    {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.color = BLACK;
                    z.parent.parent.color = RED;
                    leftRotate(z.parent.parent);
                }
            }
        }
        // Root must always be black
        root.color = BLACK;
    }

    /* ---------------- Rotation helpers ---------------- */

    // Rotate left around x:
    //      x           y
    //       \   =>    / \
    //        y       x   β
    private void leftRotate(Node x) 
    {
        Node y = x.right;
        x.right = y.left;
        if (y.left != NIL) y.left.parent = x;

        y.parent = x.parent;
        if (x.parent == NIL) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;

        y.left = x;
        x.parent = y;
    }

    // Rotate right around x (mirror of leftRotate)
    private void rightRotate(Node x) 
    {
        Node y = x.left;
        x.left = y.right;
        if (y.right != NIL) y.right.parent = x;

        y.parent = x.parent;
        if (x.parent == NIL) root = y;
        else if (x == x.parent.right) x.parent.right = y;
        else x.parent.left = y;

        y.right = x;
        x.parent = y;
    }

    public V get(K key) 
    {
        Node n = getNode(key);
        return (n == NIL) ? null : n.value;
    }

    public boolean containsKey(K key) 
    {
        return getNode(key) != NIL;
    }

    private Node getNode(K key) 
    {
        Node cur = root;
        while (cur != NIL) 
        {
            int cmp = compare(key, cur.key);
            if (cmp == 0) return cur;
            if (cmp < 0) cur = cur.left;
            else cur = cur.right;
        }
        return NIL;
    }

    public V remove(K key) 
    {
        Node z = getNode(key);
        if (z == NIL) return null;
        V old = z.value;
        delete(z);
        return old;
    }

    /**
     * Standard RB-delete:
     * 1. Remove z (or its successor if z has 2 children)
     * 2. If removed node was black, call deleteFixup()
     */
    private void delete(Node z) 
    {
        Node y = z;
        boolean yOriginalColor = y.color;
        Node x;

        if (z.left == NIL) 
        {
            // Only right child
            x = z.right;
            transplant(z, z.right);
        } 
        else if (z.right == NIL) 
        {
            // Only left child
            x = z.left;
            transplant(z, z.left);
        } 
        else 
        {
            // Two children → use successor
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) 
            {
                x.parent = y;
            } 
            else 
            {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }

        size--;
        // Fix double-black violations
        if (yOriginalColor == BLACK) deleteFixup(x);
    }

    /**
     * Restore red-black properties after deletion.
     * Handles 4 cases (plus mirror cases) using sibling colors.
     */
    private void deleteFixup(Node x) 
    {
        while (x != root && x.color == BLACK) 
        {
            if (x == x.parent.left) 
            {
                Node w = x.parent.right; // sibling
                if (w.color == RED) 
                {
                    // Case 1: sibling red → rotate & recolor
                    w.color = BLACK;
                    x.parent.color = RED;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                if (w.left.color == BLACK && w.right.color == BLACK) 
                {
                    // Case 2: both nephews black → recolor sibling
                    w.color = RED;
                    x = x.parent;
                } 
                else 
                {
                    if (w.right.color == BLACK)
                    {
                        // Case 3: far nephew black → rotate to case 4
                        w.left.color = BLACK;
                        w.color = RED;
                        rightRotate(w);
                        w = x.parent.right;
                    }
                    // Case 4: far nephew red → final rotation
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.right.color = BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            } 
            else 
            {
                // Mirror of above (x is right child)
                Node w = x.parent.left;
                if (w.color == RED) 
                {
                    w.color = BLACK;
                    x.parent.color = RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                if (w.right.color == BLACK && w.left.color == BLACK) 
                {
                    w.color = RED;
                    x = x.parent;
                } 
                else 
                {
                    if (w.left.color == BLACK) 
                    {
                        w.right.color = BLACK;
                        w.color = RED;
                        leftRotate(w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.left.color = BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = BLACK;
    }

    /* ---------------- Helper utilities ---------------- */

    // Replace subtree rooted at u with v
    private void transplant(Node u, Node v) 
    {
        if (u.parent == NIL) root = v;
        else if (u == u.parent.left) u.parent.left = v;
        else u.parent.right = v;
        v.parent = u.parent;
    }

    // Minimum node in a subtree
    private Node minimum(Node x) 
    {
        while (x.left != NIL) x = x.left;
        return x;
    }

    /** Print tree sideways with node colors for visualization */
    public void printTree() 
    {
        System.out.println("RB-Tree (sideways):");
        if (root == NIL) System.out.println("  (empty)");
        else printSubtree(root, "", true);
        System.out.println("---- end ----");
    }

    private void printSubtree(Node n, String prefix, boolean isRoot) 
    {
        if (n == NIL) return;
        if (n.right != NIL) printSubtree(n.right, prefix + (isRoot ? "" : "│   "), false);
        String col = n.color == RED ? "R" : "B";
        System.out.println(prefix + (isRoot ? "" : "└── ") + "(" + n.key + ":" + n.value + ")[" + col + "]");
        if (n.left != NIL) printSubtree(n.left, prefix + (isRoot ? "" : "    "), false);
    }

    public int size() { return size; }

    public static void main(String[] args) 
    {
        RBTreeMap<Integer, String> map = new RBTreeMap<>();

        int[] inserts = {10, 20, 30, 15, 25, 5, 1, 6, 18, 28};
        System.out.println("Inserting:");
        for (int k : inserts) 
        {
            System.out.println("\nput(" + k + ", v" + k + ")");
            map.put(k, "v" + k);
            map.printTree();
        }

        System.out.println("\nRemovals:");
        int[] removes = {20, 10, 5, 30};
        for (int k : removes) 
        {
            System.out.println("\nremove(" + k + ")");
            map.remove(k);
            map.printTree();
        }

        System.out.println("\nFinal size: " + map.size());
    }
}
