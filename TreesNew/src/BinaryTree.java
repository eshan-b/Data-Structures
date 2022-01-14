import java.util.Arrays;

public class BinaryTree<E extends Comparable<E>> {
    private static class Node<E> {
        E item;
        Node<E> left, right;

        Node(E item) {
            this.item = item;
            left = right = null;
        }
    }

    private Node<E> root;

    public BinaryTree() { this.root = null; }

    public boolean isEmpty() { return root == null; }

    // recursive insert
    public void insert(E item) { this.root = insert(root, item); }

    // recursive insert
    public Node<E> insert(Node<E> curr, E item) {
        // if empty space, then insert and stop
        if (curr == null) return new Node<>(item);

        if (item.compareTo(curr.item) < 0) // move left
            curr.left = insert(curr.left, item);
        else if (item.compareTo(curr.item) > 0) // move right
            curr.right = insert(curr.right, item);

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

    public void levelOrder(){
        int height = findHeight(root);
        for (int i = 1; i <= height; i++)
            currLevel(root, i);
    }

    private int findHeight(Node<E> root) {
        if (root != null) {
            int lHeight = findHeight(root.left), rHeight = findHeight(root.right);
            return lHeight > rHeight ? (lHeight + 1) : (rHeight + 1);
        }
        return 0;
    }

    private void currLevel(Node<E> root, int level) {
        if (root != null) {
            if (level == 1)
                System.out.print(root.item + " ");
            else if (level > 1) {
                currLevel(root.left, level - 1);
                currLevel(root.right, level - 1);
            }
        }
    }

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        int[] nums = {23, 45, 67, 11, 17, 7, 9, 27};
        Arrays.stream(nums).forEach(tree::insert);

        System.out.println("In-Order");
        tree.inOrder(tree.root);

        System.out.println("\nPre-Order");
        tree.preOrder(tree.root);

        System.out.println("\nPost-Order");
        tree.postOrder(tree.root);

        System.out.println("\nLevel-Order");
        tree.levelOrder();
    }
}
