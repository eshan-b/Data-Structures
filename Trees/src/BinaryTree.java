public class BinaryTree extends Comparable<E> {
    private static class Node<E> {
        E item;
        Node<E> left, right;

        Node(E item) {
            this.item = item;
            left = right = null;
        }
    }

    private Node<E> root; // top-most node

    public BinaryTree() { this.root = null; }

    public boolean isEmpty() { return root == null; }

    // iterative insert
    public void insertI(E item) {
        Node<E> newNode = new Node<>(item);

        if (this.root == null) root = newNode;
        else {
            Node<E> temp = root; // temporary node for traversal
            Node<E> top = null; // current parent node
            while (temp != null) {
                // set parent value to current
                top = temp;

                // decide to go left or right during traversal
                temp = item.compareTo(temp.item) < 0 ? temp.left : temp.right;
            }

            // adding new node to correct side
            if (item.compareTo(top.item) < 0)
                top.right = newNode;
            else
                top.left = newNode;
        }
    }

    public void insertR(E item) { this.root = insertR(root, item); }

    // recursive insert
    public Node<E> insertR(Node<E> curr, E item) {
        // if empty space, then insert and stop
        if (curr == null) return new Node<>(item);

        if (item.compareTo(curr.item) < 0) // move left
            curr.left = insertR(curr.left, item);
        else if (item.compareTo(curr.item) > 0) // move right
            curr.right = insertR(curr.right, item);

        return curr; // default return
    }

    // iterative search
    public Node<E> searchI(E item) {
        // temporary node for traversal
        Node<E> temp = root;

        while (temp.item != item) { // loop until item found
            // decide to go left or right during traversal
            temp = item.compareTo(temp.item) < 0 ? temp.left : temp.right;
            // no value found
            if (temp == null) return null;
        }

        return temp;
    }

    // recursive search
    //public Node<E> searchR(E item) {}

    public void inOrder(Node<E> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.item + " ");
            inOrder(node.right);
        }
    }

    public void preOrder(Node<E> node) {
        if (node != null) {
            System.out.print(node.item + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void postOrder(Node<E> node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.item + " ");
        }
    }

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        IntStream.rangeClosed(1, 10).forEach(tree::insertR);
        tree.inOrder(tree.root);
    }
}
