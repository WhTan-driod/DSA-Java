import java.util.Comparator;

/**
 * AVLTreeMap<K,V>
 *
 * Single file implementation of an AVL-based map.
 * - Generic keys K, values V
 * - Supports natural ordering (K extends Comparable<K>) or a Comparator<K>
 * - put, get, remove, containsKey
 * - prints tree sideways for inspection
 *
 * This file includes a main() demonstrating insertions and deletions with prints after each step.
 */

public class AVLTreeMap<K, V> 
{
    private Node root;
    private int size;
    private final Comparator<? super K> comp;

    // Inner node class
    private class Node 
    {
        K key;
        V value;
        Node left, right, parent;
        int nodeHeight;

        Node(K k, V v, Node p) 
        {
            key = k;
            value = v;
            parent = p;
            nodeHeight = 1; // single node height = 1
        }
    }

    // Constructor with natural ordering
    public AVLTreeMap() 
    {
        this.comp = null;
    }

    //Constructor with user defined comparator
    public AVLTreeMap(Comparator<? super K> comparator) 
    {
        this.comp = comparator;
    }

    // Compare helper (uses comparator if provided, otherwise natural ordering)
    @SuppressWarnings("unchecked")
    private int compare(K a, K b) 
    {
        if (comp != null) return comp.compare(a, b);
        return ((Comparable<? super K>) a).compareTo(b);
    }

    // Height helpers
    private int heightFunc(Node n) 
    {
        return (n == null) ? 0 : n.nodeHeight;
    }

    private void updateHeight(Node n) 
    {
        if (n != null) n.nodeHeight = 1 + Math.max(heightFunc(n.left), heightFunc(n.right));
    }

    private int balanceFactor(Node n) 
    {
        if (n == null) return 0;
        return heightFunc(n.left) - heightFunc(n.right);
    }

    private boolean isBalanced(Node n) 
    {
        int bf = balanceFactor(n);
        return (bf >= -1 && bf <= 1);
    }

    // find taller child used for identifying rotation cases
    private Node tallerChild(Node n) 
    {
        if (n == null) return null;
        int lh = heightFunc(n.left);
        int rh = heightFunc(n.right);
        if (lh > rh) return n.left;
        if (rh > lh) return n.right;
        // tie-breaker: prefer child on same side as parent to produce deterministic behavior
        if (n.parent != null && n == n.parent.left) return n.left;
        return n.right;
    }

    // rotateRight: rotate around y where x = y.left
    private Node rotateRight(Node y) 
    {
        Node x = y.left;
        Node B = x.right;

        x.parent = y.parent;
        if (y.parent == null) root = x;
        else if (y == y.parent.left) y.parent.left = x;
        else y.parent.right = x;

        x.right = y;
        y.parent = x;

        y.left = B;
        if (B != null) B.parent = y;

        // update heights
        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // rotateLeft: rotate around x where y = x.right
    private Node rotateLeft(Node x) 
    {
        Node y = x.right;
        Node B = y.left;

        y.parent = x.parent;
        if (x.parent == null) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;

        y.left = x;
        x.parent = y;

        x.right = B;
        if (B != null) B.parent = x;

        // update heights
        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // Rebalance walking up from node (after insertion/removal)
    private void rebalance(Node start) 
    {
        Node n = start;
        while (n != null) 
        {
            updateHeight(n);
            if (!isBalanced(n)) 
            {
                // identify taller child and grandchild to decide rotation
                Node a = n;
                Node b = tallerChild(a);
                Node c = tallerChild(b);

                // Four cases:
                if (b == a.left && c == b.left) 
                {
                    // Left Left
                    rotateRight(a);
                } 
                else if (b == a.left && c == b.right) 
                {
                    // Left Right
                    rotateLeft(b);
                    rotateRight(a);
                } 
                else if (b == a.right && c == b.right) 
                {
                    // Right Right
                    rotateLeft(a);
                } 
                else if (b == a.right && c == b.left) 
                {
                    // Right Left
                    rotateRight(b);
                    rotateLeft(a);
                }
                // After rotation, the subtree root has been adjusted; heights updated in rotations
            }
            n = n.parent;
        }
    }

    // put operation (insert or replace)
    public V put(K key, V value) 
    {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        if (root == null) 
        {
            root = new Node(key, value, null);
            size = 1;
            return null;
        }
        Node cur = root, parent = null;
        int cmp = 0;
        while (cur != null) 
        {
            parent = cur;
            cmp = compare(key, cur.key);
            if (cmp == 0) 
            {
                V old = cur.value;
                cur.value = value;
                return old; // replaced
            } 
            else if (cmp < 0) cur = cur.left;
            else cur = cur.right;
        }
        Node newNode = new Node(key, value, parent);
        if (cmp < 0) parent.left = newNode;
        else parent.right = newNode;
        size++;
        rebalance(parent);
        return null;
    }

    // get operation
    public V get(K key) 
    {
        Node n = getNode(key);
        return (n == null) ? null : n.value;
    }

    private Node getNode(K key) 
    {
        Node cur = root;
        while (cur != null) 
        {
            int cmp = compare(key, cur.key);
            if (cmp == 0) return cur;
            if (cmp < 0) cur = cur.left;
            else cur = cur.right;
        }
        return null;
    }

    public boolean containsKey(K key) 
    {
        return getNode(key) != null;
    }

    // remove operation
    public V remove(K key) 
    {
        Node node = getNode(key);
        if (node == null) return null;

        V oldValue = node.value;
        deleteNode(node);
        size--;
        return oldValue;
    }

    private void deleteNode(Node node) 
    {
        if (node.left != null && node.right != null) 
        {
            // find successor (min in right subtree)
            Node succ = node.right;
            while (succ.left != null) succ = succ.left;
            // swap key/value
            node.key = succ.key;
            node.value = succ.value;
            // now delete succ
            node = succ;
        }

        // node has at most one child
        Node replacement = (node.left != null) ? node.left : node.right;
        Node parent = node.parent;
        if (replacement != null) 
        {
            // link replacement to parent
            replacement.parent = parent;
            if (parent == null) root = replacement;
            else if (node == parent.left) parent.left = replacement;
            else parent.right = replacement;

            // cleanup node
            node.left = node.right = node.parent = null;
            // rebalance from parent
            rebalance(parent);
        } 
        else 
        {
            // no children (leaf)
            if (parent == null) 
            {
                root = null;
            } 
            else 
            {
                if (node == parent.left) parent.left = null;
                else parent.right = null;
                rebalance(parent);
            }
            node.parent = null;
        }
    }

    // Utility: print tree sideways for inspection
    public void printTree() 
    {
        System.out.println("Tree (sideways, root at left):");
        printSubtree(root, "", true);
        System.out.println("---- End tree ----");
    }

    private void printSubtree(Node n, String prefix, boolean isRoot) 
    {
        if (n == null) 
        {
            if (isRoot) System.out.println("  (empty)");
            return;
        }
        // print right subtree
        if (n.right != null) printSubtree(n.right, prefix + (isRoot ? "" : "│   "), false);

        // print this node
        System.out.println(prefix + (isRoot ? "" : "└── ") + formatNode(n));

        // print left subtree
        if (n.left != null) printSubtree(n.left, prefix + (isRoot ? "" : "    "), false);
    }

    private String formatNode(Node n) 
    {
        return String.format("(%s:%s)[h=%d]", n.key, n.value, n.nodeHeight);
    }

    // For debugging/verification: in-order traversal prints sorted keys
    public void printInOrder() 
    {
        System.out.print("In-order: ");
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node n) 
    {
        if (n == null) return;
        inOrder(n.left);
        System.out.print(n.key + " ");
        inOrder(n.right);
    }

    public int size() 
    {
        return size;
    }

    // Main - demonstration for testing requirements.
    public static void main(String[] args) 
    {
        // Example using integers as keys (natural order)
        AVLTreeMap<Integer, String> map = new AVLTreeMap<>();

        int[] inserts = {30, 20, 40, 10, 25, 35, 50, 5, 15, 27};
        System.out.println("Inserting keys:");
        for (int k : inserts) 
        {
            System.out.println("\nInsert " + k + " -> \"" + ("v" + k) + "\"");
            map.put(k, "v" + k);
            map.printTree();
            map.printInOrder();
        }

        // Trigger rotations: insert values that cause imbalance
        System.out.println("\nExtra inserts to force rotations:");
        map.put(26, "v26"); 
        map.printTree(); 
        map.printInOrder();
        map.put(28, "v28"); 
        map.printTree(); 
        map.printInOrder();

        // Deletions
        int[] deletes = {10, 30, 20, 27};
        System.out.println("\nDeleting keys:");
        for (int k : deletes) 
        {
            System.out.println("\nRemove " + k);
            map.remove(k);
            map.printTree();
            map.printInOrder();
        }

        System.out.println("\nFinal map size: " + map.size());
    }
}
