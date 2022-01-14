import java.util.*;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class LinkedList<E> implements Iterable<E> {
    @Override
    public Iterator<E> iterator() { return new MyIterator(); }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node(E element) { this.item = element; }
    }

    private Node<E> head;
    private int len = 0;

    LinkedList() { this.head = null; }

    public boolean isEmpty() { return this.head == null; }

    public Node<E> getHead() { return head; }

    public int length() { return len + 1; }

    public void add(E item) {
        Node<E> newNode = new Node<>(item); // new node with data

        if (isEmpty())
            head = newNode;
        else {
            Node<E> temp = head;
            while (temp.next != null) temp = temp.next; // go to end of list
            temp.next = newNode;
        }

        len++;
    }

    public Object get(int index) {
        // check for out of bounds
        if (index < 0 || index > len) throw new ArrayIndexOutOfBoundsException();

        // temp node
        Node<E> temp = head;

        // loop to index
        for (int i = 0; i < index; i++)
            temp = temp.next;

        // returns value or null if list is empty
        return temp.item;
    }

    public void remove(int index) {
        // check for out of bounds
        if (index < 0 || index > len) throw new ArrayIndexOutOfBoundsException();

        // temp node
        Node<E> temp = head;

        // loop to index
        if (!isEmpty()) {
            for (int i = 0; i < index; i++)
                temp = temp.next;

            temp.next = temp.next.next;
            len--;
        } else System.out.println("List is empty");
    }

    public void insert(E item, int position) {
        // create new node with item
        Node<E> newNode = new Node<>(item);
        newNode.next = null;

        // check if empty and modify head
        if (isEmpty()) {
            if (position != 0) return; // exit
            else this.head = newNode;
        }

        // check if insert at first position (modify head and next node)
        if (!isEmpty() && position == 0) {
            newNode.next = this.head;
            this.head = newNode;
            return; // exit
        }

        Node<E> current = this.head; // start with head
        Node<E> previous = null;

        for (int i = 0; i < position; i++) {
            previous = current;
            current = current.next;
            if (current == null) break;
        }

        newNode.next = current;
        assert previous != null; // stop null-pointer exception
        previous.next = newNode;
    }

    public void reverse() {
        // temp node
        Node<E> temp = head;
        Node<E> prev = null, curr = null;
        // check not empty
        while (temp != null) {
            curr = temp;
            temp = temp.next;

            // reverse
            curr.next = prev;
            prev = curr;
            this.head = curr;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedList<?> that = (LinkedList<?>) o;
        return len == that.len && Objects.equals(head, that.head);
    }

    @Override
    public int hashCode() { return Objects.hash(head, len); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> n = head;

        while (n != null) {
            sb.append(n.item).append(" ");
            n = n.next;
        }

        return sb.toString();
    }

    private class MyIterator implements Iterator<E> {
        Node<E> curr;

        @Override
        public boolean hasNext() { return curr != null; }

        @Override
        public E next() {
            E data = curr.item;
            curr = curr.next;
            return data;
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        // list from 1-10
        IntStream.rangeClosed(1, 10).forEach(list::add);

//        // print list
//        System.out.println(list);
//
//        // get value at index
//        System.out.println(list.get(5));
//
//        // remove value at index
//        list.remove(5);
//
//        // print list again to check if removed
//        System.out.println(list);
//
//        // insert 20 at index 5
//        list.insert(20, 5);
//
//        // print list again to see if object was inserted
//        System.out.println(list);
//
//        // test length getter
//        System.out.println(list.length());
//
//        // test reverse
//        list.reverse();
//        System.out.println(list);

        for (int i : list) System.out.println(i);
    }
}
