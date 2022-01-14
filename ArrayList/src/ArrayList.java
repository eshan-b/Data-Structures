import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayList<E> {
    private static final int INITIAL_SIZE = 10;
    private int size = 0;
    private Object[] arr;

    public ArrayList() { this.arr = new Object[INITIAL_SIZE]; }

    // resize array to twice the size
    private void resize() {
        this.arr = Arrays.copyOf(this.arr, this.size * 2);
    }

    // returns true is array is full
    private boolean isFull() { return this.size == this.arr.length; }

    // insert element at the end of the list
    public void add(E item) {
        if (isFull()) resize();
        this.arr[this.size++] = item;
    }

    // return true if list contains element
    public boolean contains(E item) {
        for (int i = 0; i < this.size; i++) {
            if (this.arr[i] == item) return true;
        }
        return false;
    }

    // removes element at position "index" in the array
    public boolean remove(int index) {
        // check for valid index
        if (index < 0 || index >= this.size) return false;

        // shift and copy array from that index
        for (int i = index; i < this.arr.length - 1; i++)
            this.arr[i] = this.arr[i + 1];

        // decrease size
        this.size--;
        return true;
    }


    // inserts element into array and returns true on success
    public boolean insert(E item, int index) {
        // check for valid index
        if (index < 0 || index >= this.size) return false;

        // resize the array if it is full
        if (isFull()) resize();

        // add to the end if array is full
        if (index == this.size) this.arr[index] = item;
        // otherwise shift all values and insert
        else {
            for (int i = this.size - 1; i < index; i++)
                this.arr[i + 1] = this.arr[i];
            this.arr[index] = item;
        }

        // increase size
        this.size++;
        return true;
    }

    public int getSize() { return size; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.size; i++)
            sb.append(this.arr[i]).append(" ");

        return sb.toString();
    }


    public static void main(String[] args) {
        // instantiate with numbers 1 - 10 inclusive
        ArrayList<Integer> list = new ArrayList<>();
        IntStream.rangeClosed(1, 10).forEach(list::add);
        System.out.println(list);

        // test removing last element
        System.out.println(list.remove(9));
        System.out.println(list);

        // test inserting 5 at index 3
        System.out.println(list.insert(5, 3));
        System.out.println(list);

        // test size getter
        System.out.println("The size of the array is: " + list.getSize());

        // test contains method
        System.out.println("This list contains the number 5: " + list.contains(5));
    }
}
